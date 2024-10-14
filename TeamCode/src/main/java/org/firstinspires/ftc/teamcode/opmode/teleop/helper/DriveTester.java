package org.firstinspires.ftc.teamcode.opmode.teleop.helper;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.SampleCommandTeleop;
@TeleOp
public class DriveTester extends SampleCommandTeleop {

    @Override
    public void onInit() {
        robot.drive.setDefaultCommand(robot.drive.getDriveCommand(()->g1.getLeftX(),()->g1.getLeftY(),()->g1.getRightX()));
        g1.getGamepadButton(GamepadKeys.Button.A).whenActive(robot.drive.drive::resetImu);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onLoop() {

    }

    @Override
    public void onStop() {

    }
}
