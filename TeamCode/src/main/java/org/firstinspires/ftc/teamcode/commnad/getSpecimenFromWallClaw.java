package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.SpecimenClaw;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class getSpecimenFromWallClaw extends SequentialCommandGroup {

    public getSpecimenFromWallClaw(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist, ClawSubsystem claw){

        addCommands(
                new PivotRunToCommand(pivot, Constants.PivotConstants.clawWallPickup),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.clawWallPickup),

                new InstantCommand(wrist::setWallClawPickupPosition),
                new InstantCommand(()->claw.setTwistState(0)),

        new InstantCommand(claw::open));

        addRequirements(pivot, extendo,wrist, claw);
    }
}
