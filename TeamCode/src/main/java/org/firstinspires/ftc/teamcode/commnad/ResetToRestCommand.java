package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class ResetToRestCommand extends SequentialCommandGroup {

    public ResetToRestCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist) {

        addCommands(
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(wrist::setFacingStraightParallelToSlider),
                                new WaitCommand(1000)),
                        new InstantCommand(wrist::setFacingStraightParallelToSlider),()->extendo.getPosition()> Constants.ExtendoConstants.highBasket-200 && pivot.getPosition() > Constants.PivotConstants.submersibleIntake+200),

                new ConditionalCommand(new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),new InstantCommand(),()->pivot.getPosition() < Constants.PivotConstants.submersibleIntake),

                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest), new PivotRunToCommand(pivot, Constants.PivotConstants.rest),
                new InstantCommand(wrist::setFacingBelt)
        );
        //if inaking from submersible wris back to facing away from belt
        //if outtakin reset to facing belts
        //.5 outake position for wrist

        addRequirements(pivot, extendo, wrist);
    }
}
