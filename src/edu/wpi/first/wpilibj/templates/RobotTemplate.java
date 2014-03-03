package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * This is the code for the 2014 Crusader Robot "Fido" it uses 2 digital
 * sidecars this year. All of this code is available from
 * github.com/Team3167/CrusaderRobot2014 there is also a wiki page on the github
 * so that people can find the controls.
 *
 * @author Eric Slaweski, Mark Macerato
 */
public class RobotTemplate extends IterativeRobot {

    private DriverStation driverStation = new DriverStation();
    private Driver driveTrain = new Driver();
    private Grabber grabber = new Grabber();
    private Shooter shooter = new Shooter();

    public void robotInit() {

        
    }

    public void disabledInit() {

        driverStation.print("The Robot is Ready to Rock and Roll");
    }

    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {

        driveTrain.drive();  //Drives the robot
        grabber.moveGrabber();  //Rotates the grabber up and down
        grabber.spinGrabber();  //spins the elastic bands on the grabber
        shooter.shoot();  //shoots the ball
    }
}
