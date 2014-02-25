/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package judge.util.sensors;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class MicroSwitch{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

	private final DriverStationLCD msg = DriverStationLCD.getInstance();
	private AnalogChannel microswitch = new AnalogChannel(1, 1);


    public void robotInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
	 public void disabledInit()
    {
		msg.clear();
        System.out.println("The Robot is ready to Rock and Roll!");
		msg.updateLCD();
    }

    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

		msg.clear();
		msg.println(DriverStationLCD.Line.kUser2, 1, "" + microswitch.getVoltage());

		if(microswitch.getVoltage() < 2)
		{
			msg.println(DriverStationLCD.Line.kUser2, 1, "Stop Raising the Grabber!");


		}

		msg.updateLCD();
	}

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }
}

