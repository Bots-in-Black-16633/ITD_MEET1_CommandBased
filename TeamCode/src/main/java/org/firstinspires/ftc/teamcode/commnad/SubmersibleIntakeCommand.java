package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SubmersibleIntakeCommand extends SequentialCommandGroup {

    public  SubmersibleIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist){
        addCommands(new ResetToIntakeCommand(pivot,extendo,wrist),
                new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.submersibleInitialExtension),
                new InstantCommand(wrist::setFacingStraightParallelToSlider)
        );
        addRequirements(pivot, extendo, wrist);
    }
}
