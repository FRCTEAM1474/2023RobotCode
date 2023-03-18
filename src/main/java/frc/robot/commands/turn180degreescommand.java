package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainsubsystem;
//import frc.robot.subsystems.autosubsystem;

public class turn180degreescommand extends CommandBase {
    
    public turn180degreescommand(){
        
    }
    @Override
    public void initialize() {
        
        }

    @Override
    public void execute() {

        // TODO: make a decent auto lol

        double currentm_motorRightEncoderPosition = drivetrainsubsystem.EncoderRightPOSinmeters();

          double currentm_motorLeftEncoderPosition = drivetrainsubsystem.EncoderLeftPOSinmeters();

          if (currentm_motorLeftEncoderPosition < 0.426 && currentm_motorRightEncoderPosition < 0.426) {

            drivetrainsubsystem.m_robotDrive.arcadeDrive(0.05, 0.65);

            //System.out.println("firstprint");

          }


          else if (currentm_motorLeftEncoderPosition < 0.426) {

              drivetrainsubsystem.m_robotDrive.tankDrive(0.65, 0);

              //System.out.println("secondprint");

          }

          else if (currentm_motorRightEncoderPosition < 0.426) {

              drivetrainsubsystem.m_robotDrive.tankDrive(0, 0.65);         

              //System.out.println("thirdprint");

          }

          else {

            //System.out.println("STOPPING");

            drivetrainsubsystem.m_robotDrive.arcadeDrive(0, 0);

          }

          //System.out.println(currentm_motorLeftEncoderPosition);

          //System.out.println(currentm_motorRightEncoderPosition);
        
    }
    @Override
    public void end(boolean interup){
        drivetrainsubsystem.m_robotDrive.arcadeDrive(0, 0);
    }
}