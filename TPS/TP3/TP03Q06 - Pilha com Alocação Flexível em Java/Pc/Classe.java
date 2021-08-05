class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;

    public static void main(String[] args) throws Exception {
        Pilha pilha = new Pilha();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(pilha,p);
        int acoes = MyIO.readInt();
        Ocorrencia(acoes, pilha, p);
        pilha.print();
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
public static void PubInNumbers(Pilha pilha, Player[] p)throws Exception {
    String str = MyIO.readLine();
    while (!str.contains("FIM")) {            
            pilha.inserirFim(p[Integer.parseInt(str)]);   
            str = MyIO.readLine();     
        }       
    }

    public static void Ocorrencia(int cont,Pilha pilha,Player[] p)throws Exception {
        Player x;
        for(int i=0;i<cont;i++){
            String linha = MyIO.readLine();
            String parte[] = linha.split(" ");
            if(parte[0].charAt(0) =='R'){
                        x = pilha.removerFim();
                        MyIO.println("(R) " + x.getNome());
            }
            else{
                pilha.inserirFim(p[Integer.parseInt(parte[1])]);
            }
        }
    }    
}
