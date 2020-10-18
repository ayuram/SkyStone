package org.firstinspires.ftc.teamcode.WobbleArm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleArm extends LinearOpMode {
    public DcMotor arm;
    public Servo grabber;
    public void runOpMode() throws InterruptedException {
        arm = hardwareMap.get(DcMotor.class, "wobbleArm");
        grabber = hardwareMap.get(Servo.class, "wobbleGrabber");

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.left_bumper == true){
                arm.setPower(0.25);
            }
            else if(gamepad1.left_trigger >0.1){
                arm.setPower(-0.25);
            }
            else arm.setPower(0);
        }
    }
}
