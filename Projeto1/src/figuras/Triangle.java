package figuras;
import java.awt.*;


public class Triangle extends Figure
{
    private Polygon triangle;
    private int[] xPoints;
    private int[] yPoints;
    public Triangle(int x, int y, int w, int h, Color c, Color bkg, boolean selected)
    {
        super(x, y, w, h, c, bkg, selected);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        xPoints = new int[]{this.x, this.x-this.w/2, this.x+this.w};
        yPoints = new int[]{this.y, this.y+this.h, this.y+this.h};

        if(this.selected)
        {
            // Cor quando o objeto esta selecionado
            g2d.setPaint(Color.DARK_GRAY);
            g2d.fillPolygon(new Polygon(new int[]{this.x-3, this.x-3-this.w/2, this.x+this.w+8}, 
            new int[]{this.y-3, this.y+this.h+3, this.y+this.h+3}, 3));
        }

        // background
        g2d.setPaint(this.bkg);
        g2d.fillPolygon(new Polygon(xPoints, yPoints, 3));

        // contorno
        g2d.setPaint(this.c);
        g2d.drawPolygon(xPoints, yPoints, 3);

    }

    public boolean clicked(int x, int y)
    {
        Point p = new Point(x, y);
        Polygon pol = new Polygon(xPoints, yPoints, 3);
        return (pol.contains(p));
    }

}