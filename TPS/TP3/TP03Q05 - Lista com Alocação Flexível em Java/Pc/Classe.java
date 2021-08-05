class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;

    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();
        Player[] p = new Player[TamArq];
        p = saveArk();
        PubInNumbers(lista,p);
        int acoes = MyIO.readInt();
        Ocorrencia(acoes, lista, p);
        MyIO.print(lista.tamanho());
        lista.print();

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
    while (!str.contains("FIM")) {            
            lista.inserirFim(p[Integer.parseInt(str)]);   
            str = MyIO.readLine();     
        }       
    }

    public static void Ocorrencia(int cont,Lista lista,Player[] p)throws Exception {
        Player x;
        for(int i=0;i<cont;i++){
            String linha = MyIO.readLine();
            String parte[] = linha.split(" ");
            if(parte[0].charAt(0) =='R'){
                if(parte[0].charAt(1) =='I'){
                    x = lista.removerInicio();
                    MyIO.println("(R) " + x.getNome());
                }
                else{
                    if(parte[0].charAt(1) =='F'){
                        x = lista.removerFim();
                        MyIO.println("(R) " + x.getNome());
                    }
                    else{
                        x = lista.remover(Integer.parseInt(parte[1]));
                        MyIO.println("(R) " + x.getNome());
                    }
                }
            }
            else{
                if(parte[0].charAt(1) =='I')
                    lista.inserirInicio(p[Integer.parseInt(parte[1])]);
                else{
                    if(parte[0].charAt(1) =='F')
                        lista.inserirFim(p[Integer.parseInt(parte[1])]);
                    else{
                        lista.inserir(p[Integer.parseInt(parte[2])], Integer.parseInt(parte[1]));
                    }
                }
            }
        }
    }    
}
