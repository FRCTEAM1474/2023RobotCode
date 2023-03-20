// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class blinkinsubsystem extends SubsystemBase {
    public static PWMSparkMax m_blinkinMax = new PWMSparkMax(1);
  /** Creates a new ExampleSubsystem. */
  public blinkinsubsystem() {
  }
    

    public static void setSpeed(String pattern){
        if (pattern == "Violet") {
            m_blinkinMax.set(0.91);
        }
        if (pattern == "Yellow") {
            m_blinkinMax.set(0.69); //nice ( ͡° ͜ʖ ͡°)
        }
        if (pattern == "Rainbow") {
            m_blinkinMax.set(-0.97);
        }
        

        
    }
   
}
