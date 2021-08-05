class Hash {
    Lista tabela[];
    int tamanho;
    final int NULO = -1;
 
    public Hash (){
       this(7);
    }
 
    public Hash (int tamanho){
       this.tamanho = tamanho;
       tabela = new Lista[tamanho];
       for(int i = 0; i < tamanho; i++){
          tabela[i] = new Lista();
       }
    }
 
    public int h(int elemento){
       return elemento % tamanho;
    }
 
    boolean pesquisar(String nome){
    boolean resp = false;
        for(int i = 0; i < tamanho; i++){
            resp = tabela[i].pesquisar(nome);
            if(resp == true)
                break;
         }
       return resp;
    }
    public void inserir(Player p){
        inserirInicio(p.getNome(),p.getAltura());
    }
 
    public void inserirInicio ( String nome, int elemento){
       int pos = h(elemento);
       tabela[pos].inserirInicio(nome);
    }
 /*
    void remover(int elemento){
       int resp = NULO;
       if (pesquisar(elemento) == false){
          throw new Exception("Erro ao remover!");
       } else {
          int pos = h(elemento);
          resp = tabela[pos].remover(elemento);
       }
       return resp;
    }
 */
 }