package figuras;

import java.awt.*;


public class Text{
    private int x, y;
    private String text;

    public Text (String text,int x, int y) {
        this.x = x;
        this.y = y;
        this.text = text;
    }


    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString(this.text, this.x, this.y);
    }

}