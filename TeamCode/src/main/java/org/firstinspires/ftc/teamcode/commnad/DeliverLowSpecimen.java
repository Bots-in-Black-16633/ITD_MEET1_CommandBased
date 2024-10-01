package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class DeliverLowSpecimen extends SequentialCommandGroup {
    public DeliverLowSpecimen(PivotSubsystem pivot, ExtendoSubsystem extendo, ClawSubsystem claw){

        addCommands(
                new ResetToIntakeCommand(pivot,extendo,claw),
                new PivotRunToCommand(pivot, Constants.PivotConstants.lowDelivery),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest),
                new InstantCommand(claw::specimenDelivery)

        );
        addRequirements(pivot, extendo,claw);
    }
}
