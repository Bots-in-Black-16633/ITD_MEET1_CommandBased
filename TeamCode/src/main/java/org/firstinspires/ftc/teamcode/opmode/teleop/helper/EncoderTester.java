package org.firstinspires.ftc.teamcode.opmode.teleop.helper;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class EncoderTester extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftSlider;
    DcMotor rightSlider;
    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("leftDrive");
        rightMotor = hardwareMap.dcMotor.get("rightDrive");
        leftSlider = hardwareMap.dcMotor.get("leftSlider");
        rightSlider = hardwareMap.dcMotor.get("rightSlider");
        leftSlider.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        waitForStart();

        while(!isStopRequested() && opModeIsActive()){

            telemetry.addLine("LEFT:" + leftMotor.getCurrentPosition());
            telemetry.addLine("RIGHT:" + rightMotor.getCurrentPosition());
            telemetry.addLine("LEFTSLIDER:" + leftSlider.getCurrentPosition());
            telemetry.addLine("RIGHTSLIDER:" + rightSlider.getCurrentPosition());
            telemetry.update();
        }

    }
}
