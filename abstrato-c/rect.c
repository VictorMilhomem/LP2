#include "rect.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

typedef struct Rect { 
    int x, y;
    int w, h;
    void (* print) ;
    void (* drag) ;
 } Rect;

void rect_drag (Rect* this, int dx, int dy) { 
    this->x += dx;
    this->y += dy;
}

void rect_print (Rect* this) { 
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, this->x, this->y);
}
Rect* rect_new (void) {
    srand(time(NULL));
    Rect*   this  = malloc(sizeof(Rect));
    this->x = rand();
    this->y = rand();
    this->h = rand();
    this->w = rand();
    this->print =  rect_print;
    this->drag = rect_drag;
}
