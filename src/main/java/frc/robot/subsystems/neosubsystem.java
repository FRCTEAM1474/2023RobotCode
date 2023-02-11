// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class neosubsystem extends SubsystemBase {
    public static CANSparkMax m_testneo = new CANSparkMax(Constants.OperatorConstants.kCANIDforTestNeo, Constants.OperatorConstants.kMotorType);
  /** Creates a new ExampleSubsystem. */
  public neosubsystem() {
  }
    

    public static void setSpeed(double neospeed){
        double m_neospeed = neospeed;
        m_testneo.set(m_neospeed);
    }
   
}
