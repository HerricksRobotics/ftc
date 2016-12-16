package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.EncDrive;

/**
 * Created by Aeites on 12/7/2016.
 */
@TeleOp(name = "Encoder Test", group = "Rise Robot")
//@Autonomous(name = "Encoder Test", group = "Rise Robot")

public class EncoderTest extends OpMode {
    EncDrive ed;

    public void init() {
        ed = new EncDrive(hardwareMap, telemetry);
    }

    public void loop() {
//        ed.showEncPosition();
//        if (!moving) {
//            if (gamepad1.y) {
//                ed.forward(24, 0.5);
//                moving = true;
//            }
//            if (gamepad1.a) {
//                ed.resetEncoders();
//            }
//        } else {
//            if(!gamepad1.y){
//                ed.forward(0,0);
//                moving = false;
//            }
//        }
        //        ed.showEncPosition();

            if (gamepad1.y) {
                ed.gyroRotate(90, 1);
            }
    }
}