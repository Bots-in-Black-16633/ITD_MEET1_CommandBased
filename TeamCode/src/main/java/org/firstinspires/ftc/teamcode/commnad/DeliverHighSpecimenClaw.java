package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class DeliverHighSpecimenClaw extends SequentialCommandGroup {
    public DeliverHighSpecimenClaw(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist){

        addCommands(
                new PivotRunToCommand(pivot, Constants.PivotConstants.clawHighSpecimenDelivery),
                new InstantCommand(wrist::setClawHgihSpecimen),
        new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.clawHighSpecimenDelivery)

        );
        addRequirements(pivot, extendo,wrist);
    }
}
