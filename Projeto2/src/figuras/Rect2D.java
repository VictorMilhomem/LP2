package figuras;
import java.awt.*;


public class Rect2D extends Figure
{
    public Rect2D(int x, int y, int w, int h, Color c, Color bkg)
    {
        super(x, y, w, h, c, bkg);
    }

    public void paint(Graphics g, boolean selected)
    {
        Graphics2D g2d = (Graphics2D) g;

        if(selected)
        {
            // Cor quando o objeto esta selecionado
            g2d.setPaint(Color.DARK_GRAY);
            g2d.fillRect(this.x-3, this.y-3, this.w+6, this.h+6);
        }

        // background
        g2d.setPaint(this.bkg);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        // contorno
        g2d.setPaint(this.c);
        g2d.drawRect(this.x, this.y, this.w, this.h);

    }


}