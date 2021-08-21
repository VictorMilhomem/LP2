#include <stdio.h>


typedef struct{
    int x, y;
    int x_radius, y_radius;
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
    printf("Elipse com raio de tamanho %d no eixo X,\ne raio de tamanho %d no eixo Y,\nna posição (%d, %d).\n",
     e->x_radius, e->y_radius, e->x, e->y);

}


