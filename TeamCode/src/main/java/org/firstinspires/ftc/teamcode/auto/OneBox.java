package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Disabled
@Autonomous(name="NPL")
public class OneBox extends SampleAuto {
    BaseRobot robot;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, AutoUtil.NETSTART);
    }

    @Override
    public void onStart() {
        Actions.runBlocking(robot.autoGenerator.getStartToNetAction(AutoUtil.NET));
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(robot.getHighBasketOuttakeAction());
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        //Actions.runBlocking((t) ->{robot.intake.outtake(.25); return false;});
        Actions.runBlocking(AutoUtil.getDelayAction(1.5));
        robot.drive.updatePoseEstimate();
        //Actions.runBlocking((t) -> {robot.intake.rest();return false;});

        Actions.runBlocking(robot.getResetToIntakeAction());
        Actions.runBlocking(AutoUtil.getDelayAction(1));

        //Actions.runBlocking((t) -> {robot.intake.intake(1);return false;});
        Actions.runBlocking(robot.autoGenerator.getNetToBoxThree(robot.drive.getPose()));
        robot.drive.updatePoseEstimate();
        Actions.runBlocking(AutoUtil.getDelayAction(1));
        Actions.runBlocking(robot.autoGenerator.getBackupToDepositAction(robot.drive.getPose()));
        robot.drive.updatePoseEstimate();
        //Actions.runBlocking((t) -> {robot.intake.rest();return false;});


    }

    @Override
    public void onStop() {

    }
}
