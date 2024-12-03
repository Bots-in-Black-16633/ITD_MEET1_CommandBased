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

    public  SubmersibleIntakeCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, WristSubsystem wrist, ClawSubsystem claw){
        addCommands(new ResetToIntakeCommand(pivot,extendo,wrist),
                new InstantCommand(() -> {claw.open();claw.setTwistState(0);}),
                new PivotRunToCommand(pivot, Constants.PivotConstants.submersibleIntake),
                new ExtendoRunToCommand(extendo, Constants.ExtendoConstants.submersibleInitialExtension),
                new InstantCommand(wrist::setSubmersibleIntake)
        );
        addRequirements(pivot, extendo, wrist);
    }
}
