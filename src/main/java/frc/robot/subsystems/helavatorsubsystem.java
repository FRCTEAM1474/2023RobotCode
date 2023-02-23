package frc.robot.subsystems;

import java.security.DigestException;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class helavatorsubsystem {
    private final static CANSparkMax flipperMotor = new CANSparkMax(40, MotorType.kBrushless); //needs to do 36 revolutions each way, there will be limit switches. 100:1 gearbox
    private final static CANSparkMax chainextensionMotor = new CANSparkMax(41, MotorType.kBrushless); //limit switches each way, but the "extending" one should not be used (it is for safety, and is farther than it should be). the "unextended" limit switch should be used. encoder usage is ideal/ 70:1 gearbox

    static DigitalInput extendedflipperlimitswitch = new DigitalInput(0);
    static DigitalInput retractedflipperlimitswitch = new DigitalInput(1);
    
    public static void setspeedofFlipperMotor(double speed) {
        flipperMotor.set(speed);
    }

    public static void setspeedofchainextensionMotor(double speed) {
        chainextensionMotor.set(speed);
    }

    public static void toggleFlipper()
}
