package org.firstinspires.ftc.teamcode.flywheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class flywheel extends LinearOpMode{

    public DcMotor flywheel;

    public void runOpMode() throws InterruptedException {
        flywheel = hardwareMap.get(DcMotor.class, "fw");
        flywheel.setDirection(DcMotor.Direction.FORWARD);

        int accel = 0;

        telemetry.addData("Init", true);
        telemetry.update();

        waitForStart();


        while(opModeIsActive()){

            if(gamepad1.a==true && accel<1) accel += 0.05;

            else{

                if(gamepad1.a==true && accel>=1) accel = 1;

                else if(accel>0 && gamepad1.a!=true) accel -= 0.05;

                else if(accel<=0  && gamepad1.a!=true) accel=0;

            }
            flywheel.setPower(accel);

            telemetry.addData("Speed" , accel);
            telemetry.update();

        }
    }
}
