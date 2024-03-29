package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.helevatorsubsystem;

public class flipperinnieandoutiecommand extends CommandBase {
    double m_direction;
    boolean isrightpressed; 
    boolean isleftpressed;
    public flipperinnieandoutiecommand(double direction){
        m_direction = direction;
    }
    @Override
    public void initialize() {
        
        }

    @Override
    public void execute() {
        if (m_direction < 0) {
            if (!helevatorsubsystem.extendedflipperlimitswitchstatus() /*|| helevatorsubsystem.flipperencoderposition() > 25*/) {
                // We are going up and top limit is tripped so stop
                helevatorsubsystem.setspeedofFlipperMotor(0);
            } else {
                // We are going up but top limit is not tripped so go at commanded speed
                helevatorsubsystem.setspeedofFlipperMotor(m_direction);
            }
        } else {
            if (!helevatorsubsystem.retractedflipperlimitswitchstatus() /*|| helevatorsubsystem.flipperencoderposition() < 5*/) {
                // We are going down and bottom limit is tripped so stop
                helevatorsubsystem.setspeedofFlipperMotor(0);
            } else {
                // We are going down but bottom limit is not tripped so go at commanded speed
                helevatorsubsystem.setspeedofFlipperMotor(m_direction);
            }
        }
    }
    @Override
    public void end(boolean interup){
        helevatorsubsystem.setspeedofFlipperMotor(0);
    }
}