package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class RobotTester extends LinearOpMode {

   @Override
   public void runOpMode() {
      
      Menu.telemetry = telemetry;

      waitForStart();
      
      mainMenu();      
   }
   
   private void mainMenu() {

      String[] testList = new String[]{
         "Hub test",
         "Motor test",
         "Servo test",
         "CR Servo test",
         "Gamepad test",
      };

      GamepadButtons.ButtonType button;
      Boolean done = false;
      int selectedIndex = 0;

      CRServoTest crservoTest = new CRServoTest ();
      GamepadTest gamepadTest = new GamepadTest ();
      HubTest hubTest = new HubTest ();
      MotorTest motorTest = new MotorTest ();
      ServoTest servoTest = new ServoTest ();

      LinearOpMode opMode = this;
      
      while (!done && opMode.opModeIsActive())
      {
         telemetry.addData ("   Use Dpad to select items", "");
         telemetry.addData ("", "");
         Menu.drawMenu (testList, selectedIndex);
         button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
         
         switch (button)
         {
            case Dpad_Up:
               selectedIndex = Math.max(selectedIndex - 1, 0);
               break;
            case Dpad_Down:
               selectedIndex = Math.min(selectedIndex + 1, testList.length - 1);
               break;
            case Dpad_Right:
               switch (selectedIndex)
               {
                  case 0:
                     hubTest.performHubTest (this);
                     break;
                  case 1:
                     motorTest.performMotorTest (this);      
                     break;
                  case 2:
                     servoTest.performServoTest (this);
                     break;
                  case 3:
                     crservoTest.performServoTest (this);
                     break;
                  case 4:
                     gamepadTest.performGamepadTest (this);
                     break;
               }
               break;
            case Dpad_Left:
               // do not exit since this is the top menu
               break;
            default:
               break;
         }
      }
   }

}