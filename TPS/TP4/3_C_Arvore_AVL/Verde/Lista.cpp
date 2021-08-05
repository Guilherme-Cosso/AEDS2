#include <stdio.h>
#include <stdlib.h>
#include <err.h>
#include <string.h>
#include <math.h>

#define TAM 5
#define bool short
#define true 1
#define false 0
# define max(a,b) \
   ({ __typeof__ (a) _a = (a); \
       __typeof__ (b) _b = (b); \
     _a > _b ? _a : _b; })



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
typedef struct No {
   Jogador jogador;
	struct No *esq, *dir;
   int nivel;
} No;

int getNivel(No* no) {
   return (no == NULL) ? 0 : no->nivel;
}

void setNivel(No* no) {
   no->nivel = 1 + max(getNivel(no->esq),getNivel(no->dir));
}

/**
 * Criacao do novo no
 * @param elemento Conteudo do no.
 */
No* novoNo(Jogador jogador) {
   No* novo = (No*) malloc(sizeof(No));
   novo->jogador = jogador;
   novo->esq = NULL;
   novo->dir = NULL;
   novo->nivel = 1;
   return novo;
}

// INICIALIZADOR ...

//Leitura de Arquivos
void leeArq(Jogador jogador[]);
void separarDados(char dados[], int index, Jogador jogador[]);
void replace(char string[]);

//Leitura de Pubs 
void leeIds(Jogador j[]);

//Arvore
void start();
No *newNo();
//Inserir
void inserir(Jogador x);
void inserirRec(Jogador x, No** i);
No* balancear(No* no);
No* rotacionarDir(No* no);
No* rotacionarEsq(No* no);
//Caminhar na Arvore(PRE,CENTRAL,POS).
void caminharPre(); 
void caminharPreRec(No* i);
void caminharCentral();
void caminharCentralRec(No* i);
void caminharPos();
void caminharPosRec(No* i);
// Pes
void pesq(Jogador x[]);
bool pesquisar(char *x);
bool pesquisarRec(char *x, No* i);

int main(void)
{
   Jogador jogador[4000];
   //leitura do arquivo
   leeArq(jogador);
   // Start Lista
   start();
   // Leitura de ids;
   leeIds(jogador);
   // Imprimir
   pesq(jogador);
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
   inserir(j[atoi(entrada)]);
     scanf(" %s", entrada);

   while (!(strcmp(entrada, "FIM") == 0))
   {
      inserir(j[atoi(entrada)]);
      scanf(" %s", entrada);
   }
   free(entrada);
}

void pesq(Jogador j[])
{
   bool resp = false; 
   char *entrada = (char *)malloc(sizeof(char) * 70);
   fgets(entrada, 70, stdin);
   strtok(entrada,"\n");
   fgets(entrada, 70, stdin);
   strtok(entrada,"\n");
   while (!(strcmp(entrada, "FIM") == 0))
   { 
      printf("%s raiz ",entrada);
      resp = pesquisar(entrada);
      if(resp==true){
         printf("SIM\n");
      }else
      {
         printf("NAO\n");
      }
      fgets(entrada, 70, stdin);
      strtok(entrada,"\n");
   }
   free(entrada);
}
//Arvore PROPRIAMENTE DITA ========================================================
// Variavel global
No* raiz;

/**
 * Criar arvore binaria.
 */
void start() {
   raiz = NULL;
}

/**
 * Metodo publico iterativo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(char *x) {
   return pesquisarRec(x, raiz);
}

/**
 * Metodo privado recursivo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @param i No em analise.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisarRec(char *x, No* i) {
   bool resp;
   if (i == NULL) {
      resp = false;

   } else if (strcmp(x,i->jogador.nome)==0) {
      resp = true;

   } else if (strcmp(x,i->jogador.nome) < 0) {
     printf("esq ");
      resp = pesquisarRec(x, i->esq);

   } else {
      printf("dir ");
      resp = pesquisarRec(x, i->dir);
   }
   return resp;
}

/**
 * Metodo publico iterativo para exibir elementos.
 */
void caminharCentral() {
   printf("[ ");
   caminharCentralRec(raiz);
   printf("]\n");
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void caminharCentralRec(No* i) {
   if (i != NULL) {
      caminharCentralRec(i->esq);
      printf("%s ", i->jogador.nome);
      caminharCentralRec(i->dir);
   }
}

/**
 * Metodo publico iterativo para exibir elementos.
 */
void caminharPre() {
   printf("[ ");
   caminharPreRec(raiz);
   printf("]\n");
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void caminharPreRec(No* i) {
   if (i != NULL) {
      printf("%s ", i->jogador.nome);
      caminharPreRec(i->esq);
      caminharPreRec(i->dir);
   }
}

/**
 * Metodo publico iterativo para exibir elementos.
 */
void caminharPos() {
   printf("[ ");
   caminharPosRec(raiz);
   printf("]\n");
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void caminharPosRec(No* i) {
   if (i != NULL) {
      caminharPosRec(i->esq);
      caminharPosRec(i->dir);
      printf("%s ", i->jogador.nome);
   }
}

/**
 * Metodo publico iterativo para inserir elemento.
 * @param x Elemento a ser inserido.
 */
void inserir(Jogador x) {
   inserirRec(x, &raiz);
}

/**
 * Metodo privado recursivo para inserir elemento.
 * @param x Elemento a ser inserido.
 * @param i No** endereco do ponteiro No
 */

void inserirRec(Jogador x, No** i) {
   if (*i == NULL) {
      *i = novoNo(x);

   } else if (strcmp(x.nome,(*i)->jogador.nome) < 0) {
      inserirRec(x, &((*i)->esq));

   } else if (strcmp(x.nome,(*i)->jogador.nome) > 0) {
      inserirRec(x, &((*i)->dir));

   } else {
      errx(1, "Erro ao inserir!");
   }
   *i= balancear((*i));
}

No* balancear(No* no){
      if(no != NULL){
         int fator = getNivel(no->dir) - getNivel(no->esq);

         //Se balanceada
         if (abs(fator) <= 1){
            setNivel(no);

         //Se desbalanceada para a direita
         }else if (fator == 2){

            int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

            //Se o filho a direita tambem estiver desbalanceado
            if (fatorFilhoDir == -1) {
               no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);

         //Se desbalanceada para a esquerda
         }else if (fator == -2){

            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1) {
               no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);

         }else{
            printf("ERRO");
         }
      }

      return no;
   }

   No* rotacionarDir(No* no) {
     
      No* noEsq = no->esq;
      No* noEsqDir = noEsq->dir;

      noEsq->dir = no;
      no->esq = noEsqDir;

      setNivel(no);
      setNivel(noEsq);

      return noEsq;
   }

   No* rotacionarEsq(No* no) {
      No* noDir = no->dir;
      No* noDirEsq = noDir->esq;

      noDir->esq = no;
      no->dir = noDirEsq;

      setNivel(no);
      setNivel(noDir);
      return noDir;
   }