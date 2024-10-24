package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;

public class SpecimenClaw extends SubsystemBase {
    Servo s;

    public SpecimenClaw(HardwareMap hwMap){
        s = hwMap.servo.get("grasper");
    }

    public void open(){
        s.setPosition(Constants.SpecimenClawConsants.open);
    }public void close(){
        s.setPosition(Constants.SpecimenClawConsants.close);
    }

}
