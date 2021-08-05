class Cifra
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
	*	@method : Muda a posiçao do char 
	*	@param : s 'String' a ser analizada.
	*	@return: New string.
	*/


	public static String Code(String s)
	{
		String resp = " " ;	
		char mudar;	
		for(int i=0 ;i<s.length() ;i++)
		{
			mudar = ( char ) (s . charAt (i) + 3 );
			resp += mudar;		
		}
	
		return resp;
	}
	
	public static void main(String[] args){
        	
		
		String str=MyIO.readLine();
		String resp;
			
	         while(ehFim(str)){
			
	           	
				resp=Code(str);
				MyIO.println(resp);
        	   		str=MyIO.readLine();
        		
		
		}
	}
	

}

