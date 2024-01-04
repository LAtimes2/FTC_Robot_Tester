package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.bosch.BNO055IMU;
import java.util.List;
import com.qualcomm.robotcore.hardware.IMU;


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
   IMU commonImu;

   public void performTest (LinearOpMode opMode)
   {
      GamepadButtons.ButtonType button;
      Boolean done = false;
      int selection = 0;

      /* The next two lines define Hub orientation.
      * The Default Orientation (shown) is when a hub is mounted horizontally with the printed logo pointing UP and the USB port pointing FORWARD.
      *
      * To Do:  EDIT these two lines to match YOUR mounting configuration.
      */
      RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
      RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
      RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);


      this.opMode = opMode;
      
      try {
         // Now initialize the IMU with this mounting orientation
         // This sample expects the IMU to be in a REV Hub and named "imu".
         commonImu = opMode.hardwareMap.get(IMU.class, "imu");
         commonImu.initialize(new IMU.Parameters(orientationOnRobot));
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
         YawPitchRollAngles orientation = commonImu.getRobotYawPitchRollAngles();

         textList[0] = "Angles";
         textList[1] = "";
         textList[2] = "Heading: " + orientation.getYaw(AngleUnit.DEGREES);
         textList[3] = "Pitch: " + orientation.getPitch(AngleUnit.DEGREES);
         textList[4] = "Roll:  " + orientation.getRoll(AngleUnit.DEGREES);

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
