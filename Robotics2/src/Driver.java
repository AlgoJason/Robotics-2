import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.hardware.sensor.SensorMode;

public class Driver {
	
	public static Port port = LocalEV3.get().getPort("S1");
	public static MindsensorsAbsoluteIMU gyro = new MindsensorsAbsoluteIMU(port);
	public static SensorMode gyroprovider = (SensorMode) gyro.getAccelerationMode();
	public static float[] sample = new float[gyro.sampleSize()];
	
	public static final int threshold = 5;

	public static void main(String[] args) {
		
		switchMode();

	}
	
	public static void switchMode() {
		
		while(Button.getButtons() != Button.ID_ESCAPE) {
		gyroprovider.fetchSample(sample, 0);
		
		if(sample[0] < -threshold)
			forward();
		else if (sample[0] > threshold)
			back();
		else if (sample[1] > threshold)
			left();
		else if (sample[1] < -threshold)
			right();
		
		}
		
	}
	
	public static void forward() {
		LCD.clearDisplay();
		LCD.drawString("forward", 0, 0);
	}
	
	public static void back() {
		LCD.clearDisplay();
		LCD.drawString("back", 0, 0);
	}
	
	public static void left() {
		LCD.clearDisplay();
		LCD.drawString("left", 0, 0);
	}
	
	public static void right() {
		LCD.clearDisplay();
		LCD.drawString("right", 0, 0);
	}

}
