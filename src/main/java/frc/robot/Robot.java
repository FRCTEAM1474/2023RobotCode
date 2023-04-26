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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//import commands

import frc.robot.commands.flipperinnieandoutiecommand;
import frc.robot.commands.helevatorinnieandoutiecommand;
import frc.robot.commands.slelavatorinnieandoutiecommand;
import frc.robot.commands.velevatorEXACTuppieanddowniecommand;
import frc.robot.commands.velevatoruppieanddowniecommand;
import frc.robot.commands.autos.TestPath;

//import subsystems

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ShiftingGearboxesSubsystem;
//import frc.robot.subsystems.drivetrainsubsystem;
import frc.robot.subsystems.grippersubsystem;
import frc.robot.subsystems.velevatorsubsystem;
import frc.robot.subsystems.blinkinsubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Drivetrain drivetrain = new Drivetrain();

  //public static Trajectory Trajectory1 = new Trajectory();
  //public static Trajectory Trajectory2 = new Trajectory();
  //public static Trajectory Trajectory3 = new Trajectory();
  public static Trajectory CurvedTestTrajectory = new Trajectory();

  boolean solenoidTrigger = false;
  boolean gripperTrigger = false;
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final Joystick m_stick = new Joystick(0);
  private final Joystick m_stickTwo = new Joystick(1);

  public static Bling bling;

  private double startTime;

  public final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

    final String kScoreAuto = "score";
    final String kScoreandBackUpAuto = "score and back up";
    /*final String kNoAuto = "no auto :(";
    final String kBackUp = "just backup";
    final String kDropGamePiece = "just flip out and drop the gamepiece";
    final String kDropandBackUp = "drop and back up";
    final String kDropandDoNothing = "drop and do nothing";*/
    String m_autoSelected;
    final SendableChooser<String> m_chooser = new SendableChooser<>();

    double m_directionofhelevator;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  @Override
  public void robotInit() {

    blinkinsubsystem.setSpeed("Rainbow");

    //String trajectory1JSON = "paths//phase1.wpilib.json";
    //String trajectory2JSON = "paths//phase2.wpilib.json";
    //String trajectory3JSON = "paths//phase3.wpilib.json";
    String trajectorytestJSON = "paths//Unnamed.wpilib.json";
 
    try {
      //Path testPath1 = Filesystem.getDeployDirectory().toPath().resolve(trajectory1JSON);
      //Path testPath2 = Filesystem.getDeployDirectory().toPath().resolve(trajectory2JSON);
      //Path testPath3 = Filesystem.getDeployDirectory().toPath().resolve(trajectory3JSON);
      Path curvytestpath = Filesystem.getDeployDirectory().toPath().resolve(trajectorytestJSON);

      //Trajectory1 = TrajectoryUtil.fromPathweaverJson(testPath1);
      //Trajectory2 = TrajectoryUtil.fromPathweaverJson(testPath2);
      //Trajectory3 = TrajectoryUtil.fromPathweaverJson(testPath3);
      CurvedTestTrajectory = TrajectoryUtil.fromPathweaverJson(curvytestpath);
    }

    catch (IOException ex) {
      DriverStation.reportError("Unable to open Trajectory", ex.getStackTrace());
    } 

    drivetrain.zeroOdometry();

    m_chooser.setDefaultOption("Score Auto", kScoreAuto);
    m_chooser.addOption("Score and Back Up Auto", kScoreandBackUpAuto);
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

    extendflipperbutton.whileTrue(new flipperinnieandoutiecommand(-0.25));
    retractflipperbutton.whileTrue(new flipperinnieandoutiecommand(0.25));

    extendsliderbutton.whileTrue(new slelavatorinnieandoutiecommand(0.6));
    retractsliderbutton.whileTrue(new slelavatorinnieandoutiecommand(-0.75));

    extendhelevatorbutton.whileTrue(new helevatorinnieandoutiecommand(0.6));
    retracthelevatorbutton.whileTrue(new helevatorinnieandoutiecommand(-0.75));

    extendvelevatorbutton.whileTrue(new velevatoruppieanddowniecommand(0.4));
    retractvelevatorbutton.whileTrue(new velevatoruppieanddowniecommand(-0.4));

    //movevelevatortobottom.whileTrue(new velevatorEXACTuppieanddowniecommand(10)); //should be 0 and not whileTrue was 5
    //movevelevatortomidanddoublesubstation.whileTrue(new velevatorEXACTuppieanddowniecommand(80)); //should not be whileTrue
    //movevelevatortohigh.whileTrue(new velevatorEXACTuppieanddowniecommand(106)); //should be 63 and not whileTrue was 58
    
    //change below to onTrue()
    /*groundactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(5)));
    midactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(44)));
    highactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(58)));
    substationactiongroup.whileTrue(new velevatorEXACTuppieanddowniecommand(44));*/

    //drivetrainsubsystem.ZeroEncoderLeftPOS();
    //drivetrainsubsystem.ZeroEncoderRightPOS();

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



    SmartDashboard.putNumber("velevator rotation value", velevatorsubsystem.velevatorencoderposition());

    SmartDashboard.putNumber("encoderleft", drivetrain.getLeftLeadDriveDistanceMeters());
    SmartDashboard.putNumber("encoderright", drivetrain.getRightLeadDriveDistanceMeters());
    SmartDashboard.putNumber("gyro heading", drivetrain.getHeadingDegrees());
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  /* Victor commented
    SmartDashboard.putNumber("encoderleft", DualSparkMaxSubsystem2.m_encoder1.getPosition());
    SmartDashboard.putNumber("encoderright", DualSparkMaxSubsystem2.m_encoder2.getPosition());
    */
    //SmartDashboard.putNumber("encoderleft", drivetrainsubsystem.EncoderLeftPOS());
    //SmartDashboard.putNumber("encoderright", drivetrainsubsystem.EncoderRightPOS());
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

    startTime = Timer.getFPGATimestamp();
    Robot.drivetrain.setNeutralMode(NeutralMode.Brake);

    //frc.robot.Constants.OperatorConstants.topposition = 107;
    frc.robot.Constants.OperatorConstants.topposition = 80;
    //new ParallelCommandGroup(null)
    

    //System.out.println("autonomousinit1");
    /*Robot.drivetrain.setNeutralMode(NeutralMode.Brake);

    m_autonomousCommand = new TestPath(CurvedTestTrajectory);
    if (m_autonomousCommand != null) {
      //System.out.println("before scheduling autonomous command");
      drivetrain.zeroOdometry();
      
      m_autonomousCommand.schedule();
      //System.out.println("after scheduling autonomous command");
    }
    //System.out.println("autonomousinit2");*/
  }
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    double timedifference = time - startTime;
    if (timedifference > 1.5 && timedifference < 2.5 ) {
      new flipperinnieandoutiecommand(-0.25).schedule();
      //new velevatoruppieanddowniecommand(0.4).schedule();
      //grippersubsystem.m_solenoid.set(true);
      //grippersubsystem.m_solenoidTwo.set(false);
      
    }
    if (timedifference > 0 && timedifference < 3) {
      frc.robot.Constants.OperatorConstants.shelevatorrestriction = 1;
      new velevatoruppieanddowniecommand(0.4).schedule();
    }
    if (timedifference > 3 && timedifference < 6) {
      //ParallelCommandGroup shelevator = new ParallelCommandGroup(new helevatorinnieandoutiecommand(0.6), new slelavatorinnieandoutiecommand(0.6));
      //frc.robot.Robot.autonomousPeriodic.shelevator
      frc.robot.Constants.OperatorConstants.shelevatorrestriction = 1;
      new helevatorinnieandoutiecommand(0.6);
      //Commands.parallel(new helevatorinnieandoutiecommand(0.6), new slelavatorinnieandoutiecommand(0.6)).schedule();
    }
    if (timedifference > 6 && timedifference < 6.25) {
      grippersubsystem.m_solenoid.set(true);
      grippersubsystem.m_solenoidTwo.set(false);

      
    
    }
    if (timedifference > 6.5 && timedifference < 9) {
      frc.robot.Constants.OperatorConstants.shelevatorrestriction = 2;
      new helevatorinnieandoutiecommand(-0.6);
      //Commands.parallel(new helevatorinnieandoutiecommand(-0.6), new slelavatorinnieandoutiecommand(-0.6)).schedule();
      //grippersubsystem.m_solenoid.set(true);
      //grippersubsystem.m_solenoidTwo.set(false);
    }
    if (timedifference > 9 && timedifference < 10) {
      //Commands.parallel(new helevatorinnieandoutiecommand(0.4), new slelavatorinnieandoutiecommand(-0.4)).schedule();
      grippersubsystem.m_solenoid.set(false);
      grippersubsystem.m_solenoidTwo.set(true);
    }
    if (timedifference > 9 && timedifference < 12) {
      frc.robot.Constants.OperatorConstants.shelevatorrestriction = 2;
      //new flipperinnieandoutiecommand(0.25);
      //drivetrain.m_robotDrive.arcadeDrive(-0.75, 0);
      new velevatoruppieanddowniecommand(-0.4).schedule();
    }
    if (timedifference > 12 && timedifference < 15) {
      //drivetrain.m_robotDrive.arcadeDrive(-0.6, 0);
      drivetrain.m_robotDrive.arcadeDrive(-0.75, 0);
    }
    /*switch (m_autoSelected) {
      case kScoreAuto:
      if (timedifference > 0 && timedifference < 1 ) {
        new flipperinnieandoutiecommand(-0.25).schedule();
        //new velevatoruppieanddowniecommand(0.4).schedule();
        //grippersubsystem.m_solenoid.set(true);
        //grippersubsystem.m_solenoidTwo.set(false);
        
      }
      if (timedifference > 1 && timedifference < 4) {
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 1;
        new velevatoruppieanddowniecommand(0.4).schedule();
      }
      if (timedifference > 3 && timedifference < 6) {
        //ParallelCommandGroup shelevator = new ParallelCommandGroup(new helevatorinnieandoutiecommand(0.6), new slelavatorinnieandoutiecommand(0.6));
        //frc.robot.Robot.autonomousPeriodic.shelevator
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 1;
        Commands.parallel(new helevatorinnieandoutiecommand(0.6), new slelavatorinnieandoutiecommand(0.6)).schedule();
      }
      if (timedifference > 6 && timedifference < 6.25) {
        grippersubsystem.m_solenoid.set(true);
        grippersubsystem.m_solenoidTwo.set(false);
  
        
      
      }
      if (timedifference > 6.5 && timedifference < 9) {
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 2;
        Commands.parallel(new helevatorinnieandoutiecommand(-0.6), new slelavatorinnieandoutiecommand(-0.6)).schedule();
        //grippersubsystem.m_solenoid.set(true);
        //grippersubsystem.m_solenoidTwo.set(false);
      }
      if (timedifference > 9 && timedifference < 10) {
        //Commands.parallel(new helevatorinnieandoutiecommand(0.4), new slelavatorinnieandoutiecommand(-0.4)).schedule();
        grippersubsystem.m_solenoid.set(false);
        grippersubsystem.m_solenoidTwo.set(true);
      }
      if (timedifference > 9 && timedifference < 12) {
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 2;
        //new flipperinnieandoutiecommand(0.25);
        //drivetrain.m_robotDrive.arcadeDrive(-0.75, 0);
        new velevatoruppieanddowniecommand(-0.4).schedule();
      }
      if (timedifference > 12 && timedifference < 15) {
        //drivetrain.m_robotDrive.arcadeDrive(-0.6, 0);
        //drivetrain.m_robotDrive.arcadeDrive(-0.75, 0);
      }
      break;
      case kScoreandBackUpAuto:
      if (timedifference > 0 && timedifference < 1 ) {
        new flipperinnieandoutiecommand(-0.25).schedule();
        //new velevatoruppieanddowniecommand(0.4).schedule();
        //grippersubsystem.m_solenoid.set(true);
        //grippersubsystem.m_solenoidTwo.set(false);
        
      }
      if (timedifference > 1 && timedifference < 4) {
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 1;
        new velevatoruppieanddowniecommand(0.4).schedule();
      }
      if (timedifference > 3 && timedifference < 6) {
        //ParallelCommandGroup shelevator = new ParallelCommandGroup(new helevatorinnieandoutiecommand(0.6), new slelavatorinnieandoutiecommand(0.6));
        //frc.robot.Robot.autonomousPeriodic.shelevator
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 1;
        Commands.parallel(new helevatorinnieandoutiecommand(0.6), new slelavatorinnieandoutiecommand(0.6)).schedule();
      }
      if (timedifference > 6 && timedifference < 6.25) {
        grippersubsystem.m_solenoid.set(true);
        grippersubsystem.m_solenoidTwo.set(false);
  
        
      
      }
      if (timedifference > 6.5 && timedifference < 9) {
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 2;
        Commands.parallel(new helevatorinnieandoutiecommand(-0.6), new slelavatorinnieandoutiecommand(-0.6)).schedule();
        //grippersubsystem.m_solenoid.set(true);
        //grippersubsystem.m_solenoidTwo.set(false);
      }
      if (timedifference > 9 && timedifference < 10) {
        //Commands.parallel(new helevatorinnieandoutiecommand(0.4), new slelavatorinnieandoutiecommand(-0.4)).schedule();
        grippersubsystem.m_solenoid.set(false);
        grippersubsystem.m_solenoidTwo.set(true);
      }
      if (timedifference > 9 && timedifference < 12) {
        frc.robot.Constants.OperatorConstants.shelevatorrestriction = 2;
        //new flipperinnieandoutiecommand(0.25);
        //drivetrain.m_robotDrive.arcadeDrive(-0.75, 0);
        new velevatoruppieanddowniecommand(-0.4).schedule();
      }
      if (timedifference > 12 && timedifference < 15) {
        //drivetrain.m_robotDrive.arcadeDrive(-0.6, 0);
        drivetrain.m_robotDrive.arcadeDrive(-0.75, 0);
      }
      break;
    }*/
    
  }

  @Override
  public void teleopInit() {
    frc.robot.Constants.OperatorConstants.shelevatorrestriction = 0;
    drivetrain.zeroOdometry();
    blinkinsubsystem.setSpeed("Rainbow");
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

    if (m_stickTwo.getRawButton(7)) {
      frc.robot.Constants.OperatorConstants.topposition = 107;
    }
    if (m_stickTwo.getRawButton(9)) {
      frc.robot.Constants.OperatorConstants.topposition = 80;
    }
    if (m_stickTwo.getRawButton(11)) {
      frc.robot.Constants.OperatorConstants.topposition = 96;
    }

    if (m_stick.getRawButton(8)) {
      blinkinsubsystem.setSpeed("Yellow");
    }
    if (m_stick.getRawButton(10)) {
      blinkinsubsystem.setSpeed("Violet");
    }
    if (m_stick.getRawButton(12)) {
      blinkinsubsystem.setSpeed("Rainbow");
    }
    //drivetrainsubsystem.m_robotDrive.feed();
    //drivetrainsubsystem.m_robotDrive.arcadeDrive(m_stick.getX(), m_stick.getY());
    drivetrain.m_robotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());
    
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
