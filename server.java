import java.net.*;
import java.io.*;

public class server
{
       public static void main(String[] args) throws Exception
       {
              
              ServerSocket se = new ServerSocket(123);		//create a server socket
              
              Socket q = se.accept();		//let the server wait until the connection is accepted by client
              System.out.println("Connection established successfully");
              
              BufferedReader v = new BufferedReader(new InputStreamReader(q.getInputStream()));		//to receive file name from client
              
              DataOutputStream dr=new DataOutputStream(q.getOutputStream());		//to transfer file contents to client
              
              String g = v.readLine();		//read file name from client
              FileReader f = null;
              BufferedReader ff = null;
              boolean b;
              
              File r=new File(g);		//create file class object with file name
              
              if(r.exists())		//test if file exists or not
                     b=true;
              else
                     b=false;
              
              if(b==true) dr.writeBytes("Yes"+ "\n");		//if file exists, send 'yes' to client else send 'no'
              else  dr.writeBytes("No"+"\n");
              if(b==true)
              {
            	  f=new FileReader(g);		//attach file to fileReader to read data
              
                     ff=new BufferedReader(f);		//attach FileReader to BufferedReader
                     String qq;
              
                     while((qq=ff.readLine())!=null)		//read from BufferedReader and write to DataOutputStream
                     {
                           dr.writeBytes(qq+"\n");
                     }
              }
              dr.close();
              ff.close();
              v.close();
              se.close();
              q.close();
              f.close();
       }
}