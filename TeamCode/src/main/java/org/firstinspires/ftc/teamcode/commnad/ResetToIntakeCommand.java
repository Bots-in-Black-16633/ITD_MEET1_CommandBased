package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.onbotjava.OnBotJavaStandardFileManager;
import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class ResetToIntakeCommand extends SequentialCommandGroup {

    public ResetToIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, ClawSubsystem claw) {

        addCommands(new InstantCommand(claw::setRestPosition),
                new ConditionalCommand(new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),new InstantCommand(),()->pivot.getPosition() < Constants.PivotConstants.submersibleIntake),
                /**((pivot.getPosition() < Constants.PivotConstants.submersibleIntake)?new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake):new InstantCommand()),**/
        new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.rest), new PivotRunToCommand(pivot, Constants.PivotConstants.rest));
        addRequirements(pivot, extendo, claw);
    }
}
