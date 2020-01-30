/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
  private WPI_TalonSRX gantry;
  private WPI_TalonSRX claw;
 
  @Override
  public void robotInit() {
    

    gantry = new WPI_TalonSRX(1);
    claw = new WPI_TalonSRX(0);
    
    xcontroller = new XboxController(3);
    drive = new DifferentialDrive(new Spark(0), new Spark(1));
    

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
    
    double fwdpower = xcontroller.getRawAxis(1);
    double turnpower = xcontroller.getRawAxis(2);
    //drive.arcadeDrive(-fwdpower, turnpower);
    double gantryPower = xcontroller.getRawAxis(1);
    double clawPower = xcontroller.getRawAxis(3);
    gantry.set(gantryPower);
    claw.set(clawPower * 0.3);

  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
