class DoidonaComTADsProntas {
    final int TAMT1 = 8;
    final int TAMT2 = 100;
    final int TAMT3 = 100;
    final int TAMT4 = 100;
    final int NULO = -0x7FFFFF;
 
    int[] t1;
    int[] t2;
    int[] t3;
    Celula[] t4;
    AVL arvoreAVL;
 
    public Doidona(){
       t1 = new int [TAMT1];
       t2 = new int [TAMT2];
       t3 = new int [TAMT3];
       t4 = new Celula[TAMT4];
 
       for(int i = 0; i < TAMT1; i++){
          t1[i] = NULO;
       }
       for(int i = 0; i < TAMT2; i++){
          t2[i] = NULO;
       }
       for(int i = 0; i < TAMT3; i++){
         t3[i] = NULO;
      }
      for(int i = 0; i < TAMT4; i++){
         t4[i].elemento = NULO;
         Alvinegra Aviraiz = new Alvinegra();
         t4[i].prox = Aviraiz;
      }

       arvoreAVL = new AVL();
    }
 /*HASH JA IMPLETATOS
    public int hashT1(int elemento){
       // 8 elementos - 3 de reserva =5
       return elemento % 5;
    }
 
    public int ReservahashT1(int elemento){
       return elemento % 3;
    }
 
    public int hashT3(int elemento){
    }
 
    public int rehashT3(int elemento){
    }
*/
 
    public void mostrar(){
      // Mostro todos os T1 != NULO
      for(int i = 0; i < TAMT1 - 3; i++){
        if(t1[i] != NULO)
         MyIO.println(t1[i]);
      }
      // Todos ta t2
      for(int i = 0; i < TAMT2 - 1; i++){
         if(t2[i] != NULO)
          MyIO.println(t2[i]);
      }
      // Chamo o mostrar da avl no fim de T2
      arvoreAVL.mostra();
      // Todos ta t2
      for(int i = 0; i < TAMT3; i++){
         if(t3[i] != NULO)
          MyIO.println(t3[i]);
      }
      // Mostrar a t4
      for(int i = 0; i < TAMT4; i++){
         if(t4[i].elemento != NULO){
            MyIO.println(t4[i].elemento);
            // Mostro a ta Arvore
            Aviraiz.mostrar(t4[i].prox);
         }
      }
    }
 }