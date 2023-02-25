package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.velevatorsubsystem;

public class velevatorEXACTuppieanddowniecommand extends CommandBase {
    double positiontogoto;
    double currentposition;
    boolean bottomlimitswitchstatus;
    boolean toplimitswitchstatus;

    public velevatorEXACTuppieanddowniecommand (double pos) {
        positiontogoto = pos;
    }

    @Override
    public void initialize() {

        

        

    }

    @Override
    public void execute() {
        currentposition = velevatorsubsystem.velevatorencoderposition();
        bottomlimitswitchstatus = velevatorsubsystem.retractedvelevatorlimitswitchstatus();
        toplimitswitchstatus = velevatorsubsystem.extendedvelevatorlimitswitchstatus();


        /*if (m_direction > 0) {
            if (!velevatorsubsystem.extendedvelevatorlimitswitchstatus()) {
                // We are going up and top limit is tripped so stop
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            } else {
                // We are going up but top limit is not tripped so go at commanded speed
                velevatorsubsystem.setspeedofVelevatorMotors(m_direction);
            }
        } else {
            if (!velevatorsubsystem.retractedvelevatorlimitswitchstatus()) {
                // We are going down and bottom limit is tripped so stop
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            } else {
                // We are going down but bottom limit is not tripped so go at commanded speed
                velevatorsubsystem.setspeedofVelevatorMotors(m_direction);
            }
        } */
        if (currentposition < positiontogoto) {
            if (!toplimitswitchstatus) {
                if (currentposition != positiontogoto) {
                    velevatorsubsystem.setspeedofVelevatorMotors(0.1);
                }
                
            }
            else {
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            }

        }
        if (positiontogoto < currentposition) {
            if (!bottomlimitswitchstatus) {
                if (currentposition != positiontogoto) {
                    velevatorsubsystem.setspeedofVelevatorMotors(-0.1);
                }
                
            }
            else {
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            }
        }
        else {
            velevatorsubsystem.setspeedofVelevatorMotors(0);
        }
    }
}
