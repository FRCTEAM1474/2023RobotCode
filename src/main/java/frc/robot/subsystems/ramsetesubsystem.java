// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ramsetesubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final static WPI_TalonSRX m_motor1 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorOne);
  private final static WPI_TalonSRX m_motor2 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorTwo);
  private final static WPI_TalonSRX m_motor3 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorThree);
  private final static WPI_TalonSRX m_motor4 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorFour);
  public static MotorControllerGroup m_leftControllerGroup = new MotorControllerGroup(m_motor1, m_motor2);
  public static MotorControllerGroup m_rightControllerGroup = new MotorControllerGroup(m_motor3, m_motor4);
  public static DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);

  // The left-side drive encoder
  /*private final Encoder m_leftEncoder =
      new Encoder(
          DriveConstants.kLeftEncoderPorts[0],
          DriveConstants.kLeftEncoderPorts[1],
          DriveConstants.kLeftEncoderReversed);*/

    private final static double m_leftEncoderPosition = ramsetesubsystem.m_motor2.getSelectedSensorPosition();
    private final double m_leftEncoderVelocity = ramsetesubsystem.m_motor2.getSelectedSensorVelocity();
    private final static double m_leftEncoderDistance = (m_leftEncoderPosition * 0.0001168893);

  // The right-side drive encoder
    private final static double m_rightEncoderPosition = ramsetesubsystem.m_motor3.getSelectedSensorPosition();
    private final double m_rightEncoderVelocity = ramsetesubsystem.m_motor3.getSelectedSensorVelocity();
    private final static double m_rightEncoderDistance = (m_rightEncoderPosition * 0.0001168893);

  // The gyro sensor
  private final static Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  private static DifferentialDriveOdometry m_odometry;

  /** Creates a new DriveSubsystem. */
  public ramsetesubsystem() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightControllerGroup.setInverted(true);

    // Sets the distance per pulse for the encoders
    //m_leftEncoder.setDistancePerPulse(Constants.OperatorConstants.kAlternateQuadratureValue);
    //m_rightEncoder.setDistancePerPulse(Constants.OperatorConstants.kAlternateQuadratureValue);
    //ramsetesubsystem.m_motor2.sel

    resetEncoders();
    m_odometry =
        new DifferentialDriveOdometry(
            m_gyro.getRotation2d(), m_leftEncoderDistance, m_rightEncoderDistance);
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    m_odometry.update(
        m_gyro.getRotation2d(), m_leftEncoderDistance, m_rightEncoderDistance);
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoderVelocity, m_rightEncoderVelocity);
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public static void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(
        m_gyro.getRotation2d(), m_leftEncoderDistance, m_rightEncoderDistance, pose);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_robotDrive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftControllerGroup.setVoltage(leftVolts);
    m_rightControllerGroup.setVoltage(rightVolts);
    m_robotDrive.feed();

  }

  /** Resets the drive encoders to currently read a position of 0. */
  public static void resetEncoders() {
    m_motor2.setSelectedSensorPosition(0);
    m_motor3.setSelectedSensorPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return ((m_rightEncoderPosition * 0.0001168893) + (m_leftEncoderPosition * 0.0001168893)) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  /*public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }*/

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  /*public Encoder getRightEncoder() {
    return m_rightEncoder;
  }*/

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_robotDrive.setMaxOutput(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }
}