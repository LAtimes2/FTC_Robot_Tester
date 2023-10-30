package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;

@TeleOp
public class CheckEncoders extends LinearOpMode {

   @Override
   public void runOpMode() {
   
      boolean passed = false;
      String results = "";
      List<DcMotor> motorList;

      telemetry.addData ("This will test each motor encoder ", "");
      telemetry.addData ("and display Pass/Fail for each one", "");
      telemetry.update ();

      waitForStart();

      // get list of motors
      motorList = hardwareMap.getAll(DcMotor.class);

      for (DcMotor motor : motorList) {
      
         // name = first in set
         String name = hardwareMap.getNamesOf(motor).iterator().next();

         // test the encode by running the motor slightly
         motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         motor.setPower(0.1);
         sleep(50);
         motor.setPower(0.0);
         sleep(100);

         if (motor.getCurrentPosition() != 0)
         {
            passed = true;
            results = "Passed";
         }
         else
         {
            passed = false;
            results = "Failed";
         }

         // set motor back to original position
         motor.setPower(-0.1);
         sleep(50);
         motor.setPower(0.0);

         telemetry.addData (name, results);
      }

      telemetry.update ();

      // wait a long time (10 minutes)
      sleep (600000);
   }

}
