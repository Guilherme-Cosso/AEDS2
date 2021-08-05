class qualMaior
{
	public static void main (String[] args)
	{
		int	x,
			y,
			z,
			aux; 
		
		x=MyIO.readInt();
		y=MyIO.readInt();
		z=MyIO.readInt();

		if(x < y)
		{
			aux = x;
			x = y;
			y = aux; 
		}
		if(x < z)
		{
			aux = x;
			x = z;
			z = aux; 
		}
		if(y < z)
		{
			aux = y;
			y = z;
			z = aux;

		}
		
	
		MyIO.println("Maior" + x);
		MyIO.println("Menor" + z);
	}
}