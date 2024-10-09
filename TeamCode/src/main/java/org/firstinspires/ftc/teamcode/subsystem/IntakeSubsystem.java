package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

public class IntakeSubsystem extends BIBSubsystemBase{

    public CRServo intake;
    public int intakeState =0;//0rest,1intak,2outtake

    public IntakeSubsystem(HardwareMap hwMap){
        this.intake = hwMap.crservo.get("intake");
    }
    @Override
    void printTelemetry(ColorfulTelemetry t) {

    }

    public void toggleIntakeRest(){
        if(intakeState==0){intakeState = 1;intake();}
            else {intakeState = 0;rest();}
    }
    public void toggleOuttakeRest(){
        if(intakeState==0){intakeState = 2;outtake();}
        else {intakeState = 0;rest();}
    }

    public void intake(){
        intake.setPower(1);
    }
    public void outtake(){
        intake.setPower(-1);
    }
    public void rest(){
        intake.setPower(0);
    }


public CommandBase getIntakeCommand(){
        return this.runEnd(()->{
            intake();
        },()->{
            rest();
        });
}
    public CommandBase getOuttakeCommand(){
        return this.runEnd(()->{
            outtake();
        },()->{
            rest();
        });
    }

}
