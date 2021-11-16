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
   }

   static Boolean a_state = false;
   static Boolean b_state = false;
   static Boolean x_state = false;
   static Boolean y_state = false;
   static Boolean dpad_up_state = false;
   static Boolean dpad_down_state = false;
   static Boolean dpad_right_state = false;
   static Boolean dpad_left_state = false;
   static double right_stick_y_state = 0.0;

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
      right_stick_y_state = gamepad.right_stick_y;
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
//      else if (gamepad.left_stick_y != 0)
//         {
//            button = ButtonType.Left_Stick_Y;
//            done = true;
//         }
//         else if (gamepad.right_stick_y != 0)
      else if (gamepad.right_stick_y != right_stick_y_state)
      {
        button = ButtonType.Right_Stick_Y;
      }

      updateButtonStates(gamepad);
      
      return button;
   }

   public static ButtonType waitForButton (Gamepad gamepad, LinearOpMode opMode)
   {
      ButtonType button = ButtonType.None;
      Boolean done = false;

//      dpad_up_state = gamepad.dpad_up;
//      dpad_down_state = gamepad.dpad_down;
//      dpad_right_state = gamepad.dpad_right;
//      dpad_left_state = gamepad.dpad_left;
//      right_stick_y_state = gamepad.right_stick_y;
      
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