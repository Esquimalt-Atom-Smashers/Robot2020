/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.subsystem.clp.CLP;
import frc.subsystem.ButtonPanel;
import frc.subsystem.MotorPowerUpdater;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Joystick;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  
  private RobotServer server;
  private DifferentialDrive drive;
  private XboxController xcontroller;
  private CLP clp;
  private Compressor compressor;
  private DoubleSolenoid solenoid;
  private ColorSensorV3 colorsensor;
  private SmartDashboard sDashboard;
  private WPI_TalonSRX talon;
  private Joystick stick;
  private double ledmin = 0.2;
  private double fadeTime = 3000;
  private double ledpower = ledmin;
  private double ledmax = 1;
  private double ledincrease = (ledmax - ledmin)/fadeTime/0.02;
  private double ledDirection = 1;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private Spark test;
  private ButtonPanel panel;

  @Override
  public void robotInit() {
    
   
    //compressor = new Compressor(0);
    //clp = new CLP(testadoo, testaboo);
    clp = new CLP();
    //test = new Spark(0);
    xcontroller = new XboxController(3);
    //colorsensor = new ColorSensorV3(i2cPort);
    talon = new WPI_TalonSRX(0);
    try {
      drive = new DifferentialDrive(new Spark(0), new Spark(1)); // the base and wheels
      stick = new Joystick(3); //Check driver station usb settings for port number
      //drive.setInverted(true);
    } catch (Exception e) {
      System.out.println("Error communicating with drive, joystick not available");
    }
   
    try {
      panel = new ButtonPanel(9, 2); //Check driver station usb settings for port number
    } catch (Exception e) {
      System.out.println("Error communicating with button panel, unable to initialize ButtonPanel");
    }
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() 
  {
  }

  @Override
  public void teleopPeriodic() 
  {
    MotorPowerUpdater.getInstance().updateAll();
    panel.update();
    //testaboo.set(xcontroller.getRawAxis(1));

    // React to 'shoot' button press
    if (panel.wasPressed(ButtonPanel.CENTER_LEFT)) {
      if (clp.getShootMode() == true) {
        clp.shoot();
      }
    } 
    // React to 'shoot mode' button down
    if (panel.isDown(ButtonPanel.LEFT_TOP)) {
      if (clp.getShootMode() == false) {
        clp.setShootMode(true);
        System.out.println("Shoot mode on");
      }
      
    }
    // React to 'shoot mode' button up
    if (panel.wasReleased(ButtonPanel.LEFT_TOP)) {
      if (clp.getShootMode() == true) {
        clp.setShootMode(false);
        System.out.println("Shoot mode off");
      }
    }
    // React to 'collection mode' toggle
    if (panel.wasPressed(ButtonPanel.LEFT_MIDDLE)) {
      if (clp.getCollectionMode() == true) {
        clp.setCollectionMode(false);
      } else {
        clp.setCollectionMode(true);
      }
      System.out.println("Collection mode toggled");
    }
    // React to 'shooter angle up' button press
    if (panel.wasPressed(ButtonPanel.RIGHT_TOP)) {
      System.out.println("Shooter angle up");
    }
    // React to 'shooter angle down' button press
    if (panel.wasPressed(ButtonPanel.RIGHT_MIDDLE)) {
      System.out.println("Shooter angle down");
    }


    ledpower += ledincrease * ledDirection;
    if (ledpower > ledmax) ledDirection = -1;
    else if (ledpower < ledmin) ledDirection = 1;

    //test.set(Math.max(0,ledpower));

    double tal = xcontroller.getRawAxis(1);
    //talon.set(tal);

    //Color detectedColor = colorsensor.getColor();
  


    //double IR = colorsensor.getIR(); 

    //int proximity = colorsensor.getProximity();
    
    //System.out.println(detectedColor);
    //SmartDashboard.putNumber("Proximity", proximity);
    try {
        drive.arcadeDrive(xcontroller.getRawAxis(1), xcontroller.getRawAxis(2)); // move around
    } catch (Exception e) {}
    
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
