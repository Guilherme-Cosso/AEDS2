class Hash {
    final int TAM =21;
    private String[] tabela;
    static String NULO = "";


    Hash(){
        tabela = new String[TAM];
        for(int i = 0; i < TAM; i++)
            tabela[i] = NULO;        
    }

    public int hash(int altura){
        return altura % TAM;
    }
    public int rehash(int altura){
        return ++altura % TAM;
    }


    public void inserir(Player p){
        inserir(p.getNome(),p.getAltura());
    }
/*
    public void inserir(String nome, int altura){
        int pos = hash(altura);


        if(tabela[pos].equals(NULO))
            tabela[pos] = nome;
        else{
			pos = rehash(altura);

            if(tabela[pos].equals(NULO))
                tabela[pos] = nome;
            
        }
    }
*/
public boolean inserir (String nome, int altura){
    boolean resp = false;

    if(nome != NULO){

       int pos = hash(altura);

       if(tabela[pos].equals(NULO)){
          tabela[pos] = nome;
          resp = true;

       } else{

          pos = rehash(altura);

          if(tabela[pos].equals(NULO)){
             tabela[pos] =nome;
             resp = true;
          }
       }
    }
    return resp;
}

public boolean pesquisar(Player p){
    return pesquisar(p.getNome(), p.getAltura());
}

public boolean pesquisar (String elemento, int altura){
     boolean resp = false;

     int pos = hash(altura);

     if(tabela[pos].equals(elemento)){
        resp = true;

     } else {
        pos = rehash(altura);

        if(tabela[pos].equals(elemento)){
           resp = true;
        }
     }
     return resp;
  }
}
