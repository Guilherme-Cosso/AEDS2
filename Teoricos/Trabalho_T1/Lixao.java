import java.util.*;
import java.io.*;
class Retangulo
{

	private double altura;
	private double base;
	private double area;
	private double peri;
	private double diagonal;
	

	Retangulo(){	
	}

	Retangulo(double altura , double base)
	{
		this.base = base;
		this.altura = altura;
		this.area = base*altura;
		this.peri= (base*2)+(altura*2);
		this.diagonal=Math.sqrt((base*base)+(altura*altura));
		

	}
	
	public void print()
	{
		MyIO.println(this.base + " " + this.altura);
	}
	public double getArea()
	{
		return area;
	}
	public double getPerimetro(){
		return peri;
	}
	public double getDiagonal()
	{
		return diagonal;
	}
}
class Lixao{
	public static void main(String[] args){
		
		Retangulo objeto = new Retangulo(3.15 , 4.5);
		objeto.print();
	}

}