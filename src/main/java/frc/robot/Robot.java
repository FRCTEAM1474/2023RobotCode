// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// TODO: remove unused code

package frc.robot;

//import outside resources

import java.io.IOException;
import java.nio.file.Path;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//import commands

import frc.robot.commands.flipperinnieandoutiecommand;
import frc.robot.commands.helevatorinnieandoutiecommand;
//import frc.robot.commands.shiftinggearboxescommand;
import frc.robot.commands.slelavatorinnieandoutiecommand;
import frc.robot.commands.velevatorEXACTuppieanddowniecommand;
import frc.robot.commands.velevatoruppieanddowniecommand;
import frc.robot.commands.autos.TestPath;

//import subsystems

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ShiftingGearboxesSubsystem;
import frc.robot.subsystems.drivetrainsubsystem;
import frc.robot.subsystems.grippersubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Drivetrain drivetrain = new Drivetrain();

  public static Trajectory Trajectory1 = new Trajectory();
  public static Trajectory Trajectory2 = new Trajectory();
  public static Trajectory Trajectory3 = new Trajectory();
  public static Trajectory StraightTestTrajectory = new Trajectory();

  boolean solenoidTrigger = false;
  boolean gripperTrigger = false;
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final Joystick m_stick = new Joystick(0);
  private final Joystick m_stickTwo = new Joystick(1);

  public static Bling bling;

  public final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

  final String kDefaultAuto = "score and back up and turn";
    final String kCustomAuto = "score and back up and go forward again for charge station";
    final String kNoAuto = "no auto :(";
    final String kBackUp = "just backup";
    final String kDropGamePiece = "just flip out and drop the gamepiece";
    final String kDropandBackUp = "drop and back up";
    final String kDropandDoNothing = "drop and do nothing";
    String m_autoSelected;
    final SendableChooser<String> m_chooser = new SendableChooser<>();

    double m_directionofhelevator;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  @Override
  public void robotInit() {

    String trajectory1JSON = "paths//phase1.wpilib.json";
    String trajectory2JSON = "paths//phase2.wpilib.json";
    String trajectory3JSON = "paths//phase3.wpilib.json";
    String trajectorytestJSON = "paths//straightmaybe.wpilib.json";
 
    try {
      Path testPath1 = Filesystem.getDeployDirectory().toPath().resolve(trajectory1JSON);
      Path testPath2 = Filesystem.getDeployDirectory().toPath().resolve(trajectory2JSON);
      Path testPath3 = Filesystem.getDeployDirectory().toPath().resolve(trajectory3JSON);
      Path straighttestpath = Filesystem.getDeployDirectory().toPath().resolve(trajectorytestJSON);

      Trajectory1 = TrajectoryUtil.fromPathweaverJson(testPath1);
      Trajectory2 = TrajectoryUtil.fromPathweaverJson(testPath2);
      Trajectory3 = TrajectoryUtil.fromPathweaverJson(testPath3);
      StraightTestTrajectory = TrajectoryUtil.fromPathweaverJson(straighttestpath);
    }

    catch (IOException ex) {
      DriverStation.reportError("Unable to open Trajectory", ex.getStackTrace());
    } 

    drivetrain.zeroOdometry();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    m_chooser.addOption("No Auto", kNoAuto);
    m_chooser.addOption("Backup Auto", kBackUp);
    m_chooser.addOption("Drop Gamepiece", kDropGamePiece);
    m_chooser.addOption("Drop and Backup", kDropandBackUp);
    m_chooser.addOption("Drop and Do Nothing", kDropandDoNothing);
    SmartDashboard.putData("Auto choices", m_chooser);

    compressor.enableDigital();
    
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    CameraServer.startAutomaticCapture();

    JoystickButton shiftgears = new JoystickButton(m_stick, 11);
    JoystickButton grabgamepiece = new JoystickButton(m_stick, 1);

    JoystickButton groundactiongroup = new JoystickButton(m_stickTwo, 11);
    JoystickButton midactiongroup = new JoystickButton(m_stickTwo, 9);
    JoystickButton highactiongroup = new JoystickButton(m_stickTwo, 7);
    JoystickButton substationactiongroup = new JoystickButton(m_stickTwo, 10);

    JoystickButton extendflipperbutton = new JoystickButton(m_stick, 5);
    JoystickButton retractflipperbutton = new JoystickButton(m_stick, 3);

    JoystickButton extendsliderbutton = new JoystickButton(m_stickTwo, 1);
    JoystickButton retractsliderbutton = new JoystickButton(m_stickTwo, 2);

    JoystickButton extendvelevatorbutton = new JoystickButton(m_stickTwo, 6);
    JoystickButton retractvelevatorbutton = new JoystickButton(m_stickTwo, 4);

    JoystickButton extendhelevatorbutton = new JoystickButton(m_stickTwo, 5);
    JoystickButton retracthelevatorbutton = new JoystickButton(m_stickTwo, 3);

    JoystickButton movevelevatortobottom = new JoystickButton(m_stickTwo, 11);
    JoystickButton movevelevatortomidanddoublesubstation = new JoystickButton(m_stickTwo, 9);
    JoystickButton movevelevatortohigh = new JoystickButton(m_stickTwo, 7);

    //shiftgears.onTrue(new shiftinggearboxescommand());
    //grabgamepiece.onTrue(new grippercommand());

    extendflipperbutton.whileTrue(new flipperinnieandoutiecommand(-0.25));
    retractflipperbutton.whileTrue(new flipperinnieandoutiecommand(0.25));

    extendsliderbutton.whileTrue(new slelavatorinnieandoutiecommand(0.1));
    retractsliderbutton.whileTrue(new slelavatorinnieandoutiecommand(-0.1));

    extendhelevatorbutton.whileTrue(new helevatorinnieandoutiecommand(-0.4));
    retracthelevatorbutton.whileTrue(new helevatorinnieandoutiecommand(0.4));

    extendvelevatorbutton.whileTrue(new velevatoruppieanddowniecommand(0.1));
    retractvelevatorbutton.whileTrue(new velevatoruppieanddowniecommand(-0.1));

    movevelevatortobottom.whileTrue(new velevatorEXACTuppieanddowniecommand(1)); //should be 0 and not whileTrue was 5
    movevelevatortomidanddoublesubstation.whileTrue(new velevatorEXACTuppieanddowniecommand(44)); //should not be whileTrue
    movevelevatortohigh.whileTrue(new velevatorEXACTuppieanddowniecommand(62)); //should be 63 and not whileTrue was 58
    //change below to onTrue()
    /*groundactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(5)));
    midactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(44)));
    highactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(58)));
    substationactiongroup.whileTrue(new velevatorEXACTuppieanddowniecommand(44));*/

    drivetrainsubsystem.ZeroEncoderLeftPOS();
    drivetrainsubsystem.ZeroEncoderRightPOS();

    bling = new Bling();
    
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

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. 
   * @return 
   * @return */
  @Override
  public void autonomousInit() {

    System.out.println("autonomousinit1");
    Robot.drivetrain.setNeutralMode(NeutralMode.Brake);

    m_autonomousCommand = new TestPath(StraightTestTrajectory);
    if (m_autonomousCommand != null) {
      System.out.println("before scheduling autonomous command");
      m_autonomousCommand.schedule();
      System.out.println("after scheduling autonomous command");
    }
    System.out.println("autonomousinit2");

    System.out.println("auto init is running 1");
   
    System.out.println("auto init is running 2");
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
  } // TODO:slider and (helevator out) code limits

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
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

  if (m_stick.getRawButtonPressed(1)){
    if (!gripperTrigger){
      gripperTrigger = true;
    }
    else {
      gripperTrigger = false;
    }
}
if (gripperTrigger) {
  grippersubsystem.m_solenoid.set(true);
  grippersubsystem.m_solenoidTwo.set(false);
}
else {
  grippersubsystem.m_solenoidTwo.set(true);
  grippersubsystem.m_solenoid.set(false);
}
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
