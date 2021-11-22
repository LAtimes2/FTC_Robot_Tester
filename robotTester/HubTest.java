package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.RobotCoreLynxModule;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import java.util.Iterator;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.reflect.Type;
import com.qualcomm.robotcore.hardware.Gamepad;


public class HubTest {

   String[] hubList = new String[]{
      "hub",
   };

   String[] testList = new String[]{
      "IMU",
   };

   LinearOpMode opMode = null;
   int selectedIndex = 0;
   ImuTest imuTest = new ImuTest ();

   public void performHubTest (LinearOpMode opMode)
   {
      GamepadButtons.ButtonType button;
      Boolean done = false;

      this.opMode = opMode;
      
      while (!done && opMode.opModeIsActive())
      {
///printHubInfo();
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
                     imuTest.performTest(opMode);
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

   public void printHubInfo ()
   {
//      List<HardwareDevice> channels = opMode.hardwareMap.getAll(DigitalChannel.class);
//      String name = channels.get(0).getDeviceName ();
//      int size = opMode.hardwareMap.deviceInterfaceModule.size();
      int size = opMode.hardwareMap.size();
      opMode.telemetry.addData("size", size);

      for (Iterator<HardwareDevice> iter = opMode.hardwareMap.iterator(); iter.hasNext(); ) {
        HardwareDevice element = iter.next();
if (element instanceof DcMotorController)
{
        opMode.telemetry.addData("itemM", element.getDeviceName());
}
if (element instanceof RobotCoreLynxModule)
{
////        opMode.telemetry.addData("itemL", ((RobotCoreLynxModule)element).getSerialNumber());
}
        opMode.telemetry.addData("item", element.getDeviceName());
      }

//      opMode.telemetry.addData("conn", opMode.hardwareMap.get("Expansion Hub Portal 1").getConnectionInfo());
   }

}
