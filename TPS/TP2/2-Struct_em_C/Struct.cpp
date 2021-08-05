#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define DELIM "\t ,"

typedef struct Jogador{
    int id,
        altura,
        peso,
        anoNascimento;

    char nome[70],
         universidade[70],
         cidadeNascimento[70],
         estadoNascimento[70];
}Jogador;

//prototipos
//void trat(char dados[], int index);
void separarDados(char dados[], int index, Jogador jogador[]);
//void inicializadorString(char dados[]);
void leeArq(Jogador jogador[]);
void imprimir(Jogador jogador[], int index);
void replace (char string[]);

int main(void){
  Jogador j1;
  Jogador j2;

  Jogador jogador[4000];
  char ID[6];
  int j = 0;
  // leitura do arquivo
  leeArq(jogador);
  scanf("%s", ID);
  while(!(strcmp(ID, "FIM") == 0)){
     int id = atoi(ID);
     imprimir(jogador, id);
     scanf(" %s", ID);
  }
}

void leeArq(Jogador jogador[]){
  FILE *arq = fopen("./players.csv","r");
  int i = 0;
  char dados[200]; 
  fgets(dados,200,arq);
  while (i < 3922){
    fgets(dados,200,arq);
    replace (dados);
    separarDados(dados, i, jogador);
    i++;
  }
  fclose(arq);
}

void replace (char dados[]) {
    char aux[] = "nao informado";
    char nString[200];
    int i = 0;
    int j = 0;

    while (dados[i] != '\0') {
        nString[j++] = dados[i++];
          if (dados[i - 1] == ',' && (dados[i] == ',' || dados[i] == '\n')) {
            for (int k = 0; k < strlen(aux); k++) 
                nString[j++] = aux[k];
          }
    }

    nString[j] = '\0';
    strcpy(dados, nString);
    //printf("%s", dados);
}

void separarDados(char dados[], int index, Jogador jogador[]){
  char string[70];
  int j = 0,
      k = 0;

  for (int i = 0; i < 8; i++) {
    while(dados[k] != ',' && dados[k] != '\n') {
        string[j++] = dados[k++];
    }
    string[j] = '\0';

    switch(i){
        case 0:
        jogador[index].id = atoi(string); 
        break;
        case 1:
          strcpy(jogador[index].nome, string); 
           break;
        case 2: 
          jogador[index].altura = atoi(string) ; 
           break;
        case 3:
          jogador[index].peso = atoi(string) ;   
           break;     
        case 4:
          strcpy(jogador[index].universidade,string); 
           break;       
        case 5:
          jogador[index].anoNascimento = atoi(string);
           break;
        case 6:
          strcpy(jogador[index].cidadeNascimento,string);
           break;
        case 7:
         strcpy(jogador[index].estadoNascimento, string);
          break; 
     }
    k++;
    j=0;
  }
}

void imprimir(Jogador jogador[], int index){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador[index].id ,jogador[index].nome, jogador[index].altura, jogador[index].peso, jogador[index].anoNascimento,  jogador[index].universidade, jogador[index].cidadeNascimento, jogador[index].estadoNascimento);
}
