package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.grippersubsystem;

public class ungripcommand extends CommandBase {
    
    

    @Override
    public void initialize() {
        
        }

    @Override
    public void execute() {
       
            
          grippersubsystem.m_solenoidTwo.set(true);
          grippersubsystem.m_solenoid.set(false);
        
    }
    @Override
    public void end(boolean interup){
        
    }
}
    
