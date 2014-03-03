package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * DriverStation handles all printing to the Driver Station LCD
 *
 * @author (Mark Macerato)
 */
public class DriverStation {

    private DriverStationLCD msg = DriverStationLCD.getInstance();  //The WPI Driver Station LCD

    public void print(String message) {  //Print a given message to the screen

        msg.free();
        msg.println(DriverStationLCD.Line.kUser1, 1, message);
        msg.updateLCD();
    }
}
