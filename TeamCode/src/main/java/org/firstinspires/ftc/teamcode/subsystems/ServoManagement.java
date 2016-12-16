package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoManagement {
    Servo hingel;
    Servo hinger;
    Servo pushl;
    Servo pushr;
    Servo platl;
    Servo platr;
    public static double currentPos;
    public static double initPos;
    public static boolean leftpush = false;
    public static boolean rightpush = false;
    Telemetry telemetry;

    public ServoManagement(HardwareMap hardwareMap, Telemetry telemetry) {
        pushl = hardwareMap.servo.get("left_pusher");
        pushr = hardwareMap.servo.get("right_pusher");
        pushl.setDirection(Servo.Direction.REVERSE);
        pushr.setDirection(Servo.Direction.FORWARD);
    }
//        hingel = hardwareMap.servo.get("c");
//        hinger = hardwareMap.servo.get("d");
//        hingel.setDirection(Servo.Direction.FORWARD);
//        hinger.setDirection(Servo.Direction.REVERSE);
//
//        platl = hardwareMap.servo.get("e");
//        platr = hardwareMap.servo.get("f");
//        platr.setDirection(Servo.Direction.REVERSE);
//        platl.setDirection(Servo.Direction.FORWARD);
//
//        this.telemetry = telemetry;
//    }
//
//    public void init() {
//        initPos = hingel.getPosition();
//    }
//
//    public void dropHinge() {
//        hingel.setPosition(0.18);
//        hinger.setPosition(0.18);
//        telemetry.addData("hinge", "Down");
//    }
//
//    public void raiseHinge() {
//        hingel.setPosition(0);
//        hinger.setPosition(0);
//        telemetry.addData("hinge", "Up");
//    }
//
//    public double getHingePos() {
//        return this.currentPos;
//    }
//
//    public void shootHinge() {
//        hingel.setPosition(.10);
//        hinger.setPosition(.10);
//        telemetry.addData("hinge", "Shoot");
//    }

    public void leftPusher(double pos) {
        pushl.setPosition(pos);
        if (pos > 0) {
            leftpush = true;
        } else {
            leftpush = false;
        }
    }

    public void rightPusher(double pos) {
        pushr.setPosition(pos);
        if (pos > 0) {
            rightpush = true;
        } else {
            rightpush = false;
        }
    }
}
//    public void platform_up() {
//        platl.setPosition(0.8);
//        platr.setPosition(0.8);
//
//    }
//
//    public void platform_down() {
//        platl.setPosition(0.25);
//        platr.setPosition(0.25);
//    }
//
//    public void platform_shootpos() {
//        platl.setPosition(0.65);
//        platr.setPosition(0.65);
//    }
//}
