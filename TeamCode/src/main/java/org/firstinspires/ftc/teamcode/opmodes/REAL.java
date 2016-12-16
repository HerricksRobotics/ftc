package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "AUTO_Sparky", group = "Rise Robot")


/* Sean Cardosi
 * Team Rise 7719
 * Created by rpcardosimd on 6/18/16.
 */
public class REAL extends OpMode {

    //ColorSensor bottom;
    //ColorSensor front;
    TouchSensor left;
    TouchSensor right;
    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    ColorSensor front_color;//naming color sensors
    Servo left_pusher;
    Servo right_pusher;
    final int Red = 0;

    public void init() {


//        bottom = hardwareMap.colorSensor.get("bottom");
//        front = hardwareMap.colorSensor.get("front");


        left = hardwareMap.touchSensor.get("left");
        right = hardwareMap.touchSensor.get("right");

        front_color = hardwareMap.colorSensor.get("front");

        left_back_drive = hardwareMap.dcMotor.get("1");  // give left_back_drive the name 1
        left_front_drive = hardwareMap.dcMotor.get("2");  // give left_front_drive the name 2
        left_back_drive.setDirection(DcMotor.Direction.FORWARD);  // reverse the left_back_drive
        left_front_drive.setDirection(DcMotor.Direction.FORWARD);  // reverse the left_front_drive


        right_back_drive = hardwareMap.dcMotor.get("3");  // you probably know what we do here by now
        right_front_drive = hardwareMap.dcMotor.get("4");  // and here
        right_back_drive.setDirection(DcMotor.Direction.REVERSE);  // here we just tell the motors listed to go forward
        right_front_drive.setDirection(DcMotor.Direction.FORWARD);//and not reversed

        left_pusher = hardwareMap.servo.get("left_pusher");
        right_pusher = hardwareMap.servo.get("right_pusher");

    }

    public void loop() {
        telemetry.addData("value", left.getValue());


        if (left.isPressed() && (right.isPressed())) { // if both touch sensors are pressed
            left_back_drive.setPower(0.0);
            left_front_drive.setPower(0.0);
            right_back_drive.setPower(0.0);
            right_front_drive.setPower(0.0);
        }

        else if (left.isPressed()) { // left touch sensor is pressed
            left_front_drive.setPower(0.0);
            left_back_drive.setPower(0.0);
            right_back_drive.setPower(-0.3);
            right_front_drive.setPower(-0.3);


        }

        else if (right.isPressed()) { // if right color sensor is pressed
            left_back_drive.setPower(-0.3);
            left_front_drive.setPower(-0.3);
            right_back_drive.setPower(0.0);
            right_front_drive.setPower(0.0);
        }
        else{

            left_back_drive.setPower(-0.3);
            left_front_drive.setPower(-0.3);
            right_back_drive.setPower(-0.3);
            right_front_drive.setPower(-0.3);
        }
        telemetry.addData("Red1", front_color.red());// getting telemetry for the color red
        telemetry.addData("alpha1", front_color.alpha());//getting telemetry, color values for alpha
        telemetry.addData("blue1", front_color.blue());//
        telemetry.addData("green1", front_color.green());
        telemetry.addData("hue1", front_color.argb());

        if (front_color.argb() >= Red) {// no clue what red is yet
            right_pusher.setPosition(1.0);
        }
        else{
            left_pusher.setPosition(1.0);
        }
    }
}