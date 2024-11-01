package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.onbotjava.OnBotJavaStandardFileManager;
import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class ResetToIntakeCommand extends SequentialCommandGroup {

    public ResetToIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist) {

        addCommands(
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(wrist::setFacingOppositeBelts),
                                new WaitCommand(400)),
                        new InstantCommand(wrist::setFacingBelt),
                        ()->pivot.getPosition() > Constants.PivotConstants.submersibleIntake+200),

                new ConditionalCommand(
                        new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),
                        new InstantCommand(),()->pivot.getPosition() < Constants.PivotConstants.submersibleIntake),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest),
                new InstantCommand(wrist::setFacingStraightParallelToSlider),
                new PivotRunToCommand(pivot, Constants.PivotConstants.rest)
        );


        addRequirements(pivot, extendo, wrist);
    }
}
