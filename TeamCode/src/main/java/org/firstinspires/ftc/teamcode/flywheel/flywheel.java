package org.firstinspires.ftc.teamcode.flywheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="flywheel", group = "LinearOpMode")
public class flywheel extends LinearOpMode{

    public DcMotor flywheel, flywheel1;
    public Servo mag, flap;
    public void runOpMode() throws InterruptedException {
        flywheel = hardwareMap.get(DcMotor.class, "fw");
        flywheel1 = hardwareMap.get(DcMotor.class, "fw1");
        flywheel.setDirection(DcMotor.Direction.FORWARD);
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        mag = hardwareMap.get(Servo.class, "mag");
        flap = hardwareMap.get(Servo.class, "flap");
        //0.25, 0.5, x
        mag.setPosition(0.25);
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

            if(gamepad1.x==true && mag.getPosition()>0.35){
                mag.setPosition(0.25);
            }
            else if(gamepad1.x==true && mag.getPosition()<0.35){
                mag.setPosition(0.5);
            }
            if(gamepad1.x == true){
                flywheel.setPower(1);
                flywheel1.setPower(1);
                for(int i = 0; i<3; i++){
                    mag.setPosition(0.25);
                    wait(100);
                    mag.setPosition(0.5);
                    wait(100);
                }
            }
        }
    }

}
