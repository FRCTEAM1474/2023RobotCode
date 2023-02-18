/*package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainsubsystem;
public class DualSparkMaxCommand2 extends CommandBase {
    double m_LeftSpeed;
    double m_RightSpeed;
    public DualSparkMaxCommand2(double leftSpeed, double rightSpeed){
        m_LeftSpeed = leftSpeed;
        m_RightSpeed = rightSpeed;

    }
    @Override
    public void initialize() {
        drivetrainsubsystem.setSpeedOfLeft(m_LeftSpeed);
        drivetrainsubsystem.setSpeedOfRight(m_RightSpeed);
    }
    @Override
    public void execute() {
        drivetrainsubsystem.setSpeedOfLeft(m_LeftSpeed);
        drivetrainsubsystem.setSpeedOfRight(m_RightSpeed);
    }
    @Override
    public void end(boolean interup){
        drivetrainsubsystem.setSpeedOfLeft(0);
        drivetrainsubsystem.setSpeedOfRight(0);
    }
}*/