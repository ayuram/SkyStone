package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {
    final double V = 50; // random value rn
    final double g = -9.81;

    public Servo angle;
    public DcMotor launchWheel;
    public double height;
    public double theta;

    public void findAngle(double x, double y, double goalX, double goalY, double h){
        double d = Math.hypot(Math.abs(x-goalX), Math.abs(y-goalY));
        double theta;
        double sintheta = (d+Math.sqrt(d*d + ((4*g*d*d)/(2*V*V)) - 4*h))/-2;
        if(sintheta > 1 || sintheta < 1){
            sintheta = sintheta * -1 + 2*d;
        }
        theta = Math.asin(sintheta);
        this.theta = theta;
    }
}
