package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by rpcardosimd on 12/4/16.
 */
@Autonomous(name = "Time_Auto", group = "Rise Robot")

public class TimeOpMode extends LinearOpMode {

    ColorSensor front_color;
    ColorSensor bottom_color;
    TouchSensor left;
    TouchSensor right;
    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    DcMotor catapult;
    Servo left_pusher;
    Servo right_pusher;

    public void runOpMode() throws InterruptedException {

        front_color = hardwareMap.colorSensor.get("front");
        bottom_color = hardwareMap.colorSensor.get("bottom");

        left = hardwareMap.touchSensor.get("left");
        right = hardwareMap.touchSensor.get("right");


        left_back_drive = hardwareMap.dcMotor.get("1");
        left_front_drive = hardwareMap.dcMotor.get("2");
        left_back_drive.setDirection(DcMotor.Direction.REVERSE);
        left_front_drive.setDirection(DcMotor.Direction.REVERSE);


        right_back_drive = hardwareMap.dcMotor.get("3");
        right_front_drive = hardwareMap.dcMotor.get("4");
        right_back_drive.setDirection(DcMotor.Direction.FORWARD);
        right_front_drive.setDirection(DcMotor.Direction.REVERSE);

        left_pusher = hardwareMap.servo.get("left_pusher");
        left_pusher.setDirection(Servo.Direction.REVERSE);
        right_pusher = hardwareMap.servo.get("right_pusher");

        catapult = hardwareMap.dcMotor.get("catapult");

        waitForStart();

        float catapult_encoders = catapult.getCurrentPosition();

        while (catapult_encoders < 1700) {

//            float catapult_Current_Position = catapult.getCurrentPosition();
            boolean catapult1 = true;

            front_color.enableLed(false);


            if (catapult1) {
                catapult.setPower(1.0);
            }
            if (catapult_encoders >= 1700) {
                catapult1 = false;
            }
            if (!catapult1) {
                catapult.setPower(0.0);
            }
//            sleep(1000);
        }

        while (time < 6) {

            getRuntime();

            telemetry.addData("time", getRuntime());

            if (time >= 6) { //may need to be adjusted

                left_back_drive.setPower(0.0);
                left_front_drive.setPower(0.0);
                right_back_drive.setPower(0.0);
                right_front_drive.setPower(0.0);
            } else {
                left_back_drive.setPower(0.5);
                left_front_drive.setPower(0.5);
                right_back_drive.setPower(0.5);
                right_front_drive.setPower(0.5);
            }
        }
        resetStartTime();
        while (time < 7) {

            if (time >= 7) { //may need to be adjusted
                left_back_drive.setPower(0.0);
                left_front_drive.setPower(0.0);
                right_back_drive.setPower(0.0);
                right_front_drive.setPower(0.0);
            } else {
                left_back_drive.setPower(-0.5);
                left_front_drive.setPower(-0.5);
                right_back_drive.setPower(0.5);
                right_front_drive.setPower(0.5);
            }
        }
    }
}
