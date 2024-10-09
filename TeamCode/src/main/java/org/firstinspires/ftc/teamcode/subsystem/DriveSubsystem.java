package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

import java.util.function.DoubleSupplier;

public class DriveSubsystem extends BIBSubsystemBase  {
    DcMotor leftDrive;
    DcMotor rightDrive;
    IMU imu;

    public DriveSubsystem(HardwareMap hwMap){
        //leftDrive = hwMap.dcMotor.get("leftDrive");
        //rightDrive = hwMap.dcMotor.get("rightDrive");
        imu = hwMap.get(IMU.class,"imu");
        imu.initialize(new IMU.Parameters(Constants.DriveConstants.params));
//        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }


    public double getHeading(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }
    public void resetImu(){
        imu.resetYaw();
    }

    public void drive(double lateralPower, double rotPower){
//        double leftPower = lateralPower + rotPower;
//        double rightPower = lateralPower - rotPower;
//        leftDrive.setPower(leftPower);
//        rightDrive.setPower(rightPower);
    }
    public void driveBumperCars(double leftPower, double rightPower){

//        leftDrive.setPower(leftPower);
//        rightDrive.setPower(rightPower);
    }


    public void printTelemetry(ColorfulTelemetry t) {
        t.addLine("");
//        t.addLine("RightDrive: pow: " + rightDrive.getPower() + " encod: " + rightDrive.getCurrentPosition());
//        t.addLine("LeftDrive: pow: " + leftDrive.getPower() + " encod: " + leftDrive.getCurrentPosition());
//        t.addLine("IMU Yaw: " + imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        t.addLine("IMU PITCH: " + imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));
        t.addLine("IMU Roll: " + imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES));

    }

    public CommandBase getDriveCommand(DoubleSupplier lateralPower, DoubleSupplier rotPower){
        return this.runEnd(()->{this.drive(lateralPower.getAsDouble(), rotPower.getAsDouble());}, ()->{
            this.drive(0,0);
        });
    }
    public CommandBase getBumperCarsDriveCommand(DoubleSupplier leftPower, DoubleSupplier rightPower){
        return this.runEnd(()->{this.driveBumperCars(leftPower.getAsDouble(), rightPower.getAsDouble());}, ()->{
            this.drive(0,0);
        });
    }


    @Override
    public void periodic() {

    }


}
