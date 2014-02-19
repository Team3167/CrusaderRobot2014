/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author Ryan Young
 */
public class DriveController
{
  private RobotDrive driver;
  public static final double LEFT = -0.1;
  public static final double RIGHT = 0.1;

  public DriveController(RobotDrive driver)
  {
	  this.driver = driver;
  }

  public void setCommand(double cmd)
  {
     if(cmd > 0.05)
	 {
 		 incrementRight();
		 System.out.println("Move Right");
	 }
	 else if(cmd < -.05)
	 {
 		 incrementLeft();
		 System.out.println("Move Left");
	 }
	 else
	 {
		 stop();
	 }
  }

  private void incrementLeft()
  {
	  driver.arcadeDrive(0.0, LEFT);
  }

  private void incrementRight()
  {
	  driver.arcadeDrive(0.0, RIGHT);
  }

  private void stop()
  {
	  driver.arcadeDrive(0.0, 0.0);
  }
}
