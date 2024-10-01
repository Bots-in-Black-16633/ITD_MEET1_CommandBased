package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.onbotjava.OnBotJavaStandardFileManager;
import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class ResetToIntakeCommand extends SequentialCommandGroup {

    public ResetToIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, ClawSubsystem claw) {

        addCommands(
                new ConditionalCommand(new SequentialCommandGroup(new InstantCommand(claw::setHorizontalIntake),new WaitCommand(1000)),new InstantCommand(claw::setRestPosition),()->extendo.getPosition()> Constants.ExtendoConstants.highBasket-200 && pivot.getPosition() > Constants.PivotConstants.submersibleIntake+200),

                new ConditionalCommand(new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),new InstantCommand(),()->pivot.getPosition() < Constants.PivotConstants.submersibleIntake),
                new InstantCommand(claw::setRestPosition),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest), new PivotRunToCommand(pivot, Constants.PivotConstants.rest)
        );

        addRequirements(pivot, extendo, claw);
    }
}
