package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class LowBasketDeposit extends SequentialCommandGroup {


    public LowBasketDeposit(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist){

        addCommands(
            new ResetToIntakeCommand(pivot,extendo,wrist),
                new PivotRunToCommand(pivot, Constants.PivotConstants.vertical),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.lowBasket),
                new InstantCommand(wrist::setFacingBelt)
        );
        addRequirements(pivot, extendo,wrist);
    }


}
