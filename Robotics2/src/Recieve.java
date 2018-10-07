import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import lejos.hardware.Bluetooth;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.utility.Delay;

public class Recieve {
	
	//connection setup
	public static NXTCommConnector connector;
	public static NXTConnection connection;
	
	//input/output streams
	public static DataInputStream input;
	public static DataOutputStream output;

	public static void main(String[] args) throws Exception {
		
		initialize();
		
		byte[] numsRead = new byte[1];
		
		System.out.println(numsRead[0]);

	}
	
	//demo code from class
	public static void demo() throws Exception {
		
		byte[] n = new byte[8];
		
		while (true) {
			try {
				if (input.read(n) == -1)
					break;
			} catch (EOFException e) {
				break;
			}
			
			System.out.println("Read " + n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7]);
			output.write(n);
			output.flush();
			
		}
		
		Delay.msDelay(1000);
		input.close();
		output.close();
		connection.close();
		
	}
	
	//assigns values for global variables (connections and input/output)
	public static void initialize() {
		connector = Bluetooth.getNXTCommConnector();
		System.out.println("Waiting for connection");
		connection = connector.waitForConnection(0, NXTConnection.RAW);
		input = connection.openDataInputStream();
		output = connection.openDataOutputStream();
	}

}
