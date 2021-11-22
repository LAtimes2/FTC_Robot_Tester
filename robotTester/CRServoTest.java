package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import java.util.List;
import java.lang.reflect.Type;


public class CRServoTest {

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
      Boolean done = false;
      int localServoIndex = selectedServoIndex;

      List<DeviceData> servoList = Config.getCRServos (opMode);

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
               servo = (CRServo)servoList.get(localServoIndex).device;
               selectedServoIndex = localServoIndex;
               done = true;
               break;
         }
      }
      
      return servo;
   }
}
