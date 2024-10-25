package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="SpecimenPush")
public class SpecimenPush extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, new Pose2d(12, -63, Math.toRadians(270)));
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
                .strafeToConstantHeading(new Vector2d(0, -32))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(2));
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(2));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .5);return false;});
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.rest, .5);return false;});

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(0, -58))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(1.5));

        Actions.runBlocking((t) -> {robot.wrist.setFacingBelt();return false;});

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(36, -58))
                .strafeToConstantHeading(new Vector2d(36, -12))
                .strafeToConstantHeading(new Vector2d(48, -12))
                .strafeToConstantHeading(new Vector2d(48, -54))
                        .strafeToConstantHeading(new Vector2d(48, -12))
                        .strafeToConstantHeading(new Vector2d(60, -12))
                        .strafeToConstantHeading(new Vector2d(60, -54))
                        .strafeToConstantHeading(new Vector2d(60, -12))

                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.wallPickup, 1);return false;});
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);return false;});
        Actions.runBlocking((t) -> {robot.wrist.setFacingStraightParallelToSlider();return false;});
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToLinearHeading(new Vector2d(60, -50), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(60, -65)));
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClip, 1);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(60, -50))
                .strafeToLinearHeading(new Vector2d(0, -50), Math.toRadians(270))
                .strafeToConstantHeading(new Vector2d(0, -32))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1); return false;});
        Actions.runBlocking((t) -> {robot.specimenClaw.open(); return false;});
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .5); return false;});
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.rest, .5);return false;});







    }

    @Override
    public void onStop() {

    }
}
