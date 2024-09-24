package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

public class BaseRobot extends BIBSubsystemBase {

    public DriveSubsystem drive;
    public ClawSubsystem claw;
    public PivotSubsystem pivot;
    public ExtendoSubsystem extendo;
    public ClimberSubsystem climber;


    public BaseRobot(HardwareMap hwMap){
        drive = new DriveSubsystem(hwMap);
        claw = new ClawSubsystem(hwMap);
        pivot = new PivotSubsystem(hwMap);
        extendo = new ExtendoSubsystem(hwMap);
        climber = new ClimberSubsystem(hwMap);
    }


    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        drive.printTelemetry(t);
        claw.printTelemetry(t);
        pivot.printTelemetry(t);
        extendo.printTelemetry(t);
        climber.printTelemetry(t);

    }

    @Override
    public void periodic() {
        drive.periodic();
        claw.periodic();
        pivot.periodic();
        extendo.periodic();
        climber.periodic();
    }
}
