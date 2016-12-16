package org.firstinspires.ftc.teamcode.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/*
 *
 * This is an example LinearOpMode that shows how to use
 * the Modern Robotics Gyro.
 *
 * The op mode assumes that the gyro sensor
 * is configured with a name of "gyro".
 *
 *
 *
 */
@TeleOp(name = "Gyro Test", group = "Rise Robot")
public class Gyro_Test extends OpMode {

    GyroSensor sensorGyro;
    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    int xVal, yVal, zVal = 0;
    int heading = 0;

    public void init() {

        GyroSensor sensorGyro;
        int xVal, yVal, zVal = 0;
        int heading = 0;

        // write some device information (connection info, name and type)
        // to the log file.
        hardwareMap.logDevices();

        // get a reference to our GyroSensor object.
        sensorGyro = hardwareMap.gyroSensor.get("gyro");
        left_back_drive = hardwareMap.dcMotor.get("1");
        left_front_drive = hardwareMap.dcMotor.get("2");
        left_back_drive.setDirection(DcMotor.Direction.FORWARD);
        left_front_drive.setDirection(DcMotor.Direction.FORWARD);


        right_back_drive = hardwareMap.dcMotor.get("3");
        right_front_drive = hardwareMap.dcMotor.get("4");
        right_back_drive.setDirection(DcMotor.Direction.REVERSE);
        right_front_drive.setDirection(DcMotor.Direction.FORWARD);

        // calibrate the gyro.
        sensorGyro.calibrate();
    }
        // wait for the start button to be pressed.
        public void loop() {

        // make sure the gyro is calibrated.
            

            // get the x, y, and z values (rate of change of angle).
            xVal = sensorGyro.rawX();
            yVal = sensorGyro.rawY();
            zVal = sensorGyro.rawZ();

            // get the heading info.
            // the Modern Robotics' gyro sensor keeps
            // track of the current heading for the Z axis only.
            heading = sensorGyro.getHeading();

            telemetry.addData("1. x", String.format("%03d", xVal));
            telemetry.addData("2. y", String.format("%03d", yVal));
            telemetry.addData("3. z", String.format("%03d", zVal));
            telemetry.addData("4. h", String.format("%03d", heading));

        }
    }
