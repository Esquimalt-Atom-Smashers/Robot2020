package frc.control;

public class PIDSetPointCalculator
{
    private double gearingFromEncoder;
    private double encoderPlusesPerRevolution;
    

    public double getSetPoint(double value)
    {
        return value *encoderPlusesPerRevolution * gearingFromEncoder;
    }
}