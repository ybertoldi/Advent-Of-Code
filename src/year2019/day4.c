#include <stdio.h>

#define MIN 153517
#define MAX 630395
#define MAXDIGITS 15

int isvalid2(int num) {
  char numstr[MAXDIGITS] = {0};
  sprintf(numstr, "%d", num);
  int j, adj = 0, decrease = 0;

  for (j = 0; numstr[j + 1]; j++) {

    if (numstr[j] == numstr[j + 1] && numstr[j + 1] == numstr[j + 2]) {
      char v = numstr[j];
      while (numstr[j+2] == v) 
        j++;
      continue;
    }

    if (numstr[j] == numstr[j + 1])
      adj = 1;

    if (numstr[j] > numstr[j + 1]) {
      decrease = 1;
      break;
    }
  }
  return adj && !decrease;
}

int isvalid(int num) {
  char numstr[MAXDIGITS] = {0};
  sprintf(numstr, "%d", num);
  int j, adj = 0, decrease = 0;

  for (j = 0; numstr[j + 1]; j++) {
    if (numstr[j] == numstr[j + 1])
      adj = 1;
    if (numstr[j] > numstr[j + 1])
      decrease = 1;
  }
  return adj && !decrease;
}

int main() {
  int total1 = 0;
  int total2 = 0;
  printf("%d", isvalid2(123444));

  for (int i = MIN; i < MAX; i++) {
    if (isvalid(i))
      total1++;

    if (isvalid2(i))
      total2++;
  }

  printf("parte 1: %d\n", total1);
  printf("parte 2: %d\n", total2);
  return 0;
}
