package frc.subsystem;

import frc.util.MathExtended;

public class Shooter implements MathExtended
{
    public static final double LUANCH_VELOCITY = 10.0; // --> m/s
    public static final double GRAVITY = 9.80; // --> N/kg or m/s^2


    //dx is the distance to the target, dh is the change in height
    //there is two soultions for the angle, however the smaller one should be used
    //the angle is returned in radians
    //the function will return null, if no solutions exist.
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