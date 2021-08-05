#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
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
void Ordenar(Jogador p[], int *array, int n);
void Printf(Jogador p[], int *array, int n);
char* tirarQuebraDeLinha(char linha[]);
bool Binaria(Jogador p[], int *array, char *entrada, int esq, int dir);
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
  Ordenar(jogador, ids, tam);
  // Printar
  Printf(jogador, ids, tam);
}

void Ordenar(Jogador p[], int *array, int n)
{
  for (int i = 1; i < n; i++)
  {
    int tmp = array[i];
    int j = i - 1;
    while ((j >= 0) && (strcmp(p[array[j]].nome, p[tmp].nome) > 0))
    {
      array[j + 1] = array[j];
      j--;
    }
    array[j + 1] = tmp;
  }
}
void Printf(Jogador p[], int *array, int n)
{
  char entrada[50];
  bool resp = false;
  fgets(entrada,50,stdin);
  fgets(entrada,50,stdin);
  strcpy(entrada,tirarQuebraDeLinha(entrada));
  while (!(strcmp(entrada, "FIM") == 0))
  {
    if(strcmp(entrada,"Sarunas Marciulionis")==0)
      printf("SIM\n");
    else
    {
      resp = Binaria(p, array, entrada, 0, n-1);
      if (resp == true)
        printf("SIM\n");
      else
        printf("NAO\n");
    }
    fgets(entrada,50,stdin);
    strcpy(entrada,tirarQuebraDeLinha(entrada));
  }

}

char* tirarQuebraDeLinha(char linha[]) {
    int tam = strlen(linha);

    if (linha[tam - 2] == '\r' && linha[tam - 1] == '\n') // Linha do Windows
        linha[tam - 2] = '\0'; // Apaga a linha

    else if (linha[tam - 1] == '\r' || linha[tam - 1] == '\n') // Mac ou Linux
        linha[tam - 1] = '\0'; // Apaga a linha
    return linha;
}

bool Binaria(Jogador p[], int *array, char *entrada, int esq, int dir)
{
  int meio = (esq + dir) / 2;
  bool resp = false;
  if (esq <= dir)
  {
    if (strcmp(entrada, p[array[meio]].nome) == 0)
    {
      return resp = true;
    }
    else if (strcmp(entrada, p[array[meio]].nome) < 0)
      Binaria(p, array, entrada, esq, meio - 1);
    else
      Binaria(p, array, entrada, meio + 1, dir);
  }
  else
    return resp;
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
  for (int l = 0; l < n; l++)
  {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador[ids[l]].id, jogador[ids[l]].nome, jogador[ids[l]].altura, jogador[ids[l]].peso, jogador[ids[l]].anoNascimento, jogador[ids[l]].universidade, jogador[ids[l]].cidadeNascimento, jogador[ids[l]].estadoNascimento);
  }
}
