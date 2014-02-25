package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import judge.util.JoystickButton;
import judge.util.sensors.UltrasonicDistanceSensor;

/**
 * This is the code for the 2014 Crusader Robot "Fido" it uses 2 digital
 * sidecars this year. All of this code is available from
 * github.com/Team3167/CrusaderRobot2014 there is also a wiki page on the github
 * so that people can find the controls.
 *
 * @author Eric Slaweski, Mark Macerato
 */
public class RobotTemplate extends IterativeRobot
{

	private Joystick driver;
	private Joystick shooter;
	private JoystickButton driver1;
	private JoystickButton driver2;
	private JoystickButton driver3;
	private JoystickButton driver4;
	private JoystickButton driver5;
	private JoystickButton driver6;
	private JoystickButton driver7;
	private JoystickButton driver8;
	private JoystickButton driver9;
	private JoystickButton driver10;
	private JoystickButton driver11;
	private JoystickButton driver12;
	private JoystickButton shooter1;
	private JoystickButton shooter2;
	private JoystickButton shooter3;
	private JoystickButton shooter4;
	private JoystickButton shooter5;
	private JoystickButton shooter6;
	private JoystickButton shooter7;
	private JoystickButton shooter8;
	private JoystickButton shooter9;
	private JoystickButton shooter10;
	private JoystickButton shooter11;
	private JoystickButton shooter12;
	private Jaguar leftDrive;
	private Jaguar rightDrive;
	private Jaguar leftGrabber;
	private Jaguar rightGrabber;
	private Jaguar grabberSpinner;
	private Jaguar shooterBL; //Back left
	private Jaguar shooterBR; //Back right
	private Jaguar shooterMR; //Middle right
	private Jaguar shooterML; //Middle left
	private Jaguar shooterFR; //Front Right
	private Jaguar shooterFL; //Front left
	private RobotDrive drive;
	private Aligner aligner;
	private DriveController driveController;
	private double setSpeed;
	private double variance;
	private boolean varianceToggle;
	private final DriverStationLCD msg = DriverStationLCD.getInstance();
	private double grabberArmSpeed;
	private boolean grabberPreviouslyUp;
	private UltrasonicDistanceSensor sensor1;
	private Timer autonomousDriveTimer;
	private Timer autonomousShootTimer;
	private double timeToShoot;
	private double counter;
	double distanceToTarget;
	double initialDistance;
	double startWidth;
	double finalWidth;
	double finalDistance;

	public void robotInit()
	{
		driver = new Joystick(2);// the right Joystick
		driver1 = new JoystickButton(driver, 1);
		driver2 = new JoystickButton(driver, 2);
		driver3 = new JoystickButton(driver, 3);
		driver4 = new JoystickButton(driver, 4);
		driver5 = new JoystickButton(driver, 5);
		driver6 = new JoystickButton(driver, 6);
		driver7 = new JoystickButton(driver, 7);
		driver8 = new JoystickButton(driver, 8);
		driver9 = new JoystickButton(driver, 9);
		driver10 = new JoystickButton(driver, 10);
		driver11 = new JoystickButton(driver, 11);
		driver12 = new JoystickButton(driver, 12);
		shooter = new Joystick(1);// the left Joystick
		shooter1 = new JoystickButton(shooter, 1);
		shooter2 = new JoystickButton(shooter, 2);
		shooter3 = new JoystickButton(shooter, 3);
		shooter4 = new JoystickButton(shooter, 4);
		shooter5 = new JoystickButton(shooter, 5);
		shooter6 = new JoystickButton(shooter, 6);
		shooter7 = new JoystickButton(shooter, 7);
		shooter8 = new JoystickButton(shooter, 8);
		shooter9 = new JoystickButton(shooter, 9);
		shooter10 = new JoystickButton(shooter, 10);
		shooter11 = new JoystickButton(shooter, 11);
		shooter12 = new JoystickButton(shooter, 12);
		leftDrive = new Jaguar(1, 1);
		rightDrive = new Jaguar(1, 4);
		rightGrabber = new Jaguar(1, 2);
		leftGrabber = new Jaguar(1, 3);
		grabberSpinner = new Jaguar(1, 5);
		//Start using the second digital sidecar
		shooterBL = new Jaguar(2, 1);
		shooterBR = new Jaguar(2, 2);
		shooterML = new Jaguar(2, 3);
		shooterMR = new Jaguar(2, 4);
		shooterFL = new Jaguar(2, 5);
		shooterFR = new Jaguar(2, 6);
		drive = new RobotDrive(leftDrive, rightDrive);
		driveController = new DriveController(leftDrive, rightDrive);
		aligner = new Aligner();
		setSpeed = 0.0;
		variance = 1;
		varianceToggle = false;
		sensor1 = new UltrasonicDistanceSensor(1, 5);
		autonomousDriveTimer = new Timer();
		autonomousShootTimer = new Timer();
		timeToShoot = 3.0;
		counter = 0.0;
		distanceToTarget = 0.0;
		initialDistance = 3.0;
		startWidth = 0.0;
		finalWidth = 0.0;
		finalDistance = 0.0;
	}

