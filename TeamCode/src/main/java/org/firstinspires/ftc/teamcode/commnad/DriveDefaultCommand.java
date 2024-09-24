package org.firstinspires.ftc.teamcode.commnad;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveDefaultCommand extends CommandBase {
    private DriveSubsystem drive;
    private DoubleSupplier rotPower;
    private DoubleSupplier lateralPower;

    public DriveDefaultCommand(DriveSubsystem drive, DoubleSupplier lateralPower, DoubleSupplier rotPower){
        this.drive = drive;
        this.rotPower=rotPower;
        this.lateralPower=lateralPower;
        addRequirements(drive);
    }



    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        drive.drive(lateralPower.getAsDouble(), rotPower.getAsDouble());

    }
    @Override
    public void end(boolean interupted){
        drive.drive(0,0);
    }
}
