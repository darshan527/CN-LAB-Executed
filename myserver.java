import java.io.*;  
import java.net.*;

public class myserver
{  
	public static void main(String[] args)
	{  
		try
		{  
			ServerSocket ss = new ServerSocket(6666);  
			Socket s = ss.accept();		//establishes connection 
			
			DataInputStream d = new DataInputStream(s.getInputStream());  
			String  str = (String)d.readUTF();  
			System.out.println("message= "+str);  
			ss.close();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
	}  
}  