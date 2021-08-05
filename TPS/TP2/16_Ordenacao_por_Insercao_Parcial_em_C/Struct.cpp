#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define DELIM "\t ,"

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

//prototipos
int leeIds(int *v);
void leeArq(Jogador jogador[]);
void replace(char string[]);
void separarDados(char dados[], int index, Jogador jogador[]);
void imprimir(Jogador jogador[], int ids[], int n);
void Ordenar(Jogador p[], int *array, int n,int k);
void swap(int *a, int *b);

int main(void)
{

  Jogador jogador[4000];
  int is[500];
  // Leitura de ids;
  int tam = leeIds(is);
  int ids[tam];
  for (int i = 0; i < tam; i++)
    ids[i] = is[i];
  // leitura do arquivo
  leeArq(jogador);
  // Oredenar
  Ordenar(jogador,ids, tam-1,10);
  // Printar
  imprimir(jogador, ids, tam);
}

void Ordenar(Jogador p[], int *array, int n, int k)
{
  for (int i = 1; i < n; i++)
  {
    int tmp = array[i],
        j = (i < k) ? i - 1 : k - 1;
    while (  (j >= 0)  && ( p[array[j]].anoNascimento > p[tmp].anoNascimento || ( p[array[j]].anoNascimento == p[tmp].anoNascimento && strcmp( p[array[j]].nome,p[tmp].nome) > 0))){
      array[j + 1] = array[j];
      j--;
    }
    array[j + 1] = tmp;
  }
}

void swap(int *a, int *b)
{
  int temp = *a;
  *a = *b;
  *b = temp;
}

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

int leeIds(int *v)
{
  char *entrada = (char *)malloc(sizeof(char) * 70);
  int i = 0;
  scanf(" %s", entrada);
  while (!(strcmp(entrada, "FIM") == 0))
  {
    *(v + i) = atoi(entrada); //array[i]
    i++;
    scanf(" %s", entrada);
  }
  free(entrada);
  return i;
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

void imprimir(Jogador jogador[], int ids[], int n)
{
  n = 10;
  for (int l = 0; l < n; l++)
  {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador[ids[l]].id, jogador[ids[l]].nome, jogador[ids[l]].altura, jogador[ids[l]].peso, jogador[ids[l]].anoNascimento, jogador[ids[l]].universidade, jogador[ids[l]].cidadeNascimento, jogador[ids[l]].estadoNascimento);
  }
}
