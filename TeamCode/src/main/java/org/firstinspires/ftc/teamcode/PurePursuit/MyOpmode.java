package org.firstinspires.ftc.teamcode.PurePursuit;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class MyOpmode extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        RobotMovement.goToPosition(24, 24, 0.5, 0, 0.5);

    }
}
