package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SafetyResetToIntakeCommand extends SequentialCommandGroup {

    public SafetyResetToIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist) {

        addCommands(
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(wrist::setFacingStraightParallelToSlider),
                                new WaitCommand(400)),
                        new SequentialCommandGroup(
                                new InstantCommand(wrist::setFacingOppositeBelts),
                                new WaitCommand(400)),
                        ()->extendo.getPosition() <= Constants.ExtendoConstants.lowBasket-100),

                new ConditionalCommand(
                        new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),
                        new InstantCommand(),()->pivot.getPosition() < Constants.PivotConstants.submersibleIntake),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest),
                new PivotRunToCommand(pivot, Constants.PivotConstants.rest),
                new InstantCommand(() -> wrist.setFacingStraightParallelToSlider())
        );


        addRequirements(pivot, extendo, wrist);
    }
}
