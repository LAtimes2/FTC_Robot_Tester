package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class RobotTester extends LinearOpMode {

   public GamepadButtons gamepadButtons1;
   public GamepadButtons gamepadButtons2;

   @Override
   public void runOpMode() {

      gamepadButtons1 = new GamepadButtons (gamepad1, this);   
      gamepadButtons2 = new GamepadButtons (gamepad2, this);
      
      Menu.telemetry = telemetry;

      telemetry.addData ("Robot Tester   ", "V1.1    7/11/2025");
      telemetry.update ();

      waitForStart();
      
      mainMenu();      
   }
   
   private void mainMenu() {

      String[] testList = new String[]{
         "Hub test",
         "Motor test",
         "Servo test",
         "CR Servo test",
         "Touch Sensor test",
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
      TouchSensorTest touchSensorTest = new TouchSensorTest ();

      LinearOpMode opMode = this;
      
      while (!done && opMode.opModeIsActive())
      {
         telemetry.addData ("   Use Dpad or A/B/X/Y to select items", "");
         telemetry.addData ("", "");
         Menu.drawMenu (testList, selectedIndex);
         button = gamepadButtons1.waitForButton ();
         
         switch (button)
         {
            case Dpad_Up:
            case Y:
               selectedIndex = Math.max(selectedIndex - 1, 0);
               break;
            case Dpad_Down:
            case A:
               selectedIndex = Math.min(selectedIndex + 1, testList.length - 1);
               break;
            case Dpad_Right:
            case B:
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
                     touchSensorTest.performTouchSensorTest (this);
                     break;
                  case 5:
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