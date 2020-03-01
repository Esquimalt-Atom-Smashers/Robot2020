package frc.util;

import edu.wpi.first.wpilibj.CAN;

public class PointRotater
{
    public static class Point
    {
        public double x, y;
    }

    /*
        Adding this to clp target may help as it will adjust for height including angle
    */

    public final static Point getRotated(double x, double y, double rad)
    {
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        Point ret = new Point();
        ret.x = x*cos + y*-sin;
        ret.y = x*sin + y*cos;

        return ret;
    }
}