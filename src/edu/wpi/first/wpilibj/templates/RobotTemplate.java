/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

	private Joystick driver;
	private Jaguar leftMotor;
	private Jaguar rightMotor;
	private RobotDrive drive;
	private double setSpeed;

    public void robotInit() {

		driver = new Joystick(1);
		leftMotor = new Jaguar(1, 1);
		rightMotor = new Jaguar(1, 2);
		drive = new RobotDrive(leftMotor, rightMotor);
		setSpeed = 0.0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {


    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

		drive.arcadeDrive(driver);
		setSpeed = driver.getThrottle();
		if(driver.getRawButton(1))
		{
		  leftMotor.set(setSpeed);
		  rightMotor.set(setSpeed);
		}
		else
		{
		  leftMotor.set(0.0);
		  rightMotor.set(0.0);
		}
    }

}
