#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../c-includes/utils.h"


// se liga na recursao
int calc_fuel(int mass){
  int res = (mass/3) - 2;

  if (res <= 0)
    return 0;
  return  res + calc_fuel(res);
}

int main(){
  char *buf, *res;
  int n, mass[300], mc;
  mc = 0;

  FILE *f = fopen("inputs/day1.txt", "r");
  n = filelen(f);
  buf = malloc(n);
  fread(buf, 1, n, f);

  // PT 1
   res = strtok(buf, "\n"); 
   while (res) { 
     mass[mc++] = (atoi(res)/3) - 2; 
     res = strtok(NULL, "\n"); 
   } 

   int soma = 0; 
   for (int i = 0; i < mc; i++)  
     soma += mass[i]; 
   printf("parte 1 = %d\n", soma); 

  // PT 2
  mc = 0;
  res = strtok(buf, "\n");
  while (res) {
    mass[mc++] = calc_fuel(atoi(res));
    res = strtok(NULL, "\n");
  }

  soma = 0;
  for (int i = 0; i < mc; i++)
    soma += mass[i];
  printf("parte 2: %d\n", soma);
  return 0;
}
