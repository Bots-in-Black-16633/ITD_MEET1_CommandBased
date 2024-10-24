package org.firstinspires.ftc.teamcode.auto.util;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="SpecimenHighBasket")
public class SpecimenHighBasket extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, new Pose2d(-36, -63, Math.toRadians(270)));
    }

    @Override
    public void onStart() {
        Actions.runBlocking((t) ->{robot.specimenClaw.close();return false;});
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(0, -58))
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.highSpecimenDelivery, .5);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(2));
        Actions.runBlocking((t) ->{robot.wrist.setFacingStraightParallelToSlider();return false;});
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClip, 1);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(2));
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(0, -36))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(2));
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(2));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .5);return false;});
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.rest, .5);return false;});

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToLinearHeading(new Vector2d(0, -58), Math.toRadians(150))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(1.5));

        Actions.runBlocking((t) -> {robot.intake.intake(1);return false;});
        Actions.runBlocking((t) -> {robot.wrist.setAutoIntake();return false;});

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(-36, -24))
                .build());




    }

    @Override
    public void onStop() {

    }
}
