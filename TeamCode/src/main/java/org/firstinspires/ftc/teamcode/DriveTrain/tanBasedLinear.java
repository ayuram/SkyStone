package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name="TanDriveLinear", group = "LinearOpMode")
public class tanBasedLinear extends LinearOpMode {

    public DcMotor fl, fr, bl,br;

    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class , "fl");
        bl = hardwareMap.get(DcMotor.class , "bl");
        fr = hardwareMap.get(DcMotor.class , "fr");
        br = hardwareMap.get(DcMotor.class , "br");

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        waitForStart();
        boolean ninja = false;
        double lastTime = System.currentTimeMillis();
        while(opModeIsActive()){

            double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x*0.8;
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;
            if(ninja == false && gamepad1.a == true && System.currentTimeMillis() >= lastTime + 300){
                ninja = true;
                lastTime = System.currentTimeMillis();
            }
            else if(ninja == true && gamepad1.a == true && System.currentTimeMillis() >= lastTime + 300){
                ninja = false;
                lastTime = System.currentTimeMillis();
            }
            if(ninja==true){
                v1 /= 0.1;
                v2 /= 0.1;
                v3 /= 0.1;
                v4 /= 0.1;
            }
            fl.setPower(v1);
            fr.setPower(v2);
            bl.setPower(v3);
            br.setPower(v4);
        }
    }
}
