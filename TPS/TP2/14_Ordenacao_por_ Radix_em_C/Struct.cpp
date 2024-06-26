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
int getMax(int arr[], int n);
void countSort(int arr[], int n, int exp);
void radixsort(int *arr, int n);
void Ordenar(Jogador p[], int *array, int n);
void insercaoPorCor(Jogador p[], int *array, int n, int cor, int h);
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
  int n = sizeof(ids) / sizeof(ids[0]);
  // leitura do arquivo
  leeArq(jogador);
  // Oredenar
  radixsort(ids, tam);
  // Printar
  imprimir(jogador, ids, tam);
}

int getMax(int arr[], int n)
{
    int mx = arr[0];
    for (int i = 1; i < n; i++)
        if (arr[i] > mx)
            mx = arr[i];
    return mx;
}
 
// A function to do counting sort of arr[] according to
// the digit represented by exp.
void countSort(int arr[], int n, int exp)
{
    int output[n]; // output array
    int i, count[10] = { 0 };
 
    // Store count of occurrences in count[]
    for (i = 0; i < n; i++)
        count[(arr[i] / exp) % 10]++;
 
    // Change count[i] so that count[i] now contains actual
    //  position of this digit in output[]
    for (i = 1; i < 10; i++)
        count[i] += count[i - 1];
 
    // Build the output array
    for (i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }
 
    // Copy the output array to arr[], so that arr[] now
    // contains sorted numbers according to current digit
    for (i = 0; i < n; i++)
        arr[i] = output[i];
}
 
// The main function to that sorts arr[] of size n using
// Radix Sort
void radixsort(int *arr, int n)
{
    int m = getMax(arr, n);
    for (int exp = 1; m / exp > 0; exp *= 10)
        countSort(arr, n, exp);
}
 


void Ordenar(Jogador p[], int *array, int n)
{
  int i, j;
  for (i = (n - 1); i > 0; i--)
  {
    for (j = 0; j < i; j++)
    {
      if (p[array[j]].peso > p[array[j + 1]].peso || (p[array[j]].peso == p[array[j + 1]].peso && strcmp(p[array[j]].nome, p[array[j + 1]].nome) > 0))
      {
        swap(array + j, array + (j + 1));
      }
    }
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
  for (int l = 0; l < n; l++)
  {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador[ids[l]].id, jogador[ids[l]].nome, jogador[ids[l]].altura, jogador[ids[l]].peso, jogador[ids[l]].anoNascimento, jogador[ids[l]].universidade, jogador[ids[l]].cidadeNascimento, jogador[ids[l]].estadoNascimento);
  }
}
