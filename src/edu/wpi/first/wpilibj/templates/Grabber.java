package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;
import judge.util.JoystickButton;

/**
 * Controls the grabber
 *
 * @author (Mark Macerato)
 */
public class Grabber {

    private Joystick driver = new Joystick(2);
    private Joystick shooter = new Joystick(1);
    private double grabberArmSpeed = 0;
    private double grabberSpeedIncrement = 60.0 * 0.3;  //Cycle rate in Hertz times speed up time in sec
    private double upSpeed = .6;//fightin against gravity
    private double downSpeed = -.15;//usin gravity
    private double speedLimitUp = 0, speedLimitDown = 0;
    private boolean grabberPreviouslyUp = true;
    private Jaguar leftGrabber = new Jaguar(1, 2);
    private Jaguar rightGrabber = new Jaguar(1, 3);
    private AnalogChannel microswitch = new AnalogChannel(1);
    private JoystickButton driver3 = new JoystickButton(driver, 3);
    private JoystickButton driver5 = new JoystickButton(driver, 5);
    private JoystickButton shooter3 = new JoystickButton(shooter, 3);
    private JoystickButton shooter5 = new JoystickButton(shooter, 5);
    private Jaguar grabberSpinner = new Jaguar(1, 5);

    public void moveGrabber() {

        if (driver.getRawAxis(6) == -1 || shooter.getRawAxis(6) == -1) {

            grabberArmSpeed += grabberSpeedIncrement;
            speedLimitUp = upSpeed;
            grabberPreviouslyUp = true;
        } else if (driver.getRawAxis(6) == 1 || shooter.getRawAxis(6) == 1) {

            grabberArmSpeed -= grabberSpeedIncrement;
            speedLimitDown = downSpeed;
            grabberPreviouslyUp = false;
        } else {
            if (grabberPreviouslyUp = true && grabberArmSpeed > 0) {

                grabberArmSpeed -= grabberSpeedIncrement;
            } else if (grabberPreviouslyUp = false && grabberArmSpeed < 0) {
                grabberArmSpeed += grabberSpeedIncrement;
            }
        }
        if (grabberArmSpeed > speedLimitUp) {

            grabberArmSpeed = speedLimitUp;
        } else if (grabberArmSpeed < speedLimitDown) {

            grabberArmSpeed = speedLimitDown;
        }
        if (microswitch.getVoltage() > 2) {

            rightGrabber.set(grabberArmSpeed);
            leftGrabber.set(grabberArmSpeed);
        } else if (microswitch.getVoltage() < 2 && grabberArmSpeed <= 0) {

            rightGrabber.set(grabberArmSpeed);
            leftGrabber.set(grabberArmSpeed);
        } else {

            rightGrabber.set(0);
            leftGrabber.set(0);
        }
    }

    public void spinGrabber() {

        if (driver3.IsPressed() || shooter3.IsPressed()) {
            grabberSpinner.set(1.0);
        } else if (driver5.IsPressed() || shooter5.IsPressed()) {
            grabberSpinner.set(-1.0);
        } else {
            grabberSpinner.set(0.0);
        }
    }
}
