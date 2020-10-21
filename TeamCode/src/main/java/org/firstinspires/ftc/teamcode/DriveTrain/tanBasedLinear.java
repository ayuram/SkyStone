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
        //double x, y, turn, mag, angle, d1, d2;

        while(opModeIsActive()){
            /*
            //input values
            x = -gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;

            //find angle of joystick
            angle = Math.atan2(y,x);

            //find magnitude of joystick
            mag = Math.sqrt(x*x + y*y);

            //find the speed of frontLeft and backRight
            d1 = Math.sin(angle-Math.PI/4) * mag + turn;
            //find the speed of backLeft and frontRight
            d2 = Math.sin(angle+Math.PI/4) * mag + turn;

            //prevent going over 1
            if(Math.abs(d1)>1){
                d1 = d1/Math.abs(d1);
                d2 = d2/Math.abs(d1);
            }
            else if(Math.abs(d2)>1){
                d1 = d1/Math.abs(d2);
                d2 = d2/Math.abs(d2);
            }

            //set power
            fl.setPower(d1);
            br.setPower(d1);
            bl.setPower(d2);
            fr.setPower(d2);
            */
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;

            fl.setPower(v1);
            fr.setPower(v2);
            bl.setPower(v3);
            br.setPower(v4);
        }
    }
}
