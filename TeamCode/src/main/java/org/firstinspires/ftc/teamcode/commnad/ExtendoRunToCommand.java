package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.CommandBase;

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
    public boolean isFinished(){
        return Math.abs(extendo.getPosition()-position)<100;
    }

    @Override
    public void end(boolean inter){
        extendo.runToPosition(position,1);
    }
}
