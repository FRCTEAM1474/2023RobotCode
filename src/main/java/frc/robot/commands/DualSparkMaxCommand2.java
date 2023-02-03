package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DualSparkMaxSubsystem2;
public class DualSparkMaxCommand2 extends CommandBase {
    double m_Speed;
    public DualSparkMaxCommand2(double speed){
        m_Speed = speed;
    }
    @Override
    public void initialize() {
        DualSparkMaxSubsystem2.setSpeed(m_Speed);
    }
    @Override
    public void execute() {
        DualSparkMaxSubsystem2.setSpeed(m_Speed);
    }
    @Override
    public void end(boolean interup){
        DualSparkMaxSubsystem2.setSpeed(0);
    }
}