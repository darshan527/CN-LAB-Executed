import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
 
public class rsaAlgorithm
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger z;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 64;
    private Random rand;
 
    public rsaAlgorithm()
    {
        rand = new Random();
        p = BigInteger.probablePrime(bitlength, rand);
        q = BigInteger.probablePrime(bitlength, rand);
        n = p.multiply(q);
        z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, rand);
        while (z.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(z);
        System.out.print("p = "+p+"\nq = "+q+"\nn = "+n+"\nz = "+z+"\ne = "+e+"\nd = "+d+"\n");
    }
 
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException
    {
        rsaAlgorithm rsa = new rsaAlgorithm();
        DataInputStream in = new DataInputStream(System.in);
        
        String teststring;
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        
        System.out.println("String in Bytes: " + bytesToString(teststring.getBytes()));
        
        byte[] encrypted = rsa.encrypt(teststring.getBytes());		//encrypting
        
        System.out.println("encrypted String : ");
        for(int i=0;i<encrypted.length;i++)
        {
        	System.out.print(encrypted[i]+" ");
        }
        
        byte[] decrypted = rsa.decrypt(encrypted);		//decrypting
        
        System.out.println("\nDecrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
        
        in.close();
    }
 
    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
 
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }
 
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }
}