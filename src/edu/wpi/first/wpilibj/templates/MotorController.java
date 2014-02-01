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
public class MotorController
{
  private Jaguar motor;

  public MotorController()
  {
	motor = new Jaguar(1, 1);
  }

  public MotorController(Jaguar motor)
  {
	  this.motor = motor;
  }

  public void setCommand(double cmd)
  {
     if(cmd > 0.05)
	 {
 		 incrementRight();
		 System.out.println("Move Left");
	 }
	 else if(cmd < -.05)
	 {
 		 incrementLeft();
		 System.out.println("Move Right");
	 }
	 else
	 {
		 stop();
	 }
  }

  private void incrementLeft()
  {
	  motor.set(0.1);
  }

  private void incrementRight()
  {
	  motor.set(-0.1);
  }

  private void stop()
  {
	  motor.set(0.0);
  }
}
