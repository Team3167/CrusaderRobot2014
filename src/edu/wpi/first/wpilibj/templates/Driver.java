/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import judge.util.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Handles the driving of the Robot
 *
 * @author (Mark Macerato)
 */
public class Driver {

    private Joystick driver = new Joystick(2);
    private Jaguar leftDrive = new Jaguar(1, 1);
    private Jaguar rightDrive = new Jaguar(1, 4);
    private RobotDrive drive = new RobotDrive(leftDrive, rightDrive);

    public void drive() {  //Call in teleop to enable driving with one joystick

        drive.arcadeDrive(-driver.getY(), -driver.getTwist()); // makin it easer to drive
    }
}
