import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient
{
	public static void main(String[] args) 
	{
	Scanner s = new Scanner(System.in);
	byte buf[] = new byte[1024];
	try{
		DatagramSocket clientsocket = new DatagramSocket(5008);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		Scanner sc = new Scanner(System.in);
		InetAddress host = InetAddress.getLocalHost();
		System.out.println("Client is Running... Type 'STOP' to Quit");
		while(true)
		{
			clientsocket.receive(dp);
			String str = new String(dp.getData(), 0,dp.getLength());
			if(str.equals("STOP"))
			{
			System.out.println("Terminated...");
			break;
			}
			System.out.println("Server: " + str);
		}
		
	   }
	
	catch(Exception e) {
	}
	}
}

