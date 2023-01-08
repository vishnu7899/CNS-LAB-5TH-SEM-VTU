import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPServer {
	public static byte buf[] = new byte[1024];
	public static int cport = 789,sport=790;
	public static void main(String[] args) {
		
		
		try {
			InetAddress host=InetAddress.getByName("127.0.0.1");
			DatagramSocket serversocket = new DatagramSocket(5009);
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
			Scanner s = new Scanner(System.in);
			System.out.println("Server is Running...");
			while(true)
			{System.out.println("Enter msg");
				String str = new String(s.next());
				buf = str.getBytes();
				
			//serversocket.receive(dp);
			//String str = new String(dp.getData(), 0,dp.getLength());
				if(str.equals("STOP"))
				{
				System.out.println("Terminated...");
				serversocket.send(new DatagramPacket(buf,str.length(),host,5008));
				break;
				}
				DatagramPacket sd = new DatagramPacket(buf,buf.length,host,5008);
			serversocket.send(sd);
			}
		}
	catch(Exception e) {}
}
}	


