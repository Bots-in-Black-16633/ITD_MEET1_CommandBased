package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

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
    }

    @Override
    public void onStop() {

    }
}
