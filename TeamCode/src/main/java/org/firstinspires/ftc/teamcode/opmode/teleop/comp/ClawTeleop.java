package org.firstinspires.ftc.teamcode.opmode.teleop.comp;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commnad.ClimbCommand;
import org.firstinspires.ftc.teamcode.commnad.DeliverHighSpecimen;
import org.firstinspires.ftc.teamcode.commnad.DeliverHighSpecimenClaw;
import org.firstinspires.ftc.teamcode.commnad.HighBasketDeposit;
import org.firstinspires.ftc.teamcode.commnad.LowBasketDeposit;
import org.firstinspires.ftc.teamcode.commnad.ResetToIntakeCommand;
import org.firstinspires.ftc.teamcode.commnad.SubmersibleIntakeCommand;
import org.firstinspires.ftc.teamcode.commnad.DropAndGrabCommand;
import org.firstinspires.ftc.teamcode.commnad.getSpecimenFromWall;
import org.firstinspires.ftc.teamcode.commnad.getSpecimenFromWallClaw;
import org.firstinspires.ftc.teamcode.util.SampleCommandTeleop;

@TeleOp
public class ClawTeleop extends SampleCommandTeleop {
    @Override
    public void onInit() {
        //robot.pivot.setStartPosition(Constants.PivotConstants.verticalAuto);
        //Drive Controls
        robot.drive.setDefaultCommand(robot.drive.getDriveFieldcentric(()->g1.getLeftX(),()->g1.getLeftY(), ()->-g1.getRightX(), .75));
        //Speed Controls, slowmode * fastmode
        new Trigger(() -> g1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)>.05).whileActiveOnce(robot.drive.getDriveFieldcentric(()->g1.getLeftX(),()->g1.getLeftY(), ()->-g1.getRightX(), .45));
        new Trigger(() -> g1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>.05).whileActiveOnce(robot.drive.getDriveFieldcentric(()->g1.getLeftX(),()->g1.getLeftY(), ()->-g1.getRightX(),1));
        g1.getGamepadButton(GamepadKeys.Button.A).whenActive(()->robot.drive.drive.resetHeadingRelative());


        new Trigger(()->Math.abs(g2.getLeftY())>.05).whileActiveOnce(robot.pivot.getPivotPowerCommand(()->g2.getLeftY(), ()->g2.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON)));
        new Trigger(()->Math.abs(g2.getRightY())>.05).whileActiveOnce(robot.extendo.getExtendoPowerCommand(()->-g2.getRightY(), ()->g2.getButton(GamepadKeys.Button.RIGHT_STICK_BUTTON), ()->robot.pivot.getPosition()));
        new Trigger(()->Math.abs(g2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)-g2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER))>.05).whileActiveOnce(robot.wrist.getManualControlCommand(()->g2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)-g2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)));

        //slider encoder auto reseter with touch sensor
        new Trigger(()->robot.extendo.isSliderAtRest()).whenActive(new InstantCommand(robot.extendo::resetEncoders));


        new Trigger(()->g2.getButton(GamepadKeys.Button.START) && g2.getButton(GamepadKeys.Button.LEFT_BUMPER)).whenActive(new InstantCommand(robot.pivot::resetEncoders));
        new Trigger(()->g2.getButton(GamepadKeys.Button.START) && g2.getButton(GamepadKeys.Button.RIGHT_BUMPER)).whenActive(new InstantCommand(robot.extendo::resetEncoders));

        //g2.getGamepadButton(GamepadKeys.Button.START).whenActive(new InstantCommand(robot.extendo::resetEncoders));



        g2.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(() -> robot.claw.open(), () -> robot.claw.close());
        g2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileActiveOnce(new InstantCommand(() -> robot.claw.twistCounterclockwise()));
        g2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileActiveOnce(new InstantCommand(() -> robot.claw.twistClockwise()));
        g2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenActive(new DropAndGrabCommand(robot.extendo, robot.wrist, robot.claw));

        g2.getGamepadButton(GamepadKeys.Button.Y).whenActive(new ResetToIntakeCommand(robot.pivot,robot.extendo,robot.wrist).andThen(new InstantCommand(() -> robot.claw.setTwistState(4))));
        g2.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenActive(new HighBasketDeposit(robot.pivot,robot.extendo,robot.wrist, robot.claw));
        g2.getGamepadButton(GamepadKeys.Button.A).whenActive(new SubmersibleIntakeCommand(robot.pivot,robot.extendo,robot.wrist, robot.claw));
        //g2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenActive(new LowBasketDeposit(robot.pivot,robot.extendo,robot.wrist));
        g2.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(robot.specimenClaw::open, robot.specimenClaw::close);


        //g2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenActive(new getSpecimenFromWall(robot.pivot,robot.extendo,robot.wrist, robot.specimenClaw));
        //g2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenActive(new DeliverHighSpecimen(robot.pivot,robot.extendo,robot.wrist));
        g2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenActive(new DeliverHighSpecimenClaw(robot.pivot,robot.extendo,robot.wrist));
        g2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenActive(new getSpecimenFromWallClaw(robot.pivot,robot.extendo,robot.wrist, robot.claw));

        new Trigger(()->g2.getButton(GamepadKeys.Button.START) && g2.getButton(GamepadKeys.Button.DPAD_LEFT)).whenActive(new DeliverHighSpecimenClaw(robot.pivot,robot.extendo,robot.wrist));
        new Trigger(()->g2.getButton(GamepadKeys.Button.START) && g2.getButton(GamepadKeys.Button.DPAD_RIGHT)).whenActive(new getSpecimenFromWallClaw(robot.pivot,robot.extendo,robot.wrist, robot.claw));
        new Trigger(()->!g2.getButton(GamepadKeys.Button.START) && g2.getButton(GamepadKeys.Button.DPAD_LEFT)).whenActive(new DeliverHighSpecimen(robot.pivot,robot.extendo,robot.wrist));
        new Trigger(()->!g2.getButton(GamepadKeys.Button.START) && g2.getButton(GamepadKeys.Button.DPAD_RIGHT)).whenActive(new getSpecimenFromWall(robot.pivot,robot.extendo,robot.wrist, robot.specimenClaw));

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onLoop() {
        telemetry.addLine(g1.getRightX()+"");
        telemetry.addLine(g1.getRightY()+"");
        telemetry.addLine(g1.getLeftX()+"");
        telemetry.addLine(g1.getLeftY()+"");


    }

    @Override
    public void onStop() {

    }
}
