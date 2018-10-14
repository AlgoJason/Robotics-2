import java.io.DataOutputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.hardware.sensor.SensorMode;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;

public class Driver {
	

	
	public static final int threshold = 5;

	public static void main(String[] args) throws Exception {
		
		//code for the 3in1 sensor
		Port port = LocalEV3.get().getPort("S1");
		MindsensorsAbsoluteIMU gyro = new MindsensorsAbsoluteIMU(port);
		SensorMode gyroprovider = (SensorMode) gyro.getAccelerationMode();
		float[] sample = new float[gyro.sampleSize()];
		
		//code for communication
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		NXTConnection connection = connector.connect("00:16:53:44:66:05", NXTConnection.RAW);
		DataOutputStream output = connection.openDataOutputStream();
		
		//byte array that is being communicated
		byte[] data = new byte[1];
		
		// main loop of the program, exits when escape button is pressed
		while(Button.getButtons() != Button.ID_ESCAPE) {
			
		//fetches data from the sensor and stores it in sample
		gyroprovider.fetchSample(sample, 0);
		
		//conditional logic for which value should be passed to the data stream
		if(sample[0] < -threshold)		//forward
			data[0] = 0;
		else if (sample[0] > threshold)	//backward
			data[0] = 1;
		else if (sample[1] > threshold)	//left
			data[0] = 2;
		else if (sample[1] < -threshold)//right
			data[0] = 3;
		
		//writes the data variable to the stream
		output.write(data);
		
		}
		
		gyro.close();

	}

}
