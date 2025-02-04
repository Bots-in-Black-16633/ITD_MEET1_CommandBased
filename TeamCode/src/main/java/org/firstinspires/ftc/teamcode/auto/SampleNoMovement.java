package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous(name="SampleNoMovement")
public class SampleNoMovement extends SampleAuto {
    BaseRobot robot;
    PIDController c = new PIDController(.5,0,0);

    @Override
    public void onInit() {

        robot = new BaseRobot(hardwareMap, new Pose2d(-40, -63, Math.toRadians(0)));
        robot.pivot.setStartPosition(Constants.PivotConstants.verticalAuto);
    }

    @Override
    public void onStart() {

        Actions.runBlocking((t) -> {
            robot.claw.close();
            //robot.claw.setTwistState(3);
            robot.claw.setTwistPosition(.66);
            robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);
            robot.specimenClaw.open();
            return false;
        });


        Actions.runBlocking(AutoUtil.getDelayAction(.2));
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.claw.setTwistPosition(.66);
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);

                    return false;
                }).afterTime(1.5,(t)->{
                    robot.wrist.setDropBlock();
                    return false;
                })
                .strafeToLinearHeading(new Vector2d(-56, -60), Math.toRadians(50), new AngularVelConstraint(.33*Math.PI/2))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(.5));

        robot.claw.open();
        Actions.runBlocking(AutoUtil.getDelayAction(.5));

        robot.wrist.setSubmersibleIntake();
        Actions.runBlocking(AutoUtil.getDelayAction(.5));

        robot.extendo.runToPosition(Constants.ExtendoConstants.halfExtension, .8);

        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToLinearHeading(new Vector2d(-50, -60), Math.toRadians(75))

                .build());

        AutoUtil.delay(.5);


        Actions.runBlocking((t)->{
            robot.wrist.setOuttakeHighBasket();
            robot.claw.open();
            robot.claw.setTwistPosition(.54);
            Actions.runBlocking(AutoUtil.getDelayAction(.5));
            robot.pivot.runToPosition(Constants.PivotConstants.submersibleIntake, .5);
            AutoUtil.delay(.5);
            robot.wrist.setSubmersibleIntake();
            Actions.runBlocking(AutoUtil.getDelayAction(2));




            return false;
        });
        robot.claw.close();
        AutoUtil.delay(.5);
        robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .8);





//        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
//
//                .strafeToLinearHeading(new Pose2d(-38, -52.5, Math.toRadians()), new TranslationalVelConstraint(20)).build());
//
//        Actions.runBlocking((t) -> {
//            robot.claw.close();
//            //robot.claw.setTwistState(3);
//            robot.claw.setTwistPosition(.66);
//            robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);
//            robot.specimenClaw.open();
//            return false;
//        });
//
//
//        Actions.runBlocking(AutoUtil.getDelayAction(.2));
//        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
//                .afterTime(0, (t) -> {
//                    robot.claw.setTwistPosition(.66);
//                    robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);
//
//                    return false;
//                }).afterTime(1.5,(t)->{
//                    robot.wrist.setDropBlock();
//                    return false;
//                })
//                .strafeToLinearHeading(new Vector2d(-56, -60), Math.toRadians(50), new AngularVelConstraint(.33*Math.PI/2))
//                .build());
//        robot.drive.updatePoseEstimate();
//        Actions.runBlocking(AutoUtil.getDelayAction(.5));
//
//        robot.claw.open();
//        robot.wrist.setSubmersibleIntake();
//        Actions.runBlocking(AutoUtil.getDelayAction(.5));
//
//
//
//        Actions.runBlocking(AutoUtil.getDelayAction(.5));
//


    }

    @Override
    public void onStop() {

    }
}
