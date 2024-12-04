package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

import java.util.function.DoubleSupplier;

public class WristSubsystem extends BIBSubsystemBase{
    Servo wrist;

    public WristSubsystem(HardwareMap hwMap){
        wrist = hwMap.servo.get("wrist");
    }
    @Override
    void printTelemetry(ColorfulTelemetry t) {
    t.addLine();
        t.addLine("WRISST SUBSYSTEM");
        t.addLine("position"+wrist.getPosition());
    }

    public void setWallClawPickupPosition(){
        wrist.setPosition(Constants.WristConstants.clawWallIntake);

    }

    public void setClawHgihSpecimen(){
        wrist.setPosition(Constants.WristConstants.clawHighSpecimenDelivery);

    }

    public void setFacingBelt(){
        wrist.setPosition(Constants.WristConstants.facingBelts);
    }
    public void setFacingStraightParallelToSlider(){
        wrist.setPosition(Constants.WristConstants.facingStraightParallel);
    }
    public void setAutoIntake(){
        wrist.setPosition(Constants.WristConstants.autoIntake);
    }
    public void setFacingOppositeBelts(){
        wrist.setPosition(Constants.WristConstants.facingOppositeBelts);
    }
    public void setOuttakeHighBasket(){
        wrist.setPosition(Constants.WristConstants.highNetOuttake);
    }
    public void setSubmersibleIntake(){
        wrist.setPosition(Constants.WristConstants.submersibleIntake);
    }
    public void setAllTheWayBack(){
        wrist.setPosition(Constants.WristConstants.allTheWayBack);
    }



    public CommandBase getManualControlCommand(DoubleSupplier pow){
        return this.runEnd(()->{
            wrist.setPosition(wrist.getPosition()+pow.getAsDouble()*.025);
        },()->{
            return;
        });
    }

    public void setDropPosition() {
        wrist.setPosition(Constants.WristConstants.dropPosition);
    }
}
