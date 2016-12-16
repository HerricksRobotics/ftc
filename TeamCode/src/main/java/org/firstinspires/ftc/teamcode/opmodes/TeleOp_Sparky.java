package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.subsystems.ServoManagement;
import org.firstinspires.ftc.teamcode.subsystems.EncDrive;

/** Sean Cardosi
 * Team Rise 7719
 * Created by rpcardosimd on 10/25/16.
 */
@TeleOp(name = "Sparky", group = "Rise Robot")
public class TeleOp_Sparky extends OpMode {


    DcMotor left_back_drive;
    DcMotor left_front_drive;
    DcMotor right_back_drive;
    DcMotor right_front_drive;
    DcMotor catapult;
    //    EncDriveTrain edt;
    Servo rcbl;
    Servo lcbl;
    DcMotor Up_Down;
    ServoManagement srvo;



    public void init() {
        //edt = new EncDriveTrain(hardwareMap);

        left_back_drive = hardwareMap.dcMotor.get("1");
        left_front_drive = hardwareMap.dcMotor.get("2");
        left_back_drive.setDirection(DcMotor.Direction.FORWARD);
        left_front_drive.setDirection(DcMotor.Direction.FORWARD);


        right_back_drive = hardwareMap.dcMotor.get("3");
        right_front_drive = hardwareMap.dcMotor.get("4");
        right_back_drive.setDirection(DcMotor.Direction.REVERSE);
        right_front_drive.setDirection(DcMotor.Direction.FORWARD);
        catapult = hardwareMap.dcMotor.get("catapult");
        Up_Down = hardwareMap.dcMotor.get("UD");
        rcbl = hardwareMap.servo.get("rcbl");
        lcbl = hardwareMap.servo.get("lcbl");
        lcbl.setDirection(Servo.Direction.REVERSE);


//        left_pusher = hardwareMap.servo.get("left_pusher");
//        right_pusher = hardwareMap.servo.get("right_pusher");

        srvo = new ServoManagement(hardwareMap, telemetry);
    }
// puglifr


    public void loop() {


        float throttle_left = gamepad1.left_stick_y;
        float throttle_right = gamepad1.right_stick_y;

        float UD = -gamepad2.right_stick_y;


        
        throttle_left = Range.clip(throttle_left, -1, 1);// we clip the motor values so that th motor does not stop while
        throttle_right = Range.clip(throttle_right, -1, 1);//trying to run at 101% speed

        UD = Range.clip(UD, -1, 1);


        left_back_drive.setPower(throttle_left);//tell the motors to do what the gamesticks do
        left_front_drive.setPower(throttle_left);
        right_back_drive.setPower(throttle_right);
        right_front_drive.setPower(throttle_right);

        Up_Down.setPower(UD);



        // toggle pushers using bumpers on the 2nd gamepad
        if (gamepad2.left_bumper) {
            if (!srvo.leftpush) {
                srvo.leftPusher(1);
            }
        } else if (!gamepad2.left_bumper) {
            if (srvo.leftpush) {
                srvo.leftPusher(0);
            }
        }
        if (gamepad2.right_bumper) {
            if (!srvo.rightpush) {
                srvo.rightPusher(1);
            }
        } else if (!gamepad2.right_bumper) {
            if (srvo.rightpush) {
                srvo.rightPusher(0);
            }
        }
        if (gamepad2.x) {
            rcbl.setPosition(1.0);
            lcbl.setPosition(1.0);
        }


        float current = right_front_drive.getCurrentPosition();// get the encoders for right_front_drive

        telemetry.addData("t1", throttle_left);
        telemetry.addData("t2", throttle_right);
        telemetry.addData("UD", UD);
        telemetry.addData("ENCODERS", current);// show encoder ticks right front motor


       if (gamepad2.a) {

            catapult.setPower(1.0);//if the gamepad button a is pressed then set the catapult motor to the speed 0.5
        }
        if (gamepad2.b) {
            catapult.setPower(0.0);//if button b is pressed set the catapult power to 0.0
        }
    }
}