import java.net.*;
import java.io.*;

public class client
{
       public static void main(String[] args) throws Exception
       {
              Socket s = new Socket("localhost",123);		//create client Socket
              
              BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		//accept file name from keyboard
              System.out.print("Enter file name = ");
              String filename = br.readLine();
              
              DataOutputStream d = new DataOutputStream(s.getOutputStream());		//send file name to server using DataOutputStream
              d.writeBytes(filename +"\n");
              
              BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));		//to read data sent from server
              String string;
              
              string=i.readLine();		//read first line from server into st
              
              if(string.equals("Yes"))		//if file is found on server side, then send "Yes" else "No"
              {
            	  while((string = i.readLine()) != null)		//read and display the file contents coming from Server
            		  System.out.println(string);
              }
              else
            	  System.out.println("File not found");
              
              i.close();
        	  d.close();
        	  br.close();
        	  s.close();
       }
}