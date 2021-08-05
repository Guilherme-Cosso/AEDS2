class Principal {
    final static int TamArq = 3922;

    public static void main(String[] args) throws Exception {    
        TimeExecution t = new TimeExecution();
        t.start();
        Arvore bin = new Arvore();
        int[] array = { 7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14 };
        bin.inserirMood15(array,t);
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(bin, p, t);
        Ocorrencia(bin, t);
        t.stop();
        // */
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
    public static void PubInNumbers(Arvore bin, Player[] p, TimeExecution t) throws Exception {
        String str = MyIO.readLine();
        while (!str.contains("FIM")) {
            bin.inserir(p[Integer.parseInt(str)],t);
            str = MyIO.readLine();
        }
    }

    public static void Ocorrencia(Arvore bin, TimeExecution t) {
        String str = MyIO.readLine();
        while (!str.contains("FIM")){
            MyIO.print(str + " raiz ");
            bin.pesquisar(str, t);
            str = MyIO.readLine();
        }
    }
}
