import java.util.*;

class Dicionario {
    public static void main(String[] args) {
        String str, total = "";
        str = MyIO.readLine();
        while (!str.isEmpty()) {
            total += str + " ";
            str = MyIO.readLine();
        }
        total = tratar(total);
        String[] o = total.split(" ");
        String[] newarray = tirarepetidos(o);
        
        //System.out.print(Arrays.toString(o));
        //System.out.print(Arrays.toString(newarray));
        ordenar(newarray);
        //System.out.print(Arrays.toString(newarray));
        imprimir(newarray);
    }

    public static void ordenar(String[] str){
        quicksort(str, 0,str.length-1);
    }

    public static void quicksort(String[] str, int esq, int dir) {
        int i = esq, j = dir;
        String pivo = str[(esq + dir) / 2];
        while (i <= j) {
            while (str[i].compareTo(pivo) < 0)
                i++;
            while (str[j].compareTo(pivo) > 0)
                j--;
            if (i <= j) {
                swap(i, j, str);
                i++;
                j--;
            }
        }

        if (esq < j)
            quicksort(str, esq, j);
        if (i < dir)
            quicksort(str, i, dir);
    }

    public static void swap(int i, int j, String[] array) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static String tratar(String total) {
        String str = total;
        int n = str.length();
        total = "";
        str = str.toLowerCase();
        // Tira os Caracteres especias
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                total += str.charAt(i);

            } else
                total += " ";
        }
        total = total.replace("    ", " ");
        total = total.replace("   ", " ");
        total = total.replace("  ", " ");
        return total;
    }

    public static void imprimir(String[] str){
        for(int i= 0; i< str.length;i++){
            MyIO.println(str[i]);
        }
    }

    public static String[] tirarepetidos(String[] palavra){
        String[] newarray = new String[palavra.length];
        int cont  = 0;
        for(int i = 0; i < palavra.length; i++){
            if(!(contem(newarray, palavra[i]))){
                 newarray[cont] = palavra[i];
                 cont++;
            }
        } 

       newarray = Arrays.copyOf(newarray, cont);
       return newarray;
    }

    public static Boolean contem(String[] palavra, String palavra1){
        for(int i = 0; i < palavra.length; i++){
             if(palavra1.equals(palavra[i])){
                 return true;
             }
        }
        return false;
    }
}