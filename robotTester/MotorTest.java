package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class MotorTest {

   String[] motorList = new String[]{
      "left_drive_motor",
      "right_drive_motor",
      "left_rear_motor",
      "right_rear_motor",
   };

   String[] testList = new String[]{
      "Joystick control",
      "Encoder test",
      "Set parameters"
   };

   String[] joystickList = new String[]{
      "Joystick y",
      "Power",
      "Encoder"
   };

   LinearOpMode opMode = null;
   int selectedIndex = 0;
   int selectedMotorIndex = 0;

   public void performMotorTest (LinearOpMode opMode)
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
                     performEncoderTest ();
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
         DcMotor motor = selectMotor ();
      
         if (motor == null)
         {
            done = true;
         }
         else
         {
            Boolean testDone = false;

            while (!testDone && opMode.opModeIsActive())
            {
               motor.setPower (Math.abs(opMode.gamepad1.right_stick_y) * opMode.gamepad1.right_stick_y);
               
               joystickList[0] = "Joystick y: " + opMode.gamepad1.right_stick_y;
               joystickList[1] = "Power: " + motor.getPower();
               joystickList[2] = "Encoder: " + motor.getCurrentPosition();
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

   private void performEncoderTest ()
   {
      DcMotor motor = selectMotor ();
      
      if (motor == null)
      {
         return;
      }
      
      while (motor != null && opMode.opModeIsActive ())
      {
         motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         motor.setPower(0.5);
      }
   }

   private DcMotor selectMotor ()
   {
      DcMotor motor = null;
      GamepadButtons.ButtonType button;
      Boolean done = false;

motorList = Config.getMotors (opMode);

      if (motorList.length == 0)
      {
         done = true;
      }

      while (!done && opMode.opModeIsActive())
      {
         Menu.drawMenu (motorList, selectedMotorIndex);
         button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
         
         switch (button)
         {
            case Dpad_Up:
               selectedMotorIndex = Math.max(selectedMotorIndex - 1, 0);
               break;
            case Dpad_Down:
               selectedMotorIndex = Math.min(selectedMotorIndex + 1, motorList.length - 1);
               break;
            case Dpad_Right:
               try {
                  motor = opMode.hardwareMap.dcMotor.get(motorList[selectedMotorIndex]);
                  done = true;
               } catch(Exception e) {
                  motor = null;
                  opMode.telemetry.addData("Error", "No motor " + motorList[selectedMotorIndex] + " found in hardwareMap");
                  opMode.telemetry.update();
                  opMode.sleep(5000);
               }
               break;
            case Dpad_Left:
               motor = null;
               done = true;
               break;
            default:
               break;
         }
      }
      
      return motor;
   }
}