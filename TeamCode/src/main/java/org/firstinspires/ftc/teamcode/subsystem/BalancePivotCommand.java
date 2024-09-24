package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.robocol.Command;

import java.util.function.DoubleSupplier;

public class BalancePivotCommand extends CommandBase {

    PIDController controller = new PIDController(.1,0,0);
    DoubleSupplier tilt;
    PivotSubsystem pivot;
    public BalancePivotCommand(PivotSubsystem pivot, DoubleSupplier tilt){
        controller.setSetPoint(0);
        this.tilt=tilt;
        this.pivot = pivot;
    }

    @Override
    public void execute(){
        pivot.setPower(controller.calculate(tilt.getAsDouble()));
    }

    @Override
    public boolean isFinished(){
        return Math.abs(tilt.getAsDouble())>.5;
    }

    @Override
    public void end(boolean inter){
        pivot.runToPosition(pivot.getPosition(), 1);
    }
}
