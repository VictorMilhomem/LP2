package figuras;
import java.awt.*;


public class Rect2D
{
    private int x, y;
    private int h, w;
    public Rect2D(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
    
}