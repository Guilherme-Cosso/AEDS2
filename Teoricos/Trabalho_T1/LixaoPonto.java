import java.util.*;
import java.io.*;
class Ponto
{
	//Declaraçao de variavel.

	private double x;
	private double y;
	private int id;
	private int nextID;

	// Construtores.

	Ponto()
	{	
		this.id = nextID;
		this.x = 0.0;
		this.y = 0.0;
		++ nextID ;
	}

	Ponto(double x, double y)
	{	
		this.id = nextID;
		++ nextID ;
		this.x = x;
		this.y = y;
	}

	// get e set X e Y

	public double getX()
	{ 
		return this.x;
        }

	public void setX(double x)
        {
                this.x = x;
        }
	
	public double getY()
	{ 
		return this.x;
        }

	public void setY(double y)
        {
                this.y = y;
        }
	
	// getID & getNextID
	
	public int getID()
        {
                return this.id;
        }

        public static int getNextID()
        {
                return nextID;
        }
	
	public double dist(ponto obj1)
	{
		return Math.sqrt(Math.pow(obj1.getX() - this.x, 2) + Math.pow(obj1.getY() - this.y, 2));
		
	}
	public double dist(double x,double y)
	{
		return Math.sqtr(Math.pow(x - this.x , 2) + Math.pow(y - this.y, 2));
	}
	public double dist(double x,double y, double z , double p)
	{
		return Math.sqtr(Math.pow(z - a, 2) + Math.pow(d - b, 2))
	}
	 public static boolean isTriangulo(Ponto ob1, Ponto ob2, Ponto ob3)
        {
                if((a.dist(ob1) < a.dist(ob2) + c.dist(ob3)) && (a.dist(ob3) < a.dist(ob1) + c.dist(ob2)) && (c.dist(ob2) < a.dist(ob3) + a.dist(ob1)))
			return true;
                else 
			return false;
        }
	public double Area(Ponto ob1)
        {
                return (ob1.getX() - this.x) * (ob1.getY() - this.y);
        }

	
}
class LixaoPonto {
	public static void main (String[] args)
	{
		Ponto p1 = new Ponto(4,3);
		Ponto p2 = new Ponto(8,5);
		Ponto p3 = new Ponto(9.2,10);

		System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
		System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5,2));
		System.out.println("Distancia (4,3) entre e (5,2): " + Ponto.dist(4,3,5,2));
		System.out.println("P1, P2, P3 sao triangulo:" + Ponto.isTriangulo(p1,p2,p3));
		System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
		System.out.println("ID de P1: " + p1.getID());
		System.out.println("ID de P2: " + p2.getID());
		System.out.println("ID de P3: " + p3.getID());
		System.out.println("Next ID: " + Ponto.getNextID());
	}
}
