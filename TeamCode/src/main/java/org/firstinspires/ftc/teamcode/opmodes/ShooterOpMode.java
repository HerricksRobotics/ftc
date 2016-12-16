package org.firstinspires.ftc.teamcode.opmodes;

 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import org.firstinspires.ftc.teamcode.subsystems.BallManagementSystem;
 import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp(name = "ShooterOpMode", group = "Rise Robot")
public class ShooterOpMode extends OpMode {
    Drivetrain drivetrain;
    BallManagementSystem bms;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        bms = new BallManagementSystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        drivetrain.drive(gamepad1.left_stick_y, gamepad1.right_stick_y);

        bms.update();

        if((gamepad1.right_trigger>0.1)&&(gamepad1.right_trigger<0.75)){
            if(bms.getShooterTarget()!=BallManagementSystem.SHOOTER_MED_SPEED){
                bms.setShooterTarget(BallManagementSystem.SHOOTER_MED_SPEED);
            }
            bms.setShooterActive(true);

        }
        else if(gamepad1.right_trigger>=0.75){
            if(bms.getShooterTarget()!=BallManagementSystem.SHOOTER_HIGH_SPEED){
                bms.setShooterTarget(BallManagementSystem.SHOOTER_HIGH_SPEED);
            }
            bms.setShooterActive(true);

        }
        else{
            bms.setShooterActive(false);

        }

//        if((gamepad1.left_trigger>0.1)&&(gamepad1.left_trigger<0.75)){
//            bms.intake.setPower(0.5);
//        }
//        else if(gamepad1.left_trigger>=0.75){
//            bms.intake.setPower(1);
//        }
//        else if(gamepad1.left_bumper){
//            bms.intake.setPower(-1);
//        }
//        else{
//            bms.intake.setPower(0);
//
//        }
    }
}