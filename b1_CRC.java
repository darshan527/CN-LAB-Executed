package crc;

import java.util.*;

public class CRC {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int data_len,gen_len;
		System.out.println("Enter size of data");
		data_len= sc.nextInt();
		System.out.println("Enter data bits");
		int[] data = new int[data_len];
		for(int i=0;i<data_len;i++) {
			data[i]= sc.nextInt();
		}
		/*System.out.println("Enter generator length");*/
		gen_len = 17;
		//System.out.println("Enter 16bit generator bits");
		int[] gen = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};
		/*for(int i=0;i<gen_len;i++) {
			gen[i]= sc.nextInt();
		}*/
		int total_len = data_len+gen_len-1;
		int[] div = new int[total_len];
		int[] rem = new int[total_len];
		int[] crc = new int[total_len];
		//append zeros
		System.arraycopy(data, 0, div, 0, data_len);
		System.arraycopy(div, 0, rem, 0, total_len);
		
		//generate code
		crc = divide(gen,div,rem);
		//append rem to data
		for(int i=0;i<data_len;i++)
			crc[i]=(data[i]^rem[i]);
		
		System.out.println("Generated Code");
		for(int i=0;i<total_len;i++)
			System.out.print(crc[i]);
		
		//crc checker 
		int[]ver =new int[total_len];
		for(int i=0;i<total_len;i++)
			ver[i]=crc[i];
		
		System.out.println("\nDo you Want to induce error.? Y(1)/N(0)");
		int ch = sc.nextInt();
		if(ch==1)
		{
			System.out.println("Enter position");
			int pos = sc.nextInt();
			if(ver[pos]==1)
				ver[pos]=0;
			else
				ver[pos]= 1;
		}
		for(int i=0;i<total_len;i++)
			System.out.print(ver[i]);
		
		
		System.arraycopy(ver, 0, rem, 0, total_len);
		rem = divide(gen,ver,rem);
		for(int i=0;i<=rem.length;i++)
		{
			if(rem[i]!=0) {
				System.out.println("\nError");
				break;
			}
			if(i==rem.length-1) {
				System.out.println("\nNo Error");
				break;
			}
		}
	}
		//divide method
		static int[] divide(int[] ge, int[] data,int[] rm)
		{
			int cur=0;
			while(true){
				for(int i=0;i<ge.length;i++) {
					rm[cur+i]=(rm[cur+i]^ge[i]);
				}
				while(rm[cur]==0&& cur!=rm.length-1)
					cur++;
				if((rm.length-cur)<ge.length)
					break;
			}
			return rm;
		}
	}

