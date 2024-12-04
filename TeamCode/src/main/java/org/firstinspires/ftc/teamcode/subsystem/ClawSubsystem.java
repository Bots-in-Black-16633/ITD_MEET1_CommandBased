package org.firstinspires.ftc.teamcode.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

import java.util.function.DoubleSupplier;

public class ClawSubsystem extends BIBSubsystemBase {

    Servo twist;
    Servo claw;
    int twistState = Constants.ClawConstants.twistPositions.length/2;
    int lastTwistState = 0;
    boolean clawOpen = false;
    boolean twistIsHorizontal = false;
    public ClawSubsystem(HardwareMap hwMap){
        twist = hwMap.servo.get("twist");
        claw = hwMap.servo.get("claw");
    }

    public void setTwistPosition(double position){
        twist.setPosition(position);
    }
    public void setTwistState(int k) {twistState=k;}
    public void twistCounterclockwise() {if(twistState!=Constants.ClawConstants.twistPositions.length-1)twistState++;
    /**  (IF WE WANT TO MAKE IT CONTINUOUS) else twistState=0;**/}
    public void twistClockwise() {
        if(twistState!=0)twistState--;
        /** (IF WE WANT TO MAKE IT CONTINUOUS) else twistState=Constants.ClawConstants.twistPositions.length-1;**/
    }
    public void setClawPosition(double position){
        claw.setPosition(position);
    }
    public void open(){
        claw.setPosition(Constants.ClawConstants.clawOpen);
        clawOpen = true;
    }
    public void close(){claw.setPosition(Constants.ClawConstants.clawClose);clawOpen = false;}
    public double getClawPosition(){return claw.getPosition();}
    public double getTwistPosition(){
        return twist.getPosition();
    }

    public void toggleClawOpenClose(){
        if(clawOpen)close();
        else open();
    }
    public void toggleTwistHorizontalVertical(){
        if(twistIsHorizontal)twistVerticalIntake();
        else twistHorizontal();
    }
    public void twistHorizontal(){
        twist.setPosition(Constants.ClawConstants.openTowardsControlHub);
        twistIsHorizontal = true;

    }
    public void twistVerticalIntake(){
        twist.setPosition(Constants.ClawConstants.openTowardsBelt);
        twistIsHorizontal = false;

    }

    //Commands
    public void setOutputHighBasket(){
        setTwistPosition(Constants.ClawConstants.openTowardsControlHub);
    }
    public void setOutputLowBasket(){
        setTwistPosition(Constants.ClawConstants.openTowardsControlHub);
    }
    public void setRestPosition(){
        close();
        twistHorizontal();
    }



   public void setHorizontalIntake(){
        twistHorizontal();
   }

    public void setVerticalIntake(){
        twistVerticalIntake();

    }
    public void specimenDelivery(){
        twist.setPosition(Constants.ClawConstants.openTowardsControlHub);

    }
    public CommandBase getManualTwistControlCommand(DoubleSupplier pow){
        return this.runEnd(()->{
            twist.setPosition(twist.getPosition()+pow.getAsDouble()*.025);
        },()->{
            return;
        });
    }


    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        t.addLine();
        t.addLine("Claw: " + (clawOpen?"Open":"Closed"));
        t.addLine("ClawPos: " + getClawPosition());
        t.addLine("Twist State: " + twistState);
        t.addLine("Twist: " + getTwistPosition());

    }

    @Override
    public void periodic() {
        if(twistState!=lastTwistState) {
            setTwistPosition(Constants.ClawConstants.twistPositions[twistState%Constants.ClawConstants.twistPositions.length]);
        }

        lastTwistState=twistState;

    }
}
