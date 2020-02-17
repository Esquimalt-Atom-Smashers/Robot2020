package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import frc.util.MathExtended;

public class CLP implements MathExtended
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
    

    public double[] calculateShooterAngle(double dx, double dh)
    {
        double a = Math.pow(dx, 2) * GRAVITY * -1.0;
        double b = 2*Math.pow(LUANCH_VELOCITY, 2) * dx;
        double c = (2*Math.pow(LUANCH_VELOCITY, 2) * dh + Math.pow(dx, 2) * GRAVITY) * -1.0;

        double[] soultions = polysolve2ndDeg(a, b, c); //the quadratic formula will return tanget ratios so they need to be converted to angles (angles are in radians)
        double[] angles = new double[2];
        if (soultions == null) return null;
        angles[0] = Math.atan(soultions[0]);
        angles[1] = Math.atan(soultions[1]);
        return angles;
    }


}