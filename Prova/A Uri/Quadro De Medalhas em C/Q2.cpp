#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define DELIM "\t ,"

int main(){
    int num =0;
    scanf("%d",&num);
    char paises[num];
    int ouro[num],
        prata[num],
        bronze[num];
    for (int i = 0; i < num; i++){
        scanf("%s",&paises[i]);
        scanf("%i",&ouro[i]);
        scanf("%i",&prata[i]);    
        scanf("%i",&bronze[i]);
    }
    for (int i = 0; i < num; i++){
       printf("%s,%d,%d,%d\n",paises,ouro[i],prata[i],bronze[i]);
    }
}

