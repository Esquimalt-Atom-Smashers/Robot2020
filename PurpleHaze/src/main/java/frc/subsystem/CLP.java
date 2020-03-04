package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
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
    }


    public void update(XboxController test)
    {
        shooter.set(test.getRawAxis(1));
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