package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class DeliverHighSpecimen extends SequentialCommandGroup {
    public DeliverHighSpecimen(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist){

        addCommands(
                new ResetToIntakeCommand(pivot,extendo,wrist),
                new PivotRunToCommand(pivot, Constants.PivotConstants.highSpecimenDelivery),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.highSpecimenDelivery),
                new InstantCommand(wrist::setFacingOppositeBelts)

        );
        addRequirements(pivot, extendo,wrist);
    }
}
