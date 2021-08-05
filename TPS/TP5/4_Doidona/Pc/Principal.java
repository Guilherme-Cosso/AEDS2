class Principal {
    final static int TamArq = 3922;

    public static void main(String[] args) throws Exception {
        Doidona doido = new Doidona();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(doido,p);
        doido.mostrar();
        Ocorrencia(doido,p);        
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
    public static void PubInNumbers(Doidona doi, Player[] p)throws Exception {
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {            
            doi.inserir(p[Integer.parseInt(str)]);   
            str = MyIO.readLine();     
        }       
    }

    public static void Ocorrencia(Doidona doi, Player[] p){
        String str = MyIO.readLine();
        Boolean resp;
        while (!str.contains("FIM")) {
            MyIO.print(str + " ");        
            resp = doi.pesquisar(str);
            SorN(resp);
            str = MyIO.readLine();     
        }
    }
    public static void SorN(Boolean resp){
        if(resp == true)
            MyIO.print("SIM\n");
        else
            MyIO.print("NAO\n");        
    }
}
