class Fut
{
	public static void main (String[] args)
	{
		int	x,
			y;
 
			x = MyIO.readInt();
			y= MyIO.readInt();
		
		
			if(x >= y)
			{
				MyIO.println("Mandante vencedor");
				if(x==y)
				MyIO.println("Empate");
			}
			else
				MyIO.println("Visitante vencedor");
				
		
	}
}