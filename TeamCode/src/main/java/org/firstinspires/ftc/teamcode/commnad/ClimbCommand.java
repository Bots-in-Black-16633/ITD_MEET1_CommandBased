package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.ClimberSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;

public class ClimbCommand extends SequentialCommandGroup {

    public ClimbCommand(PivotSubsystem pivot, ExtendoSubsystem extendo, ClimberSubsystem climb){

        //run sliders to 1st rung level, wait for Input, run climbers to prep position, pull sliders in,


    }
}
