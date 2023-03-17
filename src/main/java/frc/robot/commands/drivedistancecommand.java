package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainsubsystem;
//import frc.robot.subsystems.autosubsystem;

public class drivedistancecommand extends CommandBase {
    double distancetodrive;
    
    public drivedistancecommand(double distancetodriveinmeters){
        distancetodrive = distancetodriveinmeters;
    }
    @Override
    public void initialize() {

        drivetrainsubsystem.m_robotDrive.setSafetyEnabled(false);
        
        }
        
        double previousEncoderDelta;

        

    @Override
    public void execute() {

        // TODO: make a decent auto lol

        drivetrainsubsystem.m_robotDrive.setSafetyEnabled(false);

        drivetrainsubsystem.m_robotDrive.arcadeDrive(-0.1, 0);
        
    }
    @Override
    public void end(boolean interup){
        //drivetrainsubsystem.m_robotDrive.arcadeDrive(0, 0);
    }
}