package org.firstinspires.ftc.teamcode.PurePursuit;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MyOpmode extends OpMode {

    static double movement_x;
    static double movement_y;

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        RobotMovement.goToPosition(24, 24, 0.5);

    }
}
