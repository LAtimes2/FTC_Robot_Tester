# FTC_Robot_Tester

This directory has 2 opmodes in it.

Check Encoders will test every motor in the configuration to see if its encoder works. It moves each motor slightly to see if the encoder value changes. If it does, it passes; otherwise, it fails. It then moves the motor back to the original position (you have to look closely to see it move).

Robot Tester can be used to test individual motors and servos on a REV Expansion Hub or Control Hub. It also can test the gamepad and display IMU values.

It uses the Dpad to go through menus on the Driver Station. Dpad up and down move the cursor, and Dpad right selects an item. Dpad left exits the current menu and goes back to the previous menu.

It allows selecting all motor or servo ports, even if not in the configuration file. If it is in the configuration file, it uses the name from there.
