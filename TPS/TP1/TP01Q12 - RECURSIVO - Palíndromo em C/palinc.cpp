#include<stdio.h>
#include<string.h>
#include<locale.h>
#include <ctype.h>
#include<stdbool.h>


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
   palavra[i-1]= '\0';
}
 
bool ehFIM(char palavra[])
{
    bool resp=false;
    if(strlen(palavra)==3 && palavra[0]=='F'&& palavra[1]=='I'&& palavra[2]=='M' )
        resp = true;

     return resp;
}
bool vogal(char palavra[])
{
   bool resp=false;
   int i = 0,
       aux = 0;
   while(palavra[i] != '\0')
   {
      if(palavra[i] >= 'A' && palavra[i] <= 'Z')
      {
         if(palavra[i] != 'A' A)
				aux++;
      }
      i++;
    }
    if(i == aux)
        resp = true;
    return resp;
}      


int main() 
{
   setlocale(LC_ALL, "");
   bool resp = true;
   char palavra[1000];
   ler(palavra);
     
   while(!ehFIM(palavra))  
   {   
      if(!vogal(palavra))
            printf("NAO\n");
      else
            printf("SIM\n");
      
         
           
      
       ler(palavra);
   }
   
   return 0;
}