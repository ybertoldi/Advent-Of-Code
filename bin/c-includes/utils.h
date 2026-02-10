#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
  char *buf;
  size_t count;
} StringView;

long filelen(FILE *f){
  long len ;
  fseek(f, 0, SEEK_END);
  len = ftell(f);
  fseek(f, 0, SEEK_SET);
  return len;
}

StringView read_entire_file(const char* fname){
  StringView sv = {0};
  FILE *f = fopen(fname, "r");
  long len = filelen(f);

  sv.buf = calloc(len + 1, 1);
  sv.count = len;

  fread(sv.buf, len, 1, f);
  fclose(f);
  return sv;
}

