package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class velevatorsubsystem extends SubsystemBase {
    public static CANSparkMax leftvelevationmotor = new CANSparkMax(43, MotorType.kBrushless);
    public static CANSparkMax rightvelevationmotor = new CANSparkMax(44, MotorType.kBrushless);

    static DigitalInput extendedvelevatorlimitswitch = new DigitalInput(6);
    static DigitalInput retractedvelevatorlimitswitch = new DigitalInput(7);

    public velevatorsubsystem() {
        
    }

    public static void setspeedofVelevatorMotors(double speed) {
        leftvelevationmotor.set(speed);
        rightvelevationmotor.set(-speed);
    }

    public static boolean extendedvelevatorlimitswitchstatus() {
        return extendedvelevatorlimitswitch.get();
    }

    public static boolean retractedvelevatorlimitswitchstatus() {
        return retractedvelevatorlimitswitch.get();
    }

    public static double velevatorencoderposition() {
        RelativeEncoder velevationencoder = leftvelevationmotor.getEncoder();
        return velevationencoder.getPosition();
    }

    public static void zerovelevatorencoder() {
        leftvelevationmotor.getEncoder().setPosition(0);
    }

}
