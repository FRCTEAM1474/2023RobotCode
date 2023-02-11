package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.neosubsystem;
import frc.robot.subsystems.testlimitswitchessubsystem;

public class neoandlimitswitchtesting extends CommandBase {
    double m_direction;
    boolean isrightpressed;
    boolean isleftpressed;
    public neoandlimitswitchtesting(double direction){
        m_direction = direction;
    }
    @Override
    public void initialize() {
        if (!testlimitswitchessubsystem.leftlimitswitchstatus()) {
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
        }
        
        }

    @Override
    public void execute() {
        if (testlimitswitchessubsystem.leftlimitswitchstatus()){
            if (m_direction > 0 ) {
                neosubsystem.setSpeed(m_direction);
            }
            
        }

        if (testlimitswitchessubsystem.rightlimitswitchstatus()) {
            if (m_direction < 0) {
                neosubsystem.setSpeed(m_direction);
            }
            
        }
        if (!testlimitswitchessubsystem.rightlimitswitchstatus() && !testlimitswitchessubsystem.leftlimitswitchstatus()) {
            neosubsystem.setSpeed(m_direction);
        }
    }
    @Override
    public void end(boolean interup){
        neosubsystem.setSpeed(0);
    }
}