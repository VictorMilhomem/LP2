import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import figuras.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main (String[] args) {
        MainFrame frame = new MainFrame();
    }
}

class MainFrame extends JFrame {
    ArrayList<Figure> figures = new ArrayList<>();
    Random rand = new Random();

    public MainFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener(
            new KeyAdapter(){
                public void keyPressed(KeyEvent evt)
                {
                    int x = rand.nextInt(400);
                    int y = rand.nextInt(400);
                    int w = 100;
                    int h = 100;

                    switch(evt.getKeyCode())
                    {
                        case KeyEvent.VK_E:
                            Ellipse e = new Ellipse(x, y, w, h, Color.BLACK, Color.BLUE, false);
                            figures.add(e);
                            break;
                        case KeyEvent.VK_R:
                            Rect2D r = new Rect2D(x, y, w, h, Color.BLACK, Color.BLUE, false);
                            figures.add(r);
                            break;
                        case KeyEvent.VK_T:
                            Triangle t = new Triangle(x, y, w, h, Color.BLACK, Color.BLUE, false);
                            figures.add(t);
                            break;
                        default: break;
                    }
                    repaint();
                }
            }
        );

        this.setTitle("Java2D");
        this.setSize(640, 640);
        this.setVisible(true);

        
    }

    public void paint (Graphics g) {
        //super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, 640, 640);

        for(Figure f: figures)
        {
            f.paint(g);
        }

    }
}