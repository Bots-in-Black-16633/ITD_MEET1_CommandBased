package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

public class BaseRobot extends BIBSubsystemBase {

    public DriveSubsystem drive;
    public PivotSubsystem pivot;
    public ExtendoSubsystem extendo;
    public WristSubsystem wrist;
    public IntakeSubsystem intake;
    public AutoUtil autoGenerator;

    public BaseRobot(HardwareMap hwMap, Pose2d startPos){
        drive = new DriveSubsystem(hwMap, startPos);
        pivot = new PivotSubsystem(hwMap);
        extendo = new ExtendoSubsystem(hwMap);
        wrist = new WristSubsystem(hwMap);
        intake = new IntakeSubsystem(hwMap);
        autoGenerator = new AutoUtil(drive);
    }


    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        drive.printTelemetry(t);
        pivot.printTelemetry(t);
        extendo.printTelemetry(t);
        intake.printTelemetry(t);
        wrist.printTelemetry(t);

    }

    @Override
    public void periodic() {
        drive.periodic();
        pivot.periodic();
        extendo.periodic();
        wrist.periodic();
        intake.periodic();

    }
}
