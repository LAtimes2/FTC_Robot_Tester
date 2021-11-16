package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorController;
//import com.qualcomm.robotcore.hardware.HardwareDevice;
//import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
//import com.qualcomm.robotcore.hardware.Servo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.qualcomm.robotcore.hardware.DcMotor;
//import java.lang.reflect.Type;
//import com.qualcomm.robotcore.hardware.Gamepad;


public class Config {

  public static String[] getCRServos (LinearOpMode opMode)
  {
    List<CRServo> servoList;
    List<String> servoNames = new ArrayList<String>();
    
    // get list of servos
    servoList = opMode.hardwareMap.getAll(CRServo.class);

    // get names
    for (CRServo servo : servoList) {
      // may have multiple names
      Set<String> names = opMode.hardwareMap.getNamesOf(servo);

      // name = first in set
      String name = names.iterator().next();
      
      servoNames.add(name);
    }

    String[] namesArray = new String[servoNames.size()];
    servoNames.toArray (namesArray);

    return namesArray;
  }

  public static String[] getServos (LinearOpMode opMode)
  {
    List<Servo> servoList;
    List<String> servoNames = new ArrayList<String>();
    
    // get list of servos
    servoList = opMode.hardwareMap.getAll(Servo.class);

    // get names
    for (Servo servo : servoList) {
      // may have multiple names
      Set<String> names = opMode.hardwareMap.getNamesOf(servo);

      // name = first in set
      String name = names.iterator().next();
      
      servoNames.add(name);
    }

    String[] namesArray = new String[servoNames.size()];
    servoNames.toArray (namesArray);

    return namesArray;
  }

  public static String[] getMotors (LinearOpMode opMode)
  {
    List<DcMotor> motorList;
    List<String> motorNames = new ArrayList<String>();
    
    // get list of motors
    motorList = opMode.hardwareMap.getAll(DcMotor.class);

    // get names
    for (DcMotor motor : motorList) {
      // may have multiple names
      Set<String> names = opMode.hardwareMap.getNamesOf(motor);

      // name = first in set
      String name = names.iterator().next();
      
      motorNames.add(name);
    }

    String[] namesArray = new String[motorNames.size()];
    motorNames.toArray (namesArray);

    return namesArray;
  }

  public static String[] getMotorControllers (LinearOpMode opMode)
  {
    List<DcMotorController> motorList;
    List<String> motorNames = new ArrayList<String>();
    
    // get list of motor controllers
    motorList = opMode.hardwareMap.getAll(DcMotorController.class);

    // get names
    for (DcMotorController motor : motorList) {
      // may have multiple names
      Set<String> names = opMode.hardwareMap.getNamesOf(motor);

      // name = first in set
      String name = names.iterator().next();
      
      motorNames.add(name);
    }

    String[] namesArray = new String[motorNames.size()];
    motorNames.toArray (namesArray);

    return namesArray;
  }

}
