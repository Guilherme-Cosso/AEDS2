class Q1 {
    public static int sumint(int n) {
        int soma = 0,
            i = 1;
        MyIO.print(i);
        i++;
        for (; i <= n; i++) {
            soma += i;
            MyIO.print(" + " + i);
        }
        return soma + 1;
    }
    public static void main(String[] args) {
        int n = MyIO.readInt();
        n = sumint(n);
        MyIO.print(" =" + n);
    }
}

