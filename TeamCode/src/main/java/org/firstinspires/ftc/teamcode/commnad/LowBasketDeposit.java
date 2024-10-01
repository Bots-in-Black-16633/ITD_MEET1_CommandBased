package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class LowBasketDeposit extends SequentialCommandGroup {


    public LowBasketDeposit(PivotSubsystem pivot, ExtendoSubsystem extendo, ClawSubsystem claw){

        addCommands(
            new ResetToIntakeCommand(pivot,extendo,claw),
                new PivotRunToCommand(pivot, Constants.PivotConstants.vertical),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.lowBasket),
                new InstantCommand(claw::setOutputLowBasket)
        );
        addRequirements(pivot, extendo,claw);
    }


}
