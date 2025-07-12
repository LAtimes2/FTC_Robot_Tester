package org.firstinspires.ftc.teamcode.robotTester;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.RobotCoreLynxModule;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Device;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.qualcomm.robotcore.hardware.DcMotor;


public class RobotConfig {

  public static List<DeviceData> getHubs (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<RobotCoreLynxModule> hubList;

    // get list of hubs
    hubList = opMode.hardwareMap.getAll(RobotCoreLynxModule.class);

    for (RobotCoreLynxModule hub : hubList) {
      deviceList.add(new DeviceData (opMode.hardwareMap.getNamesOf(hub).iterator().next(),
                                     hub));
    }

    return deviceList;
  }

  public static List<DeviceData> getIMUs (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<IMU> imuList;

    // get list of hubs
    imuList = opMode.hardwareMap.getAll(IMU.class);

    for (IMU imu : imuList) {
      deviceList.add(new DeviceData (opMode.hardwareMap.getNamesOf(imu).iterator().next(),
                                     imu));
    }

    return deviceList;
  }

  public static List<DeviceData> getMotors (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<DcMotor> motorList;
    List<DeviceData> controllerList;
    int controllerIndex = 0;

    controllerList = getMotorControllers (opMode);

    if (controllerList.size() == 0) {
       return null;
    }

    for (DeviceData deviceData : controllerList) {
      
      DcMotorController controller = (DcMotorController)deviceData.device;
      String controllerName = "";

      if (controllerList.size() > 1) {
         controllerName = deviceData.name;
      }

      // set up default motors in case not in Configuration
      deviceList.add(new DeviceData(controllerName + " Port 0",
                                    new DcMotorImpl(controller, 0) ));
      deviceList.add(new DeviceData(controllerName + " Port 1",
                                    new DcMotorImpl(controller, 1) ));
      deviceList.add(new DeviceData(controllerName + " Port 2",
                                    new DcMotorImpl(controller, 2) ));
      deviceList.add(new DeviceData(controllerName + " Port 3",
                                    new DcMotorImpl(controller, 3) ));

      // get list of motors
      motorList = opMode.hardwareMap.getAll(DcMotor.class);

      for (DcMotor motor : motorList) {
      
        // if motor is on this controller
        if (motor.getController() == controller)
        {
          // name = first in set
          String name = opMode.hardwareMap.getNamesOf(motor).iterator().next();

          // index into deviceList is 4 (ports) for each previous controller + port number on current controller
          int index = controllerIndex * 4 + motor.getPortNumber();
        
          deviceList.get(index).name = deviceList.get(index).name + " (" + name + ")";
          deviceList.get(index).device = motor;
        }
      }
      
      controllerIndex++;
    }

    return deviceList;
  }

  public static List<DeviceData> getMotorControllers (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<DcMotorController> controllerList;

    // get list of motor controllers
    controllerList = opMode.hardwareMap.getAll(DcMotorController.class);

    for (DcMotorController controller : controllerList) {
      deviceList.add(new DeviceData (opMode.hardwareMap.getNamesOf(controller).iterator().next(),
                                     controller));
    }

    return deviceList;
  }

  public static List<DeviceData> getCRServos (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<CRServo> servoList;
    List<DeviceData> controllerList;
    int controllerIndex = 0;

    controllerList = getServoControllers (opMode);

    if (controllerList.size() == 0) {
       return null;
    }

    for (DeviceData deviceData : controllerList) {
      
      ServoController controller = (ServoController)deviceData.device;
      String controllerName = "";

      if (controllerList.size() > 1) {
         controllerName = deviceData.name;
      }

      // set up default servos in case not in Configuration
      deviceList.add(new DeviceData(controllerName + " CR Servo 0",
                                    new CRServoImpl(controller, 0) ));
      deviceList.add(new DeviceData(controllerName + " CR Servo 1",
                                    new CRServoImpl(controller, 1) ));
      deviceList.add(new DeviceData(controllerName + " CR Servo 2",
                                    new CRServoImpl(controller, 2) ));
      deviceList.add(new DeviceData(controllerName + " CR Servo 3",
                                    new CRServoImpl(controller, 3) ));
      deviceList.add(new DeviceData(controllerName + " CR Servo 4",
                                    new CRServoImpl(controller, 4) ));
      deviceList.add(new DeviceData(controllerName + " CR Servo 5",
                                    new CRServoImpl(controller, 5) ));

      // get list of servos
      servoList = opMode.hardwareMap.getAll(CRServo.class);

      for (CRServo servo : servoList) {
      
        // if servo is on this controller
        if (servo.getController() == controller)
        {
          // name = first in set
          String name = opMode.hardwareMap.getNamesOf(servo).iterator().next();

          // index into deviceList is 6 (ports) for each previous controller + port number on current controller
          int index = controllerIndex * 6 + servo.getPortNumber();
        
          deviceList.get(index).name = deviceList.get(index).name + " (" + name + ")";
          deviceList.get(index).device = servo;
        }
      }
      
      controllerIndex++;
    }

    return deviceList;
  }

