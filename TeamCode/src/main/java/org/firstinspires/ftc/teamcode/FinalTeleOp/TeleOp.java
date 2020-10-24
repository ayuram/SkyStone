package org.firstinspires.ftc.teamcode.FinalTeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TeleOp {

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
        while(opModeIsActive()){

            double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x*0.8;
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;
            if(ninja == false && gamepad1.a == true){
                ninja = true;
            }
            else if(ninja == true && gamepad1.a == true){
                ninja = false;
            }
            if(ninja==true){
                v1 /= 0.5;
                v2 /= 0.5;
                v3 /= 0.5;
                v4 /= 0.5;
            }
            fl.setPower(v1);
            fr.setPower(v2);
            bl.setPower(v3);
            br.setPower(v4);
        }
    }
}
