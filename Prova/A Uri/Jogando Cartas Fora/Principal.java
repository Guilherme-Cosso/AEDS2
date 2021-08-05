class Principal{
    public static void main(String[] args)throws Exception {
        Pilha l = new Pilha();
        int x = MyIO.readInt();
        while(x != 0){
            l.inicializar(x);
            l.mostra();
            RemoverPilha(l,x);
            x = MyIO.readInt();
        }
    }

    public static void RemoverPilha(Pilha l, int x){
        int num;
        MyIO.print("Numeros removidos: ");
        while(x>=2){
            num = l.removerInicio();
            l.anvt();
            MyIO.print(num + " ");
            x--;
        }
        MyIO.println("\nRemanecente: "+ l.primeiro.elemento);     
    }
}
