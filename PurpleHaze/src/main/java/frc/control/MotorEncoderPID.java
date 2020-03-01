package frc.control;

import edu.wpi.first.wpilibj.Encoder;

public class MotorEncoderPID extends PID
{
    private Encoder encoder;

    public MotorEncoderPID(double kI, double kP, double kD, double integralLimit, double integralFunctionRange, Encoder encoder)
    {
        super(kI,kP,kD,integralFunctionRange,integralLimit);
        this.encoder = encoder;
    }

    @Override
    public double getPosition()
    {
        return (double) encoder.get();
    }
}