package frc.util;

public class KinematicsCalculator
{
    public static double[] calculateLaunchAngle(double dx, double dh, double lv)
    {
        double a = Math.pow(dx, 2) * 9.8 * -1.0;
        double b = 2*Math.pow(lv, 2) * dx;
        double c = (2*Math.pow(lv, 2) * dh + Math.pow(dx, 2) * 9.80) * -1.0;

        double[] soultions = MathExtended.polysolve2ndDeg(a, b, c); //the quadratic formula will return tanget ratios so they need to be converted to angles (angles are in radians)
        double[] angles = new double[2];
        if (soultions == null) return null;
        angles[0] = Math.atan(soultions[0]);
        angles[1] = Math.atan(soultions[1]);
        
        return angles;
    }

    public static double calculateLaunchVelocity(double theta, double dx, double dh)
    {
        double numcheck = ((9.80) * Math.pow(dx,2) * MathExtended.sec2(theta))/(2*(dx*Math.tan(theta) - dh));
        if (numcheck < 0) return -1;
        else
        return Math.sqrt(numcheck);
    }
}