// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShiftingGearboxesSubsystem extends SubsystemBase {
    public final static Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    public final static Solenoid m_solenoidTwo = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
  /** Creates a new ExampleSubsystem. */
  public ShiftingGearboxesSubsystem () {
    
    
    

    

  }
  
 
    
   
}
  
    

 