package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShiftingGearboxesSubsystem;

public class shiftinggearboxescommand extends CommandBase {
    boolean solenoidTrigger = false;
    

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
       
            if (!solenoidTrigger){
              solenoidTrigger = true;
            }
            else {
              solenoidTrigger = false;
            }
        
        if (solenoidTrigger) {
          ShiftingGearboxesSubsystem.m_solenoid.set(true);
          ShiftingGearboxesSubsystem.m_solenoidTwo.set(false);
        }
        else {
          ShiftingGearboxesSubsystem.m_solenoidTwo.set(true);
          ShiftingGearboxesSubsystem.m_solenoid.set(false);
        }
    }
    @Override
    public void end(boolean interup){
        
    }
}
    
