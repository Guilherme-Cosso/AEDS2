class AlgebraBool{  
    /*
	*	@method : Retira os espaços da String
	*	@param : s String a ser analizada.
	*	@return : String
	*/
    public static String semEspaco(String s)
    {
        String str= "";
        for(int i=0 ; i<s.length() ; i++)
        {
            if(s.charAt(i) != ' ')
                str += s.charAt(i);
        }
        return str; 
    }
    
     /*
	*	@method : Ler o os valores e Trocar A B C por numeros
    *	@param : s String a ser analizada.
    *   @param : t numero de pos.
	*	@return : String cortada
	*/

    public static String Expressao(String s, int tamanho){
        
        int[] vetor = new int[tamanho];        
        for (int i=0; i<tamanho; i++){
            vetor[i] =(int)s.charAt(i+1);

        }
        String str ="";
        tamanho++;
        for (         ;tamanho<s.length(); tamanho++){
            if(s.charAt(tamanho)=='A')
                    str +=(char)vetor[0];
            else if(s.charAt(tamanho)=='B')
                    str +=(char)vetor[1];
            else if(s.charAt(tamanho)=='C')
                    str +=(char) vetor[1];
            else  str +=s.charAt(tamanho);
        }
        return str;    
    }

    public static boolean ehnot(String s){
        boolean resp=false;
        for(int i = 0; i < s.length()-3; i++){
            if(s.charAt(i) == 'n' && s.charAt(i+1) == 'o' && s.charAt(i+2) == 't')
                    resp = true;
        }
        return resp;
    }
    public static boolean ehand(String s){
        boolean resp=false;
        for(int i = 0; i < s.length()-3; i++){
            if(s.charAt(i) == 'a' && s.charAt(i+1) == 'n' && s.charAt(i+2) == 'd')
                    resp = true;
        }
        return resp;
    }
    public static boolean ehor(String s){
        boolean resp=false;
        for(int i = 0; i < s.length()-3; i++){
            if(s.charAt(i) == 'o' && s.charAt(i+1) == 'r')
                    resp = true;
        }
        return resp;
    }

    public static String AlgebraB(String s){
    
        String newstr="";
        while(s.length() > 2){
               
            if (ehnot(s)==true){
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == 'n' && s.charAt(i + 1) == 'o' && (s.charAt(i + 4) == '1' || s.charAt(i + 4) == '0')) {
                            if (s.charAt(i + 4) == '1') {
                                newstr += '0';
                                i += 5;
                            } else if (s.charAt(i + 4) == '0') {
                                newstr += '1';
                                i += 5;
                            }
                        } else newstr += s.charAt(i);
                    }
            } else newstr += s;
                
                s=newstr;
                newstr="";

            if (ehand(s)==true) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == 'a' && s.charAt(i + 1) == 'n' && (s.charAt(i + 4) == '1' || s.charAt(i + 4) == '0') && (s.charAt(i + 6) == '1' || s.charAt(i + 6) == '0')) {
                        if (i < s.length() - 7 && s.charAt(i + 7) == ',') {
                                if (s.charAt(i + 4) == '0') {
                                    newstr += "and(0";
                                    i += 6;
                                } else if (s.charAt(i + 4) == '1') {
                                    i += 6;
                                    newstr += "and(" + s.charAt(i);
                                }
                            } else if (s.charAt(i + 7) == ')') {
                                if (s.charAt(i + 4) == '0') {
                                    newstr += "0";
                                    i += 7;
                                } else if (s.charAt(i + 4) == '1') {
                                    i += 6;
                                    newstr += s.charAt(i); 
                                    i++;
                                }
                            }
                        } else
                            newstr += s.charAt(i);
                    }
                } else {
                    newstr += s;
                }
                
                s=newstr;
                newstr="";
     
                if (ehor(s)==true) {
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == 'o' && s.charAt(i + 1) == 'r' && (s.charAt(i + 3) == '1' || s.charAt(i + 3) == '0') && (s.charAt(i + 5) == '1' || s.charAt(i + 5) == '0')) {
                            if (i < s.length() - 6 && s.charAt(i + 6) == ',') {
                           
                                if (s.charAt(i + 3) == '1') {
                                        newstr += "or(1";
                                        i += 5;
                                    } else if (s.charAt(i + 3) == '0') {
                                        i += 5;
                                        newstr += "or(" + s.charAt(i);
                                    }
                                } else if (s.charAt(i + 6) == ')') {
                                    if (s.charAt(i + 3) == '1') {
                                        newstr += "1";
                                        i += 6;
                                    } else if (s.charAt(i + 3) == '0') {
                                        i +=5 ;
                                        newstr += s.charAt(i);
                                        i++; 
                                    }
                                }
                            } else
                                newstr += s.charAt(i);
                        }
                    } else {
                        newstr += s;
                    }
                    s=newstr;
                    newstr="";
    

        }//Fim do while

        
        return s;

    }
      
        public static void main(String[] args){
    
            String str=MyIO.readLine();
            int tam = (int)str.charAt(0)-48;
                       
            while(tam!=0)
            {
                str=semEspaco(str);
                str=Expressao(str, tam);
                str = AlgebraB(str);
                MyIO.println(str.charAt(0));
                str=MyIO.readLine(); 
                tam = (int)str.charAt(0)-48;             
            }
        }   
}