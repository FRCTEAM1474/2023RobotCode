/*package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainsubsystem;
public class GoodDualSparkMaxCommand extends CommandBase {
    double m_Speed;
    double m_Rotation;
    public GoodDualSparkMaxCommand(double speed, double rotation){
        m_Speed = speed;
        m_Rotation = rotation;

    }
    @Override
    public void initialize() {
        drivetrainsubsystem.setRobotDrive(m_Speed, m_Rotation);
    }
    @Override
    public void execute() {
        drivetrainsubsystem.setRobotDrive(m_Speed, m_Rotation);
    }
    @Override
    public void end(boolean interup){
        drivetrainsubsystem.setRobotDrive(0, 0);
    }
}*/