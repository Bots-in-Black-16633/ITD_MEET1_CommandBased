package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;

public class PivotRunToCommand extends CommandBase {

    public PivotSubsystem pivot;
    int position;
    public PivotRunToCommand(PivotSubsystem pivot, int position){
        this.pivot=pivot;
        this.position=position;
        addRequirements(pivot);
    }


    @Override
    public void initialize(){
        pivot.runToPosition(position, 1);
    }

    @Override
    public boolean isFinished(){
        return Math.abs(pivot.getPosition()-position)<100;
    }

    @Override
    public void end(boolean inter){
        pivot.runToPosition(position,1);
    }
}
