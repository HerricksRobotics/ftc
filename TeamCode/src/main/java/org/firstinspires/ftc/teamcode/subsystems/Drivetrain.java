
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    DcMotor backl;
    DcMotor frontl;
    DcMotor backr;
    DcMotor frontr;

    public Drivetrain(HardwareMap hardwareMap){
        backl = hardwareMap.dcMotor.get("backl");
        frontl = hardwareMap.dcMotor.get("frontl");
        backr = hardwareMap.dcMotor.get("backr");
        frontr = hardwareMap.dcMotor.get("frontr");
        backr.setDirection(DcMotor.Direction.REVERSE);
        frontr.setDirection(DcMotor.Direction.REVERSE);


    }

    public void drive(double left, double right){
        backl.setPower(-left);
        frontl.setPower(-left);
        frontr.setPower(-right);
        backr.setPower(-right);
    }

}