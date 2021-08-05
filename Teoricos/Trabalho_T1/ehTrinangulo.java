class ehTrinangulo
{
	public static void main (String[] args)
	{
		double  ladox,
			ladoy,
			ladoz; 
		
		ladox=MyIO.readDouble();
		ladoy=MyIO.readDouble();
		ladoz=MyIO.readDouble();

		if(ladox==ladoy && ladox ==ladoz)
		{
			MyIO.println("Eh Equilatero");
		}
		else
		{
			if(ladox==ladoy || ladox ==ladoz || ladoy ==ladoz)
			{
				MyIO.println("Eh Isoceles");
			}
			else
				MyIO.println("Eh Escaleno");
		}
	}
}