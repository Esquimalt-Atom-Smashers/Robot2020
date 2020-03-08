package frc.subsystem;

import java.util.LinkedList;

import edu.wpi.first.wpilibj.SpeedController;


/*
    this class will make it so that we dont need to consantantly run code to figure out what to set motor power to,
    this class will just set motor power to whatever it currently is, so the MotorSaftey class is happy as motor power is being updated but we dont need
    to directly update motor power.
*/

public class MotorPowerUpdater
{
    private static MotorPowerUpdater instance;

    private LinkedList<SpeedController> motors;

    static
    {
        instance = new MotorPowerUpdater();
    }

    public static synchronized MotorPowerUpdater getInstance()
    {
        return instance;
    }

    private MotorPowerUpdater()
    {
        motors = new LinkedList<SpeedController>();
    }

    public boolean addMotor(SpeedController motor)
    {
        return motors.add(motor);
    }

    public void updateAll()
    {
        for (SpeedController motor : motors) 
        {
                motor.set(motor.get()); //set motor power to whatever it is
        }
    }
}