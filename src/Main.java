import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import figuras.*;

public class Main {
    public static void main (String[] args) {
        MainFrame frame = new MainFrame();
    }
}

class MainFrame extends JFrame {
    Rect2D r1; Text text; Ellipse e1;
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
        this.r1 = new Rect2D(50, 50, 150, 100);
        this.text = new Text("Hello world", 100, 200);
        this.e1 = new Ellipse(50, 250, 150, 150);
        
    }

    public void paint (Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, 640, 640);

        this.r1.paint(g);

        this.text.paint(g);

        this.e1.paint(g);
    }
}