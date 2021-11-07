#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

int main(int argc, char **argv){
    
    Rect* test = rect_new();
    rect_print(test);
    rect_drag(test, 10, 20);
    rect_print(test);
    free(test);
    
    return 0;
}