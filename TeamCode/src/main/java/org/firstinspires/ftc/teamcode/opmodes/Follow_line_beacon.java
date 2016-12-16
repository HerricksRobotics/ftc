package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


/* Sean Cardosi
 * Team Rise 7719
 * Created by rpcardosimd on 6/18/16.
 */
@Autonomous(name = "Follow_Line", group = "Rise Robot")

public class Follow_line_beacon extends LinearOpMode {

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


        while (bottom_color.red() <= 0) { // need values
            float catapult_encoders = catapult.getCurrentPosition();
            boolean catapult1 = true;


            front_color.enableLed(false);

            if(catapult1) {
                catapult.setPower(1.0);
            }
            if (catapult_encoders >= 1700) {
                catapult1 = false;
            }
            if (!catapult1){
                catapult.setPower(0.0);
            }
            sleep(1000);

            telemetry.addData("Red", bottom_color.red());



            left_back_drive.setPower(0.15);
            left_front_drive.setPower(0.15);
            right_back_drive.setPower(0.15);
            right_front_drive.setPower(0.15);
        }

        left_back_drive.setPower(0.0);
        left_front_drive.setPower(0.0);
        right_back_drive.setPower(0.0);
        right_front_drive.setPower(0.0);


        while (opModeIsActive()) {

            front_color.enableLed(false);


            telemetry.addData("Red", front_color.red());
            telemetry.addData("alpha", front_color.alpha());
            telemetry.addData("blue", front_color.blue());
            telemetry.addData("green", front_color.green());
            telemetry.addData("hue", front_color.argb());

            double base = 0.15;


            if (bottom_color.alpha() > 0) { //change values
                left_back_drive.setPower(base);
                left_front_drive.setPower(base);
                right_back_drive.setPower(0.0);
                right_front_drive.setPower(0.0);



            } else if (bottom_color.alpha() <= 0) { //change values
                left_back_drive.setPower(0.0);
                left_front_drive.setPower(0.0);
                right_back_drive.setPower(base);
                right_front_drive.setPower(base);

            }
            if (left.isPressed()) {
                left_front_drive.setPower(0.0);
                left_back_drive.setPower(0.0);
                right_back_drive.setPower(1.0);
                right_front_drive.setPower(1.0);

            }else if (right.isPressed()) {
                right_front_drive.setPower(0.0);
                right_back_drive.setPower(0.0);
                left_back_drive.setPower(1.0);
                left_front_drive.setPower(1.0);
            }else {
                left_back_drive.setPower(0.5);
                left_front_drive.setPower(0.5);//go forward
                right_back_drive.setPower(0.5);
                right_front_drive.setPower(0.5);
            }
            if (left.isPressed() && (right.isPressed())) {
                right_front_drive.setPower(0.0);
                right_back_drive.setPower(0.0);
                left_back_drive.setPower(0.0);
                left_front_drive.setPower(0.0);
            }

            if (front_color.red() >= 0) {// Remember to change red values when they are found.
                right_pusher.setPosition(1.0);// red team
            }else{
                left_pusher.setPosition(1.0);//red team
            }
        }
    }
}
