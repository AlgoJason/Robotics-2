import java.io.DataOutputStream;
import java.io.DataInputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;

public class Send {
	
	//Connection setup [values assigned in initialize()]


	public static void main(String[] args) throws Exception {
		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		NXTConnection connection = connector.connect("00:16:53:44:66:05", NXTConnection.RAW);
		
		connector.waitForConnection(0, NXTConnection.RAW);
		
		DataOutputStream output = connection.openDataOutputStream();
		DataInputStream input = connection.openDataInputStream();
		
		byte[] nums = new byte[1];
		nums[0] = 15;
		
		output.write(nums);
		output.flush();
		
		//comm(output);
		
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
		
		System.out.println("before output");
		while(Button.getButtons() != Button.ID_ESCAPE);
		//output = connection.openDataOutputStream();
		
		byte[] nums = new byte[1];
		nums[0] = 15;
		
		output.write(nums);
		output.flush();
		
	}
	*/

}
