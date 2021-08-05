class Is {
	
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
	*	@method : Confere se a frase so possui vogais
	*	@param : s String a ser analizada.
	*	@return : true or false
	*/
	public static boolean Vogal(String s)
	{
		boolean resp = false;
		int aux=0;
		s = s.toUpperCase();
		for(int i=0; i<s.length(); i++)
			if(s.charAt(i) == 'A' || s.charAt(i) == 'E' ||  s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' )
				aux++;	
		if(s.length()==aux)
			resp=true;
	
		return resp;
	}
	/*
	*	@method : Confere se a frase so possui consoante
	*	@param : s String a ser analizada.
	*	@return : true or false
	*/
	public static boolean Consoante(String s)
	{
		boolean resp = false;
		int aux=0;
		s = s.toUpperCase();
		for(int i=0; i<s.length(); i++)
			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
				if(s.charAt(i) != 'A' && s.charAt(i) != 'E' &&  s.charAt(i) != 'I' && s.charAt(i) != 'O' && s.charAt(i) != 'U' )
					aux++;
		if(s.length()==aux)
			resp=true;
	
		return resp;
	}
	/*
	*	@method : Confere se a frase so possui inteiros
	*	@param : s String a ser analizada.
	*	@return : true or false
	*/
	public static boolean Inteiro(String s)
	{
		boolean resp = false;
		int aux=0;
		for(int i=0; i<s.length(); i++)
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
				aux++;
			
		if(s.length()==aux)
			resp=true;
	
		return resp;
	}
	/*
	*	@method : Confere se a frase so possui reais
	*	@param : s String a ser analizada.
	*	@return : true or false
	*/
	public static boolean Real(String s)
	{
		boolean resp = false;
		int aux=0,
		    aux2=0;
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
				aux++;

			if(s.charAt(i) == ';' || s.charAt(i) == '.' || s.charAt(i) == ','){
				aux++;
				aux2++;
			}
		}
		if(s.length()==aux && aux2<=1)
			resp=true;
	
		return resp;
	}
	/*
	*	@method : Printa o resultado
	*	@param : v boolean true 
	*	@param : c boolean true
	*	@param : i boolean true 
	*	@param : r boolean true
	*/
	public static void print(boolean v,boolean c,boolean i,boolean r)
	{
		if(v==true)	MyIO.print("SIM ");
		else MyIO.print("NAO ");
		if(c==true) MyIO.print("SIM ");
		else MyIO.print("NAO ");
		if(i==true) MyIO.print("SIM ");
		else MyIO.print("NAO ");	
		if(r==true) MyIO.println("SIM ");
		else MyIO.println("NAO ");		
	}			 
	public static void main(String[] args)
	{
   	
		String str=MyIO.readLine();
		boolean vogal, 
			consoante,
			inteiro,
			real;
			
	        while(ehFim(str)){
				vogal=Vogal(str);
				consoante=Consoante(str);
				inteiro=Inteiro(str);
				real=Real(str);
				print( vogal, consoante, inteiro, real);
	       	   		str=MyIO.readLine();		
		}
	}
}
