package org.firstinspires.ftc.teamcode.FinalTeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group = "LinearOpMode")
public class TeleOp extends LinearOpMode{

    public DcMotor fl, fr, bl,br;
    public DcMotor intakeR, intakeL;
    public DcMotor flywheel, flywheel1;
    public Servo mag, flap;
    public CRServo in1, in2;
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class , "fl");
        bl = hardwareMap.get(DcMotor.class , "bl");
        fr = hardwareMap.get(DcMotor.class , "fr");
        br = hardwareMap.get(DcMotor.class , "br");
        intakeR = hardwareMap.get(DcMotor.class, "intakeR");
        intakeL = hardwareMap.get(DcMotor.class, "intakeL");
        in1 = hardwareMap.get(CRServo.class, "in1");
        in2 = hardwareMap.get(CRServo.class, "in2");
        intakeR.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeL.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        flywheel = hardwareMap.get(DcMotor.class, "fw");
        flywheel1 = hardwareMap.get(DcMotor.class, "fw1");
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        flywheel1.setDirection(DcMotor.Direction.REVERSE);
        mag = hardwareMap.get(Servo.class, "mag");
        flap = hardwareMap.get(Servo.class, "flap");
        //0.25, 0.5, x
        mag.setPosition(0.25);
        telemetry.addData("Status", "Initialized");
        waitForStart();
        boolean ninja = false;
        double lastTime = System.currentTimeMillis();
        while(opModeIsActive()){

            double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x*0.8;
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;
            if(ninja == false && gamepad1.a == true && System.currentTimeMillis() >= lastTime + 300){
                ninja = true;
                lastTime = System.currentTimeMillis();
            }
            else if(ninja == true && gamepad1.a == true && System.currentTimeMillis() >= lastTime + 300){
                ninja = false;
                lastTime = System.currentTimeMillis();
            }
            if(ninja==true){
                v1 /= 3;
                v2 /= 3;
                v3 /= 3;
                v4 /= 3;
            }
            fl.setPower(v1);
            fr.setPower(v2);
            bl.setPower(v3);
            br.setPower(v4);
            if(gamepad1.right_trigger > 0.01){
                intakeL.setPower(-gamepad1.right_trigger);
                intakeR.setPower(-gamepad1.right_trigger);
                in1.setPower(1);
                in2.setPower(0);
            }
            else if(gamepad1.left_trigger > 0.01){
                intakeL.setPower(gamepad1.left_trigger);
                intakeR.setPower(gamepad1.left_trigger);
                in1.setPower(0);
                in2.setPower(1);
            }
            else {
                intakeL.setPower(0);
                intakeR.setPower(0);
                in1.setPower(0.5);
                in2.setPower(0.5);
            }
            if(gamepad2.right_trigger>0.1){
                flywheel.setPower(1);
                flywheel1.setPower(1);
            }
            else if(gamepad2.left_trigger >0.1){
                flywheel.setPower(-1);
                flywheel1.setPower(-1);
            }
            else{
                flywheel.setPower(0);
                flywheel1.setPower(0);
            }
            if(gamepad2.left_bumper==true){
                flap.setPosition(1);
            }
            if(gamepad2.x==true){
                mag.setPosition(0.25);
                mag.setPosition(0.5);
            }
        }
    }


}
