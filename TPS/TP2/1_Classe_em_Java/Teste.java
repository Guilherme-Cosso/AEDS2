public class Teste {

    public static String[] Ler(String Dados){
        Dados = Dados+',';
        Dados = Dados.replace(",,," , ",nao informado,nao informado,");
        Dados = Dados.replace(",," , ",nao informado,");
        
        String[] subDados = Dados.split(",");
      
       
        return subDados;
    }

    public static void main(String[] args) {

        String Dado = "665,Dick Van,208,106,,1940,,";
        String[] string = new String[8];
        string = Ler(Dado);
        for(int i=0;i<8;i++){

            MyIO.println(string[i]);

        }
    }
    
}
