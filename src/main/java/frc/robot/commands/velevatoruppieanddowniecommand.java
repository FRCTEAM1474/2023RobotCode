package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.velevatorsubsystem;

public class velevatoruppieanddowniecommand extends CommandBase {
    double m_direction;
    
    boolean isrightpressed; 
    boolean isleftpressed;
    public velevatoruppieanddowniecommand(double direction){
        m_direction = direction;
        //m_topposition = topposition;
    }
    

    @Override
    public void initialize() {
        
        }

    @Override
    public void execute() {

        double m_topposition = frc.robot.Constants.OperatorConstants.topposition;

        if (m_direction > 0) {
            if (!velevatorsubsystem.extendedvelevatorlimitswitchstatus() || velevatorsubsystem.velevatorencoderposition() > m_topposition ) {
                // We are going up and top limit is tripped so stop
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            } else {
                // We are going up but top limit is not tripped so go at commanded speed
                velevatorsubsystem.setspeedofVelevatorMotors(m_direction);
            }
        } else {
            if (!velevatorsubsystem.retractedvelevatorlimitswitchstatus() || velevatorsubsystem.velevatorencoderposition() < 1) {
                // We are going down and bottom limit is tripped so stop
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            } else {
                // We are going down but bottom limit is not tripped so go at commanded speed
                velevatorsubsystem.setspeedofVelevatorMotors(m_direction);
            }
        }
    }
    @Override
    public void end(boolean interup){
        velevatorsubsystem.setspeedofVelevatorMotors(0);
    }
}