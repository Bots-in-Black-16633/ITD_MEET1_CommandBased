package org.firstinspires.ftc.teamcode.util;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.ExtendoSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.PivotSubsystem;

public final class Constants {


    public static final class PivotConstants{
        public static final int submersibleIntake = 280;
        public static final int vertical = 1400;
        public static final int deg45 = 907;

        public static final int rest = 0;
    }
    public static final class DriveConstants{
        public static final RevHubOrientationOnRobot params = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
    }

    public static final class ExtendoConstants{
        public static final int fullExtension = 3091;//tuned
        public static final int halfExtension = 1500;//tuned
        public static final int lowBasket = 1452;//tuned
        public static final int highBasket = 3000;//tuned
        public static final int lowClip = 1000;
        public static final int highClip = 2000;
        public static final int zone1Climb = 1000;
        public static final int zone2Climb = 2000;
        public static final int rest = 0;

    }



    public static final class ClimberConstants{

    }
    public static final class ClawConstants{
        //passthrough
        public static final double faceBeltPos = .47;
        public static final double outwardFacing = .75;
        public static final double inwardFacing =1;
        public static final double highBasketOutput = .63;

        //twist
        public static final double openTowardsBelt = .9;
        public static final double openTowardsControlHub = .59;
        public static final double openOutwards = .26;
        public static final double openTowardsExpansion = 0;

        //Claw

        public static final double clawClose = .58;
        public static final double clawOpen = .91;
    }

    public static final class ActionsClass{
       public static class ResetExtendoPivot implements  Action{
            PivotSubsystem p;
            ExtendoSubsystem e;
            Claw claw;

            public ResetExtendoPivot(PivotSubsystem p, ExtendoSubsystem e,Claw claw){
                this.p=p;
                this.e=e;
                this.claw=claw;
            }
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                e.runToPosition(ExtendoConstants.rest, 1);
                claw.getRestPosition();
                Actions.runBlocking(claw.getRestPosition());
                while(e.abovePosition(ExtendoConstants.rest+500)){

                }
                p.runToPosition(PivotConstants.rest,1);

                return false;
            }
        }

        public static class SubmersibleIntake implements Action{
            PivotSubsystem p;
            ExtendoSubsystem e;
            Claw claw;
            public SubmersibleIntake(PivotSubsystem p, ExtendoSubsystem e, Claw claw){
                this.p=p;this.e=e;this.claw=claw;
            }
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                p.runToPosition(PivotConstants.submersibleIntake,1);

                while(p.belowPosition(PivotConstants.submersibleIntake-10)){

                }
                e.runToPosition(ExtendoConstants.fullExtension, 1);
                Actions.runBlocking(claw.getVerticalIntake());

                return false;
            }
        }

        public static class HighBasketDelivery implements Action{
            PivotSubsystem p;
            ExtendoSubsystem e;
            Claw claw;
            public HighBasketDelivery(PivotSubsystem p, ExtendoSubsystem e,Claw claw){
                this.p=p;this.e=e;this.claw=claw;
            }
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                p.runToPosition(PivotConstants.vertical,1);
                while(p.belowPosition(PivotConstants.vertical-10)){

                }
                e.runToPosition(ExtendoConstants.highBasket, 1);
                Actions.runBlocking(claw.getOutputHighBasket());

                return false;
            }
        }

        public static class ResetExtendoPivotAsync implements  Action{
            PivotSubsystem p;
            ExtendoSubsystem e;
            Claw claw;

            public ResetExtendoPivotAsync(PivotSubsystem p, ExtendoSubsystem e,Claw claw){
                this.p=p;
                this.e=e;
                this.claw=claw;
            }
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                e.runToPosition(ExtendoConstants.rest, 1);
                claw.getRestPosition().run(telemetryPacket);
                if(e.abovePosition(ExtendoConstants.rest+500)){
                    p.runToPosition(PivotConstants.rest,1);

                }

                return !(Math.abs(e.getPosition()-ExtendoConstants.rest)<1) && (Math.abs(p.getPosition()-PivotConstants.rest)<1);
            }
        }


    }

}
