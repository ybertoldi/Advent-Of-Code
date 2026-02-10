#include "../c-includes/cvector.h"
#include "../c-includes/utils.h"
#include <stdio.h>
#include <string.h>

int calc(cvector(int) v, int noun, int verb) {
  v[1] = noun;
  v[2] = verb;
  
  int i = 0, l, r, run = 1;
  while (run)
    switch (v[i]) {
    case 1:
      l = v[v[i + 1]];
      r = v[v[i + 2]];
      v[v[i + 3]] = l + r;
      i += 4;
      break;
    case 2:
      l = v[v[i + 1]];
      r = v[v[i + 2]];
      v[v[i + 3]] = l * r;
      i += 4;
      break;
    case 99:
      run = 0;
      break;
    }

  return v[0];
}

int main() {
  StringView sv = read_entire_file("inputs/day2.txt");
  cvector(int) v = NULL;
  cvector(int) cpy = NULL;

  char *res = strtok(sv.buf, ",");
  while (res) {
    cvector_push_back(v, atoi(res));
    res = strtok(NULL, ",");
  }

  cvector_copy(v, cpy);
  int sol1 = calc(cpy, 12, 2);
  printf("parte 1: %d\n", sol1);

  int a = 0, b = 0;
  while (a <= 99) {
    cvector_copy(v, cpy);
    if (calc(cpy, a, b) == 19690720)
      break;

    if (b != 99)
      b++;
    else {
      b = 0;
      a++;
    }
  }

  printf("parte2: %d%d\n", a, b);
}
