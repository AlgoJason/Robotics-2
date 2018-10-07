import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;

public class Send {
	
	//Connection setup [values assigned in initialize()]
	public static NXTCommConnector connector;
	public static NXTConnection connection;
	
	//input/output streams [values assigned in initialize()]
	public static DataInputStream input;
	public static DataOutputStream output;

	public static void main(String[] args) throws Exception {
		
		initialize();
		
		byte[] nums = new byte[1];
		
		nums[0] = 5;

		output.write(nums);
		
		output.close();
		input.close();
		connection.close();
	}
	
	//demo code from class
	public static void demo() throws Exception {
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
			input.read(r);

			System.out.println("Read: " + r[0] + r[1] + r[2] + r[3] + r[4] + r[5] + r[6] + r[7]);
		}
		System.out.println("All data sent");
	}
	
	//assigns values for global variables (connections and input/output)
	public static void initialize() {
		connector = Bluetooth.getNXTCommConnector();
		connection = connector.connect("00:16:53:44:66:05", NXTConnection.RAW);
		input = connection.openDataInputStream();
		output = connection.openDataOutputStream();
		
	}

}
