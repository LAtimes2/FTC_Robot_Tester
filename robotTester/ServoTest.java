package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class ServoTest {

   String[] servoList = new String[]{
      "claw",
   };

   String[] testList = new String[]{
      "Joystick control",
      "0/1 control",
   };

   String[] joystickList = new String[]{
      "Joystick y",
      "Value",
   };

   LinearOpMode opMode = null;
   int selectedIndex = 0;
   int selectedServoIndex = 0;

   public void performServoTest (LinearOpMode opMode)
   {
      GamepadButtons.ButtonType button;
      Boolean done = false;

      this.opMode = opMode;
      
      while (!done && opMode.opModeIsActive())
      {
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
                     performJoystickTest ();
                     break;
                  case 1:
                     performDiscreteTest ();
                     break;
               }
               break;
            case Dpad_Left:
               done = true;
               break;
            default:
               break;
         }
      }
   }

   private void performJoystickTest ()
   {
      GamepadButtons.ButtonType button;
      Boolean done = false;

      while (!done && opMode.opModeIsActive())
      {
         Servo servo = selectServo ();
      
         if (servo == null)
         {
            done = true;
         }
         else
         {
            Boolean testDone = false;

            while (!testDone && opMode.opModeIsActive())
            {
               // stick goes -1 to 1, servo goes 0 to 1
               servo.setPosition ((opMode.gamepad1.left_stick_y + 1.0) / 2.0);
               
               joystickList[0] = "Joystick y: " + opMode.gamepad1.left_stick_y;
               joystickList[1] = "Value: " + servo.getPosition();
               Menu.drawMenu (joystickList, 0);
               button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
            
               switch (button)
               {
                  case Dpad_Left:
                     testDone = true;
                     break;
                  default:
                     break;
               }
            }
         }
      }
   }

   private void performDiscreteTest ()
   {
      String[] positionList = new String[]{
         "Set to 0",
         "Set to 1",
      };

      GamepadButtons.ButtonType button;
      Boolean done = false;
      int selectedPositionIndex = 0;

      while (!done && opMode.opModeIsActive())
      {
         Servo servo = selectServo ();
      
         if (servo == null)
         {
            done = true;
         }
         else
         {
            Boolean testDone = false;

            while (!testDone && opMode.opModeIsActive())
            {
               Menu.drawMenu (positionList, selectedPositionIndex);
               button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
            
               switch (button)
               {
                  case Dpad_Up:
                     selectedPositionIndex = Math.max(selectedPositionIndex - 1, 0);
                     break;
                  case Dpad_Down:
                     selectedPositionIndex = Math.min(selectedPositionIndex + 1, positionList.length - 1);
                     break;
                  case Dpad_Right:
                     switch (selectedPositionIndex)
                     {
                        case 0:
                           servo.setPosition (0.0);
                           break;
                        case 1:
                           servo.setPosition (1.0);
                           break;
                     }
                     break;
                  case Dpad_Left:
                     testDone = true;
                     break;
                  default:
                     break;
               }
            }
         }
      }
   }

   private Servo selectServo ()
   {
      Servo servo = null;
      GamepadButtons.ButtonType button;
      Boolean done = false;

servoList = Config.getServos (opMode);

      if (servoList.length == 0)
      {
         done = true;
      }

      while (!done && opMode.opModeIsActive())
      {
         Menu.drawMenu (servoList, selectedServoIndex);
         
         /*
         if (servoList.length == 1)
         {
            // if only one item, automatically select it
            button = GamepadButtons.ButtonType.Dpad_Right;
            done = true;
         }
         else
         */
         {
            button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
         }
         
         switch (button)
         {
            case Dpad_Up:
               selectedServoIndex = Math.max(selectedServoIndex - 1, 0);
               break;
            case Dpad_Down:
               selectedServoIndex = Math.min(selectedServoIndex + 1, servoList.length - 1);
               break;
            case Dpad_Right:
               try {
                  servo = opMode.hardwareMap.servo.get(servoList[selectedServoIndex]);
                  done = true;
               } catch(Exception e) {
                  servo = null;
                  opMode.telemetry.addData("Error", "No servo " + servoList[selectedServoIndex] + " found in hardwareMap");
                  opMode.telemetry.update();
                  opMode.sleep(5000);
               }
               break;
            case Dpad_Left:
               servo = null;
               done = true;
               break;
            default:
               break;
         }
      }
      
      return servo;
   }
}
