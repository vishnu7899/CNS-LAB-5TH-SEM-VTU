import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
public class Client
{
public static void main(String args[])throws Exception
{
String address ="";
Scanner sc=new Scanner(System.in);
System.out.println("enter server Address");
address = sc.nextLine();
Socket s=new Socket (address,5000);
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout= new DataOutputStream(s.getOutputStream());
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("send get to start");
String str ="",filename="";
try
{
while(!str.equals("start"))
str=br.readLine();
dout.writeUTF(str);
dout.flush();
filename=din.readUTF();
System.out.println("receving file:"+filename);
filename="download";
System.out.println("saving as file:"+filename);
long sz =Long.parseLong(din.readUTF());
System.out.println("File Size"+(sz/(1024*1024))+"MB");
byte b[]=new byte[1024];
System.out.println("receiving file....");
FileOutputStream fos =new FileOutputStream(new File(filename),true);
long bytesRead;
do
{
bytesRead =din.read(b,0,b.length);
fos.write(b,0,b.length);
}
while(!(bytesRead<1024));
System.out.println("completed");
fos.close();
dout.close();
s.close();
}
catch(EOFException e)
{
}
}
}

