package org.firstinspires.ftc.teamcode.FinalTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOpFinal", group = "LinearOpMode")
public class TeleOpFinal extends LinearOpMode {

    public DcMotor fl, fr, bl,br;
    public DcMotor intakeR, intakeL;
    public DcMotor flywheel, flywheel1;
    public Servo mag, flap, tilt;
    public Servo in1, in2;
    public Servo arm1, arm2;
    public Servo grabber;
    public boolean ninja = false, reverse = false, magUp = false;
    public double multiplier = 1;
    public double lastTime = System.currentTimeMillis();
    public boolean flapUp = false, armUp = false;

    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while(opModeIsActive()){

            drive();

            wobbleArm();

            intake();

            if(gamepad2.b==true && flapUp == false){
                flap.setPosition(0.05);
                flapUp = true;
            }
            else if(gamepad2.b==true && flapUp == true){
                flap.setPosition(0.04);
                flapUp = false;
            }

            if(gamepad2.right_bumper==true){

                sleep(400);
                mag.setPosition(0.2);
                sleep(300);
                mag.setPosition(0);
                sleep(300);
                tilt.setPosition(0.1);
            }
            if(gamepad2.left_trigger >=0.1){
                tilt.setPosition(0.32);
                flywheel.setPower(-1);
                flywheel1.setPower(-1);
            }
            else{
                tilt.setPosition(0.1);
                flywheel.setPower(0);
                flywheel1.setPower(0);

            }
            if(gamepad2.right_trigger >= 0.1){
                int i = 0;
                while(i<3){

                    mag.setPosition(0.2);
                    sleep(500);
                    mag.setPosition(0);
                    sleep(500);
                    i++;
                    telemetry.addData("i", i);
                    telemetry.update();

                }
            }

        }
    }
    public void initialize(){
        fl = hardwareMap.get(DcMotor.class , "fl"); //green
        bl = hardwareMap.get(DcMotor.class , "bl"); //red
        fr = hardwareMap.get(DcMotor.class , "fr"); //blue
        br = hardwareMap.get(DcMotor.class , "br"); //white
        intakeR = hardwareMap.get(DcMotor.class, "intakeR"); //green
        intakeL = hardwareMap.get(DcMotor.class, "intakeL"); //red
        in1 = hardwareMap.get(Servo.class, "in1");
        in2 = hardwareMap.get(Servo.class, "in2");

        intakeR.setDirection( DcMotorSimple.Direction.FORWARD);
        intakeL.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        flywheel = hardwareMap.get(DcMotor.class, "fw");//black
        flywheel1 = hardwareMap.get(DcMotor.class, "fw1");//silver
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        flywheel1.setDirection(DcMotor.Direction.REVERSE);
        mag = hardwareMap.get(Servo.class, "mag");
        flap = hardwareMap.get(Servo.class, "flap");
        tilt = hardwareMap.get(Servo.class, "tilt");
        arm1 = hardwareMap.get(Servo.class, "wobbleArm1");
        arm2 = hardwareMap.get(Servo.class, "wobbleArm2");
        arm1.setDirection( Servo.Direction.REVERSE);
        arm1.setPosition(0.05);
        arm2.setPosition ( 0.9 );
        grabber = hardwareMap.get(Servo.class, "wobbleGrabber");
        grabber.setPosition(0.58);
        //0.25, 0.5, x
        mag.setPosition(0);
        tilt.setPosition(0.1);
        flap.setPosition(0.04);
        telemetry.addData("Status", "Initialized");
    }
    public void drive(){
        double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x*0.8;
        double v1 = r * Math.cos(robotAngle)*multiplier + rightX;
        double v2 = r * Math.sin(robotAngle)*multiplier - rightX;
        double v3 = r * Math.sin(robotAngle)*multiplier + rightX;
        double v4 = r * Math.cos(robotAngle)*multiplier - rightX;
        if(ninja == false && gamepad1.left_bumper == true && System.currentTimeMillis() >= lastTime + 300){
            ninja = true;
            lastTime = System.currentTimeMillis();
        }
        else if(ninja == true && gamepad1.left_bumper == true && System.currentTimeMillis() >= lastTime + 300){
            ninja = false;
            lastTime = System.currentTimeMillis();
        }
        if(reverse == false && gamepad1.right_bumper == true && System.currentTimeMillis() >= lastTime + 300){
            reverse = true;
            lastTime = System.currentTimeMillis();
        }
        else if(reverse == true && gamepad1.right_bumper == true && System.currentTimeMillis() >= lastTime + 300){
            reverse = false;
            lastTime = System.currentTimeMillis();
        }
        if(ninja==true){
            v1 /= 3;
            v2 /= 3;
            v3 /= 3;
            v4 /= 3;
        }
        if(reverse==true){
            multiplier = -1;
        }
        else{
            multiplier = 1;
        }
        fl.setPower(v1);
        fr.setPower(v2);
        bl.setPower(v3);
        br.setPower(v4);
    }
    public void wobbleArm(){
        if(gamepad2.a == true && armUp == false){
            arm1.setPosition(0.9);
            arm2.setPosition ( 0.05 );
            armUp = true;
        }
        else if(gamepad2.a == true && armUp == true){
            arm1.setPosition(0.05);
            arm2.setPosition ( 0.9 );
            armUp = false;
        }

        if(gamepad2.y == true && grabber.getPosition()>0.8){
            grabber.setPosition(0.58);
        }
        else if(gamepad2.y == true && grabber.getPosition()<0.8){
            grabber.setPosition(0.9);
        }
    }
    public void intake(){
        double intakeSpeed = -gamepad2.right_stick_y;
        intakeL.setPower(intakeSpeed);
        intakeR.setPower(intakeSpeed);
        if(intakeSpeed<0){
            in1.setPosition(1);
            in2.setPosition(0);
        }
        else if(intakeSpeed>0){
            in1.setPosition(0);
            in2.setPosition(1);
        }
        else{
            in1.setPosition(0.5);
            in2.setPosition(0.5);
        }
    }
}
