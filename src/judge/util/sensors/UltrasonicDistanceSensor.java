/*
 * This Class allows us to use the "Paralax" "PING)))" ultrasonic distance
 * sensor, altough it should work with any 3 pin ultrasonic sensor(don't take me
 * on that though);
 */
package judge.util.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Eric Slaweski
 */
public class UltrasonicDistanceSensor
{

	private DigitalInput sensorInput;
	private DigitalOutput sensorOutput;
	private Timer timer;
	private Timer timer2;
	private boolean pulsed = false;
	private int lostPulses = 0;
	private int microseconds;

	/**
	 *
	 * @param moduleNumber the digital sidecar's breakout module number
	 * @param channel the digital i/o port
	 */
	public UltrasonicDistanceSensor(int moduleNumber, int channel)
	{
		sensorInput = new DigitalInput(moduleNumber, channel);
		sensorOutput = new DigitalOutput(moduleNumber, channel + 1);
		timer = new Timer();
		timer2 = new Timer();
	}


	public void sensorCalc()
	{
		if(!pulsed)
		{
			timer2.start();
			if(timer2.get() > .000002)
			{
				sensorOutput.pulse(.000005);
				pulsed = true;
				timer2.stop();
				timer2.reset();
				timer.start();
			}
		}

		if(sensorInput.get())
		{
			timer.stop();
			microseconds = (int)(timer.get() * 1000000);
			pulsed = false;
		}

		if(timer.get() > .25)
		{
			timer.stop();
			timer.reset();
			pulsed = false;
			lostPulses++;
		}
	}

	public int distanceInInches()
	{
		sensorCalc();
		return microseconds * 74 / 2;
	}

	public int someNumber()
	{
		return 3;
	}

	public int distanceInCentimeters()
	{
		sensorCalc();
		return microseconds * 29 / 2;
	}
}
