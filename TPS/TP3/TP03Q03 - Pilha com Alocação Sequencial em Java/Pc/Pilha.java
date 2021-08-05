/**
 * Pilha dinamica
 * 
 * @author Guilherme Cosso Lima Pimenta
 * @version 1 21/10
 */
public class Pilha {
	private int[] array;
	private int n;

   /**
    * Construtor da classe.
    */
	public Pilha() {
		this(200);
	}


	/**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
	*/
	
	public Pilha (int tamanho){
		array = new int[tamanho];
		n = 0;
	 }



	/**
	 * Insere elemento na pilha (politica FILO).
	 * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
	 */

	public void inserir(int x)throws Exception {

		//validar insercao
      	if(n >= array.length){
       	  throw new Exception("Erro ao inserir!");
     	}
      array[n] = x;
      n++;
   }

	/**
	 * Remove elemento da pilha (politica FILO)
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
	 */

	public int remover() throws Exception {
		//validar remocao
		if (n == 0) {
			throw new Exception("Erro ao remover!");
		 }
   
		 return array[--n];
	}
	

	/**
	 * Mostra os elementos separados por espacos, comecando do topo.
	 */

	public void mostrar (){
		System.out.print("[ ");
		for(int i = n-1; i >= 0; i--){
		   System.out.print(array[i] + " ");
		}
		System.out.println("]");
	 }

	 public int dados (int pos){
		int resp = array[pos];
		return resp;
	 }

	public int getN() {
		return n;
	}

}
