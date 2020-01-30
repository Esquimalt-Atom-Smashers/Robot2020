package frc.subsystem;

import frc.util.MathExtended;

public class Shooter implements MathExtended
{
    public static final double LUANCH_VELOCITY = 10; // --> m/s

    public void getAngle(double deltaHeight, double dx, double currentAngle, int nTimes)
    {
        for (int count = 0; count < nTimes; count++) 
        {
            currentAngle = currentAngle - (dx*Math.tan(currentAngle) - deltaHeight - (4.9*Math.pow(dx,2)*sec2(currentAngle))/(Math.pow(LUANCH_VELOCITY,2)))/(dx*sec2(currentAngle) - (9.8*Math.pow(dx,2)*sec2(currentAngle)/(Math.pow(LUANCH_VELOCITY,2)) * Math.tan(currentAngle)));
        }
    }

}