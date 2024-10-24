package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class PivotSubsystem extends BIBSubsystemBase{
    public static final int leftMotor = 0;
    public static final int rightMotor = 1;
    public static final int bothMotors = 2;
    DcMotor rightPivot;
    DcMotor leftPivot;
    public PivotSubsystem(HardwareMap hwMap){
        rightPivot = hwMap.dcMotor.get("rightPivot");
        leftPivot = hwMap.dcMotor.get("leftPivot");
        rightPivot.setDirection(DcMotorSimple.Direction.REVERSE);
        //reverse any motor directions
        resetEncoders();
    }
    public void setPower(double power, int motorType){
        if(motorType==bothMotors||motorType==PivotSubsystem.leftMotor){
            leftPivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftPivot.setPower(power);
        }
        if(motorType==bothMotors||motorType==PivotSubsystem.rightMotor){
            rightPivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightPivot.setPower(power);
        }
    }
    public void setPower(double power){
        setPower(power,PivotSubsystem.bothMotors);
    }
    public void runToPosition(int encoderCounts, double power,int motorType){
        if(motorType==bothMotors||motorType==PivotSubsystem.leftMotor){
            leftPivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftPivot.setTargetPosition(encoderCounts);
            leftPivot.setPower(power);
        }
        if(motorType==bothMotors||motorType==PivotSubsystem.rightMotor){
            rightPivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightPivot.setTargetPosition(encoderCounts);
            rightPivot.setPower(power);
        }
    }
    public void runToPosition(int encoderCounts, double power){
        runToPosition(encoderCounts,power,PivotSubsystem.bothMotors);
    }

    public void resetEncoders(int motorType){
        leftPivot.setPower(0);
        rightPivot.setPower(0);
        if(motorType==bothMotors||motorType==PivotSubsystem.leftMotor){
            leftPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftPivot.setTargetPosition(0);
        }
        if(motorType==bothMotors||motorType==PivotSubsystem.rightMotor){
            rightPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightPivot.setTargetPosition(0);
        }
    }

    public void resetEncoders(){
        resetEncoders(PivotSubsystem.bothMotors);
    }
    public int getPosition(){
        return leftPivot.getCurrentPosition();
    }

    public boolean belowPosition(int position){
        return getPosition() < position;
    }
    public boolean abovePosition(int position){
        return getPosition() > position;
    }
    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        t.addLine("");
        t.addLine("PIVOT SUBSYSTEM");
        t.addLine("RightPivot : " + rightPivot.getCurrentPosition() + " Power: " + rightPivot.getPower());
        t.addLine("LeftPivot : " + leftPivot.getCurrentPosition() + " Power: " + leftPivot.getPower());

    }

    @Override
    public void periodic() {

    }

    public CommandBase getPivotPowerCommand(DoubleSupplier power, BooleanSupplier increaseSpeed){
        return this.runEnd(()->{
            setPower((increaseSpeed.getAsBoolean()?3:2)*power.getAsDouble()*.25); },()->{
            runToPosition(getPosition(), 1);
        });
    }
}
