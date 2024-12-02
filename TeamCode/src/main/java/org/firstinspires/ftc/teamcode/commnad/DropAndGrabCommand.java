package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.WristSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class DropAndGrabCommand extends SequentialCommandGroup {

    //When hovering over a game piece, drops the intake and grabs it and returns to rest.
    public DropAndGrabCommand(ExtendoSubsystem extendo, WristSubsystem wrist, ClawSubsystem claw) {
        addCommands(new InstantCommand(() -> wrist.setDropPosition()),
                new WaitCommand(100),
                new InstantCommand(() -> claw.close()),
                new WaitCommand(100),
                new InstantCommand(() -> {wrist.setFacingBelt();
                    extendo.runToPosition(Constants.ExtendoConstants.rest, 1);
                    claw.setTwistPosition(Constants.ClawConstants.twistPositions.length/2);}
                ));
    }
}
