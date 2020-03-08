package frc.subsystem;

import java.util.LinkedList;
import java.util.Queue;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.control.PID;
import frc.robot.Constants;

public class DriveBase implements RobotSystem
{

    private Spark leftDrive;
    private Spark rightDrive;

    private PID velControllerRight;
    private PID velControllerLeft;

    private PID autoOpRight;
    private PID autoOpLeft;


    private double robotCircumference = 0.555*Math.PI;
    private double wheelCircumference = 0.1524;
    private double wheelRevolutionsPerFullRotation = robotCircumference/(wheelCircumference);
    private double encoderPlusesPerRevolution = 8192;
    private double turningCoefficient = 0.8;
    private double encoderPlusesPerFullRevolution = wheelRevolutionsPerFullRotation * 8192;

    private boolean hasAutoCommand;
    private double lastPowerRight; //keep track of last power outpouts of the autoDrive PIDs and the driver controller PIDs to output jerkynees when swicthing from auto to driver control
    private double lastPowerLeft;

    private Joystick controller;
    private Queue<AutoDriveCommand> driveCommandQueue;

    private DutyCycleEncoder hexRight;
    private DutyCycleEncoder hexLeft;

    private boolean doAutoDrive;

    public DriveBase(double maxAccel, boolean doDriverAssist, Joystick controller) throws CloneNotSupportedException
    {
        leftDrive = new Spark(Constants.SPARK_LEFT_DRIVE_PWM);
        rightDrive = new Spark(Constants.SPARK_RIGHT_DRIVE_PWM);
        this.controller = controller;
        this.driveCommandQueue = new LinkedList<AutoDriveCommand>();


        velControllerRight = new PID(0.1, 0.2, 0.3, Double.MAX_VALUE, 1, 1000);
        velControllerRight.initRateLimiting(0.1, 0.2, 0.3, 1000, true); //rateLimiting will limit speed or if set to a high Limit may act as traction control (maybe probably not)
        velControllerRight.setMaxPowerChangePerTick(0.1); //set max speed controller output change per tick
    
        velControllerLeft = (PID) velControllerRight.clone();
        autoOpRight = (PID) velControllerRight.clone();
        autoOpLeft = (PID) velControllerRight.clone();
    }

    @Override
    public void teleOpPeriodic() 
    {
        if (doAutoDrive && controller.getRawAxis(1) == 0 && controller.getRawAxis(3) == 0) //no overriding driver input and auto enabled do autoCommands
        {
            if (hasAutoCommand)
            {
                if (autoOpLeft.reachedPosition() && autoOpRight.reachedPosition())
                {
                    if (!driveCommandQueue.isEmpty()) 
                    {
                        //apply the next command and re run this method to begin the command
                        applyNextCommand(driveCommandQueue.poll());
                        teleOpPeriodic();
                    }
                }
            }
            else
            {
                //if there are no auto commands running, this means there is no driver ouput, and if no autoCommands PIDs will output 0
                autoOpLeft.setLastPower(lastPowerLeft);
                autoOpRight.setLastPower(lastPowerRight);
            
                leftDrive.set(autoOpLeft.calculate(hexLeft.get()));
                rightDrive.set(autoOpRight.calculate(hexRight.get()));

                lastPowerLeft = autoOpLeft.getLastPower();
                lastPowerRight = autoOpRight.getLastPower();
            
            }

            
        }
        else //normal teleOP Drive
        {
            velControllerLeft.setLastPower(lastPowerLeft);
            velControllerRight.setLastPower(lastPowerRight);
            

            double fwdPower = controller.getRawAxis(1);
            double turnPower = controller.getRawAxis(3);

            double lPower = fwdPower + (turnPower * turningCoefficient);
            double rPower = fwdPower - (turnPower * turningCoefficient);
            

            velControllerLeft.setTarget(lPower);
            velControllerRight.setTarget(rPower);

            lastPowerLeft = velControllerLeft.getLastPower();
            lastPowerRight = velControllerRight.getLastPower();
        }
    }

    @Override
    public void autoPeriodic()
    {
        teleOpPeriodic();
    }


    /*
        for better behaviour do a rotation command then a distance command
    */

    //rotation number of radians to Rotate Clockwise, distance in meters, maxSpeed m/s (we metric)
    public DriveBase.AutoDriveCommand buildCommand(double rotation, double distance, double maxSpeed)
    {
        AutoDriveCommand command = new AutoDriveCommand();
        command.distance = distance;
        command.rotation = rotation;
        command.maxSpeed = maxSpeed;

        return command;
    }

    public void addAutoDriveCommand(AutoDriveCommand driveCommand)
    {
        driveCommandQueue.add(driveCommand);
    }

    public void cancelAutoOperations() //cancel all automonous operations of drivebase;
    {
        this.doAutoDrive = false;
    }

    public void enableAutoOperations()
    {
        doAutoDrive = true;
    }

    private void applyNextCommand(AutoDriveCommand command)
    {
        double pluseR = 0;
        double pluseL = 0;
        if (command.rotation != 0)
        {
            double percentRev = (command.rotation/(Math.PI * 2));
            double pluses = encoderPlusesPerFullRevolution * percentRev;
            pluseR += -pluses;
            pluseL += pluses;
        }

        if (command.distance != 0)
        {
            double pluses = encoderPlusesPerRevolution * (command.distance/wheelCircumference);
            pluseR += pluses;
            pluseL += pluses;
        }
    
        hasAutoCommand = true;
        doAutoDrive = true;
        autoOpLeft.setMaxRate(command.maxSpeed);
        autoOpRight.setMaxRate(command.maxSpeed);
        autoOpRight.setTarget(hexRight.get() + pluseR);
        autoOpLeft.setTarget(hexLeft.get() + pluseL);
    }


    public static class AutoDriveCommand
    {
        public double distance;
        public double rotation;

        public double maxSpeed;
    }
}