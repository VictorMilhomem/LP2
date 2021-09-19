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
        xPoints = new int[]{this.x, this.x+this.w};
        yPoints = new int[]{this.y, this.y-this.h, this.h};
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        triangle = new Polygon();
        triangle.addPoint(xPoints[0], yPoints[0]);
        triangle.addPoint(xPoints[1], yPoints[1]);
        triangle.addPoint(xPoints[1], yPoints[2]);

        if(this.selected)
        {
            //TODO
        }

        g2d.setPaint(this.bkg);
        g2d.fillPolygon(triangle);

        // contorno
        g2d.setPaint(this.c);
        g2d.draw(triangle);

    }


}