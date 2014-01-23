/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import judge.util.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

	private Joystick driver;
	private JoystickButton button1;
    private JoystickButton button2;
    private JoystickButton button3;
    private JoystickButton button4;
    private JoystickButton button5;
    private JoystickButton button6;
    private JoystickButton button7;
    private JoystickButton button8;
    private JoystickButton button9;
    private JoystickButton button10;
    private JoystickButton button11;
    private JoystickButton button12;
	private Jaguar leftDrive;
	private Jaguar rightDrive;
	private Jaguar leftGrabber;
	private Jaguar rightGrabber;
	private Jaguar grabberSpinner;
	private Jaguar shooterBL;
	private Jaguar shooterBR;
	private Jaguar shooterFL;
	private Jaguar shooterFR;
	private RobotDrive drive;
	private double setSpeed;
    private double variance;
    private boolean varianceToggle;
	private final DriverStationLCD msg = DriverStationLCD.getInstance();

    public void robotInit() {

		driver = new Joystick(2);
		button1 = new JoystickButton(driver, 1);
		button2 = new JoystickButton(driver, 2);
		button3 = new JoystickButton(driver, 3);
		button4 = new JoystickButton(driver, 4);
		button5 = new JoystickButton(driver, 5);
		button6 = new JoystickButton(driver, 6);
		button7 = new JoystickButton(driver, 7);
		button8 = new JoystickButton(driver, 8);
		button9 = new JoystickButton(driver, 9);
		button10 = new JoystickButton(driver, 10);
		button11 = new JoystickButton(driver, 11);
		button12 = new JoystickButton(driver, 12);
		leftDrive = new Jaguar(1, 1);
		rightDrive = new Jaguar(1, 4);
		leftGrabber = new Jaguar(1, 3);
		rightGrabber = new Jaguar(1, 2);
		grabberSpinner = new Jaguar(1, 5);
		shooterBL = new Jaguar(1,6);
		shooterBR = new Jaguar(1,7);
		shooterFL = new Jaguar(1, 8);
		shooterFR =	new Jaguar(1, 9);
		drive = new RobotDrive(leftDrive, rightDrive);
		setSpeed = 0.0;
        variance = 1;
        varianceToggle = false;
    }

	public void disabledInit()
    {
        System.out.println("The Robot is ready to Rock and Roll!");
    }

    public void disabledContinuous()
    {
        //nothing goes here!
    }

	public void disabledPeriodic()
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
    public void teleopPeriodic() {

		drive.arcadeDrive(-driver.getY(), -driver.getTwist());

//driver.getRawAxis(6) forward backward movement of the D-pad (-1 up, 1 down)
//driver.getRawAxis(5) left right movement of the D-pad (-1 left, 1 right)

		double speed = .7;
		if(driver.getRawAxis(6) == -1)
		{
		  leftGrabber.set(speed);
		  rightGrabber.set(speed);
		}
		else if(driver.getRawAxis(6) == 1)
		{
			leftGrabber.set(-speed);
			rightGrabber.set(-speed);
		}
		else
		{
		  leftGrabber.set(0.0);
		  rightGrabber.set(0.0);
		}

		if(button5.IsPressed())
		{
			grabberSpinner.set(1.0);
		}
		else if(button3.IsPressed())
		{
			grabberSpinner.set(-1.0);
		}
		else
		{
			grabberSpinner.set(0.0);
		}

		if (varianceToggle)
        {
            variance = ((driver.getThrottle() * -1 / 2) + .5);
            if (variance > 1)
            {
                variance = 1;
            }
            else if (variance < 0)
            {
                variance = 0;
            }
            printSpeed(variance);
        }
        else
        {
            setSpeed = ((driver.getThrottle() * -1 / 2) + .5);
            if (setSpeed > 1)
            {
                setSpeed = 1;
            }
            else if (setSpeed < 0)
            {
                setSpeed = 0;
            }
            printSpeed(setSpeed);
        }

        if (button4.IsPressed())
        {
            setAllMotors(setSpeed, variance);
        }
        else if (button6.IsPressed())
        {
            setAllMotors(setSpeed * -1, variance);
            printSpeed(setSpeed);
        }
        else
        {
            setAllMotors(0.0, variance);
        }

        if (button8.IsPressed())
        {
            varianceToggle = true;
        }
		else
		{
			varianceToggle = false;
		}
    }

    public void setAllMotors(double speed, double variance)
    {
        if (speed <= 0)
        {
            shooterFL.set(-speed);
            shooterFR.set(speed);
            shooterBL.set(-speed);
            shooterBR.set(speed);
        }
        else
        {
            shooterFL.set(-speed);
            shooterFR.set(speed);
            shooterBL.set(-speed * variance);
            shooterBR.set(speed * variance);
        }
    }

    public void printSpeed(double speed)
    {
        msg.clear();
        int number;
        number = (int) (speed * 100);  // make sure this works right
        if (varianceToggle)
        {
            msg.println(DriverStationLCD.Line.kUser2, 1, "Varaince is: "
                + number);
        }
        else
        {
            msg.println(DriverStationLCD.Line.kUser2, 1, "Speed is: "
                + number);
        }
        msg.updateLCD();
    }
}

