import java.util.*;
import java.math.BigInteger;
import java.lang.*;

class RSAKeyGen
{
public static void main(String[] args)
{

Random rand1=new Random(System.currentTimeMillis()); 
Random rand2=new Random(System.currentTimeMillis()*10);
Scanner sc = new Scanner(System.in);
System.out.println("Enter The public key");
int pubkey=sc.nextInt();

BigInteger p=BigInteger.probablePrime(32, rand1);
BigInteger q=BigInteger.probablePrime(32, rand2);

BigInteger n=p.multiply(q);

BigInteger p_1=p.subtract(new BigInteger("1"));
BigInteger q_1=q.subtract(new BigInteger("1"));

BigInteger phi=p_1.multiply(q_1);

while(true)
{

BigInteger GCD=phi.gcd(new BigInteger(""+pubkey)); 
if(GCD.equals(BigInteger.ONE))

{
break;
}
pubkey++;
}
BigInteger pubkey1=new BigInteger(""+pubkey);
BigInteger prvkey=pubkey1.modInverse(phi);
System.out.println("public key : "+pubkey1+","+n);
System.out.println("private key : "+prvkey+","+n);

System.out.println("Encryption and Decription\n Enter the message to pass");


int asciiVal=sc.nextInt();

BigInteger val=new BigInteger(""+asciiVal);

BigInteger cipher=val.modPow(pubkey1,n); 
System.out.println("Cipher text: " + cipher);

BigInteger plainVal=cipher.modPow(prvkey,n); 
int Val=plainVal.intValue();

System.out.println("Plain text:" + plainVal);
}
}
