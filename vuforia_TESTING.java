package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Testing the vuforia technology to tell the robot where everything
 * and itself is located in the area
 *
 * Created by RichtXO on 11/8/16.
 */

@Autonomous (name = "Vuforia", group = "Testing Codes")
public class vuforia_TESTING extends LinearOpMode
{
    private VuforiaLocalizer vuforiaLocalizer;
    private VuforiaLocalizer.Parameters parameters;
    private VuforiaTrackables visionTargets;
    private VuforiaTrackableDefaultListener listener;


    private OpenGLMatrix lastKnownLocation;
    private OpenGLMatrix phoneLocation;



    private static final String VUFORIA_KEY = "AUJb+FP/////AAAAGY+/mDZPWU7EkMXFOo9xLI9NFh6hVH6lf8ngon3bPH" +
            "th/O7S0I11Qec2elhxWeEWO4XMU654PDleevVoD4zQEhbnn3SlUVjEwYdNUUaNckuD0SXHmD12ZYvgDuzZJPey2nNnVIRsVKSB5w9SCf" +
            "QWjiqXOdwnnLay6/j29AyERyoKOT3f29IsbXPtmpIEpq0j81hwIKQVHl1ggwAONbY/phs/ylIblj0GONExsg7QK" +
            "wixdI+2y38PtJdKvEq025xKuPJkNaEGpX3C7WTMaubKFYMM//vvWMZ9Rp0nHVi2uvBMznsxqDM7lpPsRUVrVX6z" +
            "EarecvbH3xALQSqcV8QNtkNYkwTI34OIqylGBkZ4YM8d";



    private float robotX = 0;
    private float robotY = 0;
    private float robotAngle = 0;


    //*************************************************************************************************


    public void runOpMode() throws InterruptedException
    {
        setupVuforia();

        //We don't know where the robot is, so set it to the origin
        lastKnownLocation = createMatrix(0, 0, 0, 0, 0, 0);

        waitForStart();

        // Start tracking the targets
        visionTargets.activate();

        while(opModeIsActive())
        {
            // Ask the listener for the latest information on where the robot is
            OpenGLMatrix latestLocation = listener.getUpdatedRobotLocation();

            // The listener will sometimes return null, so we check for that to prevent errors
            if(latestLocation != null)
                lastKnownLocation = latestLocation;


            for(VuforiaTrackable beacon : visionTargets)
            {
                OpenGLMatrix position = ((VuforiaTrackableDefaultListener) beacon.getListener()).getPose();

                if (position != null)
                {
                    VectorF translation = position.getTranslation();

                    telemetry.addData(beacon.getName() + "-Translation", translation);

                    double degreeTurn = Math.toDegrees(Math.atan2(translation.get(0), translation.get(2)));          //phone on the landscape
                                                                                                                     //if want vertical, change to
                                                                                                                     //translation.get(1), translation.get(2)
                    telemetry.addData(beacon.getName() + "-Degrees", degreeTurn);

                }
            }



            float[] coordinates = lastKnownLocation.getTranslation().getData();

            robotX = coordinates[0];
            robotY = coordinates[1];
            robotAngle = Orientation.getOrientation(lastKnownLocation, AxesReference.EXTRINSIC,
                    AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;

            // Send information about whether the target is visible, and where the robot is
            telemetry.addData("Last Known Location", formatMatrix(lastKnownLocation));

            // Send telemetry and idle to let hardware catch up
            telemetry.update();
            idle();
        }
    }

    //*************************************************************************************************

    private void setupVuforia()
    {
        // Setup parameters to create localize
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);                     //Delete "R.id.cameraMonitorViewId" to disable
                                                                                                    //camera feed in the phone to save battery


        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;                         //to use the back camera



        //delete when not debugging
        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;  //displays axes on the screen if it detects it...testing only
        parameters.useExtendedTracking = false;


        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);


        // These are the vision targets that we want to use
        // The string needs to be the name of the appropriate .xml file in the assets folder
        visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("FTC_2016-17");
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);


        //------------------------------------------------------------------------------------------
        //SETTING UP TARGETS
        visionTargets.get(0).setName("Wheel");
        visionTargets.get(1).setName("Tool");
        visionTargets.get(2).setName("Lego");
        visionTargets.get(3).setName("Gear");

        // Set phone location on robot
        phoneLocation = createMatrix(0, 225, 0, 90, 0, 0);

        // Setup listener and inform it of phone information

        for (VuforiaTrackable element : visionTargets)
        {
            listener = (VuforiaTrackableDefaultListener) element.getListener();
            listener.setPhoneInformation(phoneLocation, parameters.cameraDirection);
        }
    }


    //*************************************************************************************************


    /**
     * Creates a matrix for determining the locations and orientations of objects
     *
     * @param x millimeters     (locations)
     * @param y millimeters     (locations)
     * @param z millimeters     (locations)
     * @param u degrees         (orientations)
     * @param v degrees         (orientations)
     * @param w degrees         (orientations)
     * @return matrix of the locations
     */
    private OpenGLMatrix createMatrix(float x, float y, float z, float u, float v, float w)
    {
        return OpenGLMatrix.translation(x, y, z).
                multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, u, v, w));
    }


    //*************************************************************************************************


    /**
     * Formats a matrix into a readable string
     * @param matrix
     * @return string of the matrix
     */
    private String formatMatrix(OpenGLMatrix matrix)
    {
        return matrix.formatAsTransform();
    }


}
