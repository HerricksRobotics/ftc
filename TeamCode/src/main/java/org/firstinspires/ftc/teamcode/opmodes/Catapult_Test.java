package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by rpcardosimd on 11/21/16.
 */
@TeleOp(name = "catapult", group = "Rise Robot")

public class Catapult_Test extends OpMode {

    DcMotor catapult;


    public void init() {

        catapult = hardwareMap.dcMotor.get("catapult");//getting the DcMotor from the hardwaremap and naming it
    }

    public void loop() {

        if (gamepad1.a) {//making an if statement for the gamepad button a

            catapult.setPower(1);//if the gamepad button a is pressed then set the choo choo motor to the speed 0.4
        }
    }
}