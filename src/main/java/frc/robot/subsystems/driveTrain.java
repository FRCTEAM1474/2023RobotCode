// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPRamseteCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import frc.robot.frc.Utils.Interfaces.beanieDriveTrain;

import frc.robot.frc.Utils.logging.Logger;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Command;

public class driveTrain extends beanieDriveTrain {



 
  private final Field2d m_field = new Field2d();
  

    // static CANSparkMax left1 = new CANSparkMax(18, MotorType.kBrushless);
    // static CANSparkMax left2 = new CANSparkMax(23, MotorType.kBrushless);

    // static CANSparkMax right1 = new CANSparkMax(24, MotorType.kBrushless);
    // static CANSparkMax right2 = new CANSparkMax(12, MotorType.kBrushless);


    /*static CANSparkMax left1 = new CANSparkMax(9, MotorType.kBrushless);
    static CANSparkMax left2 = new CANSparkMax(24, MotorType.kBrushless);

    static CANSparkMax right1 = new CANSparkMax(20, MotorType.kBrushless);
    static CANSparkMax right2 = new CANSparkMax(12, MotorType.kBrushless);*/

    private final static WPI_TalonSRX m_motor1 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorOne);
  private final static WPI_TalonSRX m_motor2 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorTwo);
  private final static WPI_TalonSRX m_motor3 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorThree);
  private final static WPI_TalonSRX m_motor4 = new WPI_TalonSRX(Constants.OperatorConstants.kCANIDforMotorFour);

    



  



  DifferentialDriveKinematics dKinematics = new DifferentialDriveKinematics( Units.inchesToMeters(21.12)); //tbd
  DifferentialDrivePoseEstimator dEstimator = new DifferentialDrivePoseEstimator(dKinematics, getRotation(), leftDistance(), rightDistance(), new Pose2d(1.0, 3.0 ,getRotation()));
  private static driveTrain mDriveTrain = new driveTrain();
    private static double kv = 1.055;
    private static double ka = .27947;
    private static double ks = .2432 ;
    public  static HashMap<String, Command> eventMap = new HashMap<>();
    

    

  /** Creates a new driveTrain. */
  public driveTrain() {
    super(new AHRS() , new MotorControllerGroup(m_motor1, m_motor2), new MotorControllerGroup(m_motor3, m_motor4));     
    Shuffleboard.getTab("primary").add(m_field);
  }



  
  public static driveTrain getInstance() {
      return mDriveTrain;
  }

  @Override
  public Pose2d currentPose() {
      return dEstimator.getEstimatedPosition();
  }

      /**
     * @return in meters
     */
  @Override
  public double rightDistance() {
      // TODO Auto-generated method stub

      return Units.inchesToMeters((m_motor3.getSelectedSensorPosition() / 4096) * Math.PI * 6);
  }


  // private double wheelAverage(wheelLinearDistance[] wheelDistances){

  //   double average = 0;
  //   for(wheelLinearDistance wheelDistance : wheelDistances){
  //     average += wheelDistance.getDistanceMeters();
  //   }
  //   average = average/wheelDistances.length;
  //   return average;

  // }

    /**
     * @return in meters
     */
    @Override
    public double leftDistance() {
        // TODO Auto-generated method stub
        //return -Units.inchesToMeters((left1.getEncoder().getPosition() * 6 * Math.PI)/ 8.01);
        return -Units.inchesToMeters((m_motor2.getSelectedSensorPosition() / 4096) * Math.PI * 6);
    }

    public Supplier<Pose2d> getPoseSupplier(){
        Supplier<Pose2d> s = () -> dEstimator.getEstimatedPosition();
        return s;
    }

    /**
     * 
     * @return dKinematics is in meters
     */
    public DifferentialDriveKinematics getKinematics() {
        return dKinematics;
    }

    public void resetPose(Pose2d newPose){
        
        dEstimator.resetPosition(getRotation(), leftDistance(), rightDistance(), newPose);
    }



    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        dEstimator.update(getRotation(), leftDistance(), rightDistance());
        System.out.println("left distance" + leftDistance());
        System.out.println("right distance:"+ rightDistance());
        System.out.println(dEstimator.getEstimatedPosition().getX());
        System.out.println(dEstimator.getEstimatedPosition().getY());


        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("leftDistance," + leftDistance() + ",");
        sBuilder.append("rightDistance," + rightDistance()+ ",");
        sBuilder.append("angle," + getRotation().getDegrees() + ",");
        sBuilder.append("leftVelocity," + getLeftVelocity() + ",");
        sBuilder.append("rightVelocity," + getRightVelocity() + ",");
        sBuilder.append("displacement" + Math.sqrt(Math.pow(dEstimator.getEstimatedPosition().getX(), 2) + Math.pow(dEstimator.getEstimatedPosition().getY(), 2)) + ",");
        
        Logger.info(sBuilder.toString());

        Logger.flush();
    }

    @Override
    public void autonomousSetup() {
        // TODO Auto-generated method stub
        
        
        
    }

    public BiConsumer<Double, Double> getBiConsumer() {
        BiConsumer<Double, Double> biC = (leftVoltage, rightVoltage) -> {
          leftControllerGroup.setVoltage(-leftVoltage);
          rightControllerGroup.setVoltage(-rightVoltage);
          super.mDrive.feed();  
        };
        return biC;
    }

    @Override
    public void teleopSetup() {
        // TODO Auto-generated method stub
        //driveTrain.getInstance().setDefaultCommand(new joystickDrive(driveTrain.getInstance(), RobotContainer.driverController));
    }


    public void updatePoseVisually(Pair<Pose2d, Double> pose) {

        dEstimator.addVisionMeasurement(pose.getFirst(), pose.getSecond());
        m_field.setRobotPose(dEstimator.getEstimatedPosition());
      

    }

    public static void teleOp(double speed, double rotation){
        mDrive.arcadeDrive(speed, rotation);
    }
    /**
     * 
     * @return in rotations per second
     */
    public double getLeftVelocity(){
        return -(m_motor2.getSelectedSensorVelocity() * 10);
        }
        /**
         * 
         * @return in rotations per second
         */
    public double getRightVelocity(){
        return m_motor3.getSelectedSensorVelocity() * 10;
        }
    




    /**
     * 
     * @return in meters per second
     */
  public Supplier<DifferentialDriveWheelSpeeds> getWheelSpeedSupplier(){
    Supplier<DifferentialDriveWheelSpeeds> s = () -> new DifferentialDriveWheelSpeeds((Units.inchesToMeters(getLeftVelocity() * 6*Math.PI)/8.01), (Units.inchesToMeters(getRightVelocity()* 6*Math.PI)/8.01));
    return s;
 }
 public Command followTrajectoryCommand(PathPlannerTrajectory traj, boolean isFirstPath) {

  if(isFirstPath){
    this.resetPose(traj.getInitialPose());
  }

     PPRamseteCommand controller1 = new PPRamseteCommand(
        traj,
        driveTrain.getInstance().getPoseSupplier(),
        new RamseteController(),
        new SimpleMotorFeedforward(ks, kv, ka),
        driveTrain.getInstance().getKinematics(),
        driveTrain.getInstance().getWheelSpeedSupplier(),
        new PIDController(3.8826, 0, 0), // Left controller. Tune these values for your robot. Leaving them 0 will only use feedforwards.
        new PIDController(3.8826, 0, 0),
        driveTrain.getInstance().getBiConsumer(),
        this
        );

    return controller1;
 }




}