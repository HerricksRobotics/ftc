package org.firstinspires.ftc.teamcode.opmodes;

//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.subsystems.BallManagementSystem;
//import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
//import org.firstinspires.ftc.teamcode.subsystems.ServoManagement;
//
//
////Initialize TeleOpMode
//@TeleOp(name = "SamsBot", group = "Rise Robot")
//public class SamsBot extends OpMode {
//    //Import Class Variables
//    Drivetrain drivetrain;
//    ServoManagement srvo;
//    BallManagementSystem bms;
//
//    public void init() {
//        //Import Subsystems and Initialize Them
//        drivetrain = new Drivetrain(hardwareMap);
//        bms = new BallManagementSystem(hardwareMap, telemetry);
//        srvo = new ServoManagement(hardwareMap, telemetry);
//
//        //Initialize Servos
//        srvo.init();
//    }
//
//    public void loop() {
//        //Send Sticks to Drive Train
//        drivetrain.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
//
//        //Update Loop for Shooter
//        bms.update();
//
//        //Right Trigger to Toggle Shooter Boolean
//
//        //Medium Speed
//        if((gamepad1.right_trigger>0.1)&&(gamepad1.right_trigger<0.75)){
//            if(bms.getShooterTarget()!=BallManagementSystem.SHOOTER_MED_SPEED){
//                bms.setShooterTarget(BallManagementSystem.SHOOTER_MED_SPEED);
//            }
//            bms.setShooterActive(true);
//
//        }
//        //Full Speed
//        else if(gamepad1.right_trigger>0.75){
//            if(bms.getShooterTarget()!=BallManagementSystem.SHOOTER_HIGH_SPEED){
//                bms.setShooterTarget(BallManagementSystem.SHOOTER_HIGH_SPEED);
//            }
//            bms.setShooterActive(true);
//        }
//        //OFF
//        else{
//            bms.setShooterActive(false);
//
//        }
//
//        //Use Buttons to Toggle Hinge
//        if(gamepad1.a){//
//            srvo.dropHinge();
//        }
//        else if(gamepad1.y) {//
//            srvo.raiseHinge();
//        }
//
//        //Prep to Shoot with X
//        if (gamepad1.x) {//
//            srvo.shootHinge();
//            srvo.platform_shootpos();
//        }
//
//        //Use Buttons to Toggle Platform
//        if (gamepad1.y) {//
//            srvo.platform_up();
//        }
//        if (gamepad1.a) {//
//            srvo.platform_down();
//        }
//
//        //Use Bumpers to Toggle Pusher Servos
//        if(gamepad1.left_bumper){
//            if(!srvo.leftpush){
//                srvo.leftPusher(1);
//            }
//        }
//        else if(!gamepad1.left_bumper){
//            if(srvo.leftpush){
//                srvo.leftPusher(0);
//            }
//        }
//        if(gamepad1.right_bumper){
//            if(!srvo.rightpush){
//                srvo.rightPusher(1);
//            }
//        }
//        else if(!gamepad1.right_bumper){
//            if(srvo.rightpush){
//                srvo.rightPusher(0);
//            }
//        }
//
//        //Left Trigger to Toggle Intake Boolean
//
//        //Full Speed
//        if(gamepad1.left_trigger>=0.1){
//            if(bms.getShooterTarget()!=BallManagementSystem.INTAKE_SPEED){
//                bms.setShooterTarget(BallManagementSystem.INTAKE_SPEED);
//            }
//            bms.setIntakeActive(true);
//        }
//        //OFF
//        else{
//            bms.setIntakeActive(false);
//
//        }
//    }
//}
