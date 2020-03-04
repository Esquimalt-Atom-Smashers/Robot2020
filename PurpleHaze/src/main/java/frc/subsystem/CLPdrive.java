package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import frc.control.PID;
import frc.robot.Constants;
import frc.util.Range;

public class CLPdrive implements RobotSystem
{

    private DigitalInput limitSwitchDown;
    private DigitalInput limitSwitchUp;

    private DoubleSolenoid piston;
    private Range angleRange;

    private PID angleController;
    private DutyCycleEncoder absEncoder;
    private Encoder cimEncoder;
    private WPI_VictorSPX driveMotor;
    private double anglesetPoint;


    public static class NonPremissibleAngleException extends Exception
    {
        private static final long serialVersionUID = 8228911573588917587L;
        
    }

    public CLPdrive()
    {
        driveMotor = new WPI_VictorSPX(Constants.CLP_DRIVE_VICTOR_CAN_ID);
        angleRange = new Range(0,Math.PI/4);
        angleController = new PID(0.1, 0.2, -0.3, 1, 0.4, 1000);
        angleController.setOutputRange(new Range(-0.6, 0.6));
        angleController.initRateLimiting(0.2, 0, 0.4, Constants.CIM_ENCODER_PPR * Constants.CLP_DRIVE_GEARING * 0.0625, true);
        angleController.setMaxPowerChangePerTick(0.05);

    }

    public void setAngle(double angle) throws NonPremissibleAngleException
    {
        if (!angleRange.contains(angle))
        {
            throw new NonPremissibleAngleException();
        }
        angleController.setTarget(toPIDsetPoint(angle));

    }

    private double toPIDsetPoint(double angle)
    {
        anglesetPoint = angle;
        return angle * Constants.CIM_ENCODER_PPR * Constants.CLP_DRIVE_GEARING;
    }

    @Override
    public void teleOpPeriodic() 
    {
        
        if (limitSwitchDown.get() || limitSwitchUp.get()) driveMotor.set(0);
    }

    @Override
    public void autoPeriodic() {
        // TODO Auto-generated method stub

    }
    
}