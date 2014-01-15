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
import judge.util.JoystickButton;

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
    private JoystickButton button8;
    private JoystickButton button9;
    private JoystickButton button10;
    private JoystickButton button11;
    private JoystickButton button12;
    // Driver Station Message thing
    private final DriverStationLCD msg = DriverStationLCD.getInstance();
    // variables
    private double setSpeed;
    private double variance;
    private boolean varianceToggle;
    private boolean reset;
    //private Compressor compressor;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        frontL = new Jaguar(1, 1);
        backL = new Jaguar(1, 2);
        frontR = new Jaguar(1, 3);
        backR = new Jaguar(1, 4);
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
        setSpeed = 0.0;
        variance = 1;
        varianceToggle = false;
        reset = false;
        //compressor = new Compressor(1,1);
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

        if (button5.IsPressed())
        {
            setAllMotors(setSpeed, variance);
        }
        else if (button3.IsPressed())
        {
            setAllMotors(setSpeed * -1, variance);
            printSpeed(setSpeed);
        }
        else
        {
            setAllMotors(0.0, variance);
        }

        if (button4.HasJustBeenPressed())
        {
            varianceToggle = !varianceToggle;
        }
    }

    public void setAllMotors(double speed, double variance)
    {
        if (speed <= 0)
        {
            frontL.set(-speed);
            frontR.set(speed);
            backL.set(-speed);
            backR.set(speed);
        }
        else
        {
            frontL.set(-speed);
            frontR.set(speed);
            backL.set(-speed * variance);
            backR.set(speed * variance);
        }
    }

    public void printSpeed(double speed)
    {
        msg.clear();
        int number;
        number = (int) speed * 100;  // make sure this works right
        if (varianceToggle)
        {
            msg.println(DriverStationLCD.Line.kUser2, 1, "varaince is: "
                + number);
        }
        else
        {
            msg.println(DriverStationLCD.Line.kUser2, 1, "speed is: "
                + number);
        }
        msg.updateLCD();
    }
}
