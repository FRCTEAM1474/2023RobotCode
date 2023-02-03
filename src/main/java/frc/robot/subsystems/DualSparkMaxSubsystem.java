// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import frc.robot.Constants;

// public class DualSparkMaxSubsystem extends SubsystemBase {
//     CANSparkMax m_motor1 = new CANSparkMax(Constants.OperatorConstants.kCANIDforMotorOne, Constants.OperatorConstants.kMotorType);
//     CANSparkMax m_motor2 = new CANSparkMax(Constants.OperatorConstants.kCanIDforMotorTwo, Constants.OperatorConstants.kMotorType);
//   /** Creates a new ExampleSubsystem. */
//   public DualSparkMaxSubsystem() {
    
    
//     m_motor1.follow(m_motor2);

//     public static void setSpeed(double speed){
//         m_motor2.set(speed);
//     }
   
// }
  
    

//   /**
//    * Example command factory method.
//    *
//    * @return a command
//    */
//   public CommandBase exampleMethodCommand() {
//     // Inline construction of command goes here.
//     // Subsystem::RunOnce implicitly requires `this` subsystem.
//     return runOnce(
//         () -> {
//           /* one-time action goes here */
//         });
//   }

//   /**
//    * An example method querying a boolean state of the subsystem (for example, a digital sensor).
//    *
//    * @return value of some boolean subsystem state, such as a digital sensor.
//    */
//   public boolean exampleCondition() {
//     // Query some boolean state, such as a digital sensor.
//     return false;
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }

//   @Override
//   public void simulationPeriodic() {
//     // This method will be called once per scheduler run during simulation
//   }
// }*/
