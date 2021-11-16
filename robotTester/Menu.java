package org.firstinspires.ftc.teamcode.robotTester;

import java.util.List;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Menu {

   public static Telemetry telemetry;
   
   public static void drawMenu (String[] menuList, int selectedIndex)
   {
      String caption;

      for (int index = 0; index < menuList.length; ++index)
      {
         if (index == selectedIndex)
         {
            caption = ">";
         }
         else
         {
            caption = "";
         }

         telemetry.addData (caption, menuList[index]);
      }
      
      telemetry.update ();
   }
   
   public static int selectFromMenu (String[] menuList, int selectedIndex, LinearOpMode opMode)
   {
      GamepadButtons.ButtonType button;
      int currentIndex = selectedIndex;
      Boolean done = false;

      while (!done && opMode.opModeIsActive())
      {
         Menu.drawMenu (menuList, currentIndex);
         button = GamepadButtons.waitForButton (opMode.gamepad1, opMode);
         
         switch (button)
         {
            case Dpad_Up:
               currentIndex = Math.max(currentIndex - 1, 0);
               break;
            case Dpad_Down:
               currentIndex = Math.min(currentIndex + 1, menuList.length - 1);
               break;
            case Dpad_Right:
               done = true;
               break;
            case Dpad_Left:
               currentIndex = -1;
               done = true;
               break;
            default:
               break;
         }
      }
      
      return currentIndex;
   }
}
