package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.grippersubsystem;

public class grippercommand extends CommandBase {
    boolean solenoidTrigger = false;
    

    @Override
    public void initialize() {
        
        }

    @Override
    public void execute() {
       
            if (!solenoidTrigger){
              solenoidTrigger = true;
            }
            else {
              solenoidTrigger = false;
            }
        
        if (solenoidTrigger) {
          grippersubsystem.m_solenoid.set(true);
          grippersubsystem.m_solenoidTwo.set(false);
        }
        else {
          grippersubsystem.m_solenoidTwo.set(true);
          grippersubsystem.m_solenoid.set(false);
        }
    }
    @Override
    public void end(boolean interup){
        
    }
}
    
