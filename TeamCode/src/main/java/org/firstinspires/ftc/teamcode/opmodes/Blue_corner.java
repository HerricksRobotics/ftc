package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Blue_Corner_Vortex_Position_1", group = "Rise Robot")
/**
 * Created by rpcardosimd on 11/8/16.
 */

public class Blue_corner extends OpMode {

    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;


    public void init() {

        left_back_drive = hardwareMap.dcMotor.get("1");
        left_front_drive = hardwareMap.dcMotor.get("2");
        left_front_drive.setDirection(DcMotor.Direction.REVERSE);
        left_back_drive.setDirection(DcMotor.Direction.REVERSE);


        right_back_drive = hardwareMap.dcMotor.get("3");
        right_front_drive = hardwareMap.dcMotor.get("4");
        right_back_drive.setDirection(DcMotor.Direction.FORWARD);
        right_front_drive.setDirection(DcMotor.Direction.REVERSE);

//resetting the encoders
        left_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        left_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);// use encoders while the motors are running
        right_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void loop() {


        float currentR = right_front_drive.getCurrentPosition(); //get encoder values
        float currentL = left_back_drive.getCurrentPosition();

        telemetry.addData("right_encoders", currentR);//display encoder values
        telemetry.addData("left_encoders", currentL);


        left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);// make robot go to the target position set below
        left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        right_back_drive.setTargetPosition(4000);//setting the motors to go to the position 7000
        right_front_drive.setTargetPosition(4000);//setting the motors to go to the position 7000
        left_back_drive.setTargetPosition(7000);//setting the motors to go to the position 4000
        left_front_drive.setTargetPosition(7000);//setting the motors to go to the position 4000


        left_back_drive.setPower(0.8);
        left_front_drive.setPower(0.8);
        right_back_drive.setPower(0.3);
        right_front_drive.setPower(0.3);

    }
}