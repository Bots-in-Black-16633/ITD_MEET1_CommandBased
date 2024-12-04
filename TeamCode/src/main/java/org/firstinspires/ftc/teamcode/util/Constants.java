package org.firstinspires.ftc.teamcode.util;

import androidx.annotation.NonNull;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;


public final class Constants {


    public static final class PivotConstants{

        public static final int submersibleIntake = 150;
        public static final int wallPickup = 1381;
        public static final int lowDelivery = 1270;
        public static final int highSpecimenDelivery = 1646;
        public static final int vertical = 1646;
        public static final int verticalAuto = 1400;


        public static final double degreesPerTick = 90/vertical;

        public static final int rest = 0;
        public static final int climbConstantPivot = 1370;

        public static final int clawWallPickup = 402;
        public static final int clawHighSpecimenDelivery = 1650;
    }
    public static final class DriveConstants{
        public static final RevHubOrientationOnRobot params = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
    }

    public static final class ExtendoConstants{
        public static final int fullExtension = 3091;//tuned
        public static final int halfExtension = 1500;//tuned
        public static final int submersibleInitialExtension = 600;
        public static final int lowBasket = 1270;//tuned
        public static final int highBasket = 3100;//tuned
        public static final int lowClip = 1000;

        public static final int highClipDeposit = 1750;
        public static final int zone1Climb = 1000;
        public static final int zone2Climb = 2000;
        public static final int rest = 0;
        public static final int highSpecimenDelivery = 2250;
        public static final int lowSpecimenDelivery = 900;

        public static final int wallSpecimenPickup = 370;

        public static final int climbConstant = 1400;
        //fr the new spinny claw
        public static final int clawWallPickup = 0;
        public static final int clawHighSpecimenDelivery = 826;

    }



    public static final class ClimberConstants{

    }
    public static final class ClawConstants{
        //passthrough
        public static final double faceBeltPos = .47;
        public static final double outwardFacing = .75;
        public static final double inwardFacing =1;
        public static final double highBasketOutput = .7;
        public static final double lowBasketOutput = .54;


        //twist
        public static final double openTowardsBelt = .9;
        public static final double openTowardsControlHub = .59;
        public static final double openOutwards = .26;
        public static final double openTowardsExpansion = 0;
        //back of servo wire is degree amount
        //back of servo  facing right is 0
        //positions increase from 0 by 45 degrees
        public static final double [] twistPositions = {.75, .62, .45, .345, .18};
        public static final double vertical = .11;
        public static final double horizantal= .75;

        //Claw

        public static final double clawClose = .8;
        public static final double clawOpen = .95;
    }

    public static final class WristConstants{
        public static final double facingBelts = .144;
        public static final double facingStraightParallel = .381;
        public static final double facingOppositeBelts = .654;
        public static final double highNetOuttake = 0.11;
        public static final double submersibleIntake = .32;

        public static final double autoIntake = .47;


        public static final double minimum = .06;
        public static final double maximum = .7;
        public static final double allTheWayBack = .05;


        public static final double dropPosition = .45;
        public static final double clawWallIntake = .18;
        public static final double clawHighSpecimenDelivery = 0;
    }
    public static class Util{
        public static double round(double in, int places){
            return ((int)(in * Math.pow(10,places)))/(double)Math.pow(10,places);
        }
    }

    public static class SpecimenClawConsants{
        public static double open = .43;
        public static double close = .71;

    }

    public static final class GrasperConstants {
        public static final double closed = 0;
        public static final double open = 0;
    }

}
