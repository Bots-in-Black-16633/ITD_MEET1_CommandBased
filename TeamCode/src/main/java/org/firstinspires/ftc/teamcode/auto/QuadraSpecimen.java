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
        robot = new BaseRobot(hardwareMap, new Pose2d(0, -64, Math.toRadians(270)));
        robot.pivot.setStartPosition(1400);
    }

    @Override
    public void onStart() {
        //Spec 1 deposit
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.highSpecimenDelivery-100,1); return false;});
        Actions.runBlocking((t) ->{robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery-50, 1);robot.specimenClaw.close();return false;});
        AutoUtil.delay(.2);
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(0, (t) -> {
                            robot.wrist.setFacingStraightParallelToSlider();
                            return false;
                        })
                .strafeToConstantHeading(new Vector2d(0, -35.5))

//                        .afterTime(0, (t) -> {
//                            robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
//                            return false;
//                        })
                .build());
        robot.drive.updatePoseEstimate();

        robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        //Spec 2 Pickup & Pushing
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(0, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);
                            return false;})
                .splineToSplineHeading(new Pose2d(32, -64, Math.toRadians(90)), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(32, -42), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(45, -42), Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(45, -75), Math.toRadians(160))
                .splineToConstantHeading(new Vector2d(38, -39), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(59, -39), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(59, -72), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(38, -70), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(38, -92), Math.toRadians(270))
                .afterTime(0, (t) -> {
                    robot.specimenClaw.close();
                    return false;
                })
//        .build());
//        robot.drive.updatePoseEstimate();
//
//        Actions.runBlocking(AutoUtil.getDelayAction(.1));






        //Spec 2 deposit
        //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
                .afterTime(0.5, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery-50, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(3, -33, Math.toRadians(270)), Math.toRadians(90))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        //Specimen 3 pickup
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                        .afterTime(0, (t) -> {
                            robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);
                            return false;
                        })
                .splineToSplineHeading(new Pose2d(40, -85, Math.toRadians(90)), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(40, -90), Math.toRadians(270))
                .afterTime(0, (t) -> {robot.specimenClaw.close();return false;})
                //.build());

//        Actions.runBlocking(AutoUtil.getDelayAction(.25));
//        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
//        Actions.runBlocking(AutoUtil.getDelayAction(.15));

        //Spec 3 Deposit
        //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
                .afterTime(.5, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery-50, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(5, -34, Math.toRadians(270)), Math.toRadians(90))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        //Specimen 4 pickup
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(40, -85, Math.toRadians(90)), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(40, -90), Math.toRadians(270))
                .afterTime(0, (t) -> {robot.specimenClaw.close();return false;})
                //.build());

//        Actions.runBlocking(AutoUtil.getDelayAction(.25));
//        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
//        Actions.runBlocking(AutoUtil.getDelayAction(.15));

        //Spec 4 Deposit
        //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
                .afterTime(.5, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery-50, 1);
                    return false;
                })
                .splineToSplineHeading(new Pose2d(13, -34, Math.toRadians(270)), Math.toRadians(90))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

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
