// An auto that tested how we followed paths using ramsete

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Example of a path, kind of like a base for creating a trajectory path. - L.S

package frc.robot.commands.autos;

import java.util.List;

import javax.management.openmbean.OpenMBeanOperationInfo;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.OperatorConstants.AutoConstants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TestPath extends SequentialCommandGroup {

  public Command getAutoCommand(Trajectory trajectory) {
    var autoVoltageConstraint = 
    new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(
      OperatorConstants.ksVolts, 
      OperatorConstants.kvVoltSecondsPerMeter, 
      OperatorConstants.kaVoltSecondsSquaredPerMeter), 
      OperatorConstants.kDriveKinematics, 
      10);

      TrajectoryConfig config =
        new TrajectoryConfig(
          AutoConstants.kMaxSpeedMetersPerSecond,
          AutoConstants.kMaxAccelerationMetersPerSecondSquared)
          .setKinematics(AutoConstants.kDriveKinematics)
          .addConstraint(autoVoltageConstraint);

        Trajectory testTrajectory =
          TrajectoryGenerator.generateTrajectory(
            new Pose2d(0,0, new Rotation2d(0)), 
            List.of(new Translation2d(0.5,0), new Translation2d(1, 0),new Translation2d(1.5,0), new Translation2d(2, 0),new Translation2d(2.5,0)), 
            new Pose2d(4.5,0, new Rotation2d(0)), 
            config);
            

          RamseteCommand ramseteCommand =
            new RamseteCommand(
              trajectory, 
              Robot.drivetrain::getPose, 
              new RamseteController(OperatorConstants.kRamseteB, OperatorConstants.kRamseteZeta),
              new SimpleMotorFeedforward(
                OperatorConstants.ksVolts,
                OperatorConstants.kvVoltSecondsPerMeter,
                OperatorConstants.kaVoltSecondsSquaredPerMeter),
              AutoConstants.kDriveKinematics, 
              Robot.drivetrain::getWheelSpeeds, 
              new PIDController(OperatorConstants.kPDriveVel, 0, 0),
              new PIDController(OperatorConstants.kPDriveVel, 0, 0), 
              Robot.drivetrain::tankDriveVolts, 
              Robot.drivetrain);

            Robot.drivetrain.resetOdometry(trajectory.getInitialPose());
            return ramseteCommand.andThen(() -> Robot.drivetrain.tankDriveVolts(0, 0));
            
  }


  /** Creates a new TestPath. */
  public TestPath(Trajectory trajectory) {

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands( this.getAutoCommand(trajectory) );
    //System.out.println("added the auto command from testpath");
  }
}
