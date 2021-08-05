class ExemploArq03Exercicio 
{
	public static String lerarq(String str)
	{	
		Arq.openRead("exemplo.txt");
		while (Arq.hasNext() == true)
		{
			str += Arq.readChar();
		}
		Arq.close();
		return str;
	}

	public static void Copyarq(String str)
	{
		Arq.openWrite("copia.txt");
		Arq.print(str);
		Arq.close();
	}

	public static void main (String[] args)
	{
		String str = "";
		lerarq(str);
		Copyarq(str);
	}
}