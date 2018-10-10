import java.io.DataOutputStream;
import java.io.DataInputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;

public class Send {
	//port declared
	public static Port mindSensor = LocalEV3.get().getPort("S1");
	//sensor
	public static MindsensorsAbsoluteIMU info = new MindsensorsAbsoluteIMU(mindSensor);
	//Connection setup [values assigned in initialize()]

	public static SensorValue(){
		SensorMode distancefinder = (SensorMode) info.getAngleMode();
		float[] test = new float[info.sampleSize()];
		LCD.drawString("Distance = " + test[0], 0, 0);
		Delay.msDelay(100);
	}


	public static void main(String[] args) throws Exception {
		SensorValue();
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		NXTConnection connection = connector.connect("00:16:53:44:66:05", NXTConnection.RAW);
<<<<<<< HEAD

		connector.waitForConnection(0, NXTConnection.RAW);

		DataOutputStream output = connection.openDataOutputStream();
		DataInputStream input = connection.openDataInputStream();

		byte[] nums = new byte[1];
		nums[0] = 15;

		output.write(nums);
		output.flush();

		//comm(output);

=======
		
		connector.waitForConnection(0, NXTConnection.RAW);
		
		connector.waitForConnection(0, NXTConnection.RAW);
		
		DataOutputStream output = connection.openDataOutputStream();
		DataInputStream input = connection.openDataInputStream();
		
		byte[] nums = new byte[1];
		nums[0] = 15;
<<<<<<< HEAD
		
		output.write(nums);
		output.flush();
=======
		
		output.write(nums);
		output.flush();
		
		//comm(output);
		
		Button.waitForAnyPress();
>>>>>>> parent of 430e996... put stuff back into comm()
		
		//comm(output);
		
>>>>>>> parent of 430e996... put stuff back into comm()
		Button.waitForAnyPress();

		output.close();
		connection.close();
	}

	//demo code from class
	/*
	public static void demo(DataOutputStream output) throws Exception {
		System.out.println("Sending data");

		int cnt = 0;
		byte[] o = new byte[8];
		byte[] r = new byte[8];

		while (Button.getButtons() != Button.ID_ESCAPE) {
			Button.waitForAnyPress();
			for (int i = 0; i < 8; i++)
				o[i] = (byte) cnt++;
			output.write(o);
			output.flush();

			System.out.println("Read: " + r[0] + r[1] + r[2] + r[3] + r[4] + r[5] + r[6] + r[7]);
		}
		System.out.println("All data sent");
	}

	//function to test output passing

	public static void comm(DataOutputStream output) throws Exception {
<<<<<<< HEAD

		System.out.println("before output");
		while(Button.getButtons() != Button.ID_ESCAPE);
		//output = connection.openDataOutputStream();

		byte[] nums = new byte[1];
		nums[0] = 15;

=======
		
		System.out.println("before output");
		while(Button.getButtons() != Button.ID_ESCAPE);
		//output = connection.openDataOutputStream();
		
		System.out.println("before output");
		while(Button.getButtons() != Button.ID_ESCAPE);
		//output = connection.openDataOutputStream();
		
		byte[] nums = new byte[1];
		nums[0] = 15;
		
>>>>>>> parent of 430e996... put stuff back into comm()
		output.write(nums);
		output.flush();

	}
	*/

}
