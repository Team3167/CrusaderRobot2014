package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import judge.util.JoystickButton;

/**
 * Controls the shooting mechanism
 *
 * @author (Mark Macerato)
 */
public class Shooter {

    private Joystick driver = new Joystick(2);
    private Joystick shooter = new Joystick(1);
    private Jaguar shooterBL = new Jaguar(2, 1);
    private Jaguar shooterBR = new Jaguar(2, 2);
    private Jaguar shooterML = new Jaguar(2, 3);
    private Jaguar shooterMR = new Jaguar(2, 4);
    private Jaguar shooterFL = new Jaguar(2, 5);
    private Jaguar shooterFR = new Jaguar(2, 6);
    private JoystickButton driver4 = new JoystickButton(driver, 4);
    private JoystickButton driver6 = new JoystickButton(driver, 6);
    private JoystickButton shooter4 = new JoystickButton(shooter, 4);
    private JoystickButton shooter6 = new JoystickButton(shooter, 6);
    private double setSpeed = 0.0;
    private DriverStation driverStation = new DriverStation();

    public void setAllMotors(double speed) {
        if (speed <= 0) {
            shooterFL.set(-speed);
            shooterFR.set(speed);
            shooterML.set(-speed);
            shooterMR.set(speed);
            shooterBL.set(-speed);
            shooterBR.set(speed);
        } else {
            shooterFL.set(-speed);
            shooterFR.set(speed);
            shooterML.set(-speed);
            shooterMR.set(speed);
            shooterBL.set(-speed);
            shooterBR.set(speed);
        }
    }

    public void shoot() {

        setSpeed = 0.5 * ((-1 * shooter.getThrottle()) + 1);
        
        if (driver4.IsPressed() || shooter4.IsPressed()) {
            setAllMotors(setSpeed);
        } else if (driver6.IsPressed() || shooter6.IsPressed()) {
            setAllMotors(setSpeed * -1);
        } else {
            setAllMotors(0.0);
        }
        
        driverStation.print("Speed is: " + setSpeed);
    }
}
