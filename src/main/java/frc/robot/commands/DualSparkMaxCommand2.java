package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DualSparkMaxSubsystem2;
public class DualSparkMaxCommand2 extends CommandBase {
    double m_LeftSpeed;
    double m_RightSpeed;
    public DualSparkMaxCommand2(double leftSpeed, double rightSpeed){
        m_LeftSpeed = leftSpeed;
        m_RightSpeed = rightSpeed;

    }
    @Override
    public void initialize() {
        DualSparkMaxSubsystem2.setSpeedOfLeft(m_LeftSpeed);
        DualSparkMaxSubsystem2.setSpeedOfRight(m_RightSpeed);
    }
    @Override
    public void execute() {
        DualSparkMaxSubsystem2.setSpeedOfLeft(m_LeftSpeed);
        DualSparkMaxSubsystem2.setSpeedOfRight(m_RightSpeed);
    }
    @Override
    public void end(boolean interup){
        DualSparkMaxSubsystem2.setSpeedOfLeft(0);
        DualSparkMaxSubsystem2.setSpeedOfRight(0);
    }
}