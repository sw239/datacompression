
//Lan Niu 1320386//Name : Lan Niu ID:1320386
//Name Shizhen Wang ID:1240171

import java.io.*;
import java.util.*;
public class LZdecode {
    public static ArrayList<byte[]> dictionary = new ArrayList<byte[]>();
    public static void main(String args[]) {
	
	
        try {

            InputStream is = new BufferedInputStream(System.in);
	    ArrayList<Byte> lines=new ArrayList<Byte>();
	    byte mis;
	    ArrayList<Byte> pn=new ArrayList<Byte>();
	
	    int data=is.read();
	    while(data!=-1)
		{
		    lines.add((byte)data);
		    data=is.read();
		}
	    is.close();
	    int tmp=0;
	    for(int i=0;i<lines.size();i++)
		{
		
		    if(lines.get(i+1)==10)//current byte is \n
			{
			    if(i+2==lines.size())
				{
				    pn.add(lines.get(i));
				    add(pn,(byte)10);
				    break;
				    
				}
			    if(lines.get(i+2)==10)//if next byte is \n, it means last byte \n is mismatch
				{
				    pn.add(lines.get(i));
				    mis=(byte)10;	
				    add(pn,mis);
				    pn.clear();
				    mis=(byte)0;
				    i=i+2;		
				}
			    else // if next by next is not \n.it means we have a new line
				{	
				    mis=lines.get(i);
				    add(pn,mis);
				    pn.clear();
				    mis=(byte)0;
				    i=i+1;	
				}
			}
		    else{
			pn.add(lines.get(i));
		    }
		}	
	}
	
	
    
	catch (Exception e) {
	    e.printStackTrace();
	}
    }
    public static void add(ArrayList<Byte> pn,byte mbyte) throws Exception {
	String total="";
	byte [] old =new byte[1];
	byte [] curr;
	for(int i=0; i<pn.size();i++)
	    {
		byte cc=pn.get(i);
		total=total+Character.toString((char)cc);
	    }	
	int pharenumber=Integer.parseInt(total);
	if (pharenumber== 0) {
	    old[0]=mbyte;
	    dictionary.add(old);
	    System.out.write(old); 
	} else {
	    curr=new byte[dictionary.get(pharenumber-1).length+1];
	   
	    for(int n=0;n<dictionary.get(pharenumber-1).length;n++)
		{
		    curr[n]=dictionary.get(pharenumber-1)[n];
			
		}
	    curr[dictionary.get(pharenumber-1).length]=mbyte;	
	    dictionary.add(curr);
	    System.out.write(curr);
	   
	}
    
    }
}
