package frc.subsystem.clp;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import frc.subsystem.Collector;
import frc.util.MathExtended;

public class CLP
{
    public static final double LAUNCH_VELOCITY = 10.0; // --> m/s
    public static final double GRAVITY = 9.80; // --> N/kg or m/s^2
    private Collector collector;
    private Launcher launcher;
    private SpeedControllerGroup shooter;
    public boolean shootMode = false;
    public boolean collectionMode = false;

    private boolean[] switchStates;
    private DigitalInput[] switches;
    private WPI_VictorSPX[] collectors;
    private WPI_VictorSPX shooter1;
    private WPI_VictorSPX shooter2;


    //dx is the distance to the target, dh is the change in height
    //there is two soultions for the angle, however the smaller one should be used
    //the angle is returned in radians
    //the function will return null, if no solutions exist.

  //  public CLP(WPI_VictorSPX motor1, WPI_VictorSPX motor2)
    public CLP()
    {
        //shooter = new SpeedControllerGroup(motor1, motor2);
        collector = new Collector();
        launcher = new Launcher();
        collectors = new WPI_VictorSPX[4];
        switchStates = new boolean[4];
        switchStates[3] = true;

        shooter1 = new WPI_VictorSPX(1);
        shooter1 = new WPI_VictorSPX(2);
        collectors[0] = new WPI_VictorSPX(1);
        collectors[1] = new WPI_VictorSPX(2);
        collectors[2] = new WPI_VictorSPX(3);
        switches[0] = new DigitalInput(1);
        switches[1] = new DigitalInput(2);
        switches[2] = new DigitalInput(3);

    }


    public void update()
    {

        for (int index = 0; index < switches.length; index ++)
        {
            switchStates[index] = switches[index].get();
        }

        if (collectionMode)
        {
            for (int index = 0; index < switchStates.length - 1; index++)
            {
                if (switchStates[index])
                {
                    if (switchStates[index + 1])
                    {
                        collectors[index].set(0);
                    }
                    else
                    {
                        collectors[index].set(1);
                    }
                }
                else
                {
                    collectors[index].set(1);
                }
            }
        }

        if (shootMode)
        {

        }
    }
    
    public void shoot() {
        launcher.shoot();
    }

    public boolean getShootMode() {
        return shootMode && launcher.readyToShoot();
    }

    public boolean setShootMode(boolean mode){
        if(mode == true) {
            launcher.shootMotorsOn();
        } else {
            launcher.shootMotorsOff();
        }

        return shootMode = mode;
    }

    public boolean getCollectionMode() {
        return collectionMode;
    }

    public boolean setCollectionMode(boolean mode) {
        if(mode == true) {
            collector.collect();
        } else {
            collector.stopCollect();
        }
        return mode;
    }


}