  public static List<DeviceData> getServos (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<Servo> servoList;
    List<DeviceData> controllerList;
    int controllerIndex = 0;

    controllerList = getServoControllers (opMode);

    if (controllerList.size() == 0) {
       return null;
    }

    for (DeviceData deviceData : controllerList) {
      
      ServoController controller = (ServoController)deviceData.device;
      String controllerName = "";

      if (controllerList.size() > 1) {
         controllerName = deviceData.name;
      }

      // set up default servos in case not in Configuration
      deviceList.add(new DeviceData(controllerName + " Servo 0",
                                    new ServoImpl(controller, 0) ));
      deviceList.add(new DeviceData(controllerName + " Servo 1",
                                    new ServoImpl(controller, 1) ));
      deviceList.add(new DeviceData(controllerName + " Servo 2",
                                    new ServoImpl(controller, 2) ));
      deviceList.add(new DeviceData(controllerName + " Servo 3",
                                    new ServoImpl(controller, 3) ));
      deviceList.add(new DeviceData(controllerName + " Servo 4",
                                    new ServoImpl(controller, 4) ));
      deviceList.add(new DeviceData(controllerName + " Servo 5",
                                    new ServoImpl(controller, 5) ));

      // get list of servos
      servoList = opMode.hardwareMap.getAll(Servo.class);

      for (Servo servo : servoList) {
      
        // if servo is on this controller
        if (servo.getController() == controller)
        {
          // name = first in set
          String name = opMode.hardwareMap.getNamesOf(servo).iterator().next();

          // index into deviceList is 6 (ports) for each previous controller + port number on current controller
          int index = controllerIndex * 6 + servo.getPortNumber();
        
          deviceList.get(index).name = deviceList.get(index).name + " (" + name + ")";
          deviceList.get(index).device = servo;
        }
      }
      
      controllerIndex++;
    }

    return deviceList;
  }

  public static List<DeviceData> getServoControllers (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<ServoController> controllerList;

    // get list of servo controllers
    controllerList = opMode.hardwareMap.getAll(ServoController.class);

    for (ServoController controller : controllerList) {
      deviceList.add(new DeviceData (opMode.hardwareMap.getNamesOf(controller).iterator().next(),
                                     controller));
    }

    return deviceList;
  }

  public static List<DeviceData> getTouchSensors (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<TouchSensor> touchSensorList;
    List<DeviceData> controllerList;
    int controllerIndex = 0;

    controllerList = getTouchSensorControllers (opMode);

    if (controllerList.size() == 0) {
       return null;
    }

    for (DeviceData deviceData : controllerList) {
      
      DigitalChannelController controller = (DigitalChannelController)deviceData.device;
      String controllerName = "";

      if (controllerList.size() > 1) {
         controllerName = deviceData.name;
      }

      // set up default sensors in case not in Configuration
      deviceList.add(new DeviceData(controllerName + " Channel 0",
                                    new DigitalChannelImpl(controller, 0) ));
      deviceList.add(new DeviceData(controllerName + " Channel 1",
                                    new DigitalChannelImpl(controller, 1) ));
      deviceList.add(new DeviceData(controllerName + " Channel 2",
                                    new DigitalChannelImpl(controller, 2) ));
      deviceList.add(new DeviceData(controllerName + " Channel 3",
                                    new DigitalChannelImpl(controller, 3) ));
      deviceList.add(new DeviceData(controllerName + " Channel 4",
                                    new DigitalChannelImpl(controller, 4) ));
      deviceList.add(new DeviceData(controllerName + " Channel 5",
                                    new DigitalChannelImpl(controller, 5) ));
      deviceList.add(new DeviceData(controllerName + " Channel 6",
                                    new DigitalChannelImpl(controller, 6) ));
      deviceList.add(new DeviceData(controllerName + " Channel 7",
                                    new DigitalChannelImpl(controller, 7) ));

      // get list of sensors from the configuration
      touchSensorList = opMode.hardwareMap.getAll(TouchSensor.class);

      // if a touch sensor in the configuration matches the channel number,
      // then append the name of the touch sensor after the channel number

      for (TouchSensor touchSensor : touchSensorList) {
      
        // TouchSensor doesn't have a getController or getPortInfo function,
        // so need to match via connection info.
         
        String touchInfo = touchSensor.getConnectionInfo ();

        // TouchSensor calls it channel, but DigitalChannel calls it port.
        // Need them to be consistent so replace channel with port
        touchInfo = touchInfo.replace ("channel", "port");
      
        for (int index = 0; index < deviceList.size(); index++) {

          if (touchInfo.equals (deviceList.get(index).device.getConnectionInfo ())) {

            // get name from the Configuration
            String name = opMode.hardwareMap.getNamesOf(touchSensor).iterator().next();

            // append the name
            deviceList.get(index).name = deviceList.get(index).name + " (" + name + ")";

            // change the device from a digital channel to a touchSensor
            deviceList.get(index).device = touchSensor;
          }
        }
      }
      
      controllerIndex++;
    }

    return deviceList;
  }

  public static List<DeviceData> getTouchSensorControllers (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<DigitalChannelController> controllerList;

    // get list of digital (touch sensor) controllers
    controllerList = opMode.hardwareMap.getAll(DigitalChannelController.class);

    for (DigitalChannelController controller : controllerList) {
      deviceList.add(new DeviceData (opMode.hardwareMap.getNamesOf(controller).iterator().next(),
                                     controller));
    }

    return deviceList;
  }

  public static List<DeviceData> getVoltageSensors (LinearOpMode opMode)
  {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    List<VoltageSensor> hubList;

    // get list of voltage sensors in hubs
    hubList = opMode.hardwareMap.getAll(VoltageSensor.class);

    for (VoltageSensor hub : hubList) {
      deviceList.add(new DeviceData (opMode.hardwareMap.getNamesOf(hub).iterator().next(),
                                     hub));
    }

    return deviceList;
  }

}
