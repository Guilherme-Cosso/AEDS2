#include<stdio.h>
#include<string.h>
#include<locale.h>
#include <ctype.h>
#include<stdbool.h>
//C
        /*
        *	@method : ler a string
        *  @param : plalavra  vetor de char
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
            palavra[i-1]= '\0';
         }
        /*
        *	@method : muda pra maisculo
        *  @param : plalavra  vetor de char
        */

         void maisc(char palavra[1000])
         { 
            
            for(int i= 0 ; palavra[i] != '\0' ; i++)
            { 
               
               palavra[i] = toupper(palavra[i]);
         
            }
           
         }
        /*
        *	@method : ve se e fim
        *  @param : plalavra  vetor de char
        */
         
         bool ehFIM(char palavra[])
         {
             bool resp=false;
             if(strlen(palavra)==3 && palavra[0]=='F'&& palavra[1]=='I'&& palavra[2]=='M' )
                 resp = true;
         
              return resp;
         }
        /*
        *	@method : ver se e vogal
        *  @param : plalavra  vetor de char
        */
      
      bool vogal(char palavra[])
      {
         bool   resp=false;
         int i = 0,
             aux = 0;
         while(palavra[i] != '\0')
         {
            if(palavra[i] >= 'A' && palavra[i] <= 'Z')
               if(palavra[i] == 'A' || palavra[i] == 'E'  ||  palavra[i] == 'I' || palavra[i] == 'O' || palavra[i] == 'U' )
      				 aux++;
              if(palavra[i] == ' ')aux++;
              
            i++;
          }
          if(i == aux)
              resp = true;
          return resp;
      }   
     
      /*
       *	@method : ver consoante
       *  @param : plalavra  vetor de char
       */
      bool consoante(char palavra[])
      {
              bool resp=false;
              int i = 0,
              aux = 0;
              while(palavra[i] != '\0')
              {
                      if(palavra[i] >= 'A' && palavra[i] <= 'Z')
                      if(palavra[i] != 'A' && palavra[i] != 'E'  &&  palavra[i] != 'I' && palavra[i] != 'O' && palavra[i] != 'U' )
                      aux++;
                      i++;
                      if(palavra[i] == ' ')aux++;
                    
              }
              if(aux == i)
                      resp = true;
                      
              return resp;
      }
       /*
       *	@method : ver int
       *  @param : plalavra  vetor de char
       */    
      bool inteiro(char palavra[])
      {
              bool resp = false;
              int aux=0,
              i = 0;
              while(palavra[i] != '\0')
              {        
                      if(palavra[i] >= '0' && palavra[i] <= '9')
                      aux++;
                      i++;
              }
              if(i==aux)
                      resp=true;
              return resp;
      }
       /*
       *	@method : ver real
       *  @param : plalavra  vetor de char
       */  
             
            
      bool real(char palavra[])
      {
              bool resp = false;
              int aux=0,
              aux2=0,
              i=0;
      
              while(palavra[i] != '\0')
              {
                      if(palavra[i] >= '0' && palavra[i] <= '9')
                              aux++;
                      i++;
              }
                      i=0;
                      while(palavra[i] != '\0')
                      {
                              if(palavra[i] == ';' || palavra[i] == '.' || palavra[i] == ',')
                               {
                                      aux++;
                                      aux2++;
      
                              }
                               i++;
                      }			
              if(i==aux && aux2<=1)
                      resp=true;
      
              return resp;
      } 

void printar(char palavra[])
{   
       if(!vogal(palavra))
               printf("NAO ");
       else
               printf("SIM ");
 
       if(!consoante(palavra))
               printf("NAO ");
       else
               printf("SIM ");
            
       if(!inteiro(palavra))
               printf("NAO ");
       else
               printf("SIM ");
      
      if(!real(palavra))
               printf("NAO\n");
       else
               printf("SIM\n");

}

int main()
{
        setlocale(LC_ALL, "");
        bool resp = true;
        char palavra[1000];
        ler(palavra);
        maisc(palavra);
   
        while(!ehFIM(palavra)) 
        { 
                printar(palavra);         
                ler(palavra);
                maisc(palavra);
        
        }       
 return 0;
}