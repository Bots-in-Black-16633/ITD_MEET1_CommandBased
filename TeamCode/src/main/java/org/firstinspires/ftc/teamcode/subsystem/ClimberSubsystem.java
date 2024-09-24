package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

import java.util.function.DoubleSupplier;

public class ClimberSubsystem extends BIBSubsystemBase{

    public static final int bothMotors = 2;
    public static final int leftMotor = 0;
    public static final int rightMotor = 1;

    DcMotor leftClimber;
    DcMotor rightClimber;

    public ClimberSubsystem(HardwareMap hwMap){
        leftClimber = hwMap.dcMotor.get("leftClimber");
        rightClimber = hwMap.dcMotor.get("rightClimber");

        rightClimber.setDirection(DcMotorSimple.Direction.REVERSE);
        resetEncoders();
    }


    public void setPower(double power, int motorType){
        if(motorType==bothMotors||motorType==ClimberSubsystem.leftMotor){
            leftClimber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftClimber.setPower(power);
        }
        if(motorType==bothMotors||motorType==ClimberSubsystem.rightMotor){
            rightClimber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            rightClimber.setPower(power);
        }
    }
    public void setPower(double power){
        setPower(power,ClimberSubsystem.bothMotors);
    }
    public void runToPosition(int encoderCounts, double power,int motorType){
        leftClimber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightClimber.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(motorType==bothMotors||motorType==ClimberSubsystem.leftMotor){
            leftClimber.setTargetPosition(encoderCounts);
            leftClimber.setPower(power);
        }
        if(motorType==bothMotors||motorType==ClimberSubsystem.rightMotor){
            rightClimber.setTargetPosition(encoderCounts);
            rightClimber.setPower(power);
        }
    }
    public void runToPosition(int encoderCounts, double power){
        runToPosition(encoderCounts,power,ClimberSubsystem.bothMotors);
    }

    public void resetEncoders(int motorType){
        if(motorType==bothMotors||motorType==ClimberSubsystem.leftMotor){
            leftClimber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftClimber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftClimber.setTargetPosition(0);
        }
        if(motorType==bothMotors||motorType==ClimberSubsystem.rightMotor){
            rightClimber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightClimber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightClimber.setTargetPosition(0);
        }
    }
    public void resetEncoders(){
        resetEncoders(ClimberSubsystem.bothMotors);
    }

    public int getPosition(){
        return leftClimber.getCurrentPosition();
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
        t.addLine("____CLIMBER_____");
        t.addLine("LEFT CLIMBER : " + leftClimber.getCurrentPosition() + " Power: " + leftClimber.getPower());
        t.addLine("RIGHT CLIMBER (FOLLOWER)" + rightClimber.getCurrentPosition() + " Power: " + rightClimber.getPower());

    }

    @Override
    public void periodic() {

    }

    public CommandBase getClimberPowerCommand(DoubleSupplier climberPower){
        return this.runEnd(()->{
            setPower(climberPower.getAsDouble());
        },()->{
            runToPosition(getPosition(),1);
        });
    }
}