	public void disabledInit()
	{
		System.out.println("The Robot is ready to Rock and Roll!");
		msg.clear();
		msg.println(DriverStationLCD.Line.kUser2, 1, "The Robot Is Ready To");
		msg.println(DriverStationLCD.Line.kUser3, 1, " Rock And Roll!");
		msg.updateLCD();
	}

	public void disabledContinuous()
	{
		//nothing goes here!
	}

	public void disabledPeriodic()
	{
		//nothing goes here!
	}

	public void autonomousPeriodic()
	{
		msg.clear();
		aligner.align(driveController);		//align with the target
		if (counter == 0.0)
		{
			startWidth = aligner.getTargetWidth();
		}
		else
		{
			finalWidth = aligner.getTargetWidth();
			distanceToTarget = (initialDistance / startWidth) * finalWidth;

			if (distanceToTarget > finalDistance)
			{
				drive.arcadeDrive(1.0, 0.0); //drive forward
			}
			else
			{
				drive.arcadeDrive(0.0, 0.0); //and then stop
				autonomousShootTimer.start();
				while (autonomousShootTimer.get() < timeToShoot)
				{
					setAllMotors(.5, 1.0);  //shoot the ball
				}
				setAllMotors(0.0, 0.0);
				autonomousShootTimer.stop();
			}
		}
		counter++;

//		autonomousDriveTimer.start();
//		if (autonomousDriveTimer.get() > 2)
//		{
//			drive.arcadeDrive(0.0, 0.0);
//		}
//		else
//		{
//			drive.arcadeDrive(1.0, 0.0);
//		}
//
//		if (autonomousDriveTimer.get() > 7)
//		{
//			setAllMotors(0.0, 0.0);
//		}
//		else if (autonomousDriveTimer.get() > 2)
//		{
//			setAllMotors(.5, 1.0);
//		}
//
//		if (autonomousDriveTimer.get() > 7)
//		{
//			rightGrabber.set(0.0);
//			leftGrabber.set(0.0);
//			grabberSpinner.set(0.0);
//		}
//		if (autonomousDriveTimer.get() > 3)
//		{
//			rightGrabber.set(-.25);
//			leftGrabber.set(-.25);
//			grabberSpinner.set(1);
//		}

	}

