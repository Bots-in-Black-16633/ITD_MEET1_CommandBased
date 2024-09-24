package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class HighBasketDeposit extends SequentialCommandGroup {


    public HighBasketDeposit(PivotSubsystem pivot, ExtendoSubsystem extendo, ClawSubsystem claw){

        addCommands(
            new ResetToIntakeCommand(pivot,extendo,claw),
                new PivotRunToCommand(pivot, Constants.PivotConstants.vertical),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.highBasket),
                new InstantCommand(claw::setOutputHighBasket)
        );
        addRequirements(pivot, extendo,claw);
    }


}
