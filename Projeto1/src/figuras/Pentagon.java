package figuras;
import java.awt.*;


public class Pentagon extends Figure
{
    private int[] xPoints;
    private int[] yPoints;
    private Polygon pol;
    public Pentagon(int x, int y, int w, int h, Color c, Color bkg)
    {
        super(x, y, w, h, c, bkg);
    }

    public Pentagon(Color c, Color bkg)
    {
        super(c, bkg);
    }

    public void paint(Graphics g, boolean selected)
    {
        Graphics2D g2d = (Graphics2D) g;

        xPoints = new int[]{this.x, this.x+this.w/2, this.x+this.w, this.x+this.w-this.w/4, this.x+this.w/4};
        yPoints = new int[]{this.y, this.y-this.h/2, this.y, this.y+this.h/2, this.y+this.h/2};
        pol = new Polygon(xPoints, yPoints, 5);

        if(selected)
        {
            // Cor quando o objeto esta selecionado
            g2d.setPaint(Color.DARK_GRAY);
            g2d.fillPolygon(new Polygon(new int[]{this.x-3, this.x+this.w/2, this.x+this.w+3, this.x+this.w+3-this.w/4, this.x-3+this.w/4},
                    new int[]{this.y, this.y-3-this.h/2, this.y, this.y+3+this.h/2, this.y+3+this.h/2}, 5));
        }

        // background
        g2d.setPaint(this.bkg);
        g2d.fillPolygon(new Polygon(xPoints, yPoints, 5));

        // contorno
        g2d.setPaint(this.c);
        g2d.drawPolygon(pol);

    }

    public boolean clicked(int x, int y)
    {
        Point p = new Point(x, y);
        return (pol.contains(p));
    }

}