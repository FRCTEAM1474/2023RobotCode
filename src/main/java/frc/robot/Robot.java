// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DualSparkMaxCommand2;
import frc.robot.commands.GoodDualSparkMaxCommand;
//import frc.robot.commands.GetEncoderOutputFromSparkmaxesCommand;
import frc.robot.subsystems.drivetrainsubsystem;
import frc.robot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  boolean solenoidTrigger = false;
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final Joystick m_stick = new Joystick(0);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    JoystickButton spinnyButton = new JoystickButton(m_stick, 3);
    spinnyButton.whileTrue(new DualSparkMaxCommand2(1, 1));
    drivetrainsubsystem.ZeroEncoderLeftPOS();
    drivetrainsubsystem.ZeroEncoderRightPOS();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    //final double encoder = DualSparkMaxSubsystem2.EncoderPOS();
  /* Victor commented
    SmartDashboard.putNumber("encoderleft", DualSparkMaxSubsystem2.m_encoder1.getPosition());
    SmartDashboard.putNumber("encoderright", DualSparkMaxSubsystem2.m_encoder2.getPosition());
    */
    SmartDashboard.putNumber("encoderleft", drivetrainsubsystem.EncoderLeftPOS());
    SmartDashboard.putNumber("encoderright", drivetrainsubsystem.EncoderRightPOS());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //new GoodDualSparkMaxCommand(m_stick.getX(), m_stick.getY());
    drivetrainsubsystem.m_robotDrive.feed();
    drivetrainsubsystem.m_robotDrive.arcadeDrive(m_stick.getX(), m_stick.getY());

    /* Victor added 
        SmartDashboard.putNumber("encoderleft", DualSparkMaxSubsystem2.m_encoder1.getPosition());
    SmartDashboard.putNumber("encoderright", DualSparkMaxSubsystem2.m_encoder2.getPosition());*/

    if (m_stick.getRawButtonPressed(11)){
      if (!solenoidTrigger){
        solenoidTrigger = true;
      }
      else {
        solenoidTrigger = false;
      }
  }
  if (solenoidTrigger) {
    ShiftingGearboxesSubsystem.m_solenoid.set(true);
    ShiftingGearboxesSubsystem.m_solenoidTwo.set(false);
  }
  else {
    ShiftingGearboxesSubsystem.m_solenoidTwo.set(true);
    ShiftingGearboxesSubsystem.m_solenoid.set(false);
  }
  
  //boolean solenoid = m_stickTwo.toggleWhenPresssed(kSolenoidButton)
  //m_solenoid.set(m_stickTwo.toggleWhenPresssed(kSolenoidButton));
  /*
   * In order to set the double solenoid, if just one button
   * is pressed, set the solenoid to correspond to that button.
   * If both are pressed, set the solenoid will be set to Forwards.
   */
  /* Victor commented
   if (m_stick.getRawButton(kDoubleSolenoidForward)) {
    m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
  } else if (m_stickTwo.getRawButton(kDoubleSolenoidReverse)) {
    m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  */
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
