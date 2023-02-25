package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.helavatorsubsystem;

public class slelavatorinnieandoutiecommand extends CommandBase {
    double m_direction;
    boolean isrightpressed; 
    boolean isleftpressed;
    public slelavatorinnieandoutiecommand(double direction){
        m_direction = direction;
    }
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
        if (m_direction > 0) {
            if (!helavatorsubsystem.extendedsliderlimitswitchstatus()) {
                // We are going up and top limit is tripped so stop
                helavatorsubsystem.setspeedofsliderMotor(0);
            } else {
                // We are going up but top limit is not tripped so go at commanded speed
                helavatorsubsystem.setspeedofsliderMotor(m_direction);
            }
        } else {
            if (!helavatorsubsystem.retractedsliderlimitswitchstatus()) {
                // We are going down and bottom limit is tripped so stop
                helavatorsubsystem.setspeedofsliderMotor(0);
            } else {
                // We are going down but bottom limit is not tripped so go at commanded speed
                helavatorsubsystem.setspeedofsliderMotor(m_direction);
            }
        }
    }
    @Override
    public void end(boolean interup){
        helavatorsubsystem.setspeedofsliderMotor(0);
    }
}