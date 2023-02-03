// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DualSparkMaxSubsystem2 extends SubsystemBase {
    private final static CANSparkMax m_motor1 = new CANSparkMax(Constants.OperatorConstants.kCANIDforMotorOne, Constants.OperatorConstants.kMotorType);
    private final static CANSparkMax m_motor2 = new CANSparkMax(Constants.OperatorConstants.kCanIDforMotorTwo, Constants.OperatorConstants.kMotorType);
    static RelativeEncoder m_encoder1 = m_motor2.getAlternateEncoder(Type.kQuadrature, 4096);
  /** Creates a new ExampleSubsystem. */
  public DualSparkMaxSubsystem2 () {
    m_motor1.follow(m_motor2);

    

    

  }
  
  public static void setSpeed(double speed) {
    m_motor1.follow(m_motor2);
    m_motor2.set(speed);

    
  }

  public static double EncoderPOS() {
    m_encoder1 = m_motor1.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 4096);
    
    return m_encoder1.getPosition();

  }
    
   
}
  
    

 