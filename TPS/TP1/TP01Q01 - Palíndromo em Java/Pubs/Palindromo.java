class Palindromo
{
	/*
	*	@method : Confere se é o Fim
	*	@param : s String a ser analizada.
	*	@return : true or false
	*/
	public static boolean ehFim(String s){
	
		boolean resp = true;
		if(s.length()==3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')
				resp = false;                 
			return resp;
	}
	/*
	*	@method : Verifica se é Palindromo
	*	@param : s String a ser analizada.
	*	@return : true  or false 
	**/
	public static boolean palin(String s)
	{
		boolean resp=true;
		
		if(s.length()==2 )
			if(s.charAt(0) != s.charAt(1))
				resp=false;

		for(int i=0 ;i<s.length()/2 ;i++)
		{
			if(s.charAt(i)!=s.charAt(s.length()-1-i))
			{	
				resp=false;
				return resp;
			}	
		}
		
		return resp;
	}

	public static void main(String[] args){
        	
		boolean resp=false;
		String str=MyIO.readLine();
	         while(ehFim(str))
		{       	
				resp=palin(str);
				if(resp==true)
					MyIO.println("SIM");
				else
					MyIO.println("NAO");
        	   		 str=MyIO.readLine();
		}	
	}
}

