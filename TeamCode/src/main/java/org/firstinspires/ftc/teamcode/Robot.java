package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.RobotUtilities.MovementVars;

public class Robot {
    public static boolean usingComputer = true;

    public Robot(){
        worldXPosition = 50;
        worldYPosition = 50;
        worldAngle_rad = Math.toRadians(45);
    }

    public static double xSpeed = 0;
    public static double ySpeed = 0;
    public static double turnSpeed = 0;

    public static double worldXPosition;
    public static double worldYPosition;
    public static double worldAngle_rad;

    public double getXPos(){
        return worldXPosition;
    }

    public double getYPos(){
        return worldYPosition;
    }

    public double getWorldAngle_rad(){
        return worldAngle_rad;
    }

    private long lastUpdateTime = 0;

    public void update(){
        long currentTimeMillis = System.currentTimeMillis();
        double elapsedTime = (currentTimeMillis - lastUpdateTime)/1000.0;
        lastUpdateTime = currentTimeMillis;
        if(elapsedTime > 1) return;

        double totalSpeed = Math.hypot(xSpeed, ySpeed);
        double angle = Math.atan2(xSpeed, ySpeed);
        double outputAngle = worldAngle_rad + angle;

        xSpeed += Range.clip((MovementVars.movement_x-xSpeed)/0.2,-1,1) * elapsedTime;
        ySpeed += Range.clip((MovementVars.movement_y-ySpeed)/0.2,-1,1) * elapsedTime;
        turnSpeed += Range.clip((MovementVars.movement_turn-turnSpeed)/0.2,-1,1) * elapsedTime;

        xSpeed *= 1.0 - (elapsedTime);
        ySpeed *= 1.0 - (elapsedTime);
        turnSpeed *= 1.0 - (elapsedTime);
    }

}
