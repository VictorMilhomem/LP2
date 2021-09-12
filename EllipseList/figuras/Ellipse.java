package figuras;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse{
    int x, y;
    int w, h;
    Color c, bkg;

    public Ellipse (int x, int y, int w, int h, Color c, Color bkg) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
        this.bkg = bkg;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // background
        g2d.setColor(this.bkg);
        g2d.fillOval(this.x, this.y, this.w, this.h);

        // contorno
        g2d.setColor(this.c);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}