package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="ThreeSpecimenSlow")
public class ThreeSpecimenSlow extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, new Pose2d(0, -64, Math.toRadians(270)));
        robot.pivot.setStartPosition(Constants.PivotConstants.verticalAuto);
    }

    @Override
    public void onStart() {
        //Spec 1 deposit
        Actions.runBlocking((t) -> {robot.pivot.runToPosition(Constants.PivotConstants.vertical,1); return false;});
        Actions.runBlocking((t) ->{robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery-50, 1);robot.specimenClaw.close();return false;});
        AutoUtil.delay(.2);
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.wrist.setFacingStraightParallelToSlider();
                    return false;
                })
                .strafeToConstantHeading(new Vector2d(-10, -35.5))

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
                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup-25, 1);
                    return false;})
                .strafeToLinearHeading(new Vector2d(35.5, -64), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(35.5, -36))
                .strafeToConstantHeading(new Vector2d(45, -36))
                .strafeToConstantHeading(new Vector2d(55, -80))
                .strafeToConstantHeading(new Vector2d(38, -70))

                //.strafeToConstantHeading(new Vector2d(55, -39))
                //.strafeToConstantHeading(new Vector2d(56, -39))
                //.strafeToConstantHeading(new Vector2d(56, -72))
                //.strafeToConstantHeading(new Vector2d(38, -70))
                .strafeToConstantHeading(new Vector2d(38, -85), new TranslationalVelConstraint(10))
                                .build());



            robot.specimenClaw.close();
        AutoUtil.delay(.5);


                //Spec 2 deposit
                //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery, 1);
                    Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToLinearHeading(new Vector2d(-3, -33), Math.toRadians(270))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.35));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        //Specimen 3 pickup
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup-25, 1);
                    return false;
                })
                .strafeToLinearHeading(new Vector2d(40, -80), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(40, -85), new TranslationalVelConstraint(30))
                                .build());


        robot.specimenClaw.close();
        AutoUtil.delay(.5);
                //Spec 3 Deposit
                //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
                robot.drive.updatePoseEstimate();
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery+50, 1);
                    return false;
                })
                .strafeToLinearHeading(new Vector2d(2, -33), Math.toRadians(270))
                .afterTime(0, (t) -> {
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
                    return false;
                })
                .build());
        robot.drive.updatePoseEstimate();

        Actions.runBlocking(AutoUtil.getDelayAction(.25));
        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});
////
////        //Specimen 4 pickup
////        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
////                .afterTime(0, (t) -> {
////                    robot.extendo.runToPosition(Constants.ExtendoConstants.wallSpecimenPickup-25, 1);
////                    return false;
////                })
////                .splineToSplineHeading(new Pose2d(40, -85, Math.toRadians(90)), Math.toRadians(270))
////                .splineToConstantHeading(new Vector2d(40, -90), Math.toRadians(270), new TranslationalVelConstraint(30))
////                .afterTime(0, (t) -> {robot.specimenClaw.close();return false;})
////                //.build());
//
////        Actions.runBlocking(AutoUtil.getDelayAction(.25));
////        Actions.runBlocking((t) -> {robot.specimenClaw.close();return false;});
////        Actions.runBlocking(AutoUtil.getDelayAction(.15));
//
//                //Spec 4 Deposit
//                //Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
//                //.splineToConstantHeading(new Vector2d(30, -85), Math.toRadians(170))
//                .afterTime(.2, (t) -> {
//                    robot.extendo.runToPosition(Constants.ExtendoConstants.highSpecimenDelivery+50, 1);
//                    return false;
//                })
//                .splineToSplineHeading(new Pose2d(13, -31, Math.toRadians(270)), Math.toRadians(90))
//                .afterTime(0, (t) -> {
//                    robot.extendo.runToPosition(Constants.ExtendoConstants.highClipDeposit, 1);
//                    return false;
//                })
//                .build());
//        robot.drive.updatePoseEstimate();
//
//        Actions.runBlocking(AutoUtil.getDelayAction(.25));
//        Actions.runBlocking((t) -> {robot.specimenClaw.open();return false;});

        Actions.runBlocking((t) -> {
            robot.extendo.runToPosition(Constants.ExtendoConstants.rest, 1);
            robot.pivot.runToPosition(Constants.PivotConstants.rest,1);
            return false;
        });
        Actions.runBlocking(AutoUtil.getDelayAction(1));

    }

    @Override
    public void onStop() {

    }
}
