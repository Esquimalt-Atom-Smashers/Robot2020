package frc.subsystem;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


// INCOMPLETE
public class BallPostion extends Subsystem{

    DigitalInput limitSwitch;

    public BallPostion(int limitSwitchChannel){
        limitSwitch = new DigitalInput(limitSwitchChannel);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}