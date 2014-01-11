/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.*;
import judge.util.*;

 /* The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */



public class RobotTemplate extends IterativeRobot
{
	// The drive motors.
	private Jaguar frontL;
	private Jaguar frontR;
	private Jaguar backL;
	private Jaguar backR;
	// The joystick to drive the robot
	private Joystick driver;
	//The joystick button presses
	private JoystickButton button1;
	private JoystickButton button2;
	private JoystickButton button3;
	private JoystickButton button4;
	private JoystickButton button5;
	private JoystickButton button6;
	private JoystickButton button7;
	//private Compressor compressor;
	private final DriverStationLCD msg = DriverStationLCD.getInstance();
	private double setSpeed;
	private double variance;
	private boolean varianceToggle;
	private boolean reset;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
	{
		frontL = new Jaguar(1,1);
		backL = new Jaguar(1,2);
		frontR = new Jaguar(1,3);
		backR = new Jaguar(1,4);
		driver = new Joystick(2);
		button1 = new JoystickButton(driver, 1);
		button2 = new JoystickButton(driver, 2);
		button3 = new JoystickButton(driver, 3);
		button4 = new JoystickButton(driver, 4);
		button5 = new JoystickButton(driver, 5);
		button6 = new JoystickButton(driver, 6);
		button7 = new JoystickButton(driver, 7);
		//compressor = new Compressor(1,1);
		setSpeed = 0.0;
		variance = 1;
		varianceToggle = false;
		reset = false;
	}

	public void disabledInit()
	{
        System.out.println("The Robot is ready to Rock and Roll!");
    }

	public void disabledContinuous()
	{
		//nothing goes here!
	}

    /**
     * This function is called periodically during autonomous
     */
	public void autonomousPeriodic()
	{
		//no autonomous yet
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
	{
		if(varianceToggle)
		{
			variance = ((driver.getThrottle() * -1/2) + .5);
			printSpeed(variance);
		}
		else
		{
			setSpeed = ((driver.getThrottle() * -1/2) + .5);
			printSpeed(setSpeed);
		}

		if(button5.IsPressed())
		{
			setAllMotors(setSpeed, variance);
		}
		if(button3.IsPressed())
		{
			setAllMotors(setSpeed * -1);
			printSpeed(setSpeed);
		}
		else
		{
			setAllMotors(0.0);
		}

		if(button4.HasJustBeenPressed())
		{
			varianceToggle = !varianceToggle;
		}
	}


	public void setAllMotors(double speed)
	{
		frontL.set(-speed);
		frontR.set(speed);
		backL.set(-speed);
		backR.set(speed);
	}

	public void setAllMotors(double speed, double variance)
	{
		frontL.set(-speed);
		frontR.set(speed);
		backL.set(-speed * variance);
		backR.set(speed * variance);
	}

	public void printSpeed(double speed)
	{
		if(varianceToggle)
		msg.println(DriverStationLCD.Line.kUser2, 1, "varaince is: ");
		else
		msg.println(DriverStationLCD.Line.kUser2, 1, "speed is: ");

		msg.println(DriverStationLCD.Line.kUser2, 1, "" + speed + "");
		msg.updateLCD();
	}
}
