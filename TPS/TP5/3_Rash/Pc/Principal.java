class Principal {
    final static int TamArq = 3922;

    public static void main(String[] args) throws Exception {
        Hash Tabela = new Hash(21);
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
        Boolean resp = false;
        while (!str.contains("FIM")) {           
            resp = t.pesquisar(str);
            if(resp == true)
                MyIO.println(str + " SIM");
            else
                MyIO.println(str + " NAO");          
            str = MyIO.readLine();     
        }
    }

}

