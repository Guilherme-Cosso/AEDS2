#include<stdio.h>
#include<string.h>
#include<locale.h>
#include <ctype.h>
#include<stdbool.h>
   /*
	*	@method : Ler uma Frase
	*	@param : s 'String' a ser analizada.
	*/

   void ler(char palavra[1000])
   {
      char caractere;
      int i = 0;
  
      do
      {
         caractere = getchar();
         palavra[i] = caractere;
         ++i;
      }while(caractere != '\n');
      palavra[i-1]= '\0'
 }
   /*
	*	@method : inverte
   *	@param : s 'String' a ser analizada.
	*/

void revete(char inverte[100], char palavra[100])
{
   for(int i=0; i<strlen(palavra);i++)
            inverte[i]=palavra[strlen(palavra)-1-i];

}
   /*
	*	@method : compara as 2 strings   
   *	@param : p 'String' a ser analizada.
   *  @param : i 'String' a ser analizada.
	*/

bool palin(char p[1000], char i[1000])
{
    bool resp=false;
    int cp;
    revete(i,p);
    cp = strcmp(p , i);
    if(cp==0)
        resp=true;
    return resp;
}

   /*
	*	@method : Confere se � o Fim
	*	@param : s 'String' a ser analizada.
	*/

bool ehFIM (char palavra[])
{
    bool resp=false;
    if(strlen(palavra)==3 && palavra[0]=='F'&& palavra[1]=='I'&& palavra[2]=='M' )
        resp = true;

     return resp;
}

int main() 
{
   setlocale(LC_ALL, "");
   bool resp = true;
   char palavra[1000], inversa[1000];
   ler(palavra);
     
   while(!ehFIM(palavra))  
   {   
      
       //Copiar a palavra digitada para que seja invertida
       strcpy(inversa, palavra);
       resp = palin(palavra , inversa);
       if (resp == false)
           printf("NAO\n");
       else
           printf("SIM\n");
       ler(palavra);
   }
   
   return 0;
}
