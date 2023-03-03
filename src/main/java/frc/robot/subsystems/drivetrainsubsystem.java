// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class drivetrainsubsystem extends SubsystemBase {
  private final static WPI_TalonSRX m_motor1 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorOne);
  private final static WPI_TalonSRX m_motor2 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorTwo);
  private final static WPI_TalonSRX m_motor3 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorThree);
  private final static WPI_TalonSRX m_motor4 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorFour);
  public static MotorControllerGroup m_leftControllerGroup = new MotorControllerGroup(m_motor1, m_motor2);
  public static MotorControllerGroup m_rightControllerGroup = new MotorControllerGroup(m_motor3, m_motor4);
  public static DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);

  /** Creates a new ExampleSubsystem. */
  public drivetrainsubsystem () {
    
    

    

  }
  
  public static void setSpeedOfLeft(double speedLeft) {
    m_motor2.follow(m_motor1);
    m_motor1.set(speedLeft);

    
  }

  public static void setSpeedOfRight(double speedRight) {
    m_motor4.follow(m_motor3);
    m_motor3.set(speedRight);
  }

  public static void setRobotDrive(double speed, double rotation){
    m_robotDrive.arcadeDrive(speed, rotation);
    
    
  }

  public static double EncoderLeftPOS() {
    final double m_encoder1 = m_motor3.getSelectedSensorPosition();
    
    return m_encoder1;

  }

  public static double EncoderRightPOS() {
    final double m_encoder2 = m_motor1.getSelectedSensorPosition();
    
    return m_encoder2;
  }
  
  public static double EncoderLeftPOSinmeters() {
    final double m_encoder1inmeters = ((m_motor3.getSelectedSensorPosition()/3)*(Math.PI*6));
    return m_encoder1inmeters; 
  }

  public static double EncoderRightPOSinmeters() {
    final double m_encoder2inmeters = ((m_motor1.getSelectedSensorPosition()/3)*(Math.PI*6));
    return m_encoder2inmeters;
  }

  public static void ZeroEncoderLeftPOS() {
    m_motor1.setSelectedSensorPosition(0);
  }
  public static void ZeroEncoderRightPOS() {
    m_motor3.setSelectedSensorPosition(0);
  }
    
   
}
  
    

 