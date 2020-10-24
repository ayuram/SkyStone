package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name="intake", group = "LinearOpMode")
public class intake extends LinearOpMode {
    public DcMotor intakeR, intakeL;
    public void runOpMode() throws InterruptedException {
        intakeR = hardwareMap.get(DcMotor.class, "intakeR");
        intakeL = hardwareMap.get(DcMotor.class, "intakeL");
        intakeR.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeL.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.right_trigger > 0.01){
                intakeL.setPower(gamepad1.right_trigger);
                intakeR.setPower(gamepad1.right_trigger);
            }
            else if(gamepad1.left_trigger > 0.01){
                intakeL.setPower(-gamepad1.left_trigger);
                intakeR.setPower(-gamepad1.left_trigger);
            }
        }
    }
}
