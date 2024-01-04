package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class GamepadButtons {

   enum ButtonType {
      None,
      A,
      B,
      X,
      Y,
      Dpad_Up,
      Dpad_Down,
      Dpad_Right,
      Dpad_Left,
      Left_Stick_X,
      Left_Stick_Y,
      Right_Stick_X,
      Right_Stick_Y,
      Left_Stick_Button,
      Right_Stick_Button,
      Left_Bumper,
      Right_Bumper,
      Left_Trigger,
      Right_Trigger,
      Start,
      Guide,
      Back
   }

   static Boolean a_state = false;
   static Boolean b_state = false;
   static Boolean x_state = false;
   static Boolean y_state = false;
   static Boolean dpad_up_state = false;
   static Boolean dpad_down_state = false;
   static Boolean dpad_right_state = false;
   static Boolean dpad_left_state = false;
   static double left_stick_x_state = 0.0;
   static double left_stick_y_state = 0.0;
   static double right_stick_x_state = 0.0;
   static double right_stick_y_state = 0.0;
   static Boolean left_stick_button_state = false;
   static Boolean right_stick_button_state = false;
   static Boolean left_bumper_state = false;
   static Boolean right_bumper_state = false;
   static double left_trigger_state = 0.0;
   static double right_trigger_state = 0.0;
   static Boolean start_state = false;
   static Boolean guide_state = false;
   static Boolean back_state = false;

   private static void updateButtonStates (Gamepad gamepad)
   {
      a_state = gamepad.a;
      b_state = gamepad.b;
      x_state = gamepad.x;
      y_state = gamepad.y;
      dpad_up_state = gamepad.dpad_up;
      dpad_down_state = gamepad.dpad_down;
      dpad_right_state = gamepad.dpad_right;
      dpad_left_state = gamepad.dpad_left;
      left_stick_x_state = gamepad.left_stick_x;
      left_stick_y_state = gamepad.left_stick_y;
      right_stick_x_state = gamepad.right_stick_x;
      right_stick_y_state = gamepad.right_stick_y;
      left_stick_button_state = gamepad.left_stick_button;
      right_stick_button_state = gamepad.right_stick_button;
      left_bumper_state = gamepad.left_bumper;
      right_bumper_state = gamepad.right_bumper;
      left_trigger_state = gamepad.left_trigger;
      right_trigger_state = gamepad.right_trigger;
      start_state = gamepad.start;
      guide_state = gamepad.guide;
      back_state = gamepad.back;
   }

   public static ButtonType getButton (Gamepad gamepad)
   {
      ButtonType button = ButtonType.None;

      if (gamepad.a == true && a_state == false)
      {
        button = ButtonType.A;
      }
      else if (gamepad.b == true && b_state == false)
      {
        button = ButtonType.B;
      }
      else if (gamepad.x == true && x_state == false)
      {
        button = ButtonType.X;
      }
      else if (gamepad.y == true && y_state == false)
      {
        button = ButtonType.Y;
      }
      else if (gamepad.dpad_up == true && dpad_up_state == false)
      {
        button = ButtonType.Dpad_Up;
      }
      else if (gamepad.dpad_down == true && dpad_down_state == false)
      {
        button = ButtonType.Dpad_Down;
      }
      else if (gamepad.dpad_right == true && dpad_right_state == false)
      {
        button = ButtonType.Dpad_Right;
      }
      else if (gamepad.dpad_left == true && dpad_left_state == false)
      {
        button = ButtonType.Dpad_Left;
      }
      else if (gamepad.left_stick_x != left_stick_x_state)
      {
        button = ButtonType.Left_Stick_X;
      }
      else if (gamepad.left_stick_y != left_stick_y_state)
      {
        button = ButtonType.Left_Stick_Y;
      }
      else if (gamepad.right_stick_x != right_stick_x_state)
      {
        button = ButtonType.Right_Stick_X;
      }
      else if (gamepad.right_stick_y != right_stick_y_state)
      {
        button = ButtonType.Right_Stick_Y;
      }
      else if (gamepad.left_stick_button == true && left_stick_button_state == false)
      {
        button = ButtonType.Left_Stick_Button;
      }
      else if (gamepad.right_stick_button == true && right_stick_button_state == false)
      {
        button = ButtonType.Right_Stick_Button;
      }
      else if (gamepad.left_bumper == true && left_bumper_state == false)
      {
        button = ButtonType.Left_Bumper;
      }
      else if (gamepad.right_bumper == true && right_bumper_state == false)
      {
        button = ButtonType.Right_Bumper;
      }
      else if (gamepad.left_trigger != left_trigger_state)
      {
        button = ButtonType.Left_Trigger;
      }
      else if (gamepad.right_trigger != right_trigger_state)
      {
        button = ButtonType.Right_Trigger;
      }
      else if (gamepad.start == true && start_state == false)
      {
        button = ButtonType.Start;
      }
      else if (gamepad.guide == true && guide_state == false)
      {
        button = ButtonType.Guide;
      }
      else if (gamepad.back == true && back_state == false)
      {
        button = ButtonType.Back;
      }

      updateButtonStates(gamepad);
      
      return button;
   }

   public static ButtonType waitForButton (Gamepad gamepad, LinearOpMode opMode)
   {
      ButtonType button = ButtonType.None;
      Boolean done = false;

      while (!done && opMode.opModeIsActive ())
      {
         button = getButton (gamepad);
         
         if (button != ButtonType.None)
         {
           done = true;
         }
      }

      return button;      
   }
}