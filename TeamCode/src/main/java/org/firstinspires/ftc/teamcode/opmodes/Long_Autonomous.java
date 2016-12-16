package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by rpcardosimd on 11/29/16.
 */
@Autonomous(name = "Autonomous Test", group = "Rise Robot")

public class Long_Autonomous extends LinearOpMode {

    ColorSensor front_color;

    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    DcMotor catapult;
    Servo left_pusher;
    Servo right_pusher;

    public void runOpMode() throws InterruptedException {


        front_color = hardwareMap.colorSensor.get("front");


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


        catapult.setPower(1);// shoot catapult for 1 second
        Thread.sleep(1000);

        catapult.setPower(0);// shoot catapult for 1 second


        left_back_drive.setPower(0.0);
        left_front_drive.setPower(0.0);
        right_back_drive.setPower(0.3);
        right_front_drive.setPower(0.3);//turn
        Thread.sleep(6000);

        left_back_drive.setPower(0.5);
        left_front_drive.setPower(0.5);
        right_back_drive.setPower(0.5);
        right_front_drive.setPower(0.5);//knock off cap ball
        Thread.sleep(2500);

        left_back_drive.setPower(-0.3);
        left_front_drive.setPower(-0.3);
        right_back_drive.setPower(0.3);
        right_front_drive.setPower(0.3);
        Thread.sleep(1600);

        left_back_drive.setPower(0.3);
        left_front_drive.setPower(0.3);
        right_back_drive.setPower(0.3);
        right_front_drive.setPower(0.3);
        Thread.sleep(2450);
//
//        if (front_color.blue() > 6) {// if right is blue
//            right_pusher.setPosition(1.0);
//            left_pusher.setPosition(0.0);
//        }
//        if (front_color.blue() < 5) {// if left is blue
//            left_pusher.setPosition(1.0);
//            right_pusher.setPosition(0.0);
//        }
//        Thread.sleep(1500);
//        left_pusher.setPosition(0);
//        right_pusher.setPosition(0);


        left_back_drive.setPower(-0.3);
        left_front_drive.setPower(-0.3);
        right_back_drive.setPower(0.3);//2.75 to get to get to cap ball
        right_front_drive.setPower(0.3);
        Thread.sleep(2200);

        left_back_drive.setPower(0.3);
        left_front_drive.setPower(0.3);
        right_back_drive.setPower(0.3);
        right_front_drive.setPower(0.3);
        Thread.sleep(3000);
    }
}
