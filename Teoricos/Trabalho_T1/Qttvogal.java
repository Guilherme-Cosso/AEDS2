class Qttvogal
{
	public static int Vogal(String s)
	{
		s = s.toUpperCase();
		int resp = 0;
		return Vogal(s, 0,resp );
	}
		
	public static int Vogal(String s,int i,int resp)
	{		
		
		if(i<s.length())
        	{

			if(s.charAt(i) == 'A' || s.charAt(i) == 'E' ||  s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' )
					resp = Vogal(s, i + 1, resp+1);
			
		
		}
		return resp;
			
	}
	public static void main(String[] args)
	{
   	
		String str=MyIO.readLine();
		int vogal;
	         while(!str.contains("FIM"))
		{
				vogal=Vogal(str);
				MyIO.print( vogal);
	       	   		str=MyIO.readLine();		
		}
	}
}