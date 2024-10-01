package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ExtendoSubsystem extends BIBSubsystemBase{
    public static final int leftMotor = 0;
    public static final int rightMotor = 1;
    public static final int bothMotors = 2;

    DcMotorEx leftSlider;
    DcMotorEx rightSlider;

    public ExtendoSubsystem(HardwareMap hwMap){
        this.leftSlider = (DcMotorEx) hwMap.dcMotor.get("leftSlider");
        this.rightSlider = (DcMotorEx)hwMap.dcMotor.get("rightSlider");
        this.leftSlider.setDirection(DcMotorSimple.Direction.REVERSE);
        //set Motor Directions
        resetEncoders();
    }

    public void setPower(double power, int motorType){
        if(motorType==bothMotors||motorType==ExtendoSubsystem.leftMotor){
            leftSlider.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftSlider.setPower(power);
        }
        if(motorType==bothMotors||motorType==ExtendoSubsystem.rightMotor){
            rightSlider.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            rightSlider.setPower(power);
        }
    }
    public void setPower(double power){
        setPower(power,ExtendoSubsystem.bothMotors);
    }
    public void runToPosition(int encoderCounts, double power,int motorType){
        leftSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(motorType==bothMotors||motorType==ExtendoSubsystem.leftMotor){
            leftSlider.setTargetPosition(encoderCounts);
            leftSlider.setPower(power);
        }
        if(motorType==bothMotors||motorType==ExtendoSubsystem.rightMotor){
            rightSlider.setTargetPosition(encoderCounts);
            rightSlider.setPower(power);
        }
    }
    public void runToPosition(int encoderCounts, double power){
        runToPosition(encoderCounts,power,ExtendoSubsystem.bothMotors);
    }

    public void resetEncoders(int motorType){
        leftSlider.setPower(0);
        rightSlider.setPower(0);
        if(motorType==bothMotors||motorType==ExtendoSubsystem.leftMotor){
            leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftSlider.setTargetPosition(0);
        }
        if(motorType==bothMotors||motorType==ExtendoSubsystem.rightMotor){
            rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightSlider.setTargetPosition(0);
        }
    }
    public void resetEncoders(){
        resetEncoders(ExtendoSubsystem.bothMotors);
    }

    public int getPosition(){
        return rightSlider.getCurrentPosition();
    }

    public boolean belowPosition(int position){
        return getPosition() < position;
    }
    public boolean abovePosition(int position){
        return getPosition() > position;
    }
    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        t.addLine();
        t.addLine("____SLIDER_____");
        t.addLine("LEFT SLIDER : " + leftSlider.getCurrentPosition() + " Power: " + leftSlider.getPower() + "VEL" + leftSlider.getVelocity());
        t.addLine("RIGHT SLIDER (FOLLOWER)" + rightSlider.getCurrentPosition() + " Power: " + rightSlider.getPower() + "VEL" + rightSlider.getVelocity());

    }

    @Override
    public void periodic() {

    }

    public CommandBase getExtendoPowerCommand(DoubleSupplier sliderPower, BooleanSupplier increaseSpeed){
        return this.runEnd(()->{
            setPower((increaseSpeed.getAsBoolean()?3:1)*sliderPower.getAsDouble()*.25);
        },()->{
            runToPosition(getPosition(), .5);
        });
    }
}
