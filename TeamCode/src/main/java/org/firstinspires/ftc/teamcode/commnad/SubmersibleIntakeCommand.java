package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SubmersibleIntakeCommand extends SequentialCommandGroup {

    public  SubmersibleIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, ClawSubsystem claw){
        addCommands(new ResetToIntakeCommand(pivot,extendo,claw),
                new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.submersibleInitialExtension),
                new InstantCommand(claw::setVerticalIntake),
                new InstantCommand(claw::open)
        );
        addRequirements(pivot, extendo, claw);
    }
}
