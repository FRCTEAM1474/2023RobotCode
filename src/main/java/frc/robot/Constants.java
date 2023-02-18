// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kDriverControllerPort2 = 1;
    public static final MotorType kMotorType = MotorType.kBrushless;
    public static int kCANIDforMotorOne = 33;
    public static int kCANIDforMotorTwo = 30;
    public static int kCANIDforMotorThree = 32;
    public static int kCANIDforMotorFour = 31;
    public static int kCANIDforTestNeo = 45;
    public static int kAlternateQuadratureValue = 4096;
    public static int kSolenoidButton = 11;

    public static final double ksVolts = 0.58029;
    public static final double kvVoltSecondsPerMeter = 6.8565;
    public static final double kaVoltSecondsSquaredPerMeter = 0.87514;

    public static final double kTrackwidthMeters = 0.5715;

    public static final DifferentialDriveKinematics kDriveKinematics =
      new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kPDriveVel = 3.8826;

    public static final class AutoConstants {
      public static final double kMaxSpeedMetersPerSecond = 2.588;
      //public static final double kMaxSpeedMetersPerSecond = 0.5;
      public static final double kMaxAccelerationMetersPerSecondSquared = 0.1;
  
      // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
      public static final double kRamseteB = 2;
      public static final double kRamseteZeta = 0.7;
    }
    
  }
}
