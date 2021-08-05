class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;

    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(lista,p);
        lista.tirarNo();
        lista.mostrar();
        lista.Quick();
        lista.print();
        //lista.Quick();
        

        // System.out.println(Arrays.toString(nomes));
        // System.out.println(Arrays.toString(id));
        // System.out.println(Arrays.toString(nomesId));
        
        
    }

    /*
     * @ method: Ler um arquivo e instaciar os Objetos
     * @ return: Vetor de Objeto
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
     * 
     * @ return: vetor de Ids
     */      
    public static void PubInNumbers(Lista lista, Player[] p)throws Exception {
        String str = MyIO.readLine();
        int cont=0;
        while (!str.contains("FIM")) {            
            lista.inserirFim(p[Integer.parseInt(str)],cont++);   
            str = MyIO.readLine();     
        }       
    }  
}
