package org.firstinspires.ftc.teamcode.WobbleArm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="wobbleArm", group = "LinearOpMode")
public class WobbleArm extends LinearOpMode {
    public DcMotor arm;
    public Servo grabber;
    public void runOpMode() throws InterruptedException {
        arm = hardwareMap.get(DcMotor.class, "wobbleArm");
        grabber = hardwareMap.get(Servo.class, "wobbleGrabber");

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.left_bumper == true){
                arm.setPower(0.4);
            }
            else if(gamepad1.right_bumper == true){
                arm.setPower(-0.4);
            }
            else arm.setPower(0);
            if(gamepad1.a == true && grabber.getPosition()>0.85){
                grabber.setPosition(0.49);
            }
            else if(gamepad1.a == true && grabber.getPosition()<0.85){
                grabber.setPosition(1);
            }
        }
    }
}
