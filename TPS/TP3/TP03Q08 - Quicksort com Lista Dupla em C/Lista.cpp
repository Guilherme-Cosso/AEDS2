#include <stdio.h>
#include <stdlib.h>
#include <err.h>
#include <string.h>
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

// Struct Celula
typedef struct CelulaDupla
{
   Jogador j;                // Elemento inserido na celula.
   struct CelulaDupla *prox; // Aponta a celula prox.
   struct CelulaDupla *ant;  // Aponta a celula anterior.
} CelulaDupla;

//Prototipo
//prototipos da "Main"
//Ler Arquivo ...
void leeArq(Jogador jogador[]);
void separarDados(char dados[], int index, Jogador jogador[]);
void replace(char string[]);
// Ler Pub.in
void leeIds(Jogador j[]);
//Prototipos da Lista
void start(Jogador j[]);
CelulaDupla *novaCelulaDupla(Jogador j);
void mostrar();
//Iserir
void inserirInicio(Jogador j);
void inserirFim(Jogador j);
void inserir(Jogador j, int pos);
//remover
Jogador removerInicio();
Jogador removerFim();
Jogador remover(int pos);
//Quick Sort 
void quick();
void quickSort(int esquerda, int direita, CelulaDupla *esq, CelulaDupla *dir);
Jogador acharPos(int tam);
void swap(CelulaDupla *a, CelulaDupla *b);
//imprimir
void imprimir(Jogador jogador[]);

int main(void)
{
   Jogador jogador[4000];
   // leitura do arquivo
   leeArq(jogador);
   // Start Lista
   start(jogador);
   // Leitura de ids;
   leeIds(jogador);
   // Quick sort Ordena por Estado de Nascimento Nome desespate
   quick();
   // Imprimi
   imprimir(jogador);
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
      inserirFim(j[atoi(entrada)]);
      scanf(" %s", entrada);
   }
   free(entrada);
}

