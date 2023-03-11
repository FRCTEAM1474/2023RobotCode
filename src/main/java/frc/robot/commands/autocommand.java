package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.helevatorsubsystem;

public class autocommand extends CommandBase {
    String auto;
    boolean isrightpressed; 
    boolean isleftpressed;

    public autocommand(String autotodo){
        auto = autotodo;
    }

    @Override
    public void initialize() {

        
        
        }

    @Override
    public void execute() {

        SequentialCommandGroup auto() {

        switch (auto) {
            case kCustomAuto:
              // Put custom auto code here
              return new gripcommand().andThen(new velevatorEXACTuppieanddowniecommand(58).andThen(new slelavatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(0.1).andThen(new helevatorinnieandoutiecommand(-0.1).andThen(new ungripcommand().andThen(new helevatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(-0.1).andThen(new slelavatorinnieandoutiecommand(-0.1).andThen(new velevatorEXACTuppieanddowniecommand(5).andThen(new drivedistancecommand(-4).andThen(new drivedistancecommand(1.841))))))))))));
              break;
            case kDefaultAuto:
              // Put default auto code here
              return new gripcommand().andThen(new velevatorEXACTuppieanddowniecommand(58).andThen(new slelavatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(0.1).andThen(new helevatorinnieandoutiecommand(-0.1).andThen(new ungripcommand().andThen(new helevatorinnieandoutiecommand(0.1).andThen(new flipperinnieandoutiecommand(-0.1).andThen(new slelavatorinnieandoutiecommand(-0.1).andThen(new velevatorEXACTuppieanddowniecommand(5).andThen(new drivedistancecommand(-4.5).andThen(new turn180degreescommand())))))))))));
              break;
            case kBackUp:
              return new drivedistancecommand(-4.5).andThen(new ungripcommand());
              break;
            case kNoAuto:
            default:
              System.out.println("depressed coder");
            break;
            case kDropGamePiece:
              return new gripcommand().andThen(new flipperinnieandoutiecommand(0.1).andThen(new ungripcommand()));
            break;
            case kDropandBackUp:
              return new ungripcommand().andThen(new drivedistancecommand(-4.5));
            break;
            case kDropandDoNothing:
              new ungripcommand();
              //this doesn't do anything, it just drops it because the compresser
              //builds pressure and "open" is the default state of the gripper
            break;
          }

        }
        
    }

    @Override
    public void end(boolean interup){
        
    }

}