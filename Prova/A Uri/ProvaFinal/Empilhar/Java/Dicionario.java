class Dicionario {
    public static void main(String[] args) {
        int soma = 0, tam = -1;
        tam = MyIO.readInt();
        while(tam != 0){
            int pilha[] = new int[tamanho(tam)];
                for(int j = 0;j<pilha.length;j++)
                    pilha[j] = MyIO.readInt();
            switch (tam){
                case 1:
                    soma = Maior1(pilha);
                    break;
                case 2:
                    soma = Maior1(pilha);
                    soma = Maior2(pilha, soma);
                    break;
                case 3:
                    soma = Maior1(pilha);
                    soma = Maior2(pilha, soma);
                    soma = Maior3(pilha, soma);
                    break;
                case 4:
                    soma = Maior1(pilha);
                    soma = Maior2(pilha, soma);
                    soma = Maior3(pilha, soma);
                    soma = Maior4(pilha, soma);
                break;
            }
            MyIO.println(soma);
            tam = MyIO.readInt();
        }
    }
    

    public static int tamanho(int x){
        int tam = 0;
        for(int i = 1;i <= x;i++){
            tam += i;
        }
        return tam;
    }

    public static int Maior1(int pilha[]){
        int soma = 0;
        if(pilha[0] >= soma){
            soma = pilha[0];
        }
        return soma;
    }

    public static int Maior2(int pilha[], int elemeto){
        if(pilha[0] + pilha[1] >= elemeto){
            elemeto = pilha[0] + pilha[1];
        }
        if(pilha[0] + pilha[2] >= elemeto){
            elemeto = pilha[0] + pilha[2];
        }
        if(pilha[0] + pilha[1] + pilha[2] >= elemeto){
            elemeto = pilha[0] + pilha[1] + pilha[2];
        }
        return elemeto;
    }

    public static int Maior3(int pilha[], int elemeto){
        // Comparaçoes com somente 1 elemeto..
        if(pilha[0] + pilha[1] + pilha[3] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[3];
        if(pilha[0] + pilha[1] + pilha[5] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[5];
        if(pilha[4] + pilha[0] + pilha[1] + pilha[2] >= elemeto)
            elemeto = pilha[4] + pilha[0] + pilha[1] + pilha[2];
        // Coparaçoes com 2 elemetos do 3 nivel
        /** 
        *   Total + 3 + 5;
        *   Total + 3 + 4; 
        *   Total + 4 + 5;
        */
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[5] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[5];
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4];
        if(pilha[0] + pilha[1] + pilha[2] + pilha[4] + pilha[5] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[4] + pilha[5];
        // Soma de all
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5];
        return elemeto;
    }

    public static int Maior4(int pilha[], int elemeto){
         // Um elementos de 4 1/4
        if(pilha[0] + pilha[1] + pilha[3] + pilha[6] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[3] + pilha[6];
        if(pilha[0] + pilha[2] + pilha[5] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[2] + pilha[5] + pilha[9];      
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[7] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[7];
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[8] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[4] + pilha[5] + pilha[8];
        // Dois elementos de 4 2/4
        // 6 com 7 8 9.
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[6] + pilha[7] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[6] + pilha[7];       
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[8] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[8];   
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[5] + pilha[6] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[5] + pilha[6] + pilha[9];
        // 7 - 8 9

        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[7] + pilha[8] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[7] + pilha[8]; 
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[7] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[7] + pilha[9];        
        // 8 9
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[5] + pilha[8] + pilha[9] >= elemeto)
           elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[5] + pilha[8] + pilha[9];
        // 3/4
        // 6 7 8    6 7 9   6 8 9   7 8 9 
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[7] + pilha[8]  >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[7] + pilha[8];
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[7] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[7] + pilha[9];
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[8] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[8] + pilha[9];
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[7] + pilha[8] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[7] + pilha[8] + pilha[9];       
        // ALL 
        if(pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[7] + pilha[8] + pilha[9] >= elemeto)
            elemeto = pilha[0] + pilha[1] + pilha[2] + pilha[3] + pilha[4] + pilha[5] + pilha[6] + pilha[7] + pilha[8] + pilha[9];
        //exeption.
        
        return elemeto;    
    }
}