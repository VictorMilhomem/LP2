package figuras;
import java.awt.*;


public class Text extends Figure
{
    private String text;
    private float fontSize;
    public Text(String text, float fontSize, int x, int y, int w, int h, Color c,  Color bkg)
    {
        super(x, y, w, h, c, bkg);
        this.text = text;
        this.fontSize = fontSize;
    }

    public Text(String text, float fontSize, Color c, Color bkg)
    {
        super(c, bkg);
        this.text = text;
        this.fontSize = fontSize;
    }

    public void paint(Graphics g, boolean selected)
    {
        Graphics2D g2d = (Graphics2D) g;
        Font font = g.getFont().deriveFont( this.fontSize );
        g.setFont( font );
        g2d.setColor(this.c);
        g2d.drawString(this.text, this.x, this.y);
    }


}