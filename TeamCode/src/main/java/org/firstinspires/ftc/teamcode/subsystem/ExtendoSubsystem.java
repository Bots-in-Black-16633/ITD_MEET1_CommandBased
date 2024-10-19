package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ExtendoSubsystem extends BIBSubsystemBase{
    public static final int leftMotor = 0;
    public static final int rightMotor = 1;
    public static final int bothMotors = 2;
    public int lastTarget = 0;

    DcMotorEx leftSlider;
    DcMotorEx rightSlider;
    DigitalChannel touch;

    public ExtendoSubsystem(HardwareMap hwMap){
        this.leftSlider = (DcMotorEx) hwMap.dcMotor.get("leftSlider");
        this.rightSlider = (DcMotorEx)hwMap.dcMotor.get("rightSlider");
        this.leftSlider.setDirection(DcMotorSimple.Direction.REVERSE);
        this.touch = hwMap.digitalChannel.get("touch");
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
        lastTarget=encoderCounts;
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

    public double getLeftVelocity(){
        return Math.abs(leftSlider.getVelocity());
    }
    public double getRightVelocity(){
        return Math.abs(rightSlider.getVelocity());
    }

    public boolean isSliderAtRest(){
        return !touch.getState();
    }
    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        t.addLine();
        t.addLine("____SLIDER_____");
        t.addLine("LEFT SLIDER : " + leftSlider.getCurrentPosition() + " Power: " + leftSlider.getPower() + "VEL" + leftSlider.getVelocity());
        t.addLine("RIGHT SLIDER (FOLLOWER)" + rightSlider.getCurrentPosition() + " Power: " + rightSlider.getPower() + "VEL" + rightSlider.getVelocity());
        t.addLine("Target is Rest " + touch.getState() );

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


    //TODO: Change out highBasket for a tuned value on lines 135 and 134. Also test out my dubious math.
    public CommandBase getSafetyExtendoPowerCommand(DoubleSupplier sliderPower, BooleanSupplier increaseSpeed, DoubleSupplier pivotAngle){
        return this.runEnd(()->{
            if(Math.cos(Math.toRadians(pivotAngle.getAsDouble()* Constants.PivotConstants.degreesPerTick))>Constants.ExtendoConstants.highBasket) {
                runToPosition(Constants.ExtendoConstants.highBasket, .5);
            }
            else {
                setPower((increaseSpeed.getAsBoolean()?3:1)*sliderPower.getAsDouble()*.25);
            }
        },()->{
            runToPosition(getPosition(), .5);
        });
    }
}
