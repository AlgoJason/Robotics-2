import java.io.DataOutputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.hardware.sensor.SensorMode;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;

public class Driver {
	

	
	public static final int threshold = 5;

	public static void main(String[] args) throws Exception {
		
		Port port = LocalEV3.get().getPort("S1");
		MindsensorsAbsoluteIMU gyro = new MindsensorsAbsoluteIMU(port);
		SensorMode gyroprovider = (SensorMode) gyro.getAccelerationMode();
		float[] sample = new float[gyro.sampleSize()];
		
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		NXTConnection connection = connector.connect("00:16:53:44:66:05", NXTConnection.RAW);
		DataOutputStream output = connection.openDataOutputStream();
		
		byte[] data = new byte[1];
		
		while(Button.getButtons() != Button.ID_ESCAPE) {
		gyroprovider.fetchSample(sample, 0);
		
		if(sample[0] < -threshold)
			data[0] = 0;
		else if (sample[0] > threshold)
			data[0] = 1;
		else if (sample[1] > threshold)
			data[0] = 2;
		else if (sample[1] < -threshold)
			data[0] = 3;
		
		output.write(data);
		
		}
		
		gyro.close();

	}

}
