package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="DoubleSpecimen")
public class DoubleSpecimen extends SampleAuto {
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
                .afterTime(0, (t) -> {
                    robot.pivot.runToPosition(Constants.PivotConstants.highSpecimenDelivery, 1);
                    return false;})

                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery, 1);
            robot.wrist.setFacingStraightParallelToSlider();
            return false;});

        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())

                        .strafeToConstantHeading(new Vector2d(0, -36))
                                .build());

        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.5));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1));






        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, .5);return false;});
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.wallPickup, .5);return false;});

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToConstantHeading(new Vector2d(0, -63))
                .strafeToLinearHeading(new Vector2d(40, -75), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(40, -87))
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.5));

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(.5, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery, 1);
                            robot.pivot.runToPosition(Constants.PivotConstants.highSpecimenDelivery, 1);
                            return false;})

                .strafeToConstantHeading(new Vector2d(40, -75))
                .strafeToLinearHeading(new Vector2d(3, -44), Math.toRadians(270))
                        .strafeToConstantHeading(new Vector2d(8, -38))
                .build());

        Actions.runBlocking((t) -> {robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1); return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.5));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});


        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(1, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .5);
                            robot.pivot.runToPosition(Constants.PivotConstants.rest, .5);
                            robot.wrist.setFacingBelt();
                            return false;
                        })
                .strafeToConstantHeading(new Vector2d(36, -58))
                .strafeToConstantHeading(new Vector2d(36, -12))
                .strafeToConstantHeading(new Vector2d(48, -12))
                .strafeToConstantHeading(new Vector2d(48, -54))
                .strafeToConstantHeading(new Vector2d(48, -12))
                .strafeToConstantHeading(new Vector2d(58, -12))
                .strafeToConstantHeading(new Vector2d(58, -54))
                .strafeToConstantHeading(new Vector2d(58, -45))

                .build());
    }

    @Override
    public void onStop() {

    }
}
