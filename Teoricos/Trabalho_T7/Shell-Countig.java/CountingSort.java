class CountingSort {

    public static int[] countingSort(int[] array){
        int j=0;
        int moreelemet=-1;
        for(int i=0; i<array.length;i++){
            if(moreelemet<array[i])
                moreelemet=array[i];
        }
        //Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[moreelemet + 1];
       
        
        for(int i=0; i<count.length;i++) count[i]=0;
        for(int i=0; i<array.length;i++){
             count[array[i]]++;
        }  
        //MyIO.print("Number -->");    
        printVector(count);

        for(int i=0; i<count.length;i++){
            while(count[i]>0){
                array[j] = i;
                j++;
                count[i]--;
            }
            //MyIO.print("Ordenado-->");
            // printVector(ordenado);
        }       
        return array;
    }
    
    public static void printVector(int array[]){
        System.out.print("Vector {"); 
            for(int l=0; l<array.length-1;l++){
                System.out.print(array[l]+",");    
            }   
            System.out.print(array[array.length-1]+"}");    
            System.out.println(" ");  
            System.out.println(" "); 
    } 
    

    public static void main(String[] args) {
        
        int[] array = {12,4,8,2,3,3,3,3,14,17,6,18,10,16,15,5,13,9,1,11,7,3};

        System.out.println("Before Shell Sort");    
        printVector(array); 
        array=countingSort(array); 
        System.out.println("After Shell Sort");    
        printVector(array); 
    }
}    
