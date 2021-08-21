#include <stdio.h>


typedef struct{
    int x, y;
    int w, h;
}Ellipse;

void print(Ellipse *e);

int main(int argc, char**argv)
{
    Ellipse e = {100, 100, 20, 30};

    print(&e);
    return 0;
}


void print(Ellipse *e)
{
    printf("Elipse de largura %d, altura %d,\nna posição (%d, %d).\n",
     e->w, e->h, e->x, e->y);

}


