class Q{
        public static void main(String[] args) {
            int i = MyIO.readInt();
            int esquerda=0, direita=0, total=0;
            while(i!=0){
                String Diamantes = MyIO.readLine();
                for(int j=0 ; j<Diamantes.length();j++){
                    if(Diamantes.charAt(j)=='<')
                            esquerda++;
                    if(Diamantes.charAt(j)=='>')
                            direita++;
                }
                while(direita!=0&&esquerda!=0){
                    total++;
                    direita--;
                    esquerda--;
                }           
                MyIO.println(total);
                i--;
                total=0;
                direita=0;
                esquerda=0;
            }  
        }
}

