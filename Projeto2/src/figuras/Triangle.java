package figuras;
import java.awt.*;


public class Triangle extends Figure
{
    private int[] xPoints;
    private int[] yPoints;
    Polygon pol;
    public Triangle(int x, int y, int w, int h, Color c, Color bkg)
    {
        super(x, y, w, h, c, bkg);
    }

    public void paint(Graphics g, boolean selected)
    {
        Graphics2D g2d = (Graphics2D) g;

        xPoints = new int[]{this.x, this.x-this.w/2, this.x+this.w};
        yPoints = new int[]{this.y, this.y+this.h, this.y+this.h};
        pol = new Polygon(xPoints, yPoints, 3);

        if(selected)
        {
            // Cor quando o objeto esta selecionado
            g2d.setPaint(Color.DARK_GRAY);
            g2d.fillPolygon(new Polygon(new int[]{this.x, this.x-3-this.w/2, this.x+this.w+3}, 
            new int[]{this.y-3, this.y+this.h+3, this.y+this.h+3}, 3));
        }

        // background
        g2d.setPaint(this.bkg);
        g2d.fillPolygon(new Polygon(xPoints, yPoints, 3));

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