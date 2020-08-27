package org.firstinspires.ftc.teamcode.PurePursuit;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Odometry.OdometryGlobalCoordinatePosition;

import static org.firstinspires.ftc.teamcode.PurePursuit.MathFunctions.*;
import static org.firstinspires.ftc.teamcode.RobotUtilities.MovementVars.*;
import static org.firstinspires.ftc.teamcode.company.Robot.*;

public class RobotMovement {

    static final double COUNTS_PER_INCH = 307.699557;

    static OdometryGlobalCoordinatePosition globalPositionUpdate;

    public static void goToPosition(double x, double y, double movementSpeed, double preferredAngle, double turnSpeed){

        double distanceToTarget = Math.hypot(x - worldXPosition, y - worldYPosition);

        double absoluteAngleToTarget = Math.atan2(x - worldXPosition, y - worldYPosition);

        double relativeAngleToPoint = AngleWrap(absoluteAngleToTarget - (worldAngle_rad));

        double relativeXToPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;
        double relativeYToPoint = Math.sin(relativeAngleToPoint) * distanceToTarget;

        double movementXPower = relativeXToPoint / (Math.abs(relativeXToPoint) + Math.abs(relativeYToPoint));
        double movementYPower = relativeYToPoint / (Math.abs(relativeXToPoint) + Math.abs(relativeYToPoint));

        movement_x = movementXPower;
        movement_y = movementYPower;

        double relativeTurnAngle = relativeAngleToPoint - Math.toRadians(180) + preferredAngle;

        movement_turn = Range.clip(relativeTurnAngle/Math.toRadians(30), -1, 1) * turnSpeed;

        if(distanceToTarget < 10){
            movement_turn = 0;
        }
    }


}
