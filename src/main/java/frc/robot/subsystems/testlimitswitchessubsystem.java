// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class testlimitswitchessubsystem extends SubsystemBase {
    static DigitalInput leftlimitswitch = new DigitalInput(0);
    static DigitalInput rightlimitswitch = new DigitalInput(1);
    
    
  /** Creates a new ExampleSubsystem. */
  public testlimitswitchessubsystem() {}

  public static boolean leftlimitswitchstatus() {
    return leftlimitswitch.get();
  }
  public static boolean rightlimitswitchstatus() {
    return rightlimitswitch.get();
  }
    

    
   
}