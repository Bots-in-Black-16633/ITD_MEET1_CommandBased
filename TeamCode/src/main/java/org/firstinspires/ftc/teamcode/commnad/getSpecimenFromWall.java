package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class getSpecimenFromWall extends SequentialCommandGroup {

    public getSpecimenFromWall(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist){

        addCommands(
                new ResetToIntakeCommand(pivot,extendo,wrist),
                new PivotRunToCommand(pivot, Constants.PivotConstants.wallPickup),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest),
                new InstantCommand(wrist::setFacingStraightParallelToSlider)
        );
        addRequirements(pivot, extendo,wrist);
    }
}
