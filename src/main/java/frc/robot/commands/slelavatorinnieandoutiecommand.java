package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.helevatorsubsystem;

public class slelavatorinnieandoutiecommand extends CommandBase {
    double m_direction;
    boolean isrightpressed; 
    boolean isleftpressed;
    public slelavatorinnieandoutiecommand(double direction){
        m_direction = direction;
    }
    double autolimits;
    @Override
    public void initialize() {
        
        }

    @Override
    public void execute() {

        autolimits = frc.robot.Constants.OperatorConstants.shelevatorrestriction;

        if (m_direction > 0) {
            if (helevatorsubsystem.extendedsliderlimitswitchstatus()) {
                // We are going up and top limit is tripped so stop
                helevatorsubsystem.setspeedofsliderMotor(0);
            } else if (autolimits == 1 || autolimits == 0){
                // We are going up but top limit is not tripped so go at commanded speed
                //helevatorsubsystem.setspeedofsliderMotor(m_direction);
                helevatorsubsystem.setspeedofsliderMotor(m_direction);
            }
        } else if (autolimits == 2 || autolimits == 0){
            if (!helevatorsubsystem.retractedsliderlimitswitchstatus()) {
                // We are going down and bottom limit is tripped so stop
                helevatorsubsystem.setspeedofsliderMotor(0);
            } else {
                // We are going down but bottom limit is not tripped so go at commanded speed
                //helevatorsubsystem.setspeedofsliderMotor(m_direction);
                helevatorsubsystem.setspeedofsliderMotor(m_direction);
            }
        }
    }
    @Override
    public void end(boolean interup){
        helevatorsubsystem.setspeedofsliderMotor(0);
    }
}