package org.firstinspires.ftc.teamcode.PurePursuit;

import org.firstinspires.ftc.teamcode.Odometry.OdometryGlobalCoordinatePosition;

import static org.firstinspires.ftc.teamcode.PurePursuit.MathFunctions.*;
import static org.firstinspires.ftc.teamcode.PurePursuit.MyOpmode.*;

public class RobotMovement {

    static final double COUNTS_PER_INCH = 307.699557;

    static OdometryGlobalCoordinatePosition globalPositionUpdate;

    public static void goToPosition(double x, double y, double movementSpeed){

        double distanceToTarget = Math.hypot(x - globalPositionUpdate.returnXCoordinate(), y - globalPositionUpdate.returnYCoordinate());

        double absoluteAngleToTarget = Math.atan2(x - globalPositionUpdate.returnXCoordinate(), y - globalPositionUpdate.returnYCoordinate());

        double relativeAngleToPoint = AngleWrap(absoluteAngleToTarget - (globalPositionUpdate.returnOrientation()));

        double relativeXToPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;
        double relativeYToPoint = Math.sin(relativeAngleToPoint) * distanceToTarget;

        double movementXPower = relativeXToPoint / (Math.abs(relativeXToPoint) + Math.abs(relativeYToPoint));
        double movementYPower = relativeYToPoint / (Math.abs(relativeXToPoint) + Math.abs(relativeYToPoint));

        movement_x = movementXPower;
        movement_y = movementYPower;

    }


}
