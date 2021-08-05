class Classe {
    final static int TamArq = 3922;
    static int tamId = 0, tamNomes = 0, comp = 0;

    public static void main(String[] args) throws Exception {
        Matriz matriz1 = new Matriz();
        matriz1.CriarM(4,4);
        int n = MyIO.readInt();
        for(int i =0;i<n;i++){
			matriz1.inserir(4);
			matriz1.mostrar();
            matriz1.mov();
        }
    }
}
