package frc.subsystem.clp;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;
import frc.subsystem.MotorPowerUpdater;

public class Launcher 
{
    //public boolean shootMode = false;
    private WPI_VictorSPX launcher;
    private WPI_VictorSPX launcherFollower;
    private BallPostion chamber;

    public Launcher()
    {
        System.out.println("Launcher constructor");
        launcher = new WPI_VictorSPX(Constants.CLP_SHOOTER1_VICTORSPX_CAN_ID);
        launcherFollower = new WPI_VictorSPX(Constants.CLP_SHOOTER2_VICTORSPX_CAN_ID);
        
        MotorPowerUpdater.getInstance().addMotor(launcher);
        MotorPowerUpdater.getInstance().addMotor(launcherFollower);
        launcherFollower.follow(launcher);
    }

    public void shoot() 
    {
        if (chamber.hasBall())
        {
            
        }
        System.out.println("Shooting!!!");
    }

    public boolean shootMotorsOn() 
    {
        launcher.set(1);
        return true;
        //turn shoot motors on
    }

    public boolean shootMotorsOff() 
    {
        launcher.set(0);
        return true;
        //turn shoot motors off
    }

    public boolean readyToShoot() {
        return true;
        //check motor speed and check if ball in position one
    }
   /* public boolean getShootMode() {
        return shootMode;
    }
    public boolean setShootMode(boolean mode){
        shootMode = mode;
        return shootMode;
    } */
}