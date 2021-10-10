#include <stdio.h>
#include <stdlib.h>

#define MAX_FIGURES 6

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    Color fg, bg;
    void (* print) (struct Figure*);
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}


////////////////////////////////////////////////////////////////////////////////
typedef struct {
    Figure super;
    int w, h;
    int x_positions[5];
    int y_positions[5];
}Pentagon;

void Pentagon_print(Pentagon* this){
    Figure* sup = (Figure*) this;
    printf("Pentagono de tamanho (%d, %d) na posicao (%d, %d).\n",
            this->w, this->h, sup->x, sup->y);
    printf("Pentagono nos pontos\n");
    for (int i = 0; i < 5; i++){
        printf("(%d, %d)\n",this->x_positions[i], this->y_positions[i]);
    }

}

Pentagon* pentagon_new(int x, int y, int w, int h){
    Pentagon* this = malloc(sizeof(Pentagon));
    Figure* sup = (Figure*)this;
    sup->print = (Figure_Print) Pentagon_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    this->x_positions[0] = x; this->x_positions[1] = x-w;
    this->x_positions[2] = x-(int)w/2; this->x_positions[3] = x+(int)w/2;
    this->x_positions[4] = x+w;

    this->y_positions[0] = y; this->y_positions[1] = y+(int)h/2;
    this->y_positions[2] = y+h; this->y_positions[3] = y+h;
    this->y_positions[4] = y+(int)h/2;

}

//////////////////////////////////////////////////////////////////////////////////

typedef struct{
    Figure super;
    int w, h;
    int x_positions[3];
    int y_positions[3];
}Triangle;

void Triangle_print(Triangle* this){
    Figure* sup = (Figure*) this;
    printf("Triangulo de tamanho (%d, %d) na posicao (%d, %d).\n",
            this->w, this->h, sup->x, sup->y);
    printf("Triangulo nos pontos\n");
    for (int i = 0; i < 3; i++){
        printf("(%d, %d)\n",this->x_positions[i], this->y_positions[i]);
    }
}

Triangle* triangle_new(int x, int y, int w, int h){
    Triangle* this = malloc(sizeof(Triangle));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print)Triangle_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;

    this->x_positions[0] = x; this->x_positions[1] =  x-(int)w/2;
    this->x_positions[2] = x+w;

    this->y_positions[0] = y; this->y_positions[1] = y+h;
    this->y_positions[2] = y+h;

}


///////////////////////////////////////////////////////////////////////////////

int main (void) {
    Figure* figs[MAX_FIGURES] = {
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(210,110,305,130),
        (Figure*) pentagon_new(210, 110, 50, 50),
        (Figure*) triangle_new(100, 100, 50, 50)
    };

    ///

    for (int i= 0; i < MAX_FIGURES; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (int i = 0; i < MAX_FIGURES; i++) {
        free(figs[i]);
    }
    return 0;
}