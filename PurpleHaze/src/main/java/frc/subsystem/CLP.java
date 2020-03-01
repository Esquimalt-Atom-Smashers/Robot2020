package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import frc.util.MathExtended;

public class CLP
{
    public static final double LUANCH_VELOCITY = 10.0; // --> m/s
    public static final double GRAVITY = 9.80; // --> N/kg or m/s^2

    private SpeedControllerGroup shooter;


    //dx is the distance to the target, dh is the change in height
    //there is two soultions for the angle, however the smaller one should be used
    //the angle is returned in radians
    //the function will return null, if no solutions exist.

    public CLP(WPI_VictorSPX motor1, WPI_VictorSPX motor2)
    {
        shooter = new SpeedControllerGroup(motor1, motor2);
    }


    public void update(XboxController test)
    {
        shooter.set(test.getRawAxis(1));
    }
    


}