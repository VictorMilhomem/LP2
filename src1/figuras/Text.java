package figuras;

import java.awt.*;


public class Text{
    private int x, y;
    private String text;
    private Color c;

    public Text (String text,int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.c = c;
    }


    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.c);
        g2d.drawString(this.text, this.x, this.y);
    }

}