class Isrecursiva 
{
	public static boolean Vogal(String s)
	{
		s = s.toUpperCase();
		return Vogal(s, 0 );
	}
		
	public static boolean Vogal(String s,int i)
	{		
		boolean resp = true;
		if(i<s.length())
        	{

			if(s.charAt(i) == 'A' || s.charAt(i) == 'E' ||  s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' )
					resp = Vogal(s, i + 1);
			
			else
					resp = false;
		}
		return resp;
			
	}

	public static boolean Consoante(String s)
	{
		s = s.toUpperCase();
		return Consoante(s, 0 );
	}

	public static boolean Consoante(String s,int i)
	{
		boolean resp =  true;
		if(i<s.length())
        	{
			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
			{
				if(s.charAt(i) != 'A' && s.charAt(i) != 'E' &&  s.charAt(i) != 'I' && s.charAt(i) != 'O' && s.charAt(i) != 'U' )
					resp = Consoante(s, i + 1);
				else
					resp = false;		
			}
				else
					resp = false;
		}
		return resp;
	}

	public static boolean Inteiro(String s)
	{		
		return Inteiro(s, 0 );
	}
	public static boolean Inteiro(String s, int i)
	{		
		boolean resp = true;
		if(i<s.length())
        	{
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
				resp = Inteiro(s, i + 1);
			else
				resp = false;
		}		
	
		return resp;
	}

	public static boolean Real(String s)
	{		
		return Real(s, 0 , 0);
	}
	public static boolean Real(String s, int i, int aux)
	{		
		boolean resp = true;
		if(i<s.length())
        	{
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9')	
				resp = Real(s, i + 1, aux);
				
			else
			{
				if(s.charAt(i) == ';' || s.charAt(i) == '.' || s.charAt(i) == ',')
				{
					aux++;
					resp = Real(s, i + 1, aux);
				}
				else
				{
					resp = false;
				}
			}
			if(aux>1)
				resp = false;
		}		
		
		return resp;
	}

	public static void print(boolean v,boolean c,boolean i,boolean r)
	{
		if(v==true)
			MyIO.print("SIM ");
				
		else
			MyIO.print("NAO ");
		
		if(c==true)
			MyIO.print("SIM ");
				
		else
			MyIO.print("NAO ");
		
		if(i==true)
			MyIO.print("SIM ");
			
		else
			MyIO.print("NAO ");

		if(r==true)
			MyIO.println("SIM ");
	
		else
			MyIO.println("NAO ");		
	}
			 
	public static void main(String[] args)
	{
   	
		String str=MyIO.readLine();
		boolean vogal, 
			consoante,
			inteiro,
			real;
			
	         while(!str.contains("FIM"))
		{
				vogal=Vogal(str);
				consoante=Consoante(str);
				inteiro=Inteiro(str);
				real=Real(str);
				print( vogal, consoante, inteiro, real);
	       	   		str=MyIO.readLine();		
		}
	}
}
