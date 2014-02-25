package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.camera.AxisCamera;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Ryan Young and John Breslin
 */
public class Aligner
{
  /*
   * When completed, will move the servo to allign its self with the vision
   * targets then will move the robot so that it is alligned.
   *
   *
   */

	private double pi = Math.PI;
	private double freq = .250;
	private double omega = ((2.0 * pi)) * freq;
	private double time = 0.0;
	double targetWidth = 0.0;

	private Filter cmdFilter = new Filter(1.5, 1.0, 50.0);


  public void align(DriveController drive)
  {

      BarTracker tracker = new BarTracker();
      try
      {
        VisionTarget[] targets = tracker.getTarget();
        double width = AxisCamera.getInstance().getResolution().width;
        double targetPosition = width / 2.0;
        double actualPosition = 0.0;// Put position measuring function here
        double kp = 1.0;
        double servoCommand;

        if (targets == null || targets[0] == null)
        {
                // Make camera oscillate at 0.5 Hz
                servoCommand = 0.0;//oscillate();
                System.out.println("Found 0");
        }
        else if (targets.length == 1)
        {
                // Center the camera on the target
                System.out.println(targets[0].getRawXPosition());
                actualPosition = targets[0].getRawXPosition();
                System.out.println("Found 1");
                servoCommand = ((actualPosition - targetPosition) / width) * kp;

        }
        else  // Found both targets
        {
                // Center the camera between the two
                actualPosition = xMidpoint(targets[0], targets[1]);
                System.out.println("Found 2");
                servoCommand = ((actualPosition - targetPosition) / width) * kp;

        }
        System.out.println(servoCommand);
		drive.setCommand(cmdFilter.Apply(servoCommand));
      }
      catch (Exception e)
      {
		e.printStackTrace();
      }




    //}

  }

  public double getTargetWidth()
	{
	  BarTracker tracker = new BarTracker();
      try
      {
        VisionTarget[] targets = tracker.getTarget();
		targetWidth = findWidth(targets[0], targets[1]);
		System.out.println("Target width= " + targetWidth);
	  }

      catch (Exception e)
      {
		e.printStackTrace();
		System.out.println("Shit Broke!");
 		targetWidth = getTargetWidth();
      }

	  return targetWidth;
	}

  private double xMidpoint(VisionTarget t1, VisionTarget t2)
  {
    double x1 = t1.getRawXPosition();
    double x2 = t2.getRawXPosition();
    double midpoint = (x1 + x2)/2;
    return midpoint;
  }

  private double findWidth(VisionTarget t1, VisionTarget t2)
  {
	 double x1 = t1.getRawXPosition();
     double x2 = t2.getRawXPosition();
	 System.out.println("x1= " + x1);
	 System.out.println("x2= " + x2);
	 double width = x2 - x1;
	 if(width < 0)
	 {
		width = width * -1;
	 }
	 System.out.println("Width= " + width);
	 return width;
  }

  private void alignOne(VisionTarget target)
  {

  }

  public double oscillate()
	{
		double x = omega * time;
		double sinOfX = Math.sin(x);
		double move = .5 * (sinOfX);
		time += 0.02;
		return move;

	}
}
