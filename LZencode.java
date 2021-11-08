
//Lan Niu 1320386//Name : Lan Niu ID:1320386
//Name Shizhen Wang ID:1240171

import java.io.*;
import java.util.*;
public class LZencode{

    public static void main(String args[])
    {

        try
	    {
      
		ArrayList<Byte> list=new ArrayList<Byte>();
		InputStream is = new BufferedInputStream(System.in);
		int data=is.read();
		
		while(data!=-1)
		    {
			list.add((byte)data);
			data=is.read();
		    }
		is.close();
	      
		byte [] byteArray = new byte[list.size()];
		for(int i=0;i<list.size();i++)
		    {
			byteArray[i]=list.get(i);
        
		    }
		Tries tries=new Tries();
	        
		tries.insert(byteArray);
           
	    }
        catch(Exception e)
	    {
		System.out.print(e);
		System.exit(1);
	    }
    }
    public static int prefixnum=0;
    ///************************Dictionary**********************///
    public static class Tries
    {
        public static int index=0;
        public static class Node
        {
            Node[] children=new Node[256]; // according to ASCCI
            int p;
            byte[] m;
            int count;

            public Node(int p)
            {
                this.p=p;
            }
            public Node(int p, byte[] m)
	    {this.p=p; this.m=m;}
	}

	Node root=new Node(0);


	//////*******method to find an element in children array
	void insert(byte[] b)
	{
	    try{
		Node curr=root;
		byte[] tmp=new byte[b.length];
		Pair pair;
		for(int i =0;i<b.length;i++) {

		    int x = b[i]+128;
		    tmp[i]=b[i];
		    
		    if (curr.children[x] != null) //if it is not empty,then find next
			{
			    if(i+1==b.length)
				{
				    pair = new Pair(curr.p);
				    pair.output2();
				    continue;
				}
			    curr = curr.children[x];
			

			}
		    else//if it is empty ,then add it
			{
			    prefixnum++;
			    curr.children[x] = new Node(prefixnum, tmp);
			    pair = new Pair(curr.p, (byte) (x-128));
			    pair.output1();
			    curr = root;
			    Arrays.fill(tmp,(byte)0);
			}

		}
       


	    }
	    catch(Exception e)
		{
		    System.out.println(e);
		}
        }

    }
    public static class Pair
    {
	int pharse;
	byte mbyte;
	public Pair(int pharse, byte mbyte)
	{
	    this.pharse=pharse;
	    this.mbyte=mbyte;
	}
	public Pair(int pharse)
	{
	    this.pharse=pharse;
	}
	public void output1()
	{
	   
	    System.out.print(pharse);
	    System.out.write(this.mbyte);
	    System.out.write('\n');
	}
	public void output2()
	{
	    System.out.print(pharse);
	    System.out.write('\n');
	}
    }
}
