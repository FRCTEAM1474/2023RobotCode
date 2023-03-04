// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import edu.wpi.first.wpilibj2.command.Commands;
//import edu.wpi.first.wpilibj2.command.Commands;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
//import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivedistancecommand;
//import frc.robot.commands.DualSparkMaxCommand2;
import frc.robot.commands.flipperinnieandoutiecommand;
import frc.robot.commands.grippercommand;
import frc.robot.commands.helevatorinnieandoutiecommand;
//import frc.robot.commands.neoandlimitswitchtesting;
import frc.robot.commands.shiftinggearboxescommand;
import frc.robot.commands.slelavatorinnieandoutiecommand;
import frc.robot.commands.turn180degreescommand;
import frc.robot.commands.ungripcommand;
import frc.robot.commands.velevatorEXACTuppieanddowniecommand;
import frc.robot.commands.velevatoruppieanddowniecommand;
//import frc.robot.commands.GoodDualSparkMaxCommand;
//import frc.robot.commands.GetEncoderOutputFromSparkmaxesCommand;
import frc.robot.subsystems.drivetrainsubsystem;
//import frc.robot.subsystems.*;
import frc.robot.Bling;
import frc.robot.commands.ungripcommand;
import frc.robot.commands.gripcommand;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //boolean solenoidTrigger = false;
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final Joystick m_stick = new Joystick(0);
  private final Joystick m_stickTwo = new Joystick(1);

  public static Bling bling;

  final String kDefaultAuto = "score and back up and turn";
    final String kCustomAuto = "score and back up and go forward again for charge station";
    final String kNoAuto = "no auto :(";
    final String kBackUp = "just backup";
    final String kDropGamePiece = "just flip out and drop the gamepiece";
    String m_autoSelected;
    final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    m_chooser.addOption("No Auto", kNoAuto);
    m_chooser.addOption("Backup Auto", kBackUp);
    m_chooser.addOption("Drop Gamepiece", kDropGamePiece);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    CameraServer.startAutomaticCapture();
    
    //JoystickButton spinnyButton = new JoystickButton(m_stick, 3);
    //spinnyButton.whileTrue(new DualSparkMaxCommand2(1, 1));
    //JoystickButton leftneoandlimitswitchtestingbutton = new JoystickButton(m_stick, 7);
    //JoystickButton rightneoandlimitswitchtestingbutton = new JoystickButton(m_stick, 8);

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

    shiftgears.onTrue(new shiftinggearboxescommand());
    grabgamepiece.onTrue(new grippercommand());

    extendflipperbutton.whileTrue(new flipperinnieandoutiecommand(-0.1));
    retractflipperbutton.whileTrue(new flipperinnieandoutiecommand(0.1));

    extendsliderbutton.whileTrue(new slelavatorinnieandoutiecommand(0.1));
    retractsliderbutton.whileTrue(new slelavatorinnieandoutiecommand(-0.1));

    extendhelevatorbutton.whileTrue(new helevatorinnieandoutiecommand(-0.1));
    retracthelevatorbutton.whileTrue(new helevatorinnieandoutiecommand(0.1));

    extendvelevatorbutton.whileTrue(new velevatoruppieanddowniecommand(0.1));
    retractvelevatorbutton.whileTrue(new velevatoruppieanddowniecommand(-0.1));

    movevelevatortobottom.whileTrue(new velevatorEXACTuppieanddowniecommand(5)); //should be 0 and not whileTrue
    movevelevatortomidanddoublesubstation.whileTrue(new velevatorEXACTuppieanddowniecommand(44)); //should not be whileTrue
    movevelevatortohigh.whileTrue(new velevatorEXACTuppieanddowniecommand(58)); //should be 63 and not whileTrue
    //change below to onTrue()
    /*groundactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(5)));
    midactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(44)));
    highactiongroup.whileTrue(Commands.parallel(new helevatorinnieandoutiecommand(-0.1), new slelavatorinnieandoutiecommand(0.1), new velevatorEXACTuppieanddowniecommand(58)));
    substationactiongroup.whileTrue(new velevatorEXACTuppieanddowniecommand(44));*/

    //leftneoandlimitswitchtestingbutton.whileTrue(new neoandlimitswitchtesting(0.1));
    //rightneoandlimitswitchtestingbutton.whileTrue(new neoandlimitswitchtesting(-0.1));
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
    /*if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }*/

    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        new gripcommand().andThen(new velevatorEXACTuppieanddowniecommand(58).andThen(new slelavatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(0.1).andThen(new helevatorinnieandoutiecommand(-0.1).andThen(new ungripcommand().andThen(new helevatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(-0.1).andThen(new slelavatorinnieandoutiecommand(-0.1).andThen(new velevatorEXACTuppieanddowniecommand(5).andThen(new drivedistancecommand(-4).andThen(new drivedistancecommand(1.841))))))))))));
        break;
      case kDefaultAuto:
        // Put default auto code here
        new gripcommand().andThen(new velevatorEXACTuppieanddowniecommand(58).andThen(new slelavatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(0.1).andThen(new helevatorinnieandoutiecommand(-0.1).andThen(new ungripcommand().andThen(new helevatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(-0.1).andThen(new slelavatorinnieandoutiecommand(-0.1).andThen(new velevatorEXACTuppieanddowniecommand(5).andThen(new drivedistancecommand(-4.5).andThen(new turn180degreescommand())))))))))));
        break;
      case kBackUp:
        new drivedistancecommand(-4.5);
        break;
      case kNoAuto:
      default:
        System.out.println("depressed coder");
      break;
      case kDropGamePiece:
        new gripcommand().andThen(new flipperinnieandoutiecommand(0.1).andThen(new ungripcommand()));
    }
    System.out.println("auto init is running 1");
    //m_robotContainer.getAutonomousCommand().schedule();
    System.out.println("auto init is running 2");
    //new velevatorEXACTuppieanddowniecommand(58).andThen(new flipperinnieandoutiecommand(0.1).andThen(new )))
    /*(Commands.parallel(new gripcommand(), 
    new helevatorinnieandoutiecommand(-0.1), 
    new slelavatorinnieandoutiecommand(0.1), 
    new velevatorEXACTuppieanddowniecommand(58))).andThen(
      new flipperinnieandoutiecommand(0.1).andThen(
        new ungripcommand().andThen()))*/
      // basic thing: new gripcommand().andThen(new velevatorEXACTuppieanddowniecommand(58).andThen(new slelavatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(0.1).andThen(new helevatorinnieandoutiecommand(-0.1).andThen(new ungripcommand().andThen(new helevatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(-0.1).andThen(new slelavatorinnieandoutiecommand(-0.1).andThen(new velevatorEXACTuppieanddowniecommand(5).andThen(new drivedistancecommand(-4.5).andThen(new turn180degreescommand())))))))))));
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
    //new GoodDualSparkMaxCommand(m_stick.getX(), m_stick.getY());
    drivetrainsubsystem.m_robotDrive.feed();
    drivetrainsubsystem.m_robotDrive.arcadeDrive(m_stick.getX(), m_stick.getY());

    /* Victor added 
        SmartDashboard.putNumber("encoderleft", DualSparkMaxSubsystem2.m_encoder1.getPosition());
    SmartDashboard.putNumber("encoderright", DualSparkMaxSubsystem2.m_encoder2.getPosition());*/

    /*if (m_stick.getRawButtonPressed(11)){
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
  }*/
  
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
