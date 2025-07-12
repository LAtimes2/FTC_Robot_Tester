# FTC_Robot_Tester

This project has tools that can be used to test robot motors, servos and encoders (and others). One tool can run individual motors and servos using the gamepad. The other tool can quickly verify that motor encoders are working.

This directory has 2 opmodes in it - Check Encoders and Robot Tester.

To upload the files to OnBotJava:
1. Delete any previous version by selecting the robotTester folder and clicking the Delete File (Trash) icon
2. Select the Upload Files icon
3. Browse to the directory with all of the files you downloaded from Github
4. Select all of the files (using shift-select on windows) or paste this string into the filename:

"TouchSensorTest.java" "CheckEncoders.java" "CRServoTest.java" "DeviceData.java" "GamepadButtons.java" "GamepadTest.java" "HubTest.java" "ImuTest.java" "Menu.java" "MotorTest.java" "RobotConfig.java" "RobotTester.java" "ServoTest.java" 

5. Select Open. This should create a robotTester folder in OnBotJava

Check Encoders Teleop will test every motor in the configuration to see if its encoder works. It moves each motor slightly to see if the encoder value changes. If it does, it passes; otherwise, it fails. It then moves the motor back to the original position (you have to look closely to see it move).

Robot Tester Teleop can be used to test individual motors and servos on a REV Expansion Hub or Control Hub. It also can test the gamepad and display IMU and touch sensor values.

It uses the Dpad or A/B/X/Y buttons to go through menus on the Driver Station. Dpad up and down move the cursor, and Dpad right selects an item. Dpad left exits the current menu and goes back to the previous menu.

It allows selecting all motor or servo ports, even if not in the configuration file. If it is in the configuration file, it uses the name from there. The only time a configuration file is needed is if there is an expansion hub - the hub just needs to be in the configuration file, not the motors or servos.

The code is not really a good example of how to use motors and sensors since it has to go around the configuration file.

## Tests

### Motor Test

The joystick control test controls a single motor via the right joystick y. It displays the jostick value, the power value, and the encoder value for the motor.

The encoder wiggle test can be used to test the encoder by automatically moving the motor slightly. It then displays the encoder value.

The encoder test allows you to manually move the motor and see the encoder value change.

### Servos

The joystick control test controls a single servo via the right joystick y. It starts the test at the middle value (0.5), so the servo may move initially. Then it moves the servo as the right joystick y changes. It stops moving when the joystick is at the rest position. It displays joystick position and servo value.

### Continuous Rotation Servos

The joystick test controls a single servo via the right joystick y. Down is clockwise, up is counterclockwise. It displays joystick position and servo power value.

### Hub

The hub menu displays the hub voltages.

The IMU test displays the yaw/pitch/roll values of each IMU. The control hub IMU is automatically added to the configuration file, but an expansion hub IMU can be added manually to the configuration under Expansion Hub I2C Bus 0 as a REV internal IMU with the name 'imu2'. Then its values will also be displayed.

### Touchsensor

The touchsensor test displays the value of all digital inputs (which may not be touchsensors). If an input is configured as a touchsensor, the configuration name is displayed.

### Gamepad

The gamepad test displays the name of the last button that was pressed. If 2 gamepads are connected, it will indicate which gamepad the button is on. For joysticks and triggers, it also displays the analog value.

