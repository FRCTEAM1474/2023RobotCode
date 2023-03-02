// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class autosubsystem extends SubsystemBase {
  //double meterstotravel;
  //double degreestoturn;
  static double bothsidestickstotravel;
  static double lefttickstotravel;
  static double righttickstotravel;
  

  /** Creates a new ExampleSubsystem. */
  public autosubsystem () {
   
    

  }
  
  public static double drivestraight(double meterstotravel){
    //converts meters into ticks to travel
    // TODO: remember to factor in the 3:1 ratio for encoder.
    //wheels spin 3 times for every one rotation of the encoder shaft.
    bothsidestickstotravel = 
    return bothsidestickstotravel;
    
}
public static double[] turn(double degreestoturn){
     
     double[] leftandrighttickstotravel = {lefttickstotravel, righttickstotravel}

    return leftandrighttickstotravel;
}

    
   
}
  
    

 