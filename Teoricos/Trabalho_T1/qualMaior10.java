class qualMaior10
{
	public static void main (String[] args)
	{
		int	x,
			maior; 
		maior = MyIO.readInt();
		
		for(int i =0 ;i<9;i++)
		{	
			x=MyIO.readInt();
			if(x>maior)
				maior=x;
		}

	
		MyIO.println("Maior" + maior);
	}
}
\sum_{i=1}^{100} i*2