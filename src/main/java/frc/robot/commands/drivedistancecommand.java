package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainsubsystem;
import frc.robot.subsystems.autosubsystem;

public class drivedistancecommand extends CommandBase {
    
    public drivedistancecommand(double direction){
        
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

        // TODO: make a decent auto lol

        
        
    }
    @Override
    public void end(boolean interup){
        drivetrainsubsystem.m_robotDrive.arcadeDrive(0, 0);
    }
}