import java.util.*;
// Tempo Execucao + Num de Comps
class TimeExecution{
    private double temporizador;
    private int comp;
    TimeExecution() {
        temporizador = comp = 0;
    }
    public void start() {
        temporizador = new Date().getTime();
    }
    public void stop() {
        temporizador = ((new Date().getTime()) - temporizador) / 1000;
    }
    public void add(int comp){
       this.comp+=comp;
    }
    public double getTempo() {
        return temporizador;
    }
    public int getcomp() {
       return comp;
   }
}