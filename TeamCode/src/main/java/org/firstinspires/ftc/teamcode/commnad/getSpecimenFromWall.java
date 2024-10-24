package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpecimenClaw;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class getSpecimenFromWall extends SequentialCommandGroup {

    public getSpecimenFromWall(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist, SpecimenClaw sp){

        addCommands(
                new ResetToIntakeCommand(pivot,extendo,wrist),
                new PivotRunToCommand(pivot, Constants.PivotConstants.wallPickup),
                new InstantCommand(wrist::setFacingOppositeBelts),

        new InstantCommand(sp::open),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.wallSpecimenPickup)
        );
        addRequirements(pivot, extendo,wrist);
    }
}