	public void teleopPeriodic()
	{
		if (driver10.IsPressed())
		{
			//aligner.align(driveController);   //TODO: test this
			driveController.incrementRight();
		}
		else
		{
			drive.arcadeDrive(-driver.getY(), -driver.getTwist()); // makin it easer to drive
		}

		if (driver11.IsPressed())
		{
			driveController.incrementLeft();
		}

//driver.getRawAxis(6) forward backward movement of the D-pad (-1 up, 1 down)
//driver.getRawAxis(5) left right movement of the D-pad (-1 left, 1 right)

		//start Marks code
		double grabberSpeedIncrement = 60.0 * 0.3;//Cycle rate in Hertz times speed up time in sec
		double upSpeed = .6;//fightin against gravity
		double downSpeed = -.15;//usin gravity
		double speedLimitUp = 0, speedLimitDown = 0;
		if (driver.getRawAxis(6) == -1 || shooter.getRawAxis(6) == -1)
		{
			grabberArmSpeed += grabberSpeedIncrement;
			speedLimitUp = upSpeed;
			grabberPreviouslyUp = true;
		}
		else if (driver.getRawAxis(6) == 1 || shooter.getRawAxis(6) == 1)
		{
			if (driver.getRawAxis(6) == 1 || shooter.getRawAxis(6) == 1)
			{
				grabberArmSpeed -= grabberSpeedIncrement;
				speedLimitDown = downSpeed;
				grabberPreviouslyUp = false;
			}
			else
			{

				if (grabberPreviouslyUp = true && grabberArmSpeed > 0)
				{
					grabberArmSpeed -= grabberSpeedIncrement;
				}
				else if (grabberPreviouslyUp = false && grabberArmSpeed < 0)
				{
					if (grabberPreviouslyUp = false && grabberArmSpeed < 0)
					{
						grabberArmSpeed += grabberSpeedIncrement;
					}
				}
			}
		}
		// end Marks code
		if (grabberArmSpeed > speedLimitUp)
		{
			grabberArmSpeed = speedLimitUp;
		}
		else if (grabberArmSpeed < speedLimitDown)
		{
			if (grabberArmSpeed < speedLimitDown)
			{
				grabberArmSpeed = speedLimitDown;
			}
		}

		rightGrabber.set(grabberArmSpeed);
		leftGrabber.set(grabberArmSpeed);

		if (driver3.IsPressed() || shooter3.IsPressed())
		{
			grabberSpinner.set(1.0);
		}
		else if (driver5.IsPressed() || shooter5.IsPressed())
		{
			if (driver5.IsPressed() || shooter5.IsPressed())
			{
				grabberSpinner.set(-1.0);
			}
			else
			{
				grabberSpinner.set(0.0);
			}
		}

		if (varianceToggle)// are we changeing the variance
		{
			variance = ((shooter.getThrottle() * -1 / 2) + .5);
			if (variance > 1)
			{
				variance = 1;
			}
			else if (variance < 0)
			{
				if (variance < 0)
				{
					variance = 0;
				}
			}
			printSpeed(variance);
		}
		else
		{
			setSpeed = ((shooter.getThrottle() * -1 / 2) + .5);
			if (setSpeed > 1)
			{
				setSpeed = 1;
			}
			else if (setSpeed < 0)
			{
				if (setSpeed < 0)
				{
					setSpeed = 0;
				}
			}
			printSpeed(setSpeed);
		}

		if (driver4.IsPressed() || shooter4.IsPressed())
		{
			setAllMotors(setSpeed, variance);
		}
		else if (driver6.IsPressed() || shooter6.IsPressed())
		{
			if (driver6.IsPressed() || shooter6.IsPressed())
			{
				setAllMotors(setSpeed * -1, variance);
				printSpeed(setSpeed);
			}
			else
			{
				setAllMotors(0.0, variance);
			}
		}

		if (shooter8.IsPressed())
		{
			varianceToggle = true;
		}
		else
		{
			varianceToggle = false;
		}

		msg.println(DriverStationLCD.Line.kUser4, 1, "Sensor is: " + sensor1.someNumber());
		msg.updateLCD();
	}

	public void setAllMotors(double speed, double variance)
	{
		if (speed <= 0)
		{
			shooterFL.set(-speed);
			shooterFR.set(speed);
			shooterML.set(-speed);
			shooterMR.set(speed);
			shooterBL.set(-speed);
			shooterBR.set(speed);
		}
		else
		{
			shooterFL.set(-speed);
			shooterFR.set(speed);
			shooterML.set(-speed * variance);
			shooterMR.set(speed * variance);
			shooterBL.set(-speed * (variance * variance));
			shooterBR.set(speed * (variance * variance));
		}
	}

	public void printSpeed(double speed)
	{
		int number = (int) (speed * 100);
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
	}
}