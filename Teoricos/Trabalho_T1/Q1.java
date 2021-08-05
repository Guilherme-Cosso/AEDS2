class Qb
{
	public static boolean comp(int[] s , int num)
	{
		boolean resp=false;	
		for(int i=0 ;i<s.length ;i++)
		{
			if(num==s[i])
				resp=true;
		}
	
		return resp;
	}


	public static void main(String[] args)
	{
        	
		int[] iniciaValores = {12,32,54,6,8,89,64,64,6};
		int numero=6;
		boolean resp;
		resp=comp(iniciaValores,numero);
		MyIO.println(resp);
        }
	

}

