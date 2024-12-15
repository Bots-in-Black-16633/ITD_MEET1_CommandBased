package org.firstinspires.ftc.teamcode.auto;

import android.os.ParcelFileDescriptor;

import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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

        robot = new BaseRobot(hardwareMap, new Pose2d(-40, -63, Math.toRadians(0)));
        robot.pivot.setStartPosition(Constants.PivotConstants.verticalAuto);
    }

    @Override
    public void onStart() {
        Actions.runBlocking((t) -> {
            robot.wrist.setOuttakeHighBasket();
            robot.claw.close();
            //robot.claw.setTwistState(3);
            robot.claw.setTwistPosition(.66);
            robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);
            return false;
        });

        Actions.runBlocking(AutoUtil.getDelayAction(.2));
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .afterTime(0, (t) -> {
                    robot.claw.setTwistPosition(.66);
                    robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);

                    return false;
                })
                .strafeToLinearHeading(new Vector2d(-56, -60), Math.toRadians(50), new AngularVelConstraint(.33*Math.PI/2))
                .build());
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(.5));
        Actions.runBlocking((t) -> {
            robot.claw.open();

            //SPEC 2
            AutoUtil.delay(1);
            robot.wrist.setDropBlock();
            AutoUtil.delay(.2);
            robot.wrist.setSubmersibleIntake();
            robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .8);
            AutoUtil.delay(2);
            robot.pivot.runToPosition(Constants.PivotConstants.rest, .7);
            //AutoUtil.delay(2);
            return false;
        });


        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToSplineHeading(new Vector2d(-39.25, -60), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-39.25, -53.5), new TranslationalVelConstraint(20)).build());

        Actions.runBlocking((t) -> {
            robot.wrist.setAutoIntake();
            AutoUtil.delay(.3);
            robot.claw.close();
            AutoUtil.delay(.5);
            robot.wrist.setOuttakeHighBasket();
            robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);
            AutoUtil.delay(1.5);
            robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);
            return false;
        });
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToSplineHeading(new Vector2d(-49, -67), Math.toRadians(45), new AngularVelConstraint(.5*Math.PI/2))

                .build()

        );
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {
            robot.claw.open();
            //Spec 3
            AutoUtil.delay(1);
            robot.wrist.setDropBlock();
            AutoUtil.delay(.2);
            robot.wrist.setSubmersibleIntake();
            robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .8);
            AutoUtil.delay(2);
            robot.pivot.runToPosition(Constants.PivotConstants.rest, .7);
            //AutoUtil.delay(2);
            return false;
        });


        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToSplineHeading(new Vector2d(-49.5, -62), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-49.25, -54.75), new TranslationalVelConstraint(30)).build());

        Actions.runBlocking((t) -> {
            robot.wrist.setAutoIntake();
            AutoUtil.delay(.3);
            robot.claw.close();
            AutoUtil.delay(.5);
            robot.wrist.setOuttakeHighBasket();
            robot.pivot.runToPosition(Constants.PivotConstants.vertical, 1);
            AutoUtil.delay(2);
            robot.extendo.runToPosition(Constants.ExtendoConstants.highBasket, 1);
            return false;
        });
        Actions.runBlocking(robot.drive.actionBuilder(robot.drive.getPose())
                .strafeToSplineHeading(new Vector2d(-49, -67), Math.toRadians(45), new AngularVelConstraint(.5*Math.PI/2))

                .build()

        );

        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking((t) -> {
            robot.claw.open();
            AutoUtil.delay(1);
            robot.wrist.setDropBlock();
            AutoUtil.delay(.2);
            robot.wrist.setSubmersibleIntake();
            robot.extendo.runToPosition(Constants.ExtendoConstants.rest, .8);
            AutoUtil.delay(2);
            robot.pivot.runToPosition(Constants.PivotConstants.rest, .7);
            AutoUtil.delay(2);
            return false;
        });




    }

    @Override
    public void onStop() {

    }
}
