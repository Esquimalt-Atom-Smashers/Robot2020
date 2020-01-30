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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Talon;


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
  WPI_VictorSPX testadoo;

  WPI_VictorSPX testaboo;
 
  @Override
  public void robotInit() {
    
    
    testadoo = new WPI_VictorSPX(1);
    testaboo = new WPI_VictorSPX(2);
    xcontroller = new XboxController(3);
    

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
  
    testadoo.set(xcontroller.getRawAxis(1));
    testaboo.set(xcontroller.getRawAxis(1));
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
