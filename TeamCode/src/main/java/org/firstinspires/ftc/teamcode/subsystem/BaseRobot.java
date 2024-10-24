package org.firstinspires.ftc.teamcode.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

public class BaseRobot extends BIBSubsystemBase {

    public DriveSubsystem drive;
    public PivotSubsystem pivot;
    public ExtendoSubsystem extendo;
    public WristSubsystem wrist;
    public IntakeSubsystem intake;
    public AutoUtil autoGenerator;
    public SpecimenClaw specimenClaw;

    public BaseRobot(HardwareMap hwMap, Pose2d startPos){
        drive = new DriveSubsystem(hwMap, startPos);
        pivot = new PivotSubsystem(hwMap);
        extendo = new ExtendoSubsystem(hwMap);
        wrist = new WristSubsystem(hwMap);
        intake = new IntakeSubsystem(hwMap);
        specimenClaw = new SpecimenClaw(hwMap);
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
        specimenClaw.periodic();


    }

    class HighBasketOuttakeAction implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            pivot.runToPosition(Constants.PivotConstants.vertical, 1);
//            while(pivot.getPosition()<Constants.PivotConstants.vertical) {
//
//            }


            wrist.setFacingStraightParallelToSlider();
            AutoUtil.delay(1.5);
            extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);
//            while(extendo.getPosition()<Constants.ExtendoConstants.highBasket) {
//
//            }
            AutoUtil.delay(1.5);
            wrist.setOuttakeHighBasket();

            return false;
        }
    }

    class ResetToIntakeAction implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.setFacingStraightParallelToSlider();
            extendo.runToPosition(Constants.ExtendoConstants.rest, 1);
            AutoUtil.delay(3);
            pivot.runToPosition(Constants.PivotConstants.rest, .3);
            AutoUtil.delay(2);
            wrist.setFacingOppositeBelts();
            return false;
        }
    }

    public HighBasketOuttakeAction getHighBasketOuttakeAction() {
        return new HighBasketOuttakeAction();
    }

    public ResetToIntakeAction getResetToIntakeAction() {
        return new ResetToIntakeAction();
    }
}
