package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class GamepadTest {

   LinearOpMode opMode = null;
   int selectedIndex = 0;
   int selectedServoIndex = 0;

   public void performGamepadTest (LinearOpMode opMode)
   {
      GamepadButtons.ButtonType button;
      Boolean done = false;

      this.opMode = opMode;
      
      while (!done && opMode.opModeIsActive())
      {
         button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
         
         switch (button)
         {
            case Dpad_Left:
               done = true;
               break;
            case Left_Stick_X:
               opMode.telemetry.addData(button.toString(), opMode.gamepad1.left_stick_x);
               opMode.telemetry.update();
               break;
            case Left_Stick_Y:
               opMode.telemetry.addData(button.toString(), opMode.gamepad1.left_stick_y);
               opMode.telemetry.update();
               break;
            case Right_Stick_X:
               opMode.telemetry.addData(button.toString(), opMode.gamepad1.right_stick_x);
               opMode.telemetry.update();
               break;
            case Right_Stick_Y:
               opMode.telemetry.addData(button.toString(), opMode.gamepad1.right_stick_y);
               opMode.telemetry.update();
               break;
            case Left_Trigger:
               opMode.telemetry.addData(button.toString(), opMode.gamepad1.left_trigger);
               opMode.telemetry.update();
               break;
            case Right_Trigger:
               opMode.telemetry.addData(button.toString(), opMode.gamepad1.right_trigger);
               opMode.telemetry.update();
               break;
            default:
               opMode.telemetry.addData(button.toString(), "");
               opMode.telemetry.update();
               break;
         }
      }
   }

}
