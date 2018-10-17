import java.io.DataInputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.hardware.lcd.LCD;

public class DriverR {
	
	public static final int sSpeed = 500, tSpeed = 100;

	public static void main(String[] args) throws Exception {
		
		//code for communication
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		NXTConnection connection = connector.waitForConnection(0, NXTConnection.RAW);
		DataInputStream input = connection.openDataInputStream();
		
		//byte array that is being communicated
		byte[] data = new byte[1];
		
		// main loop of the program, exits when escape button is pressed
		while(Button.getButtons() != Button.ID_ESCAPE) {
			
			byte temp = data[0];
			input.read(data);
			
			//only run this block if the data in the stream has changed
			if(temp != data[0]) {
				
				//conditional logic from sender that determines behavior
				if(data[0] == 0)		//forward
					forward();
				else if (data[0] == 1)	//backward
					back();
				else if (data[0] == 2)	//left
					left();
				else if (data[0] == 3)	//right
					right();
			
			}
		
		}

	}
	
	public static void forward() {
		stop();
		setSpeed(sSpeed);
		
		Motor.A.forward();
		Motor.D.forward();
	}
	
	public static void back() {
		stop();
		setSpeed(sSpeed);
		
		Motor.A.backward();
		Motor.D.backward();
	}
	
	public static void left() {
		stop();
		setSpeed(tSpeed);
		
		Motor.A.forward();
		Motor.D.backward();
	}
	
	public static void right() {
		stop();
		setSpeed(tSpeed);
		
		Motor.A.backward();
		Motor.D.forward();;
	}
	
	public static void stop() {
		Motor.A.stop(true);
		Motor.D.stop();
	}
	
	public static void setSpeed(int speed) {
		Motor.A.setSpeed(speed);
		Motor.D.setSpeed(speed);
	}
	
	public static void draw(String s){
		LCD.clearDisplay();
	LCD.drawString(s, 0, 1);
	}

}
