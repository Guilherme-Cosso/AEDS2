#include <stdio.h>
#include <stdlib.h>
#include <err.h>
#include <string.h>
#include <math.h>
#define TAM 5

#define bool short
#define true 1
#define false 0

// Struct Jogador
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

//TIPO CELULA ===================================================================
typedef struct Celula
{
   Jogador jogador; // Elemento inserido na celula.
   Celula *prox;    // Aponta a celula prox.
} Celula;

// INICIALIZADOR ...

//Leitura de Arquivos
void leeArq(Jogador jogador[]);
void separarDados(char dados[], int index, Jogador jogador[]);
void replace(char string[]);

//Leitura de Pubs 
void leeIds(Jogador j[]);

void altera(Jogador jogador[]);
//Lista
void start();
void tamList();
void inserir(Jogador j);
void iserirPri(Jogador j);
Jogador remover();
void mostrar();
int media();
void listar();
Celula *newCelula();
Celula *novaCelula(Jogador jogador);


int main(void)
{
   Jogador jogador[4000];
   //leitura do arquivo
   leeArq(jogador);
   // Start Lista
   start();
   // Leitura de ids;
   leeIds(jogador);
   // Remover Ou inserir  
   altera(jogador);
   // Imprimi
   listar();
}

// Metodos de Leitura de Arquivo e Instaciaçoes de Jogadores.

/**
* Le um arquivo e Salva em Um Vetor de Jogador.
* @param jogador[]
*/
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

/**
* Replace ',,' to 'nao informado'
* @param dados[]
*/
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
}

/**
* Separa os dados.
* @param dados[]
* @param index
* @param jogador[]
*/
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

void leeIds(Jogador j[])
{
   char *entrada = (char *)malloc(sizeof(char) * 70);
   scanf(" %s", entrada);
   iserirPri(j[atoi(entrada)]);
     scanf(" %s", entrada);

   while (!(strcmp(entrada, "FIM") == 0))
   {
      inserir(j[atoi(entrada)]);
      scanf(" %s", entrada);
   }
   free(entrada);
}



void altera(Jogador jogador[])
{
  int x = 0,
      num = 0;
  scanf("%d", &x);
  while (x != 0)
  {
    char *entrada = (char *)malloc(sizeof(char) * 10);
    scanf("%s", entrada);
    if (entrada[0] == 'R')
    {
      Jogador removido = remover();
      printf("(R) %s\n", removido.nome);
    }
    else if (entrada[0] == 'I')
    {
      scanf("%d", &num);
      inserir(jogador[num]);
    }
    x--;
  }
}

Celula *newCelula()
{
   Celula *nova = (Celula *)malloc(sizeof(Celula));
   return nova;
}

//FILA PROPRIAMENTE DITA ========================================================
Celula *primeiro = newCelula();
Celula *ultimo = primeiro;

/**
 * Cria uma fila sem elemetos.
 */
void start()
{
   Celula *tmp;
   for (int i = 1; i < TAM; i++)
   {
      tmp = newCelula();
      ultimo->prox = tmp;
      ultimo = ultimo->prox;
   }
   ultimo->prox = primeiro;
   ultimo = primeiro;
}

void tamList()
{
   int i = 0;
   Celula *tmp = primeiro;
   tmp = tmp->prox;
   while (primeiro != tmp)
   {
      tmp = tmp->prox;
      i++;
   }
   printf("%i", i);
}

void iserirPri(Jogador j)
{
   ultimo->jogador = j;
   printf("%d\n",media());
   ultimo = ultimo->prox;
}

void inserir(Jogador j)
{
   if (ultimo == primeiro)
   {
      primeiro = primeiro->prox;
   }
   ultimo->jogador = j;
   printf("%d\n",media());
   ultimo = ultimo->prox;
}

/**
 * Remove elemento da fila.
 * @return Elemento removido.
 */
Jogador remover()
{
   Jogador j = primeiro->jogador;
   primeiro = primeiro->prox;
   return j;
}


void mostrar(){
   Celula *tmp = ultimo->prox;
   printf("%s \n",tmp->jogador.nome);
   tmp = tmp->prox;
   while (tmp != ultimo->prox)
   {
      printf("%s \n",tmp->jogador.nome);
      tmp = tmp->prox;
   }  
}

int media(){
   float altura = 0;
   int i =1;
   Celula *tmp = primeiro;
   altura += tmp->jogador.altura;
   tmp = tmp->prox;
   while (tmp != ultimo->prox)
   {
      altura += tmp->jogador.altura;
      tmp = tmp->prox;
      i++;
   }
   return round(altura/i);
}

void listar(){
   int l=0;
   while (primeiro != ultimo)
   {
      printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", l, primeiro->jogador.nome, primeiro->jogador.altura, primeiro->jogador.peso, primeiro->jogador.anoNascimento, primeiro->jogador.universidade, primeiro->jogador.cidadeNascimento, primeiro->jogador.estadoNascimento);
      primeiro = primeiro->prox;   
      l++;
   }
}