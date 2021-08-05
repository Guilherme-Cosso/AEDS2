class Principal {
    final static int TamArq = 3922;

    public static void main(String[] args) throws Exception {
        Hash Tabela = new Hash();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(Tabela,p);
        Ocorrencia(Tabela, p);
 
    }
    /** 
     * @metod: Ler um arquivo e instaciar os Objetos
     * @return: Vetor de Objeto
     */
    public static Player[] saveArk() {
        Player[] p = new Player[TamArq];
        Arq.openRead("./players.csv");
        String Dado = Arq.readLine();
        int i = 0;
        while (Arq.hasNext() == true) {
            Dado = Arq.readLine();
            p[i] = new Player();
            p[i].Ler(Dado);
            i++;
        }
        Arq.close();
        return p;
    }

    /*
     * @ method: Ler os Ids dos PubIn e salva em um vector
     * @ return: vetor de Ids
     */      
    public static void PubInNumbers(Hash t, Player[] p)throws Exception {
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {            
            t.inserir(p[Integer.parseInt(str)]);   
            str = MyIO.readLine();     
        }       
    }

    public static void Ocorrencia(Hash t, Player[] p){
        String str = MyIO.readLine();
        Boolean resp;
        while (!str.contains("FIM")) {  
            Player x = findplayer(str, p);        
            resp = t.pesquisar(x);
            if(resp == true)
                MyIO.println(str + " SIM");
            else
                MyIO.println(str + " NAO");          
            str = MyIO.readLine();     
        }
    }

    public static Player findplayer(String elemento, Player[] p){
        for(int i = 0; i < 3922; i++)
        {
             if(p[i].getNome().contains(elemento)){
                 return p[i];
             }
        }
       return null;
    }

}

