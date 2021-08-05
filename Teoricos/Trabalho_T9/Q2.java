import java.util.Arrays;
class Q2{

    public static void main(String[] args) {
        
        String[] vector = {"1","2","3","","5","6","7","8",""};
        int num = 0;
        System.out.println(Arrays.toString(vector));
        InsetDir(vector, num);
        System.out.println(Arrays.toString(vector));
        InsetEsq(vector, num);
        System.out.println(Arrays.toString(vector));
        RetiraDir(vector);
        System.out.println(Arrays.toString(vector));
        RetiraqEsq(vector);
        System.out.println(Arrays.toString(vector));
        RetiraqEsq(vector);
        System.out.println(Arrays.toString(vector));
    }

    public static void RetiraDir(String[] vector){
        int aux=0;
        for(int i=0; i<vector.length;i++){
            if(vector[i] == "")
                aux++;
        }
        for(int i=vector.length-1; i>0;i--){
            if(aux<vector.length && vector[i]!=""){
                    vector[i] = "";   
                    i=0;
            }
        }
    }
    
    public static void RetiraqEsq(String[] vector){
        int aux=0;
        for(int i=0; i<vector.length;i++){
            if(vector[i] == null)
                aux++;
        }
        for(int i=0; i<vector.length;i++){
            if(aux<vector.length && vector[i]!=""){
                    vector[i] = "";   
                    i=vector.length;
            }
            
        }
    }

    public static void InsetDir(String[] vector, int num){
        int aux = -1;
        for(int i=0;i<vector.length;i++){
            if(vector[i] == ""){
                aux = i;
                i=vector.length;
            }
        }
        if(aux!=-1){
            for(int i=0;i<aux;i++)
                vector[i]=vector[i];
            for(int i=aux;i<vector.length-1;i++){
                vector[i]=vector[i+1];
            }
            vector[vector.length-1]=Integer.toString(num);
        }
    }
    public static void InsetEsq(String[] vector, int num){
        int aux = -1;
        for(int i=0;i<vector.length;i++){
            if(vector[i] == ""){
                aux = i;
                i=vector.length;
            }
        }
        if(aux!=-1){
            String aux2 = vector[vector.length-1];
            for(int i=vector.length-1;i>=1;i--){
                vector[i]=vector[i-1];
            }
            vector[vector.length-1]=aux2;
            vector[0]=Integer.toString(num);
        }
    }
}


