package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.hardware.HardwareDeviceManager;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class MotorTest {

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
               performEncoderTest ();
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
      
//      while (motor != null && opMode.opModeIsActive ())
//      {
//         motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//         motor.setPower(0.5);
//      }
   }

   private DcMotor selectMotor ()
   {
      DcMotor motor = null;
      Boolean done = false;
      int localMotorIndex = selectedMotorIndex;

      List<DeviceData> motorList = Config.getMotors (opMode);

      while (!done && opMode.opModeIsActive())
      {
         localMotorIndex = Menu.selectFromMenu (motorList, localMotorIndex, opMode);

         switch (localMotorIndex)
         {
            case -1:
               motor = null;
               done = true;
               break;
            default:
               motor = (DcMotor)motorList.get(localMotorIndex).device;
               selectedMotorIndex = localMotorIndex;
               done = true;
               break;
         }
      }
      
      return motor;
   }
}