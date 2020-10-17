package org.firstinspires.ftc.teamcode.flywheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class flywheel extends LinearOpMode{

    public DcMotor flywheel, flywheel1;

    public void runOpMode() throws InterruptedException {
        flywheel = hardwareMap.get(DcMotor.class, "fw");
        flywheel1 = hardwareMap.get(DcMotor.class, "fw1");
        flywheel.setDirection(DcMotor.Direction.FORWARD);
        


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





        }
    }
}
