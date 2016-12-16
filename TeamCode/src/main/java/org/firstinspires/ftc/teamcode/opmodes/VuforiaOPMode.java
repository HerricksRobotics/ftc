package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.HINT;
import com.vuforia.Vuforia;
import com.qualcomm.ftcrobotcontroller.R;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@TeleOp(name = "VuforiaOPMode", group = "Rise Robot")
public class VuforiaOPMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "ATIv+Pr/////AAAAGRbSNomRsUOwjl5ps1NItDFY7QxXxyKNhDjmtAj4Nj72eHYDJAwL+FXAMsdmengTAQndU8WfFLAmEa/Rbs3hQ/3up1Bu20JrhkFBEPbWqIz4M/P8S8rF2An6S7xXPItEyFGYvKST61q3QI9tk63BOGuYVAE8GHk8Ik5rXW1I1WrBRvmZuaZYpAPUXPh8wbGJTGXUPXX1VcJw/eAK9rACq27zpxavTw+/9J+qlBopbPBPu1pd56/GKvi48fThEgeLWdoMGdNna6UpikqujVrvA9Pp5P20q/6JGoQrCS8heRwEyIBdNJ9O5Wf9P+8EdZhsWNKwbOlNdt3voM6/0FeFehBzVP0J9PgIPJ3SNKjZ4yd/";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);
        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(0).setName("Lego");
        beacons.get(0).setName("Gears");

        waitForStart();

        beacons.activate();

        while(opModeIsActive()){
            for(VuforiaTrackable beac: beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if(pose!= null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);

                    double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1),translation.get(2)));
                    telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);

                }
            }
        telemetry.update();
        }
    }
}
