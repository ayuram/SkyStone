package org.firstinspires.ftc.teamcode.DriveTrain;

public class teleOp extends Robot {

    public void start(){};

    public void loop(){
        double x, y, turn, mag, angle, d1, d2;
        while(true){
            //input values
            x = gamepad1.left_stick_x;
            y = gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;

            //find angle of joystick
            angle = Math.atan2(y,x);

            //find magnitude of joystick
            mag = Math.sqrt(x*x + y*y);

            //find the speed of frontLeft and backRight
            d1 = Math.sin(angle-Math.PI/4) * mag + turn;
            //find the speed of backLeft and frontRight
            d2 = Math.sin(angle+Math.PI/4) * mag + turn;

            //prevent going over 1
            if(Math.abs(d1)>1){
                d1 = d1/Math.abs(d1);
                d2 = d2/Math.abs(d1);
            }
            else if(Math.abs(d2)>1){
                d1 = d1/Math.abs(d2);
                d2 = d2/Math.abs(d2);
            }

            //set power
            fl.setPower(d1);
            br.setPower(d1);
            bl.setPower(d2);
            fr.setPower(d2);
        }
    }
    public void stop(){

    }
}
