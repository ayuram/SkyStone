package org.firstinspires.ftc.teamcode.PurePursuit;

import org.firstinspires.ftc.teamcode.Odometry.OdometryGlobalCoordinatePosition;

import static org.firstinspires.ftc.teamcode.PurePursuit.MyOpmode.movement_x;
import static org.firstinspires.ftc.teamcode.PurePursuit.MyOpmode.movement_y;

public class RobotMovement {

    static final double COUNTS_PER_INCH = 307.699557;

    static OdometryGlobalCoordinatePosition globalPositionUpdate;

    public static void goToPosition(double _targetXPosition, double _targetYPosition, double robotPower){
        double targetXPosition = _targetXPosition * COUNTS_PER_INCH;
        double targetYPosition = _targetYPosition * COUNTS_PER_INCH;

        double distanceToXTarget = targetXPosition - globalPositionUpdate.returnXCoordinate();
        double distanceToYTarget = targetYPosition - globalPositionUpdate.returnYCoordinate();

        double robotMovementAngle = Math.toDegrees(Math.atan2(distanceToXTarget, distanceToYTarget));

        double robot_movement_x_component = calculateX(robotMovementAngle, robotPower);
        double robot_movement_y_component = calculateY(robotMovementAngle, robotPower);

        movement_x = robot_movement_x_component;
        movement_y = robot_movement_y_component;

    }

    /**
     * Calculate the power in the x direction
     * @param desiredAngle angle on the x axis
     * @param speed robot's speed
     * @return the x vector
     */
    private static double calculateX(double desiredAngle, double speed) {
        return Math.sin(Math.toRadians(desiredAngle)) * speed;
    }

    /**
     * Calculate the power in the y direction
     * @param desiredAngle angle on the y axis
     * @param speed robot's speed
     * @return the y vector
     */
    private static double calculateY(double desiredAngle, double speed) {
        return Math.cos(Math.toRadians(desiredAngle)) * speed;
    }
}
