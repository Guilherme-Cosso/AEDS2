public class Q2 {
    public static void main (String[] args) {
        int counter = 11;
        String auxiliar = MyIO.readLine(); 
        while (!auxiliar.equals("\n") && counter-- >= 0) {
            int nEntrada = Integer.parseInt(auxiliar);
            Operacao operacao = new Operacao(nEntrada);
            for(int i = 0; i < nEntrada; i++) {
                // operador
                int tmp = MyIO.readInt();
                operacao.operation[i] = tmp;
                // numero
                tmp = MyIO.readInt();
                operacao.numbers[i] = tmp;
            }
            operacao.isFila();
            operacao.isImpossible();
            operacao.isPilha();
            operacao.setEstrutura();
            System.out.println(operacao.estrutura);
        }
    }
}

class Operacao {
    int[] operation;
    int[] numbers;
    String estrutura;
    boolean isPilha;
    boolean isFila;
    boolean isImpossible;
    boolean isPriority;

    Operacao(int nEntrada) {
        operation = new int[nEntrada];
        numbers = new int[nEntrada];
        estrutura = new String();
    }

    void setEstrutura() {
        if (isImpossible) estrutura = "impossible";
        else if (isPriority) estrutura = "priority queue";
        else if (isPilha) estrutura = "stack";
        else if (isFila) estrutura = "queue";
        else estrutura = "not sure";
    }

    void isPilha() {
        // primeiro a entrar eh o ultimo a sair
        isPilha = true;
        int counter = numbers.length - 1;
        for (int i = 0; i < operation.length; i++)
            if (operation[i] == 2) {
                if (numbers[counter--] != numbers[i]) {
                    isPilha = false;
                    return;
                }
            }
        return;
    }

    void isFila() {
        // primeiro a entrar eh o primeiro a sair
        isFila = true;
        int counter = 0;
        for (int i = 0; i < operation.length; i++)
            if (operation[i] == 2) {
                if (numbers[0 + counter++] != numbers[i]) {
                    isPilha = false;
                    return;
                }
            }
        return;
    }

    void isImpossible() {
        // zona
        isImpossible = true;
        for (int i = 0; i < operation.length; i++)
            if (operation[i] == 2) 
                for (int j = 0; j <= i; j++) 
                    if (numbers[j] == numbers[i]) {
                        isImpossible = false;
                        return;
                    } 
        return;
    }

    void isPriority () {
        int maior = 0;
        for (int i = 0; i < numbers.length; i++) if (numbers[i] > maior) maior = numbers[i];
    
        isPriority = true;
        int counter = numbers.length - 1;
        for (int i = 0; i < operation.length; i++)
            if (operation[i] == 2) {
                if (numbers[counter--] != numbers[i] || numbers[i] < maior) {
                    isPriority = false;
                    return;
                }
            }
        return;
    }
}
