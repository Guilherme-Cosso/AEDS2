#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define DELIM "\t ,"
#define MAXTAM 5
#define bool short
#define true 1
#define false 0

typedef struct Jogador
{
  int id,
      altura,
      peso,
      anoNascimento;

  char nome[70],
      universidade[70],
      cidadeNascimento[70],
      estadoNascimento[70];
} Jogador;

Jogador array[MAXTAM];
int tam;

//prototipos da "Main"
void leeArq(Jogador jogador[]);
void separarDados(char dados[], int index, Jogador jogador[]);
void replace(char string[]);
void leeIds(Jogador j[]);
void altera(Jogador jogador[]);

//Prototipos da Lista
void start();
//Iserir
void inserir(Jogador j);
//remover
Jogador remover();
//imprimir
void imprimir(int n);
int media();

int main(void)
{
  Jogador jogador[4000];
  // leitura do arquivo
  leeArq(jogador);
  // Start Lista
  start();
  // Leitura de ids;
  leeIds(jogador);
  // Printar
  altera(jogador);
  imprimir(tam);
}

// Leitura de Arquivo
void leeArq(Jogador jogador[])
{
  FILE *arq = fopen("/tmp/players.csv", "r");
  int i = 0;
  char dados[200];
  fgets(dados, 200, arq);
  while (i < 3922)
  {
    fgets(dados, 200, arq);
    replace(dados);
    separarDados(dados, i, jogador);
    i++;
  }
}

void replace(char dados[])
{
  char aux[] = "nao informado";
  char nString[200];
  int i = 0;
  int j = 0;

  while (dados[i] != '\0')
  {
    nString[j++] = dados[i++];
    if (dados[i - 1] == ',' && (dados[i] == ',' || dados[i] == '\n'))
    {
      for (int k = 0; k < strlen(aux); k++)
        nString[j++] = aux[k];
    }
  }
  nString[j] = '\0';
  strcpy(dados, nString);
  //printf("%s", dados);
}
void separarDados(char dados[], int index, Jogador jogador[])
{
  char string[70];
  int j = 0,
      k = 0;

  for (int i = 0; i < 8; i++)
  {
    while (dados[k] != ',' && dados[k] != '\n')
    {
      string[j++] = dados[k++];
    }
    string[j] = '\0';

    switch (i)
    {
    case 0:
      jogador[index].id = atoi(string);
      break;
    case 1:
      strcpy(jogador[index].nome, string);
      break;
    case 2:
      jogador[index].altura = atoi(string);
      break;
    case 3:
      jogador[index].peso = atoi(string);
      break;
    case 4:
      strcpy(jogador[index].universidade, string);
      break;
    case 5:
      jogador[index].anoNascimento = atoi(string);
      break;
    case 6:
      strcpy(jogador[index].cidadeNascimento, string);
      break;
    case 7:
      strcpy(jogador[index].estadoNascimento, string);
      break;
    }
    k++;
    j = 0;
  }
}
// Leitura de Ids
void leeIds(Jogador j[])
{
  char *entrada = (char *)malloc(sizeof(char) * 70);
  scanf(" %s", entrada);
  while (!(strcmp(entrada, "FIM") == 0))
  {
    inserir(j[atoi(entrada)]);
    scanf(" %s", entrada);
  }
  free(entrada);
}
// Ler inserçoes
void altera(Jogador jogador[])
{
  int  num = 0,
       x = 0;
  scanf("%d", &x);
  Jogador removido;
  while (x > 0)
  {
    char *entrada = (char *)malloc(sizeof(char) * 10);
    scanf("%s", entrada);
    if (entrada[0] == 'R')
    {
      removido = remover();
      printf("(R) %s\n", removido.nome);
    }
    else if (entrada[0] == 'I')
    {
      scanf("%i",&num);
      inserir(jogador[num]);
    }
    x--;
  }
}
// Impressao
void imprimir(int n)
{
  for (int l = 0; l < n; l++)
  {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", l, array[l].nome, array[l].altura, array[l].peso, array[l].anoNascimento, array[l].universidade, array[l].cidadeNascimento, array[l].estadoNascimento);
  }
}

//Prototipos da Lista
void start()
{
  tam = 0;
}

void inserir(Jogador j)
{

  //validar insercao
  if (tam == MAXTAM)
  {
    for (int i = 0; i < MAXTAM - 1; i++)
    {
      array[i] = array[i + 1];
    }
    array[4] = j;
     printf("%d\n", media());
  }
  else
  {
    array[tam] = j;
    printf("%d\n", media());
    tam++;
  }
}

Jogador remover()
{
  Jogador resp;
  if (tam == 0)
  {
    printf("Error_404");
  }
  else
  {
    resp = array[0];
    for (int i = 0; i < MAXTAM - 1; i++)
    {
      array[i] = array[i + 1];
    }
     tam--;
  }
  return resp;
}

int media(){
   float altura = 0;
   int j  = 0;

   for(j = 0; j <= tam ;j++)
   {
      altura += array[j].altura;
   }
   if(j==6) j--;
   return round(altura/(j));
}