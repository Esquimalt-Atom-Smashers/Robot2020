package frc.util;

public interface MathExtended
{
    public default double sec(double theta)
    {
        return 1/Math.cos(theta);
    }

    public default double csc(double theta)
    {
        return 1/Math.sin(theta);
    }

    public default double cot(double theta)
    {
        return 1/Math.tan(theta);
    }

    public default double sec2(double theta)
    {
        return Math.pow(sec(theta), 2);
    }
}