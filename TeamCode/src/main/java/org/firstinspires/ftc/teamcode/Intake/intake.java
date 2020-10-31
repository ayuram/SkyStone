package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name="intake", group = "LinearOpMode")
public class intake extends LinearOpMode {
    public DcMotor intakeR, intakeL;
    public CRServo in1, in2;
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
                in1.setPower(1);
                in2.setPower(0);
            }
            else if(gamepad1.left_trigger > 0.01){
                intakeL.setPower(-gamepad1.left_trigger);
                intakeR.setPower(-gamepad1.left_trigger);
                in1.setPower(0);
                in2.setPower(1);
            }
            else {
                intakeL.setPower(0);
                intakeR.setPower(0);
                in1.setPower(0.5);
                in2.setPower(0.5);
            }
        }
    }
}
