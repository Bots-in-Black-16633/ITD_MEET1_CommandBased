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

@Autonomous(name="FiveSpecimen")
public class FiveSpecimen extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, new Pose2d(0, -64, Math.toRadians(270)));
        robot.pivot.setStartPosition(Constants.PivotConstants.verticalAuto);
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
                    robot.extendo.runToPosition(Constants.ExtendoConstants.rest, 1);
                    robot.pivot.runToPosition(Constants.PivotConstants.rest, .5);
                    robot.wrist.setWallAutoIntake();
                    robot.claw.open();
                    robot.claw.setTwistState(Constants.ClawConstants.twistPositions.length/2);
                    return false;})
                .splineToConstantHeading(new Vector2d(45, -40), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(45, -12), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(48, -12), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(48, -55), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(48, -12), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(59, -12), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(59, -50), Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(59, -10), Math.toRadians(20))
                                .splineToConstantHeading(new Vector2d(68, -8), Math.toRadians(270))
                                .splineToConstantHeading(new Vector2d(68, -50), Math.toRadians(250))
                                .splineToConstantHeading(new Vector2d(32, -45), Math.toRadians(270))
                                .splineToConstantHeading(new Vector2d(32, -62), Math.toRadians(270), new TranslationalVelConstraint(20))
                                .afterTime(0, (t) -> {robot.claw.close();return false;})
//        .build());
//        robot.drive.updatePoseEstimate();
//
//        Actions.runBlocking(AutoUtil.getDelayAction(.1));






                //Spec 2 deposit
                //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
                .afterTime(0.5, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.approachUpsideDownClip, 1);
                    robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);
                    return false;
                })
                .splineToConstantHeading(new Vector2d(3, -33), Math.toRadians(90), new TranslationalVelConstraint(25))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.depositUpsideDownClip, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.35));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        //Specimen 3 pickup
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup+25, 1);
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
                .splineToSplineHeading(new Pose2d(6, -34, Math.toRadians(270)), Math.toRadians(90))
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
                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup+25, 1);
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
