package org.firstinspires.ftc.teamcode.DriveTrain;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name="ArcadeDrive", group = "IterativeOpMode")
public class ArcadeBasedDrive extends Robot {
    public void start(){};

    public void loop(){



        double x,y, rx;

        while(true){
            //input values
            y = -gamepad1.left_stick_y; // Remember, this is reversed!
            x = gamepad1.right_stick_x;
            rx = gamepad1.left_stick_x;

            fl.setPower(y + x + rx);
            bl.setPower(y + x - rx);
            fr.setPower(y - x - rx);
            br.setPower(y - x + rx);


        }


    }
    public void stop(){

    }
}
