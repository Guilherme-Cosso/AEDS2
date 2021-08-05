class Principal {
   final static int TamArq = 3922;
   static int tamId = 0, tamNomes = 0, comp = 0;

   public static void main(String[] args) throws Exception {
       Matriz matriz1 = new Matriz();
       matriz1.CriarM(1,5);
       matriz1.inserir(1,5);
       matriz1.mostrar();
       /*
       Matriz matriz2 = new Matriz();
       int cont = MyIO.readInt();
       int n;
       while(cont != 0){
         //Ler e Salvar as Matrizes
         n = PubInNumbers(matriz1, matriz2);
         //Metodos ...
         matriz1.DiagonalP(n);
         matriz1.DiagonalS(n);
         matriz1.soma(matriz2);
         matriz1.multiplicacao(matriz2,n);

         //Prints...
         cont --;
      }
         */
   }

   /*
    * @ method: Ler os Ids dos PubIn e salva em um vector
    * 
    * @ return: vetor de Ids
    */      
   /*public static int PubInNumbers(Matriz matriz1, Matriz matriz2)throws Exception {
      MyIO.readInt();
      int num = MyIO.readInt();
      matriz1.CriarM(num);
      matriz1.inserir(num);
      MyIO.readInt();
      MyIO.readInt();
      matriz2.CriarM(num);
      matriz2.inserir(num);
      return num;
   }  

*/
}
