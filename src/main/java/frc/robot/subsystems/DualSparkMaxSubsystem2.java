// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DualSparkMaxSubsystem2 extends SubsystemBase {
  private final static CANSparkMax m_motor1 = new CANSparkMax(Constants.OperatorConstants.kCANIDforMotorOne, Constants.OperatorConstants.kMotorType);
  private final static CANSparkMax m_motor2 = new CANSparkMax(Constants.OperatorConstants.kCANIDforMotorTwo, Constants.OperatorConstants.kMotorType);
  private final static CANSparkMax m_motor3 = new CANSparkMax(Constants.OperatorConstants.kCANIDforMotorThree, Constants.OperatorConstants.kMotorType);
  private final static CANSparkMax m_motor4 = new CANSparkMax(Constants.OperatorConstants.kCANIDforMotorFour, Constants.OperatorConstants.kMotorType);
  static RelativeEncoder m_encoder1 = m_motor2.getAlternateEncoder(Type.kQuadrature, 4096);
  static RelativeEncoder m_encoder2 = m_motor4.getAlternateEncoder(Type.kQuadrature, 4096);
  /** Creates a new ExampleSubsystem. */
  public DualSparkMaxSubsystem2 () {
    m_motor1.follow(m_motor2);
    m_motor3.follow(m_motor4);

    

    

  }
  
  public static void setSpeedOfLeft(double speedLeft) {
    //m_motor1.follow(m_motor2);
    m_motor2.set(speedLeft);

    
  }

  public static void setSpeedOfRight(double speedRight) {
    m_motor4.set(speedRight);
  }

  public static double EncoderLeftPOS() {
    m_encoder1 = m_motor2.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 4096);
    
    return m_encoder1.getPosition();

  }

  public static double EncoderRightPOS() {
    m_encoder2 = m_motor4.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 4096);
    
    return m_encoder2.getPosition();
  }
    
   
}
  
    

 