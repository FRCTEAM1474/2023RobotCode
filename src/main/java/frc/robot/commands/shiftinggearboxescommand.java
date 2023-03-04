package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShiftingGearboxesSubsystem;

public class shiftinggearboxescommand extends CommandBase {
    boolean solenoidTrigger = false;
    

    @Override
    public void initialize() {

        //ShiftingGearboxesSubsystem.m_solenoid.toggle();
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

        /*if (!solenoidTrigger){
            solenoidTrigger = true;
          }
          else {
            solenoidTrigger = false;
          }
      
      if (solenoidTrigger) {
        //ShiftingGearboxesSubsystem.m_solenoid.set(true);
        //ShiftingGearboxesSubsystem.m_solenoidTwo.set(false);
        ShiftingGearboxesSubsystem.m_solenoid.set(Value.kForward);
      }
      else {
        //ShiftingGearboxesSubsystem.m_solenoidTwo.set(true);
        //ShiftingGearboxesSubsystem.m_solenoid.set(false);
        ShiftingGearboxesSubsystem.m_solenoid.set(Value.kReverse);
      }*/
        
        }

    @Override
    public void execute() {
       
            /*if (!solenoidTrigger){
              solenoidTrigger = true;
            }
            else {
              solenoidTrigger = false;
            }
        
        if (solenoidTrigger) {
          //ShiftingGearboxesSubsystem.m_solenoid.set(true);
          //ShiftingGearboxesSubsystem.m_solenoidTwo.set(false);
          ShiftingGearboxesSubsystem.m_solenoid.set(Value.kForward);
        }
        else {
          //ShiftingGearboxesSubsystem.m_solenoidTwo.set(true);
          //ShiftingGearboxesSubsystem.m_solenoid.set(false);
          ShiftingGearboxesSubsystem.m_solenoid.set(Value.kReverse);
        }*/
    }
    @Override
    public void end(boolean interup){
        
    }
}
    
