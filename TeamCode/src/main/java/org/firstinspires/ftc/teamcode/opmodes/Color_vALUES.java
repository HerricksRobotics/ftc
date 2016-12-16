package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
@Autonomous(name = "Color_Finding", group = "Rise Robot")

/**
 * Created by rpcardosimd on 11/8/16.
 */

public class Color_vALUES extends OpMode {

    ColorSensor front_color;


    public void init() {

        front_color = hardwareMap.colorSensor.get("front");
    }

    public void loop() {

        front_color.enableLed(false);

        telemetry.addData("Red", front_color.red());// getting telemetry for the color red
        telemetry.addData("alpha", front_color.alpha());//getting telemetry, color values for alpha
        telemetry.addData("blue", front_color.blue());//
        telemetry.addData("green", front_color.green());
        telemetry.addData("hue", front_color.argb());
    }
}