CelulaDupla *novaCelulaDupla(Jogador j)
{
   CelulaDupla *nova = (CelulaDupla *)malloc(sizeof(CelulaDupla));
   nova->j = j;
   nova->ant = nova->prox = NULL;
   return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla *primeiro;
CelulaDupla *ultimo;

/**
 * Cria uma lista dupla.
 */
void start(Jogador j[])
{
   char *entrada = (char *)malloc(sizeof(char) * 70);
   scanf("%s", entrada);
   primeiro = novaCelulaDupla(j[atoi(entrada)]);
   ultimo = primeiro;
   free(entrada);
}
/**
 * Insere um elemento na primeira posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Jogador j)
{
   CelulaDupla *tmp = novaCelulaDupla(j);

   tmp->ant = primeiro;
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   if (primeiro == ultimo)
   {
      ultimo = tmp;
   }
   else
   {
      tmp->prox->ant = tmp;
   }
   tmp = NULL;
}

/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFim(Jogador j)
{
   ultimo->prox = novaCelulaDupla(j);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
}

/**
 * Remove um elemento da primeira posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerInicio()
{
   if (primeiro == ultimo)
   {
      errx(1, "Erro ao remover (vazia)!");
   }

   CelulaDupla *tmp = primeiro;
   primeiro = primeiro->prox;
   Jogador resp = primeiro->j;
   tmp->prox = primeiro->ant = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}

/**
 * Remove um elemento da ultima posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerFim()
{
   if (primeiro == ultimo)
   {
      errx(1, "Erro ao remover (vazia)!");
   }
   Jogador resp = ultimo->j;
   ultimo = ultimo->ant;
   ultimo->prox->ant = NULL;
   free(ultimo->prox);
   ultimo->prox = NULL;
   return resp;
}

/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho()
{
   int tamanho = 0;
   CelulaDupla *i;
   for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
      ;
   return tamanho;
}

/**
 * Insere um elemento em uma posicao especifica considerando que o 
 * primeiro elemento valido esta na posicao 0.
 * @param x int elemento a ser inserido.
 * @param pos int posicao da insercao.
 * @throws Exception Se <code>posicao</code> invalida.
 */
void inserir(Jogador jogador, int pos)
{

   int tam = tamanho();

   if (pos < 0 || pos > tam)
   {
      errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
   }
   else if (pos == 0)
   {
      inserirInicio(jogador);
   }
   else if (pos == tam)
   {
      inserirFim(jogador);
   }
   else
   {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla *i = primeiro;
      int j;
      for (j = 0; j < pos; j++, i = i->prox)
         ;

      CelulaDupla *tmp = novaCelulaDupla(jogador);
      tmp->ant = i;
      tmp->prox = i->prox;
      tmp->ant->prox = tmp->prox->ant = tmp;
      tmp = i = NULL;
   }
}

/**
 * Remove um elemento de uma posicao especifica da lista
 * considerando que o primeiro elemento valido esta na posicao 0.
 * @param posicao Meio da remocao.
 * @return resp int elemento a ser removido.
 * @throws Exception Se <code>posicao</code> invalida.
 */
Jogador remover(int pos)
{
   Jogador resp;
   int tam = tamanho();

   if (primeiro == ultimo)
   {
      errx(1, "Erro ao remover (vazia)!");
   }
   else if (pos < 0 || pos >= tam)
   {
      errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
   }
   else if (pos == 0)
   {
      resp = removerInicio();
   }
   else if (pos == tam - 1)
   {
      resp = removerFim();
   }
   else
   {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla *i = primeiro->prox;
      int j;
      for (j = 0; j < pos; j++, i = i->prox)
         ;

      i->ant->prox = i->prox;
      i->prox->ant = i->ant;
      resp = i->j;
      i->prox = i->ant = NULL;
      free(i);
      i = NULL;
   }

   return resp;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar()
{
   CelulaDupla *i;
   printf("[ ");
   for (i = primeiro; i != NULL; i = i->prox)
   {
      printf("%d ", i->j.id);
   }
   printf("] \n");
}

/**
 * Mostra os elementos da lista de forma invertida 
 * e separados por espacos.
 */
void mostrarInverso()
{
   printf("[ ");
   CelulaDupla *i;
   for (i = ultimo; i != primeiro; i = i->ant)
   {
      printf("%d ", i->j.id);
   }
   printf("] \n"); // Termina de mostrar.
}
Jogador acharPos(int tam)
{
   CelulaDupla *tmp = primeiro;
   for (int i = 0; i != tam; i++, tmp = tmp->prox)
      ;
   return tmp->j;
}

void quick()
{
   quickSort(0, tamanho(), primeiro, ultimo);
}

void quickSort(int esquerda, int direita, CelulaDupla *esq, CelulaDupla *dir)
{
   CelulaDupla *left = esq,
               *right = dir;
   int i = esquerda,
       j = direita,
       tamanho = (direita + esquerda) / 2;
   Jogador pivo = acharPos(tamanho);
   while (i <= j)
   {
      while (strcmp(left->j.estadoNascimento, pivo.estadoNascimento) < 0 || (strcmp(left->j.estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(left->j.nome, pivo.nome) < 0))
      {
         i++;
         left = left->prox;
      }
      while (strcmp(right->j.estadoNascimento, pivo.estadoNascimento) > 0 || (strcmp(right->j.estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(right->j.nome, pivo.nome) > 0))
      {
         j--;
         right = right->ant;
      }
      if (i <= j)
      {
         swap(left, right);
         i++;
         j--;
         left = left->prox;
         right = right->ant;
      }
   }
   if (esquerda < j)
      quickSort(esquerda, j, esq, right);
   if (i < direita)
      quickSort(i, direita, left, dir);
}

void swap(CelulaDupla *a, CelulaDupla *b)
{
   Jogador tmp = a->j;
   a->j = b->j;
   b->j = tmp;
}

void imprimir(Jogador jogador[])
{
   for (CelulaDupla *i = primeiro; i != NULL; i = i->prox)
   {
      printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", i->j.id, i->j.nome, i->j.altura, i->j.peso, i->j.anoNascimento, i->j.universidade, i->j.cidadeNascimento, i->j.estadoNascimento);
   }
}