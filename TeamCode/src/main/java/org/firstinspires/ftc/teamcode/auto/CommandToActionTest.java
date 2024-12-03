package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commnad.HighBasketDeposit;
import org.firstinspires.ftc.teamcode.commnad.ResetToIntakeCommand;
import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.util.CommandToActionWrapper;
import org.firstinspires.ftc.teamcode.util.SampleAuto;

@Autonomous
public class CommandToActionTest extends SampleAuto {
    BaseRobot robot;
    Action a;

    @Override
    public void onInit() {
        robot = new BaseRobot(hardwareMap, new Pose2d(0,0,0));
        a = new CommandToActionWrapper(new HighBasketDeposit(robot.pivot, robot.extendo, robot.wrist, robot.claw));
    }

    @Override
    public void onStart() {
        Actions.runBlocking(a);
    }

    @Override
    public void onStop() {

    }
}
