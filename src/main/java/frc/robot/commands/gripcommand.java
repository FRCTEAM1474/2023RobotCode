package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.grippersubsystem;

public class gripcommand extends CommandBase {
    
    

    @Override
    public void initialize() {
        /*if (!testlimitswitchessubsystem.leftlimitswitchstatus()) {
            isleftpressed = false;
        }
        if (!testlimitswitchessubsystem.rightlimitswitchstatus()) {
            isrightpressed = false;
        }
        if (testlimitswitchessubsystem.leftlimitswitchstatus()) {
            isleftpressed = true;
        }
        if (testlimitswitchessubsystem.rightlimitswitchstatus()) {
            isrightpressed = true;
        }*/
        
        }

    @Override
    public void execute() {
       
            
          grippersubsystem.m_solenoid.set(true);
          grippersubsystem.m_solenoidTwo.set(false);
       
    }
    @Override
    public void end(boolean interup){
        
    }
}
    
