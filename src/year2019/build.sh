#!/usr/bin/bash
CC=gcc
CFLAGS="-Wall -Wextra -ggdb"

if [ "$2" ]; then
  $CC $CFLAGS "$1" && gdb ./a.out --tui
else
  $CC $CFLAGS "$1" && ./a.out
fi

