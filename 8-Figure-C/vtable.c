#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX_FIGURES 6
#define PI 3.14

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef int  (* Figure_Area)  (struct Figure*);
typedef int (* Figure_Perimeter) (struct Figure*);

typedef struct {
    void (* print) (struct Figure*);
    int  (* area)  (struct Figure*);
    int (* perimeter) (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y;
    Color fg, bg;
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////


typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d) e area %d.\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup));
}

int rect_perimeter(Rect* this){
    Figure* sup = (Figure*) this;
    return 2*this->w + 2*this->h;
}

int rect_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

Figure_vtable rect_vtable = {
    (Figure_Print) rect_print,
    (Figure_Area)  rect_area,
    (Figure_Perimeter) rect_perimeter
};

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->vtable = &rect_vtable;
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

void ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d) e area %d.\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup));
}

int ellipse_perimeter(Ellipse* this){
    Figure* sup = (Figure*) this;
    double res = pow(this->h/2, 2) + pow(this->w/2, 2);
    return (int)(2*PI * sqrt(res/2));
}

int ellipse_area (Ellipse* this) {
    Figure* sup = (Figure*) this;
    return (int)(PI * this->w/2 * this->h/2);
}

Figure_vtable ellipse_vtable = {
    (Figure_Print) ellipse_print,
    (Figure_Area)  ellipse_area,
    (Figure_Perimeter) ellipse_perimeter
};

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
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

int Pentagon_area(Pentagon* this){
    Figure* sup = (Figure*) this;
    return (int)(5 * (this->h/2 * this->w/2)/2);
}

int Pentagon_perimeter(Pentagon* this){
    Figure* sup = (Figure*) this;
    int a = (int)sqrt(pow(this->h/2, 2) + pow(this->w, 2));
    int c = this->x_positions[3] - this->x_positions[2];
    return 4*a + c;
}

Figure_vtable pentagon_vtable = {
    (Figure_Print) Pentagon_print,
    (Figure_Area) Pentagon_area,
    (Figure_Perimeter) Pentagon_perimeter
};

Pentagon* pentagon_new(int x, int y, int w, int h){
    Pentagon* this = malloc(sizeof(Pentagon));
    Figure* sup = (Figure*)this;
    sup->vtable = &pentagon_vtable;
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

int Triangle_perimeter(Triangle* this){
    Figure* sup = (Figure*)this;
    int a = sqrt(pow(this->h, 2) + pow(this->x_positions[0]- this->x_positions[1], 2));
    int b = sqrt(pow(this->h, 2) + pow(this->x_positions[2]- this->x_positions[0], 2));
    return a + b + this->w;
}

int Triangle_area(Triangle* this){
    Figure* sup = (Figure*) this;
    return (int)(this->w * this->h /2);
}

Figure_vtable triangle_vtable = {
    (Figure_Print) Triangle_print,
    (Figure_Area) Triangle_area,
    (Figure_Perimeter) Triangle_perimeter
};

Triangle* triangle_new(int x, int y, int w, int h){
    Triangle* this = malloc(sizeof(Triangle));
    Figure* sup = (Figure*) this;
    sup->vtable = &triangle_vtable;
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

void main (void) {
    Figure* figs[MAX_FIGURES] = {
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(210,110,305,130),
        (Figure*) pentagon_new(110, 110, 50, 50),
        (Figure*) triangle_new(100, 100, 50, 50)
    };

    ///

    for (int i = 0; i < MAX_FIGURES; i++) {
        figs[i]->vtable->print(figs[i]);
        printf("Area: %d\n", figs[i]->vtable->area(figs[i]));
        printf("Perimetro: %d\n", figs[i]->vtable->perimeter(figs[i]));
    }

    ///

    for (int i = 0; i < MAX_FIGURES; i++) {
        free(figs[i]);
    }
}