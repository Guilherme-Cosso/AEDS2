class ShellSort {

    public static int[] shellsort(int[] array) {
      int step = 1;
      do {
        step = (step * 3) + 1;
      } while ( step < array.length );
      do {
        step /= 3;
        for (int collor = 0; collor < step; collor++) {
          array=insertionCollor(collor, step,array);
        }
        printVector(array);
      } while ( step != 1 );
      return array;
    }
    
    public static int[] insertionCollor(int collor, int step, int[] array) {
 
      for (int j = (step + collor); j < array.length; j += step) {
            
            int key = array[j];
            int i = j - step;
            System.out.println("|Key|--------"+key+"--------|");
           
            System.out.println("Compare -> "+ array[i] + "<- ->" + key);  
            while ((i >= 0) && (array[i] > key)) {
                array[i + step] = array[i];
                i -= step;
                if(i>=0){
                  
                  System.out.println("Compare -> "+ array[i] + "<- ->" + key);  
              }
            }
            array[i + step] = key;
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
        
        int[] array = {12,4,8,2,14,17,6,18,10,16,15,5,13,9,1,11,7,3};

        System.out.println("Before Shell Sort");    
        printVector(array); 
        shellsort(array); 
        System.out.println("After Shell Sort");    
        printVector(array); 
    }
}    
