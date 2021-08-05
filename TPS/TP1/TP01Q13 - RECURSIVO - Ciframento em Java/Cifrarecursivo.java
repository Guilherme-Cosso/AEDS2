class Cifrarecursivo  {
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
	
	public static String Code(String s)
	{
		return Code(s,"", 0);
	}
        
   	 public static String Code( String  s ,String resp, int  i ) 
   	 {
          
            
        	if(i<s.length())
        	{
            		resp += ( char ) (s . charAt (i) + 3 );
            		i++;
			resp = Code(s, resp ,i); 
		}
        
        	return resp;    
    	}
		
	public static void main(String[] args){
        	
		
		String str=MyIO.readLine();
		String resp;
			
	        while(ehFim(str))
		{          	
			resp=Code(str);
			MyIO.println(resp);
	       		str=MyIO.readLine();		
		}
	}
	

}