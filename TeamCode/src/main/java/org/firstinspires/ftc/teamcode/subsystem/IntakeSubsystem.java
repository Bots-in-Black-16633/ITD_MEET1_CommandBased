package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class IntakeSubsystem extends BIBSubsystemBase{

    public CRServo intake;
    public int intakeState =0;//0rest,1intak,2outtake

    public IntakeSubsystem(HardwareMap hwMap){
        this.intake = hwMap.crservo.get("intake");
    }
    @Override
    void printTelemetry(ColorfulTelemetry t) {

    }
    public void intake(double power){
        intake.setPower(power);

       ;
    }
    public void outtake(double power){
        intake.setPower(-power);
    }
    public void rest(){
        intake.setPower(0);
    }


public CommandBase getIntakeCommand(BooleanSupplier goFast){
        return this.runEnd(()->{
            intake(goFast.getAsBoolean()?.5:1);
        },()->{
            rest();
        });
}
    public CommandBase getOuttakeCommand(BooleanSupplier goFast){
        return this.runEnd(()->{
            outtake(goFast.getAsBoolean()?1:.25);
        },()->{
            rest();
        });
    }

}
