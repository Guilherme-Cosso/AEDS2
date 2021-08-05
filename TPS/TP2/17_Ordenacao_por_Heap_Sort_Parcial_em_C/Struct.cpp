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
void tiraPos0(int *array, int *ids, int n);
int getMaiorFilho(int i, int tamHeap, int *array, Jogador p[]);
void recostruir(int tamHeap,Jogador p[], int *ids);
void Ordenar(Jogador p[], int *array, int n);
void constroi(int tamHeap,Jogador p[],int *ids);
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
  imprimir(jogador, ids, tam);
}

void Ordenar(Jogador p[], int *array, int n)
{
  int ids[n + 1];
  int tamHeap = n;
  tiraPos0(array, ids, n);

  for (int tamHeap = 2; tamHeap < 11; tamHeap++)
  {
    constroi(tamHeap, p, ids);
  }
  while(tamHeap > 1){
     swap(ids+1,ids+tamHeap--);
     recostruir(tamHeap,p,ids);
  }
  for(int i=0;i<11;i++){
    array[i] = ids[i+1];
  }  
}

void tiraPos0(int *array, int *ids, int n)
{
  ids[0] = -1;
  for (int i = 0; i < n; i++)
  {
    ids[i + 1] = array[i];
  }
}

void constroi(int tamHeap,Jogador p[],int *ids){
    for (int i = tamHeap; i > 1 && ( p[ids[i]].altura > p[ids[i / 2]].altura || ( p[ids[i]].altura == p[ids[i / 2]].altura && strcmp(p[ids[i]].nome,p[ids[i / 2]].nome)>0)); i /= 2) {
            swap(ids+i,ids+ i / 2);
    }
}

void swap(int *a, int *b)
{
  int temp = *a;
  *a = *b;
  *b = temp;
}

void recostruir(int tamHeap,Jogador p[], int *ids){
  int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap, ids, p);
            if (p[ids[i]].altura < p[ids[filho]].altura ||(p[ids[i]].altura == p[ids[filho]].altura && strcmp(p[ids[i]].nome,p[ids[filho]].nome)<0)){
                swap(ids+i, ids+filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
}
int getMaiorFilho(int i, int tamHeap, int *array, Jogador p[]) {
        int filho;
        if (2 * i == tamHeap ||( p[array[2 * i]].altura > p[array[2 * i + 1]].altura || ( p[array[2 * i]].altura == p[array[2 * i + 1]].altura && strcmp(p[array[2 * i]].nome,p[array[2 * i + 1]].nome)>0))){
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
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
