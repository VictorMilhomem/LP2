import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse2DApp {
    public static void main (String[] args) {
        MainFrame frame = new MainFrame();
    }
}

class MainFrame extends JFrame {
    Ellipse e1; Ellipse e2; Ellipse e3;
    public MainFrame () {
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

        this.e1 = new Ellipse(50, 50, 150, 100, new Color(0,255,0));
        this.e2 = new Ellipse(50, 200, 200, 100, new Color(0,0,0));
        this.e3 = new Ellipse(50, 350, 150, 100, new Color(0,0,0));
        
    }

    public void paint (Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, 640, 640);

        this.e1.paint(g);
        this.e1.background(g);

        this.e2.paint(g);
        this.e2.background(g, new Color(255, 0, 0));
        
        this.e3.paint(g); 
    }
}


class Ellipse{
    int x, y;
    int w, h;
    Color c;

    Ellipse (int x, int y, int w, int h, Color c) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.c);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }

    public void background(Graphics g, Color c)
    {
        Graphics2D g2d = (Graphics2D) g;
        this.c = c;
        g2d.fillOval(this.x, this.y, this.w, this.h);
    }

    public void background(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.c);
        g2d.fillOval(this.x, this.y, this.w, this.h);
    }
}
