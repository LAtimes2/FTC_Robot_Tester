package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class ServoTest {

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
      Boolean done = false;

      this.opMode = opMode;
      
      selectedIndex = 0;
      
      while (!done && opMode.opModeIsActive())
      {
         selectedIndex = Menu.selectFromMenu (testList, selectedIndex, opMode);

         switch (selectedIndex)
         {
            case -1:
               done = true;
               break;
            case 0:
               performJoystickTest ();
               break;
            case 1:
               performDiscreteTest ();
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
               servo.setPosition ((opMode.gamepad1.right_stick_y + 1.0) / 2.0);
               
               joystickList[0] = "Joystick y: " + opMode.gamepad1.right_stick_y;
               joystickList[1] = "Value: " + servo.getPosition();
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
      Boolean done = false;
      int localServoIndex = selectedServoIndex;

      List<DeviceData> servoList = Config.getServos (opMode);

      while (!done && opMode.opModeIsActive())
      {
         localServoIndex = Menu.selectFromMenu (servoList, localServoIndex, opMode);

         switch (localServoIndex)
         {
            case -1:
               servo = null;
               done = true;
               break;
            default:
               servo = (Servo)servoList.get(localServoIndex).device;
               selectedServoIndex = localServoIndex;
               done = true;
               break;
         }
      }
      
      return servo;
   }
}
