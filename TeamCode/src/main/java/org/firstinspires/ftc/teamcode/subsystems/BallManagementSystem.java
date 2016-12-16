
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallManagementSystem {
    public static final double KI1 = 0.02;
    public static final double SHOOTER_LOW_SPEED = 0.40;
    public static final double SHOOTER_MED_SPEED = 0.75;
    public static final double SHOOTER_HIGH_SPEED= 1;
    public static final double INTAKE_SPEED= -0.5;
    boolean shooterActive;
    boolean intakeActive;
    public static int status = 0;
    DcMotor ballHolder;
    DcMotor shooter1;
    DcMotor shooter2;
    double shooterTarget = 1;
    Telemetry telemetry;

    long lastRun = 0;

    public BallManagementSystem(HardwareMap hardwareMap, Telemetry telemetry){
        ballHolder = hardwareMap.dcMotor.get("ballHolder");
        shooter1 = hardwareMap.dcMotor.get("5");
        shooter2 = hardwareMap.dcMotor.get("6");
        ballHolder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.telemetry = telemetry;

    }

    public void update(){

        if(System.currentTimeMillis() - lastRun > 50) {
            double power = shooter1.getPower();
            lastRun = System.currentTimeMillis();
            if (shooterActive) {
                // Reverse the power of the ball shooter
                ballHolder.setPower(-power);
                status = 1;
                if (power < shooterTarget) {
                    power += KI1;
                }
                if (power >= shooterTarget) {
                    power = shooterTarget;
                }
            }
            else if (intakeActive) {
                // Reverse the power of the ball shooter
                ballHolder.setPower(-power);
                status = 2;
                if (power > shooterTarget) {
                    power -= KI1;
                }
                if (power <= shooterTarget) {
                    power = shooterTarget;
                }
            }
            if(status==1){
                if (!shooterActive) {
                    if (power > 0) {
                        power -= KI1;
                    }
                    if (power <= 0) {
                        power = 0;
                    }
                }
            }
            if(status==2){
                if (!intakeActive) {
                    if (power < 0) {
                        power += KI1;
                    }
                    if (power >= 0) {
                        power = 0;
                    }
                }
            }

            shooter1.setPower(power);
            shooter2.setPower(-power);
            telemetry.addData("power", power);
            telemetry.addData("target", shooterTarget);
        }
    }


    public void setShooterActive(boolean shooterActive) {
        this.shooterActive = shooterActive;
    }

    public void setIntakeActive(boolean intakeActive) {
        this.intakeActive = intakeActive;
    }

    public void setShooterTarget(double shooterTarget) {
        this.shooterTarget = shooterTarget;
    }

    public double getShooterTarget() {
        return shooterTarget;
    }

}
