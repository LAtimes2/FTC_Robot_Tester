package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class CRServoTest {

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
      int selection = 0;

      this.opMode = opMode;
      
      while (!done && opMode.opModeIsActive())
      {
         selection = Menu.selectFromMenu (testList, selection, opMode);

         switch (selection)
         {
            case -1:
               done = true;
               break;
            case 0:
               performJoystickTest ();
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
         CRServo servo = selectServo ();
      
         if (servo == null)
         {
            done = true;
         }
         else
         {
            Boolean testDone = false;

            while (!testDone && opMode.opModeIsActive())
            {
               servo.setPower (Math.abs(opMode.gamepad1.right_stick_y) * opMode.gamepad1.right_stick_y);
               
               joystickList[0] = "Joystick y: " + opMode.gamepad1.right_stick_y;
               joystickList[1] = "Power: " + servo.getPower();
               Menu.drawMenu (joystickList, 0);
               button = GamepadButtons.getButton (opMode.gamepad1);
            
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

   private CRServo selectServo ()
   {
      CRServo servo = null;
      GamepadButtons.ButtonType button;
      Boolean done = false;

servoList = Config.getCRServos (opMode);

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
                  servo = opMode.hardwareMap.crservo.get(servoList[selectedServoIndex]);
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
