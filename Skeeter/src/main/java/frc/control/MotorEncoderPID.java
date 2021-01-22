package frc.control;

import edu.wpi.first.wpilibj.Encoder;

/*  
*   To wrap this code up in a nutshell, essentially the Encoder class is used so we can read quadrature encoders.
*   Quadrature encoders are devices that can count shaft rotation and sense direction.
*   The output of the Quadrature encoders will be a integer that can cound up or down, negative being reverse.
*
*/
public class MotorEncoderPID extends PID
{
    private Encoder encoder;

    public MotorEncoderPID(double kI, double kP, double kD, double integralLimit, double integralFunctionRange, Encoder encoder)
    {
        super(kI,kP,kD,integralFunctionRange,integralLimit, 0);
        this.encoder = encoder;
    }

    @Override
    public double getPosition()
    {
        return (double) encoder.get();
    }
}
