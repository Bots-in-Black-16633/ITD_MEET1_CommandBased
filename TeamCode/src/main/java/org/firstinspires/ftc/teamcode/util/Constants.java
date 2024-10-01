package org.firstinspires.ftc.teamcode.util;

import androidx.annotation.NonNull;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;


public final class Constants {


    public static final class PivotConstants{
        public static final int submersibleIntake = 150;
        public static final int vertical = 1300;
        public static final int wallPickup = 540;
        public static final int lowDelivery = 811;
        public static final int highSpecimenDelivery = 1030;


        public static final int rest = 0;
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
        public static final int highClip = 2000;
        public static final int zone1Climb = 1000;
        public static final int zone2Climb = 2000;
        public static final int rest = 0;
        public static final int highSpecimenDelivery = 1390;

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

        //Claw

        public static final double clawClose = .49;
        public static final double clawOpen = .75;
    }

}
