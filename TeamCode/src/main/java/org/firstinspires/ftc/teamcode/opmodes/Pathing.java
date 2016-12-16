package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;   //for this OpMode the robots left wheel must be on the 3rd line
import com.qualcomm.robotcore.eventloop.opmode.OpMode;       //from the right of the fields corner or in other words the line
import com.qualcomm.robotcore.hardware.DcMotor;              //that is in the middle of the center vortex
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "Cap_Ball", group = "Rise Robot")

/**
 * Created by rpcardosimd on 7/7/16.
 */
public class Pathing extends OpMode {//if info is needed visit this sight --> https://ftc-tricks.com/dc-motors/


    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    Servo pushl;
    Servo pushr;
    final int KNOCK_CAP_BALL_OFF = 10640;
    DcMotor catapult;


    public void init() {

        left_back_drive = hardwareMap.dcMotor.get("1");
        left_front_drive = hardwareMap.dcMotor.get("2");
        left_front_drive.setDirection(DcMotor.Direction.REVERSE);
        left_back_drive.setDirection(DcMotor.Direction.REVERSE);


        right_back_drive = hardwareMap.dcMotor.get("3");
        right_front_drive = hardwareMap.dcMotor.get("4");
        right_back_drive.setDirection(DcMotor.Direction.FORWARD);
        right_front_drive.setDirection(DcMotor.Direction.REVERSE);

        pushl = hardwareMap.servo.get("left_pusher");
        pushr = hardwareMap.servo.get("right_pusher");
        pushl.setDirection(Servo.Direction.REVERSE);
        catapult = hardwareMap.dcMotor.get("catapult");


        left_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        left_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

        public void loop() {
            float catapult_encoders = catapult.getCurrentPosition();
            float current = right_back_drive.getCurrentPosition();
            telemetry.addData(" CATAPULT_ENCODERS", catapult_encoders);// show encoder ticks right front motor


//            left_back_drive.setPower(-0.5);
//            left_front_drive.setPower(-0.5);
//            right_back_drive.setPower(0.5);
//            right_front_drive.setPower(0.5);


             if (current >= 4800 ) {
                left_back_drive.setPower(0.0);
                left_front_drive.setPower(0.0);
                right_back_drive.setPower(0.0);
                right_front_drive.setPower(0.0);
            }else{
                left_back_drive.setPower(1.0);
                left_front_drive.setPower(1.0);
                right_back_drive.setPower(1.0);
                right_front_drive.setPower(1.0);
            }



            float currentR = right_front_drive.getCurrentPosition(); //get encoder values
            float currentL = left_back_drive.getCurrentPosition();

            telemetry.addData("right_encoders", currentR);
            telemetry.addData("left_encoders", currentL);
        }
    }
