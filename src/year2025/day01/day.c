#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
  char buf[512];
  FILE *f = fopen("input.txt", "r");

  int pos = 50;
  int pwd = 0;
  while (fgets(buf, sizeof(buf), f)) {
    buf[strlen(buf) - 1] = '\0';
    char direction = buf[0];
    int  n = atoi(buf + 1);

    switch (direction) {
      case 'L':
        pos = (pos - n) % 100;
        if (pos < 0) pos += 100;
        break;

      case 'R':
        pos = (pos + n) % 100;
        break;

      default:
        printf("Unexpected entry '%s'\n", buf);
        exit(1);
        break;
    }
    printf("read: %s \e[15G|     pos: %d\n", buf, pos);

    if (pos == 0)
      pwd++;
  }

  printf("res: %d\n", pwd);
  return 0;
}
