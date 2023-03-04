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
        
        double previousEncoderDelta;

    @Override
    public void execute() {

        // TODO: make a decent auto lol

        double currentm_motorRightEncoderPosition = drivetrainsubsystem.EncoderRightPOSinmeters();

          double currentm_motorLeftEncoderPosition = drivetrainsubsystem.EncoderLeftPOSinmeters();

          System.out.println("MotorRightOutput " + currentm_motorRightEncoderPosition);

          System.out.println("MotorLeftOutput " + currentm_motorLeftEncoderPosition);

          double currentEncoderDelta = currentm_motorRightEncoderPosition - (-currentm_motorLeftEncoderPosition);

          double autonomousDrivetrainRotation = (((currentEncoderDelta) / 4000));

          //System.out.println("autonomousDrivetrainRotation " + autonomousDrivetrainRotation);
        if (currentm_motorLeftEncoderPosition < distancetodrive && currentm_motorRightEncoderPosition < distancetodrive){
            drivetrainsubsystem.m_robotDrive.arcadeDrive(0.10, -autonomousDrivetrainRotation, true); //change value to 0.65 later
        }
          previousEncoderDelta = currentEncoderDelta;

          //IntakeSubsystem.setSpeed(-1);

        
        
    }
    @Override
    public void end(boolean interup){
        drivetrainsubsystem.m_robotDrive.arcadeDrive(0, 0);
    }
}