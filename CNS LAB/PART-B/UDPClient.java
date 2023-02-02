import java.io.*;
import java.net.*;
class UDPC 
{
public static void main(String args[]) throws Exception
{
DatagramSocket clientsocket = new DatagramSocket();
BufferedReader clientread = new BufferedReader(new InputStreamReader(System.in));
InetAddress ip=InetAddress.getByName("10.0.6.88");
byte[] sendbuffer = new byte[1024];
byte[] receivebuffer = new byte[1024];
System.out.println("Client\n");
String clientdata = clientread.readLine();
sendbuffer = clientdata.getBytes();
DatagramPacket sendpacket= new DatagramPacket(sendbuffer,sendbuffer.length,ip,9876);
clientsocket.send(sendpacket);
DatagramPacket receivepacket = new DatagramPacket(receivebuffer,receivebuffer.length);
clientsocket.receive(receivepacket);
String serverdata = new String(receivepacket.getData());
System.out.println("\nServer msg"+serverdata);
clientsocket.close();
}
}
 
