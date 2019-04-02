#include "stdio.h"
int main(int argc, char **argv) {
  freopen("largeFile.xml", "w", stdout);
  for (int i = 0; i < 79999999; ++i) {
    printf("<A%d>\n", i);
  }
}