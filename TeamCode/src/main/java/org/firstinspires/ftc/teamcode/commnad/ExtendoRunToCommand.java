package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;

public class ExtendoRunToCommand extends CommandBase {

    public ExtendoSubsystem extendo;
    int position;
    public ExtendoRunToCommand(ExtendoSubsystem extendo, int position){
        this.extendo=extendo;
        this.position=position;
        addRequirements(extendo);
    }

    @Override
    public void initialize(){

        extendo.runToPosition(position, 1);

    }

    @Override
    public void execute(){
//        if(position == 0){
//            //if the target position is 0, the encoders are not at 9, an the velocity is 0
//            if(extendo.getPosition()>10&&extendo.getPosition()<500 && extendo.getLeftVelocity()==0 && extendo.getRightVelocity()==0){
//
//                extendo.resetEncoders();
//            }
//        }
    }

    @Override
    public boolean isFinished(){
        return Math.abs(extendo.getPosition()-position)<100;
    }

    @Override
    public void end(boolean inter){
        extendo.runToPosition(position,.5);
    }
}
