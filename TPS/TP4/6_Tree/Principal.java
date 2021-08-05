class Principal {
   final static int TamArq = 3922;
   public static void main(String[] args) throws Exception {
      ArvoreTrie arv1 = new ArvoreTrie();
      ArvoreTrie arv2 = new ArvoreTrie();
      Lista nomes = new Lista();
      ArvoreTrie completa = new ArvoreTrie();
      Player[] p = new Player[TamArq];
      p = saveArk();
      PubInNumbers(arv1,arv2,p);
      PegarDados(arv1,arv2,nomes);
      nomes.pegarNome(completa);
      Ocorrencia(completa);
     
   }

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

   /**
    * @method: Ler os Ids dos PubIn e salva em 2 arvores Trie
    * @Param ArvoreTrie arv1
    * @Param ArvoreTrie arv2
    * @Param Player[] p
    */

   public static void PubInNumbers(ArvoreTrie arv1 ,ArvoreTrie arv2 ,Player[] p) throws Exception {
      String str = MyIO.readLine();
      while (!str.contains("FIM")) {
         MyIO.println(str+'\t'+p[Integer.parseInt(str)].getNome());
         arv1.inserir(p[Integer.parseInt(str)].getNome());
         str = MyIO.readLine();
      }
      str = MyIO.readLine();
      while (!str.contains("FIM")) {
         MyIO.println(str+'\t'+p[Integer.parseInt(str)].getNome());
         arv2.inserir(p[Integer.parseInt(str)].getNome());
         str = MyIO.readLine();
      }
   }

   public static void PegarDados(ArvoreTrie arv1, ArvoreTrie arv2,Lista Nomes){
      arv1.PegarElemento(Nomes);
      arv2.PegarElemento(Nomes);
   }


   public static void Ocorrencia(ArvoreTrie n)throws Exception{
      String str = MyIO.readLine();
      while (!str.contains("FIM")){
         if(n.pesquisar(str))
            MyIO.print(str + " SIM\n"); 
         else
            MyIO.print(str + " NAO\n"); 
         str = MyIO.readLine();
      }
   }

}
