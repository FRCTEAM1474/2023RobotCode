package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class helevatorsubsystem extends SubsystemBase {
    public static CANSparkMax flipperMotor = new CANSparkMax(40, MotorType.kBrushless); //needs to do 36 revolutions each way, there will be limit switches. 100:1 gearbox
    public static CANSparkMax chainextensionMotor = new CANSparkMax(41, MotorType.kBrushless); //limit switches each way, but the "extending" one should not be used (it is for safety, and is farther than it should be). the "unextended" limit switch should be used. encoder usage is ideal/ 70:1 gearbox
    public static CANSparkMax sliderMotor = new CANSparkMax(42, MotorType.kBrushless);

    static DigitalInput extendedflipperlimitswitch = new DigitalInput(0);
    static DigitalInput retractedflipperlimitswitch = new DigitalInput(1);
    static DigitalInput extendedhelevatorlimitswitch = new DigitalInput(2);
    static DigitalInput retractedhelevatorlimitswitch = new DigitalInput(3);
    static DigitalInput extendedsliderlimitswitch = new DigitalInput(4);
    static DigitalInput retractedsliderlimitswitch = new DigitalInput(5);
    public helevatorsubsystem() {
        
    }

    public static void setspeedofFlipperMotor(double speed) {
        flipperMotor.set(speed);
    }

    public static void setspeedofchainextensionMotor(double speed) {
        chainextensionMotor.set(speed);
    }
    public static void setspeedofsliderMotor(double speed) {
        sliderMotor.set(speed);
    }

    public static boolean extendedflipperlimitswitchstatus() {
    return extendedflipperlimitswitch.get();
  }
  public static boolean retractedflipperlimitswitchstatus() {
    return retractedflipperlimitswitch.get();
  } 
    public static boolean extendedhelevatorlimitswitchstatus() {
        return extendedhelevatorlimitswitch.get();

    }
    public static boolean retractedelavatorlimitswitchstatus() {
        return retractedflipperlimitswitch.get();
    }
    public static boolean extendedsliderlimitswitchstatus() {
        return extendedsliderlimitswitch.get();
    }
    public static boolean retractedsliderlimitswitchstatus() {
        return retractedsliderlimitswitch.get();
    }
}
