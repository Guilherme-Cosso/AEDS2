class Palindromorecursivo
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
	*	@method : chama o metodo palin
	*	@param : s String a ser analizada.
	*	@return : a funçao palin
	*/
	public static boolean palin(String s)
	{
		return palin(s, 0);
	}
	
	/*
	*	@method : chama o metodo palin
	*	@param : s String a ser analizada.
	*	@param : i contador
	*	@return : true se a funçao for palindromo e false se nao for
	*/
	public static boolean palin(String s, int i)
	{	
		boolean resp = true;
		int indiceEspelhado = s.length() - 1 - i;

		if (i < indiceEspelhado)
		{
			if (s.charAt(i) == s.charAt(indiceEspelhado))
			{
				resp = palin(s, i + 1);
			}
		
			else
			{
				resp = false;
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

