package org.firstinspires.ftc.teamcode.flywheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


public class flywheel extends LinearOpMode{

    public DcMotor flywheel, flywheel1;
    public Servo angler;
    public void runOpMode() throws InterruptedException {
        flywheel = hardwareMap.get(DcMotor.class, "fw");
        flywheel1 = hardwareMap.get(DcMotor.class, "fw1");
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        angler = hardwareMap.get(Servo.class, "angler");
        //0.25, 0.5, x
        angler.setPosition(0.25);
        telemetry.addData("Init", true);
        telemetry.update();

        waitForStart();


        while(opModeIsActive()){

            if(gamepad1.right_trigger>0.1){
                flywheel.setPower(1);
                flywheel1.setPower(1);
            }
            else{
                flywheel.setPower(0);
                flywheel1.setPower(0);
            }

            if(gamepad1.x==true && angler.getPosition()>0.35){
                angler.setPosition(0.25);
            }
            else if(gamepad1.x==true && angler.getPosition()<0.35){
                angler.setPosition(0.5);
            }





        }
    }
}
