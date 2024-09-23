package org.firstinspires.ftc.teamcode.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

public class Claw implements SubsystemBase {

    Servo passthrough;
    Servo twist;
    Servo claw;
    public Claw(HardwareMap hwMap){
        passthrough = hwMap.servo.get("passthrough");
        twist = hwMap.servo.get("twist");
        claw = hwMap.servo.get("claw");
    }

    public void setTwistPosition(double position){
        twist.setPosition(position);
    }
    public void setPassthroughPosition(double position){
        passthrough.setPosition(position);
    }
    public void setClawPosition(double position){
        claw.setPosition(position);
    }
    public void open(){
        claw.setPosition(Constants.ClawConstants.clawOpen);
    }
    public void close(){claw.setPosition(Constants.ClawConstants.clawClose);}
    public double getClawPosition(){return claw.getPosition();}
    public double getPassThroughPosition(){return passthrough.getPosition();}
    public double getTwistPosition(){
        return twist.getPosition();
    }



    public Action getOutputHighBasket(){
        return new Action(){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                twist.setPosition(Constants.ClawConstants.openTowardsBelt);
                while(Math.abs(getTwistPosition()- Constants.ClawConstants.openTowardsBelt) > .1){

                }
                passthrough.setPosition(Constants.ClawConstants.highBasketOutput);


                return false;
            }
        };
    }
    public Action getRestPosition(){
        return new Action(){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                twist.setPosition(Constants.ClawConstants.openOutwards);

                passthrough.setPosition(Constants.ClawConstants.outwardFacing);


                return false;
            }
        };
    }
    public Action getVerticalInput(){
        return new Action(){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                twist.setPosition(Constants.ClawConstants.openOutwards);
                while(Math.abs(getTwistPosition()- Constants.ClawConstants.openOutwards) > .1){

                }
                passthrough.setPosition(Constants.ClawConstants.outwardFacing);


                return false;
            }
        };
    }
    public Action getHorizantalIntake(){
        return new Action(){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {

                twist.setPosition(Constants.ClawConstants.openTowardsExpansion);

                passthrough.setPosition(Constants.ClawConstants.inwardFacing);


                return false;
            }
        };
    }
    public Action getVerticalIntake(){
        return new Action(){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {

                twist.setPosition(Constants.ClawConstants.openOutwards);

                passthrough.setPosition(Constants.ClawConstants.inwardFacing);


                return false;
            }
        };
    }

    @Override
    public void printTelemetry(ColorfulTelemetry t) {

    }

    @Override
    public void periodic() {

    }
}
