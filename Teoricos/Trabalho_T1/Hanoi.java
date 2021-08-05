class Hanoi
{
	public static int hanoi(int num)
	{
		return hanoi(num, 7);		
			
	}
		
	public static int hanoi(int num , int start)
	{
		if(num!=1)
			start = hanoi(num-1,start*2+1);
		
		return start;
	}
		
		
	public static void main(String[] args)
	{
        	
		int nundisc=0;
		nundisc=MyIO.readInt();
		hanoi(nundisc);
		MyIO.println(nundisc);		
	}
	

}

