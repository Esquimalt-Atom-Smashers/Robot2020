package frc.util;

public class Range
{
    private double min, max;

    public Range(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    public boolean contains(double value)
    {
        return value >= min && value <= max;
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public void setMin(double min)
    {
        this.min = min;
    }

    public void setMax(double max)
    {
        this.max = max;
    }

}