class Principal{
    public static void main(String[] args)throws Exception {
        int num = MyIO.readInt();
        Pessoa[] p = new Pessoa[100];
        while (num != 0) {
            LerPubs(num, p);
            Ordenar(num,p);
            for(int i = 0; i < num; i++){
                p[i].imprimir();
            }
            MyIO.println(" ");
            num = MyIO.readInt();
        }        
    }

    public static void LerPubs(int num, Pessoa p[]){
        for(int i = 0; i < num; i++){
            p[i] = new Pessoa();
            p[i].Ler(); 
        }
    }

    public static Pessoa[] Ordenar(int num, Pessoa[] p) {
        int n = num;
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++) {
                if (p[menor].getCor().compareTo(p[j].getCor()) > 0 || (p[i].getCor().compareTo(p[j].getCor()) == 0  && p[i].getTamaho() < p[j].getTamaho())  || (p[i].getCor().compareTo(p[j].getCor()) == 0  && p[i].getTamaho() == p[j].getTamaho() && p[i].getNome().compareTo(p[j].getNome()) > 0))
                    menor = j;
            }
            swap(menor, i, p);
        }
        return p;
    }

    public static void swap(int i, int j, Pessoa array[]) {
        Pessoa temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
