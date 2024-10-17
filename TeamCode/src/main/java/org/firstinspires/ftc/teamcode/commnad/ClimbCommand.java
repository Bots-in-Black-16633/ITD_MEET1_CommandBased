package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClimberSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;

public class ClimbCommand extends SequentialCommandGroup {

    public ClimbCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist){

        //run sliders to 1st rung level, wait for Input, run climbers to prep position, pull sliders in,
        addCommands(
                new ResetToIntakeCommand(pivot, extendo,wrist),
                new InstantCommand(wrist::setFacingOppositeBelts),
                new PivotRunToCommand(pivot, 1470),
                new ExtendoRunToCommand(extendo, 1540)
        );
        addRequirements(pivot, extendo,wrist);

    }
}
