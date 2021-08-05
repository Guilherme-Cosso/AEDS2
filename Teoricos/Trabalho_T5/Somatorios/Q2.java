public class Q2 {
    public static int sumPA(double a, double b , int n) {
        int soma =(int) a;
        
        for (; a <= n; a++) {
            soma += b;
        }
        return soma;
    }
    public static void main(String[] args) {
        int n = sumPA(1,2,10);
        MyIO.print("Somatorio = " + n);
    }
    
}

