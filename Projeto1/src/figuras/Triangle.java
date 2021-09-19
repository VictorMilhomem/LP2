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

        xPoints = new int[]{this.x, this.x+this.w, this.x+this.w/2};
        yPoints = new int[]{this.y, this.y-this.h, this.h};

        if(this.selected)
        {
            // Cor quando o objeto esta selecionado
            g2d.setPaint(Color.DARK_GRAY);

        }

        // background
        g2d.setPaint(this.bkg);
        g2d.fillPolygon(new Polygon(xPoints, yPoints, 3));

        // contorno
        g2d.setPaint(this.c);
        g2d.draw(new Polygon(xPoints, yPoints, 3));

    }



}