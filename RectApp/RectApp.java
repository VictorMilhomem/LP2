public class RectApp {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 10,10);
        r1.print();
        System.out.println("Area do retangulo: " + r1.area());
        System.out.println("Pos. depois de arrastar:");
        r1.drag(100, 100);
        r1.print();
    }
}
class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    int area()
    {
        return this.w * this.h ;
    }

    void drag(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
}