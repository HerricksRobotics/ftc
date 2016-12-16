package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by rpcardosimd on 11/29/16.
 */
@TeleOp(name = "button", group = "Rise Robot")

public class button extends OpMode {
    int x = 0;

    public void init() {


    }
        public void loop() {



            if (gamepad1.a) {
                x++;

            }

            if (x % 2 == 0) {
                telemetry.addData("even", x);
            } else {
                telemetry.addData("odd", x);
            }
        }
    }
