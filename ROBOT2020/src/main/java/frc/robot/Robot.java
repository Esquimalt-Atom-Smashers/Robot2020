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
import frc.subsystem.CLP;
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

  private double ledmin = 0.2;
  private double fadeTime = 3000;
  private double ledpower = ledmin;
  private double ledmax = 1;
  private double ledincrease = (ledmax - ledmin)/fadeTime/0.02;
  private double ledDirection = 1;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private Spark test;
 
  @Override
  public void robotInit() {
    
   
    //compressor = new Compressor(0);
    //clp = new CLP(testadoo, testaboo);
    test = new Spark(0);
    xcontroller = new XboxController(3);
    colorsensor = new ColorSensorV3(i2cPort);
    talon = new WPI_TalonSRX(0);
    

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
    //testaboo.set(xcontroller.getRawAxis(1));

    ledpower += ledincrease * ledDirection;
    if (ledpower > ledmax) ledDirection = -1;
    else if (ledpower < ledmin) ledDirection = 1;

    test.set(Math.max(0,ledpower));

    double tal = xcontroller.getRawAxis(1);
    talon.set(tal);

    Color detectedColor = colorsensor.getColor();
  


    double IR = colorsensor.getIR(); 

    int proximity = colorsensor.getProximity();
    
    System.out.println(detectedColor);
    SmartDashboard.putNumber("Proximity", proximity);
    
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
