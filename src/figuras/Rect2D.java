package figuras;
import java.awt.*;


public class Rect2D
{
    private int x, y;
    private int h, w;
    private Color c, bkg;
    public Rect2D(int x, int y, int w, int h, Color c, Color bkg)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.c = c;
        this.bkg = bkg;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        // background
        g2d.setPaint(this.bkg);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        // contorno
        g2d.setPaint(this.c);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
    
}