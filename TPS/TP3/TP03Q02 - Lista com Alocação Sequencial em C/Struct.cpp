#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DELIM "\t ,"
#define MAXTAM 200
#define bool short
#define true 1
#define false 0

int array[MAXTAM];
int tam;

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

//prototipos da "Main"
void leeArq(Jogador jogador[]);
void separarDados(char dados[], int index, Jogador jogador[]);
void replace(char string[]);
void leeIds();
void altera(Jogador Jogador[]);

//Prototipos da Lista
void start();
//Iserir
void inserirInicio(int x);
void inserirFim(int x);
void inserir(int x, int pos);
//remover
int removerInicio();
int removerFim();
int remover(int pos);

//imprimir
void imprimir(Jogador jogador[], int ids[], int n);

int main(void)
{
  Jogador jogador[4000];
  // leitura do arquivo
  leeArq(jogador);
  // Start Lista
  start();
  // Leitura de ids;
  leeIds();
  // Printar
  altera(jogador);
  imprimir(jogador, array, tam);
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
void leeIds()
{
  char *entrada = (char *)malloc(sizeof(char) * 70);
  scanf(" %s", entrada);
  while (!(strcmp(entrada, "FIM") == 0))
  {
    inserirFim(atoi(entrada));
    scanf(" %s", entrada);
  }
  free(entrada);
}
// Ler inserçoes
void altera(Jogador jogador[])
{
  int x = 0;
  scanf("%d", &x);
  while (x > 0)
  {
    char *entrada = (char *)malloc(sizeof(char) * 10);
    int pos = 0,
        num = 0;
    scanf("%s", entrada);
    if (entrada[0] == 'R')
    {
      int removido;
      if (entrada[1] == 'I')
        removido = removerInicio();
      else if (entrada[1] == 'F')
        removido = removerFim();
      else
      {
        scanf("%d", &pos);
        removido = remover(pos);
      }
      printf("(R) %s\n", jogador[removido].nome);
    }
    if (entrada[0] == 'I')
    {
      scanf("%d", &num);

      if (entrada[1] == 'I')
        inserirInicio(num);
      else if (entrada[1] == 'F')
        inserirFim(num);
      else
      {
        pos = num;
        scanf("%d", &num);
        inserir(num, pos);
      }
    }
    x--;
  }
}
// Impressao
void imprimir(Jogador jogador[], int ids[], int n)
{
  for (int l = 0; l < n; l++)
  {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", l, jogador[ids[l]].nome, jogador[ids[l]].altura, jogador[ids[l]].peso, jogador[ids[l]].anoNascimento, jogador[ids[l]].universidade, jogador[ids[l]].cidadeNascimento, jogador[ids[l]].estadoNascimento);
  }
}

//Prototipos da Lista
void start()
{
  tam = 0;
}

void inserirInicio(int x)
{
  int i;

  //validar insercao
  if (tam >= MAXTAM)
  {
    printf("Erro ao inserir!");
    exit(1);
  }

  //levar elementos para o fim do array
  for (i = tam; i > 0; i--)
  {
    array[i] = array[i - 1];
  }

  array[0] = x;
  tam++;
}

void inserirFim(int x)
{

  //validar insercao
  if (tam >= MAXTAM)
  {
    printf("Erro ao inserir!  %d == %d", tam, MAXTAM);
    exit(1);
  }

  array[tam] = x;
  tam++;
}

void inserir(int x, int pos)
{

  //validar insercao
  if (tam >= MAXTAM || pos < 0 || pos > tam)
  {
    printf("Erro ao inserir!");
    exit(1);
  }

  //levar elementos para o fim do array
  for (int i = tam; i > pos; i--)
  {
    array[i] = array[i - 1];
  }

  array[pos] = x;
  tam++;
}

int removerInicio()
{
  int i, resp;

  //validar remocao
  if (tam == 0)
  {
    printf("Erro ao remover!");
    exit(1);
  }

  resp = array[0];
  tam--;

  for (i = 0; i < tam; i++)
  {
    array[i] = array[i + 1];
  }

  return resp;
}

int removerFim()
{

  //validar remocao
  if (tam == 0)
  {
    printf("Erro ao remover!");
    exit(1);
  }

  return array[--tam];
}

int remover(int pos)
{
  int i, resp;

  //validar remocao
  if (tam == 0 || pos < 0 || pos >= tam)
  {
    printf("Erro ao remover!");
    exit(1);
  }

  resp = array[pos];
  tam--;

  for (i = pos; i < tam; i++)
  {
    array[i] = array[i + 1];
  }

  return resp;
}
