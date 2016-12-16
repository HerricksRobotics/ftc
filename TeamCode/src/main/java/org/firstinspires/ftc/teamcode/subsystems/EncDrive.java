package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Aeites on 12/7/2016.
 */
public class EncDrive {
    GyroSensor sensorGyro;
    private Telemetry telemetry;
    private DcMotor left_back_drive;
    private DcMotor left_front_drive;
    private DcMotor right_back_drive;
    private DcMotor right_front_drive;
    private final double WHEEL_DIAMETER = 4;
    private final double PI = 3.1415926535897;
    private final int TICKS_PER_ROT = 1220;
    private int xVal, yVal, zVal = 0;
    private int heading = 0;
    public boolean moving = false;
    private double targetDegree = 0;

    public EncDrive(HardwareMap hardwareMap, Telemetry telemetry) {
        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        left_back_drive = hardwareMap.dcMotor.get("1");  // give left_back_drive the name 1
        left_front_drive = hardwareMap.dcMotor.get("2");  // give left_front_drive the name 2
        left_back_drive.setDirection(DcMotor.Direction.REVERSE);  // reverse the left_back_drive
        left_front_drive.setDirection(DcMotor.Direction.REVERSE);  // reverse the left_front_drive

        this.telemetry = telemetry;

        right_back_drive = hardwareMap.dcMotor.get("3");  // you probably know what we do here by now
        right_front_drive = hardwareMap.dcMotor.get("4");  // and here
        right_back_drive.setDirection(DcMotor.Direction.FORWARD);  // here we just tell the motors listed to go forward and not reversed
        right_front_drive.setDirection(DcMotor.Direction.FORWARD);


        left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);// run to position
        right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        moving = false;

        // calibrate the gyro.
        xVal = 0;
        yVal = 0;
        zVal = 0;
        heading = 0;
        sensorGyro.calibrate();
    }

    // Function to move the robot forward the given amount of inches
    public void forward(double inches, double speed) {
        // Calculate how much the motor must rotate
        double numRot = inches / (WHEEL_DIAMETER*PI);
        int position = (int) (numRot * TICKS_PER_ROT);

        // Set the motors to move to the position accordingly
        setEncPosition(position, position, speed);
    }


    // Function to rotate the robot the given amount of degrees
    public void gyroRotate(double degrees, double speed) {
        if(!moving) {
            moving = true;
            //Reset Gyro to 0
            sensorGyro.calibrate();
            if(degrees<0){
                targetDegree = (360-degrees)-10;
                rotateToTarget(true,speed);
            }
            else{
                targetDegree = (degrees)-10;
                rotateToTarget(false,speed);
            }
        }
        else{
            if(degrees<0){
                rotateToTarget(true,speed);
            }
            else{
                rotateToTarget(false,speed);
            }
         }
    }

    public void rotateToTarget(boolean left, double speed) {

        telemetry.addData("Heading: ", heading);
        telemetry.addData("Target: ", targetDegree);
        heading = sensorGyro.getHeading();

        left_back_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_back_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if(left){
            if(heading>targetDegree){
                left_back_drive.setPower(-speed);
                left_front_drive.setPower(-speed);
                right_back_drive.setPower(speed);
                right_front_drive.setPower(speed);
            }
            else{
                left_back_drive.setPower(0);
                left_front_drive.setPower(0);
                right_back_drive.setPower(0);
                right_front_drive.setPower(0);
            }
        }
        else{
            if(heading<targetDegree) {
                left_back_drive.setPower(speed);
                left_front_drive.setPower(speed);
                right_back_drive.setPower(-speed);
                right_front_drive.setPower(-speed);
            }
            else{
                left_back_drive.setPower(0);
                left_front_drive.setPower(0);
                right_back_drive.setPower(0);
                right_front_drive.setPower(0);
            }
        }
    }

    public void showEncPosition() {
        telemetry.addData("Encoder Drive left current:", left_back_drive.getCurrentPosition());
        telemetry.addData("Encoder Drive left target :", left_back_drive.getTargetPosition());

        telemetry.addData("Encoder Drive right current:", right_back_drive.getCurrentPosition());
        telemetry.addData("Encoder Drive right target :", right_back_drive.getTargetPosition());

    }

    // Function to reset all drive motor encoders
    public void resetEncoders() {
        left_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);// reset encoders
        right_back_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        right_back_drive.setTargetPosition(0);
        right_front_drive.setTargetPosition(0);
        left_back_drive.setTargetPosition(0);
        left_front_drive.setTargetPosition(0);

        left_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);// run to position
        right_back_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Function to set the target position of the drive motors
    private void setEncPosition(int left, int right, double power) {
        right_back_drive.setTargetPosition(right);
        right_front_drive.setTargetPosition(right);
        left_back_drive.setTargetPosition(left);
        left_front_drive.setTargetPosition(left);

        telemetry.addData("Set Position: ", right);

        left_back_drive.setPower(power);
        left_front_drive.setPower(power);
        right_back_drive.setPower(power);
        right_front_drive.setPower(power);
    }

}



