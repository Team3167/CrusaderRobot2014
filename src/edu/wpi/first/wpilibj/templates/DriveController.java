/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author Ryan Young
 */
public class DriveController
{
  private Jaguar lMotor;
  private Jaguar rMotor;
  public static final double LEFT = -0.1;
  public static final double RIGHT = 0.1;

  public DriveController(Jaguar left, Jaguar right)
  {
	  this.lMotor = left;
	  this.rMotor = right;
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

  public void incrementLeft()
  {
	  lMotor.set(LEFT);
	  rMotor.set(-LEFT);
  }

  public void incrementRight()
  {
	  lMotor.set(RIGHT);
	  rMotor.set(-RIGHT);
  }

  private void stop()
  {
	  lMotor.set(0.0);
	  rMotor.set(0.0);
  }
}
