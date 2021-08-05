public class Principal {
    public static void main(String[] args) {
        int peso;
        String nome;
        ListaDupla lista = new ListaDupla();
        peso = MyIO.readInt();
        while(peso !=0 ){
            nome = MyIO.readString();
            lista.inserir(peso, nome);
            peso = MyIO.readInt();
        }
        lista.mostrar();
        lista.ordenar();
        lista.mostrar();
    }
    
}
