package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.NaiveAccelerationIntegrator;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;


public class ImuTest {

   String[] testList = new String[]{
      "Angles",
      "Distance",
      "Gravity",
      "Magnetism",
   };

   LinearOpMode opMode = null;
   int selectedIndex = 0;
   
   BNO055IMU imu;

   public void performTest (LinearOpMode opMode)
   {
      GamepadButtons.ButtonType button;
      Boolean done = false;
      int selection = 0;

      BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
      parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
      parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
      parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
      parameters.mode                = BNO055IMU.SensorMode.NDOF;

      this.opMode = opMode;
      
      try {
         imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
         imu.initialize (parameters);
      } catch(Exception e) {
         done = true;
      }

      while (!done && opMode.opModeIsActive())
      {
         selection = Menu.selectFromMenu (testList, selection, opMode);

         switch (selection)
         {
            case -1:
               done = true;
               break;
            case 0:
               performAnglesTest ();
               break;
            case 1:
               performDistanceTest ();
               break;
            case 2:
               performGravityTest ();
               break;
            case 3:
               performMagnetismTest ();
               break;
         }
      }
   }

   private void performAnglesTest ()
   {
      GamepadButtons.ButtonType button;
      Boolean testDone = false;
      String[] textList = new String[5];

      while (!testDone && opMode.opModeIsActive())
      {
         textList[0] = "Angles";
         textList[1] = "";
         textList[2] = "Heading: " + imu.getAngularOrientation().firstAngle;
         textList[3] = "Pitch: " + imu.getAngularOrientation().secondAngle;
         textList[4] = "Roll:  " + imu.getAngularOrientation().thirdAngle;

         // -100 prevents menu selection cursor
         Menu.drawMenu (textList, -100);
         button = GamepadButtons.getButton (opMode.gamepad1);
      
         switch (button)
         {
            case Dpad_Left:
               testDone = true;
               break;
            default:
               break;
         }
         
         opMode.sleep(200);
      }
   }

   private void performDistanceTest ()
   {
      GamepadButtons.ButtonType button;
      Boolean testDone = false;
      String[] textList = new String[5];

      while (!testDone && opMode.opModeIsActive())
      {
         textList[0] = "Acceleration";
         textList[1] = "";
         textList[2] = "X: " + imu.getLinearAcceleration().xAccel;
         textList[3] = "Y: " + imu.getLinearAcceleration().yAccel;
         textList[4] = "Z: " + imu.getLinearAcceleration().zAccel;

         // -100 prevents menu selection cursor
         Menu.drawMenu (textList, -100);
         button = GamepadButtons.getButton (opMode.gamepad1);
      
         switch (button)
         {
            case Dpad_Left:
               testDone = true;
               break;
            default:
               break;
         }
         
         opMode.sleep(200);
      }
   }
   
   private void performGravityTest ()
   {
      GamepadButtons.ButtonType button;
      Boolean testDone = false;
      String[] textList = new String[5];

      while (!testDone && opMode.opModeIsActive())
      {
         textList[0] = "Gravity";
         textList[1] = "";
         textList[2] = "X: " + imu.getGravity().xAccel;
         textList[3] = "Y: " + imu.getGravity().yAccel;
         textList[4] = "Z: " + imu.getGravity().zAccel;

         // -100 prevents menu selection cursor
         Menu.drawMenu (textList, -100);
         button = GamepadButtons.getButton (opMode.gamepad1);
      
         switch (button)
         {
            case Dpad_Left:
               testDone = true;
               break;
            default:
               break;
         }
         
         opMode.sleep(200);
      }
   }
   
   private void performMagnetismTest ()
   {
      GamepadButtons.ButtonType button;
      Boolean testDone = false;
      String[] textList = new String[5];

      while (!testDone && opMode.opModeIsActive())
      {
         textList[0] = "Magnetism";
         textList[1] = "";
         textList[2] = "X: " + imu.getMagneticFieldStrength().x * 1000000.0;
         textList[3] = "Y: " + imu.getMagneticFieldStrength().y * 1000000.0;
         textList[4] = "Z: " + imu.getMagneticFieldStrength().z * 1000000.0;

         // -100 prevents menu selection cursor
         Menu.drawMenu (textList, -100);
         button = GamepadButtons.getButton (opMode.gamepad1);
      
         switch (button)
         {
            case Dpad_Left:
               testDone = true;
               break;
            default:
               break;
         }
         
         opMode.sleep(200);
      }
   }
   
}
