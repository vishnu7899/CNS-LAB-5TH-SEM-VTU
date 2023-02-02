import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server
{
public static void main(String args[]) throws Exception
{
ServerSocket sersock=new ServerSocket(4000);
System.out.println("server ready for connection");
Socket sock=sersock.accept();
System.out.println("waiting for filename");
InputStream istream=sock.getInputStream();
BufferedReader br=new BufferedReader(new InputStreamReader(istream));
String fname=br.readLine();
OutputStream ostream=sock.getOutputStream();
PrintWriter pwrite=new PrintWriter(ostream,true);
String str;
try{
BufferedReader contentRead=new BufferedReader(new FileReader(fname));
while((str=contentRead.readLine())!=null)
{
pwrite.println(str);
}
System.out.println("file contents sent successfully");
}
catch(FileNotFoundException e)
{
pwrite.println("File not found at server");
System.out.println("file not found");
}
}
}
