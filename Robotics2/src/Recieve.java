import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import lejos.hardware.Bluetooth;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Recieve {
	// Global Var
	public static NXTCommConnector connector = Bluetooth.getNXTCommConnector();
	public static NXTConnection connect = connector.waitForConnection(0, NXTConnection.RAW);
	public static DataInputStream input = connect.openDataInputStream();
	public static DataOutputStream output = connect.openDataOutputStream();
	public static byte[] n = new byte[1];
	public static RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	public static RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);

	//Global var speed
	public static int speed = 650;


	public static void forward() {
		if (n[0] == 0) {

		}
	}//end forward



	public static void start() throws Exception {
		System.out.println("Drive\n");
	    System.out.println("Press any key to start");

	    Button.LEDPattern(4);    // flash green led and
	    Sound.beepSequenceUp();  // make sound when ready.
	    Button.waitForAnyPress();


		NXTCommConnector connector = Bluetooth.getNXTCommConnector();
		System.out.println("Waiting for connection ...");
		NXTConnection connect = connector.waitForConnection(0, NXTConnection.RAW);
		System.out.println("Connected");
		DataInputStream input = connect.openDataInputStream();
		DataOutputStream output = connect.openDataOutputStream();

		while (true) {
			try {
				if (input.read(n) == -1)
					break;
			} catch (EOFException e) {
				break;
			}
			System.out.println("Read " + n[0]);
		} // end while
	}// end start

	public static void endprogram() throws Exception {
		Delay.msDelay(1000);
		input.close();
		output.close();
		connect.close();
	}// end endprgram

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		while(Button.getButtons() != Button.ID_ESCAPE) {
		start();
		endprogram();

		}//end while
	}// end main

}// end class
