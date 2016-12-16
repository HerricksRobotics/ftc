package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
/**
 * Created by Aeites on 11/18/2016.
 */


public class EncDriveTrain {
    private
    DcMotor left_back_drive;
    private DcMotor left_front_drive;
    private DcMotor right_back_drive;
    private DcMotor right_front_drive;
    private final double WHEEL_DIAMETER = 4;
    private final double PI = 3.1415926535897;
    private final int TICKS_PER_ROT = 1220;

    public EncDriveTrain(HardwareMap hardwareMap) {
        left_back_drive = hardwareMap.dcMotor.get("1");  // give left_back_drive the name 1
        left_front_drive = hardwareMap.dcMotor.get("2");  // give left_front_drive the name 2
        left_back_drive.setDirection(DcMotor.Direction.REVERSE);  // reverse the left_back_drive
        left_front_drive.setDirection(DcMotor.Direction.FORWARD);  // reverse the left_front_drive


        right_back_drive = hardwareMap.dcMotor.get("3");  // you probably know what we do here by now
        right_front_drive = hardwareMap.dcMotor.get("4");  // and here
        right_back_drive.setDirection(DcMotor.Direction.FORWARD);  // here we just tell the motors listed to go forward and not reversed
        right_front_drive.setDirection(DcMotor.Direction.FORWARD);
    }
        public void drive_1_tile() {

        left_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting encoders
        left_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);// resetting encoders
        right_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting encoders
        right_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting encoders


        left_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//run the motors using encoders
        left_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);// use encoders while the motors are running
        right_back_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//running the motors using encoders
        right_front_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//running the motors using the encoders



        left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);// make robot go to the target position set below
        left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        right_back_drive.setTargetPosition(4800);//setting the motor encoders to pos. 4800
        right_front_drive.setTargetPosition(4800);//setting the motor encoders to pos. 4800
        left_back_drive.setTargetPosition(-4800);//setting the motor encoders to pos. 4800
        left_front_drive.setTargetPosition(4800);//setting the motor encoders to pos. 4800


        left_back_drive.setPower(0.5);//setting the motors to 0.5 power
        left_front_drive.setPower(0.5);//go forward
        right_back_drive.setPower(0.5);//setting the motors to 0.5 power
        right_front_drive.setPower(0.5);//setting the motors to 0.5 power
    }
}

