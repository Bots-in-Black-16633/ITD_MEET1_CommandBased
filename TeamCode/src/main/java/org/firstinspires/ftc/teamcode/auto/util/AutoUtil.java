package org.firstinspires.ftc.teamcode.auto.util;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

public class AutoUtil {
        //Net side start (close to baskets)
        public static final int NET = 0;

        //Observation side start(farm from baskets)
        public static final int OBS = 1;


        //Starting Pose2d from the net side
        public static final Pose2d NETSTART = new Pose2d(new Vector2d(-36, -63), Math.toRadians(90));
        public static final Pose2d OBSSTART = new Pose2d(12, -63, Math.toRadians(90));

        //Base robot's drive subsystem
        private DriveSubsystem drive;

        public AutoUtil(DriveSubsystem drive) {
                this.drive=drive;
        }

        public Action getStartToNetAction(int start) {
                if(start==NET) {
                        return drive.actionBuilder(NETSTART)
                                .strafeToLinearHeading(new Vector2d(-57,-56), Math.toRadians(45))
                                .build();
                }
                else {
                        return drive.actionBuilder(OBSSTART)
                                .strafeToLinearHeading(new Vector2d(-57,-56), Math.toRadians(45))
                                .build();
                }
        }
}
