import java.util.Random;
class AlteracaoRandom
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
	*	@method : Confere se é o Fim
	*	@param : s String a ser analizada.
	*	@param : letra antiga letra
	*	@param : letra new letra
	*	@return : true or false
	*/

	public static String Random(String s, char letra, char letra2)
	{ 
		String resp= "";
		
		for(int i=0 ; i<s.length() ; i++)
		{
			if(s.charAt(i)==letra)
			{
				resp += letra2;
			}
			else
			{
				resp += s.charAt(i);
			}
		}
		return resp;
	
	}

	public static void main (String[] args)
	{
		String resp= "" ;
		String str=MyIO.readLine();
		Random gerador = new Random();
		gerador.setSeed(4);
	
		while(ehFim(str)){

			char letra = ((char)( 'a' + (Math.abs(gerador.nextInt())% 26)));
			char letra2 = ((char)( 'a' + (Math.abs(gerador.nextInt())% 26)));
			resp=Random(str,letra,letra2);
			MyIO.println(resp);
			str=MyIO.readLine();
		}
	}
}

