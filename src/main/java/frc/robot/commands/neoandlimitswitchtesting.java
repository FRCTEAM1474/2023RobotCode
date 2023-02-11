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
        /*if (testlimitswitchessubsystem.leftlimitswitchstatus()){
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
        }*/
        if (m_direction > 0) {
            if (!testlimitswitchessubsystem.leftlimitswitchstatus()) {
                // We are going up and top limit is tripped so stop
                neosubsystem.setSpeed(0);
            } else {
                // We are going up but top limit is not tripped so go at commanded speed
                neosubsystem.setSpeed(m_direction);
            }
        } else {
            if (!testlimitswitchessubsystem.rightlimitswitchstatus()) {
                // We are going down and bottom limit is tripped so stop
                neosubsystem.setSpeed(0);
            } else {
                // We are going down but bottom limit is not tripped so go at commanded speed
                neosubsystem.setSpeed(m_direction);
            }
        }
    }
    @Override
    public void end(boolean interup){
        neosubsystem.setSpeed(0);
    }
}