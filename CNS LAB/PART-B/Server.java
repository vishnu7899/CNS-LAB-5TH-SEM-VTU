import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Server {
public static void main(String args[])throws Exception 
{
String filename;
System.out.println(" enter the file name:");
Scanner sc=new Scanner(System.in);
filename=sc.nextLine();
sc.close();
while(true)
{
ServerSocket ss=new ServerSocket(5000);
System.out.println("waiting for request");
Socket s=ss.accept();
System.out.println("Connected with"+s.getInetAddress().toString());
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
try
{
String str="";
str=din.readUTF();
System.out.println("sendGet...ok");
if(!str.equals("stop"))
System.out.println("Sending file:"+filename);
dout.writeUTF(filename);
dout.flush();
File f=new File(filename);
FileInputStream fin=new FileInputStream(f);
long sz=(int)f.length();
byte b[]=new byte[1024];
int read;
dout.writeUTF(Long.toString(sz));
dout.flush();
System.out.println("Size:"+sz);
System.out.println("buff size:"+ss.getReceiveBufferSize());
while((read=fin.read(b))!=-1)
{
dout.write(b,0,read);
dout.flush();
}
fin.close();
System.out.println("..ok");
dout.flush();
dout.writeUTF("stop");
System.out.println("send compelete");
dout.flush();
}
catch(Exception e)
{
e.printStackTrace();
System.out.println("an error occured");
}
din.close();
s.close();
ss.close();
  }
   }
}
