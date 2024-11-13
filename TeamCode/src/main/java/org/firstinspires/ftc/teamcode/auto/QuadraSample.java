package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="QuadraSample")
public class QuadraSample extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, AutoUtil.NETSTART);
    }

    @Override
    public void onStart() {
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);robot.wrist.setFacingStraightParallelToSlider();return false;});

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);return false;})
                .strafeToSplineHeading(new Vector2d(-63, -46), Math.toRadians(37.5))
                .afterTime(0, (t) -> {robot.wrist.setOuttakeHighBasket();return false;})
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.intake.outtake(.5);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1.5));
        //Sample 2
        Actions.runBlocking(robot.getResetToIntakeAction());
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {robot.wrist.setAutoIntake();robot.intake.intake(1); return false;})
                .strafeToSplineHeading(new Vector2d(-44, -52), Math.toRadians(97))
                .strafeToConstantHeading(new Vector2d(-44, -23))
                .afterTime(0, (t) -> {robot.intake.rest();robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);return false;})
                .afterTime(.5, (t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1); return false;})
                        .strafeToConstantHeading( new Vector2d(-44, -50))
                .strafeToSplineHeading(new Vector2d(-59, -48), Math.toRadians(45))
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking((t) -> {robot.wrist.setOuttakeHighBasket(); return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {robot.intake.outtake(.5);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));

        //Sample 3
        Actions.runBlocking(robot.getResetToIntakeAction());

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {robot.wrist.setAutoIntake();robot.intake.intake(1); return false;})
                .strafeToSplineHeading(new Vector2d(-56.5, -52), Math.toRadians(100))
                .strafeToConstantHeading(new Vector2d(-56.5, -27))
                .afterTime(0, (t) -> {robot.intake.rest();robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);return false;})
                .afterTime(.5, (t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1); return false;})
                .strafeToSplineHeading(new Vector2d(-59, -49), Math.toRadians(45))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking((t) -> {robot.wrist.setOuttakeHighBasket(); return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {robot.intake.outtake(.5);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));

        //Sample 4
        Actions.runBlocking(robot.getResetToIntakeAction());
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToSplineHeading(new Vector2d(-48, -24), Math.toRadians(180))
                    .afterTime(0, (t) -> {robot.intake.intake(1);robot.wrist.setAutoIntake();return false;})
                .strafeToConstantHeading(new Vector2d(-72, -24))
                .afterTime(0, (t) -> {robot.intake.rest();robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);return false;})
                .afterTime(.5, (t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1); return false;})
                .strafeToLinearHeading(new Vector2d(-59, -49), Math.toRadians(45))
                .build());

        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {robot.wrist.setOuttakeHighBasket();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {robot.intake.outtake(.5); return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking(robot.getResetToIntakeAction());
    }

    @Override
    public void onStop() {

    }
}
