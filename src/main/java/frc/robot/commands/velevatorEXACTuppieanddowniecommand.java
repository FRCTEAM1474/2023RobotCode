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

        if (toplimitswitchstatus) {

        if (currentposition < positiontogoto) {
            if (toplimitswitchstatus) {
                if (currentposition != positiontogoto) {
                    velevatorsubsystem.setspeedofVelevatorMotors(0.4);
                }
                
            }
            else {
                velevatorsubsystem.setspeedofVelevatorMotors(0);
            }

        }
        if (positiontogoto < currentposition) {
            if (bottomlimitswitchstatus) {
                if (currentposition != positiontogoto) {
                    velevatorsubsystem.setspeedofVelevatorMotors(-0.4);
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
    else {
        velevatorsubsystem.setspeedofVelevatorMotors(0);
    }
}

    @Override
    public void end(boolean interup) {
        velevatorsubsystem.setspeedofVelevatorMotors(0);
    }

}
