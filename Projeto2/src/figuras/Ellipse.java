package figuras;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure
{
    public Ellipse (int x, int y, int w, int h, Color c, Color bkg) {
        super(x, y, w, h, c, bkg);
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint(Graphics g, boolean selected) {
        Graphics2D g2d = (Graphics2D) g;

        if(selected)
        {
            // Cor quando o objeto esta selecionado
            g2d.setPaint(Color.DARK_GRAY);
            g2d.fillOval(this.x-3, this.y-3, this.w+6, this.h+6);
        }

        // background
        g2d.setColor(this.bkg);
        g2d.fillOval(this.x, this.y, this.w, this.h);


        // contorno
        g2d.setPaint(this.c);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }

}