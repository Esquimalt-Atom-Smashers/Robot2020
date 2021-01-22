/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/*
  _____                            _       
 |_   _|                          | |      
   | |  _ __ ___  _ __   ___  _ __| |_ ___ 
   | | | '_ ` _ \| '_ \ / _ \| '__| __/ __|
  _| |_| | | | | | |_) | (_) | |  | |_\__ \
 |_____|_| |_| |_| .__/ \___/|_|   \__|___/
                 | |                       
                 |_|                       
*/

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

//Creates class called Robot and it extends TimedRobot so it has access to all of TimedRobot's variables, methods.
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  
  //A reference variable is just a variable which will be initialized into an object later on(a simpler and cleaner way to create objects) 
  
    //Creates a refrence variable called 'server' which refrences the RobotServer class
  private RobotServer server;
    /*The DifferentialDrive class is a class specificaly used for driving differential drive / skid-steer driving. 
    Differential drive is a two wheeled drive system with independent actuators per wheels*/
  
    //Creates a refrence variable called 'drive' which refrences the DifferentialDrive class
  private DifferentialDrive drive;
    /*The XboxController handles inputs from xbox 360 or xbox one controllers that are connected to the driver station.
    We can use methods like 'getAButton()' to get the values of each of the buttons so we can link them up with specific methods ex: if (aButtonIsPressed == true) goForward()*/
  
    //Creates a refrence variable called 'xcontroller' which refrences the XboxController class
  private XboxController xcontroller;
    /*The CLP class is actually a custom class made by the programmers, you can find it in java/frc/subsystems/CLP
    The class is made to handle the shooting mechanism and launcher*/
  
    //Creates a refrence variable called 'clp' which refrences the CLP class
  private CLP clp;
    /*The Compressor class is used for operating a compressor connected to the PCM(Pneumatic Control Module)
    Its important to note that the PCM already runs in a closed loop when a solenoid object is created meaning this class is only
    required for a more detailed status of the Compressor*/
  
    //Creates a refrence variable called 'compressor' which refrences the Compressor class
  private Compressor compressor;
    /*Essentially the DoubleSolenoid class is used for running 2 channels of high voltage digital output on the PCM(Pneumatic Control Module)
    This class is typically only used in situations where pneumatic solenoids that have 2 positions controlled by 2 different channels*/
  
    //Creates a refrence variable called 'solenoid' which refrences the DoubleSolenoid class
  private DoubleSolenoid solenoid;
    /*The ColorSensorV3 class is used to program the ColorSensorV3 component
    The color sensor can be used to read and compare colors, it also has a built in proximity sensor(IR)*/
  
    //Creates a refrence variable called 'colorsensor' which refrences the ColorSensorV3 class
  private ColorSensorV3 colorsensor;
    /*The SmartDashboard is the bridge between the robot programs and the SmartDashboard which is on the laptop
    We use this because we can input values here and the values would pop up on the SmartDashboard on the laptop*/
  
    //Creates a refrence variable called 'sDashboard' which refrences the SmartDashboard class
  private SmartDashboard sDashboard;
    /* The WPI_Talon class allows us to refer to our motor controllers and TalonSRX's are what we use to control the motors
    Keep in mind the WPI_TalonsSRX is an 3rd part library so we needed to download it seperatly*/
  
    //Creates a refrence variable called 'talon' which refrences the WPI_TalonSRX class
  private WPI_TalonSRX talon;
    /*The Joystick class handles all the standerd Joysticks connected to the Driver station.
    This class handles standard input that comes from the Driver Station. 
    Each time a value is requested the most recent value is returned. */
  
    //Creates a refrence variable called 'stick' which refrences the Joystick class
  private Joystick stick;
  private double ledmin = 0.2;
  private double fadeTime = 3000;
  private double ledpower = ledmin;
  private double ledmax = 1;
  private double ledincrease = (ledmax - ledmin)/fadeTime/0.02;
  private double ledDirection = 1;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private Spark test;
  /*The ButtonPanel class is a subsystem located in the subsystem folder
  The ButtonPanel class is a subsystem which allows us to use the purple box with the buttons in it to control the robot*/
  
  //Creates a refernnce variable called 'panel' which refrences the ButtonPanel class
  private ButtonPanel panel;
  
  //roboInit gets called once every time the robot gets booted up
  @Override
  public void robotInit() {
    
   
    //compressor = new Compressor(0);
    //clp = new CLP(testadoo, testaboo);
    //initializes the refernce variable 'clp' and makes it an instance of the CLP class
    clp = new CLP();
    //test = new Spark(0);
    
    /*initializes the refernce variable 'xcontroller' and makes it an instance of the XboxController class
    The '3' in the XboxController parameter is the usb port the xboxcontroller is plugged into*/
    xcontroller = new XboxController(3);
    
    //colorsensor = new ColorSensorV3(i2cPort);
    
    /*initializes the refernce variable 'talon' and makes it an instance of the WPI_TalonSRX class
    The '0' in the WPI_TalonSRX parameter is the device number of the TalonSRX Component*/
    talon = new WPI_TalonSRX(0);
    
    //The 'try' statment allows you to define a block of code and test it for errors as the code is being run
    try {
      drive = new DifferentialDrive(new Spark(0), new Spark(1)); // the base and wheels
      stick = new Joystick(3); //Check driver station usb settings for port number
      //drive.setInverted(true);
      //The 'catch' statment allows you to define a block of code to be run, if an error apears in the 'try' statment
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
  //autonomusInit is ran once at the start of auto
  public void autonomousInit() {
  }

  @Override
  //autonomusPeriodic runs in a loop during auto
  public void autonomousPeriodic() {
  }

  @Override
  //teleopInit is ran once at the start of teleop
  public void teleopInit() {
  
  }

  @Override
  //teleopPeriodic runs in a loop during teleop
  public void teleopPeriodic() 
  {
    MotorPowerUpdater.getInstance().updateAll();
    //panel.update() updates the current state of the buttons
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
  //testInit is ran once at the start of test
  public void testInit() {
  }

  @Override
  //testPeriodic runs in a loop during test
  public void testPeriodic() {
  }

}
