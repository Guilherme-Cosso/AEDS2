boolean pesquisar (int valor){
    boolean resp = false;
    Celula xCelula;
    int pos = hashT1(valor);
 // Pequiso em T1
    if(t1[pos].p == valor){
       resp = true;
    }else {
      // Vou para a Celula de T2;
      xCelula = t1.[pos].prox;
      if(xCelula == valor){
         resp = true;
      }
      else{
         // Hash Para ver se vou pra arvore ou Lista
         pos = hashT3(valor);
         if(pos == 0){
            // Pequiso na Lista 
            for (Celula i = primeiro.prox; i != null; i = i.prox) {
               if(i.elemento == valor){
                  resp = true;
                  i = ultimo;
               }
            }
         // Caso Nao esteja na lista por estouro pequiso na arvore
            if(resp == false){
               resp = arvore.pesquisar(valor);
            }
         }
         else{
     // Pequiso na Arvore Caso de != de 0 ou mood 1.
            resp = arvore.pesquisar(valor);
         }
      }
      return resp;
}
