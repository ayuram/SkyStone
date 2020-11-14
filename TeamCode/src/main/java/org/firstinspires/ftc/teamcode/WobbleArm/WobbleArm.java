package org.firstinspires.ftc.teamcode.WobbleArm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="wobbleArm", group = "LinearOpMode")
public class WobbleArm extends LinearOpMode {
    public Servo arm1, arm2;
    public Servo grabber;
    public void runOpMode() throws InterruptedException {
        arm1 = hardwareMap.get(Servo.class, "wobbleArm1");
        arm2 = hardwareMap.get(Servo.class, "wobbleArm2");
        arm1.setDirection( Servo.Direction.REVERSE);
        arm1.setPosition(0.05);
        arm2.setPosition ( 0.9 );
        grabber = hardwareMap.get(Servo.class, "wobbleGrabber");
        grabber.setPosition(0.58);


        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.left_bumper == true){
                arm1.setPosition(0.9);
                arm2.setPosition ( 0.05 );
            }
            else if(gamepad1.right_bumper == true){
                arm1.setPosition(0.05);
                arm2.setPosition ( 0.9 );
            }

            if(gamepad1.y == true && grabber.getPosition()>0.8){
                grabber.setPosition(0.58);
            }
            else if(gamepad1.y == true && grabber.getPosition()<0.8){
                grabber.setPosition(0.9);
            }
        }
    }
}
