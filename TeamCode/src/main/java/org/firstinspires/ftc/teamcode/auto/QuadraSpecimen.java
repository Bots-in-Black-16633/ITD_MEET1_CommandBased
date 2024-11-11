package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="QuadraSpecimen")
public class QuadraSpecimen extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, new Pose2d(0, -63, Math.toRadians(270)));
        robot.pivot.setStartPosition(0);
    }

    @Override
    public void onStart() {
        //Spec 1 deposit
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.highSpecimenDelivery-50,1); return false;});
        Actions.runBlocking((t) ->{robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery-50, 1);robot.specimenClaw.close();return false;});
        AutoUtil.delay(.3);
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(0, (t) -> {
                            robot.wrist.setFacingStraightParallelToSlider();
                            return false;
                        })
                .strafeToConstantHeading(new Vector2d(0, -34))

                        .afterTime(0, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                            return false;
                        })
                .build());
        robot.drive.updatePoseEstimate();


        Actions.runBlocking(AutoUtil.getDelayAction(.3));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.15));

        //Spec 2 Pickup & Pushing
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(0, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);
                            return false;})
                .splineToSplineHeading(new Pose2d(32, -64, Math.toRadians(90)), Math.toRadians(330))
                        .splineToConstantHeading(new Vector2d(32, -45), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(46, -45), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(46, -80), Math.toRadians(270))

                .splineToConstantHeading(new Vector2d(48, -45), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(61, -45), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(61, -75), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(40, -73), Math.toRadians(135))
                .splineToConstantHeading(new Vector2d(40, -92), Math.toRadians(270))
                .afterTime(0, (t) -> {
                    robot.specimenClaw.close();
                    return false;
                })
        .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.3));






        //Spec 2 deposit
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(110))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(5, -36, Math.toRadians(270)), Math.toRadians(90))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.15));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.15));

        //Specimen 3 pickup
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(0, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);
                            return false;
                        })
                .splineToSplineHeading(new Pose2d(38, -80, Math.toRadians(90)), Math.toRadians(300))
                .splineToConstantHeading(new Vector2d(38, -90), Math.toRadians(270))
                .afterTime(0, (t) -> {robot.specimenClaw.close();return false;})
                .build());

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.25));

        //Spec 3 Deposit
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(110))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(6, -40, Math.toRadians(270)), Math.toRadians(90))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.15));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.15));

        //Specimen 4 pickup
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(38, -80, Math.toRadians(90)), Math.toRadians(300))
                .splineToConstantHeading(new Vector2d(38, -90), Math.toRadians(270))
                .afterTime(0, (t) -> {robot.specimenClaw.close();return false;})
                .build());

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.25));

        //Spec 4 Deposit
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(110))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(7, -39, Math.toRadians(270)), Math.toRadians(90))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(.15));

        Actions.runBlocking((t) -> {
            robot.extendo.runToPosition(Constants.ExtendoConstants.rest, 1);
            return false;
        });
        Actions.runBlocking(AutoUtil.getDelayAction(5));

    }

    @Override
    public void onStop() {

    }
}
