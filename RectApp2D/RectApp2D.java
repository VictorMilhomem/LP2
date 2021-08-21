import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RectApp2D {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    Rect r1; Rect r2; Rect r3;
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D");
        this.setSize(640, 640);
        this.setVisible(true);
        this.r1 = new Rect(50, 50, 150, 100);
        this.r2 = new Rect(50, 200, 150, 100);
        this.r3 = new Rect(50, 350, 150, 100);
    }

    public void paint (Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, 640, 640);

        this.r1.paint(g);
        this.r1.background(g, new Color(0,255,0));

        this.r2.paint(g);
        this.r2.background(g, new Color(255,0,0));

        this.r3.paint(g);
        this.r3.rectColor(g, new Color(0,0,255));
    }
}


class Rect
{
    int x, y;
    int h, w;
    Rect(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
    
    void rectColor(Graphics g, Color c)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(c);
    }

    void background(Graphics g, Color c)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}