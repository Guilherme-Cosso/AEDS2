#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define DELIM "\t ,"

int main(){
    int num;
    scanf("%d",&num);
    int contpos = 0,
        contnega = num-1;
    char vetor[num][30];
    char tmp[30];
    char sinal;
    for(int i=0; i<num;i++)
    {
        scanf(" %c",&sinal);
        if(sinal == '+'){
            
            scanf("%s",vetor[contpos]);            
            contpos++;
        }
        if(sinal == '-'){
           
            scanf("%s",vetor[contnega]);            
            contnega--;
        }
    }
    for (int i = 1; i < contpos; i++) {
      strcpy(tmp,vetor[i]);
      int j = i - 1;
      while ((j >= 0) && (strcmp(vetor[j],tmp)>0)) {
         strcpy(vetor[j + 1], vetor[j]);
         j--;
      }
      strcpy(vetor[j+1] , tmp);
   }
   for (int i=contpos; i < num; i++)
   {
        strcpy(tmp,vetor[i]);
      int j = i - 1;
      while ((j >= 0) && (strcmp(vetor[j],tmp)>0)) {
         strcpy(vetor[j + 1], vetor[j]);
         j--;
      }
      strcpy(vetor[j+1] , tmp);
   }
    for(int i=0; i<num;i++)
    {
            printf("%s\n",vetor[i]);
    }
    printf("Se comportaram: %d | Nao se comportaram: %d",contpos,num-contpos);      
}

