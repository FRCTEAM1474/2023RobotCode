// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import frc.robot.subsystems.drivetrainsubsystem;
//import frc.robot.Constants;

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
    //4.5 meters backwards
    bothsidestickstotravel = ((meterstotravel / (Math.PI * 6) * (4096/3)));
    return bothsidestickstotravel;
    
}
public static double[] turn(double degreestoturn){
    //21.375 inches track width
    //each side travels 0.426 meters for 180 degrees
     
     double[] leftandrighttickstotravel = {lefttickstotravel, righttickstotravel};

    return leftandrighttickstotravel;
}
public static void turn180degrees() {

}

    
   
}
  
    

 