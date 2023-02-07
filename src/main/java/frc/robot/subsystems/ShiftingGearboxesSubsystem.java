// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class ShiftingGearboxesSubsystem extends SubsystemBase {
    public final static Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    public final static Solenoid m_solenoidTwo = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
    private final DoubleSolenoid m_doubleSolenoid =
    new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

  /** Creates a new ExampleSubsystem. */
  public ShiftingGearboxesSubsystem () {
    
    
    

    

  }
  
 
    
   
}
  
    

 