#include <stdio.h>
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

bool palin(char palavra[] , int i)
  {
        bool resp = true;
      
       if(i < strlen(palavra)/2)
       {
                if(palavra[i] == palavra[strlen(palavra)-i-1])
                {
	                resp = palin(palavra, i + 1);
                }
                else
                {
                        resp = false;
                }
       }
      return resp;
        

 }

void printar(bool resp){
        if(resp==true)
                printf("SIM\n");
	else
	        printf("NAO\n");
    
}

int main()
{
        setlocale(LC_ALL, "");
        bool resp = true;
        char palavra[1000];
        ler(palavra);

   
        while(!ehFIM(palavra)) 
        { 
            
                resp = palin(palavra, 0);
                printar(resp);     
                ler(palavra);
        
        
        }       
 return 0;
}