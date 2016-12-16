package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "Cap Ball knock", group = "Rise Robot")


/* Sean Cardosi
 * Team Rise 7719
 * Created by rpcardosimd on 6/18/16.
 */
public class Autonomous_Beacons extends LinearOpMode {

    ColorSensor front_color;
    TouchSensor left;
    TouchSensor right;
    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    Servo left_pusher;
    Servo right_pusher;
    Servo rcbl;
    Servo lcbl;


    public void runOpMode() throws InterruptedException {


        front_color = hardwareMap.colorSensor.get("front");

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

        rcbl = hardwareMap.servo.get("rcbl");
        lcbl = hardwareMap.servo.get("lcbl");
        lcbl.setDirection(Servo.Direction.REVERSE);


        left_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        left_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

            rcbl.setPosition(0.5);
            lcbl.setPosition(0.5);

            front_color.enableLed(false);

            float current = right_back_drive.getCurrentPosition();
            telemetry.addData("encoders", current);



                left_back_drive.setTargetPosition(6000);
                left_front_drive.setTargetPosition(6000);
                right_back_drive.setTargetPosition(6000);
                right_front_drive.setTargetPosition(6000);

                left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                left_back_drive.setPower(0.5);
                left_front_drive.setPower(0.5);
                right_back_drive.setPower(0.5);
                right_front_drive.setPower(0.5);
        while (left_back_drive.isBusy() || right_back_drive.isBusy()) {
            Thread.sleep(20);
        }
//Thread.sleep(3000);

                left_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                left_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                right_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                right_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//                left_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                left_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                right_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                right_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
                left_back_drive.setTargetPosition(3000);
                left_front_drive.setTargetPosition(3000);
                right_back_drive.setTargetPosition(-1000);
                right_front_drive.setTargetPosition(-1000);
//
                left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_back_drive.setPower(0.5);
        left_front_drive.setPower(0.5);
        right_back_drive.setPower(0.5);
        right_front_drive.setPower(0.5);

        while (left_back_drive.isBusy() || right_back_drive.isBusy()) {
            Thread.sleep(20);
        }
//
//                left_back_drive.setPower(0.5);
//                left_front_drive.setPower(0.5);
//                right_back_drive.setPower(0.5);
//                right_front_drive.setPower(0.5);


//            if (front_color.blue() > 6) {// if right is blue
//                right_pusher.setPosition(1.0);
//                left_pusher.setPosition(0.0);
//                sleep(1000);
//                right_pusher.setPosition(0.0);
//                left_pusher.setPosition(0.0);
//            }
//
//            else if (front_color.blue() < 5) {// if left is blue
//                left_pusher.setPosition(1.0);
//                right_pusher.setPosition(0.0);
//                sleep(1000);
//                left_pusher.setPosition(0.0);
//                right_pusher.setPosition(0.0);
//            }
//            Thread.sleep(4000);




            telemetry.addData("Red", front_color.red());// getting telemetry for the color red
            telemetry.addData("alpha", front_color.alpha());//getting telemetry, color values for alpha
            telemetry.addData("blue", front_color.blue());//
            telemetry.addData("green", front_color.green());
            telemetry.addData("hue", front_color.argb());
            }
        }