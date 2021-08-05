class Dicionario {
    public static void main(String[] args) {
        int i = MyIO.readInt();
        String str = "";
        for(int j = 0; j < i; j++){
            str = MyIO.readLine();
            str = tratar(str);
            MyIO.println(str);
            str = "";
        }
    }

    public static String tratar(String str){;
        String nova= "";
        int div[] = new int[2] ;
        div[0] = 0; 
        div[1] = 0;

        for(int i =0; i < str.length();i++){
            if(str.charAt(i) == '+' ||str.charAt(i) == '-' ||str.charAt(i) == '*' ||str.charAt(i) == '^'){
                nova += str.charAt(i+1);
                nova += str.charAt(i);
                if(str.length() >= i+2)
                    i++;
            }
            else{
                nova += str.charAt(i);
            }
        }
        str = nova;
        nova = "";
        for(int i =0; i < str.length();i++){
            if(str.charAt(i) == '('){
                for(int j = i ; str.charAt(j) != ')' ;j++){
                    if(str.charAt(j) == '/'){
                        div[0] = j;
                    }
                }
                div[1] = i;
                i = str.length();
            }
        }

        int d=0;
        if(div[0] != 0){
            for(int i = 0; i < div[0]; i++)
                nova += str.charAt(i);
            nova += ")";
            for(int i = div[0] + 1; i < str.length() ; i++){
                if(str.charAt(i) == ')' && d != 1){
                    d++;
                    nova+= "/";
                }
                else{
                    nova += str.charAt(i);
                }
            }
        }
        else{
            nova = str; 
        } 
        str = nova;
        nova = "";
        int contar = 0; 
        for(int i = 0; i < str.length() ; i++){
            if(str.charAt(i)== ')' ||str.charAt(i)== '('){
                
            }
            else{
                nova += str.charAt(i);
            }

        }
        str = nova;
        nova = "";
        
        for(int i = 0; i < str.length() ; i++){
            if(str.charAt(i)== '/' ){
                contar++;
            }
        }

        if(d == contar){
            nova = str;
        }else{
            for(int i = 0; i < str.length() ; i++){
                if(str.charAt(i)== '/' && d != contar){
                    contar--;
                }
                else  nova += str.charAt(i);
            }
            nova +="/";
            
        }
        return nova;
    }
}