/**
 * Fila dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class PrincipalFila {
   public static void main(String[] args) throws Exception {
      System.out.println("==== FILA DINAMICA ====");
      Fila fila = new Fila();
      int x1;

      fila.inserir(1);
      fila.inserir(2);
      fila.inserir(3);
      fila.inserir(4);
      fila.inserir(5);
      fila.inserir(6);
      fila.inserir(7);
      fila.inserir(15);
      
      fila.mostrar();
      fila.inverter();
      fila.mostrar();
      

      
   }
}
