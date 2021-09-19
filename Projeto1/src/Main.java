import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;
import figuras.*;

public class Main {
    public static void main (String[] args) {
        new MainFrame();
    }
}

class MainFrame extends JFrame {
    static final int WIDTH = 720;
    static final int HEIGHT = 640;
    private ArrayList<Figure> figs = new ArrayList<>();
    Random rand = new Random();
    private Point prevPt;
    private Color BackgroundColor = Color.white;

    public MainFrame () {
        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        this.addKeyListener (
                new KeyAdapter() {
                    public void keyPressed (KeyEvent evt) {
                        int x = rand.nextInt(600);
                        int y = rand.nextInt(500);
                        int w = 100;
                        int h = 100;
                        Graphics g = getGraphics();

                        switch (evt.getKeyCode())
                        {
                            case KeyEvent.VK_R: // Cria um retangulo
                                Rect2D r = new Rect2D(x,y, w,h, Color.BLACK,Color.WHITE, false);
                                figs.add(r);
                                break;
                            case KeyEvent.VK_E: // Cria uma elipse
                                Ellipse e = new Ellipse(x, y, w, h, Color.BLACK, Color.WHITE, false);
                                figs.add(e);
                                break;
                            case KeyEvent.VK_T: // Cria uma elipse
                                Triangle t = new Triangle(x, y, w, h, Color.BLACK, Color.WHITE, false);
                                figs.add(t);
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.removeIf(Figure::getSel);
                                break;
                            case KeyEvent.VK_UP: // aumenta altura
                                resizeFigure(1, 'y');
                                break;
                            case KeyEvent.VK_DOWN: // diminui altura
                                resizeFigure(-1, 'y');
                                break;
                            case KeyEvent.VK_LEFT: // diminui largura
                                resizeFigure(-1, 'x');
                                break;
                            case KeyEvent.VK_RIGHT: // aumenta largura
                                resizeFigure(1, 'x');
                                break;
                            case KeyEvent.VK_Q: // mudar a cor de contorno
                                changeColor('f');
                                break;
                            case KeyEvent.VK_W: // mudar a cor de fundo
                                changeColor('b');
                                break;
                            case KeyEvent.VK_F: // mudar a cor de fundo da tela
                                JColorChooser colorChooser = new JColorChooser();
                                BackgroundColor =
                                        JColorChooser.showDialog(null,"Escolha a cor de fundo da tela",Color.black);
                                break;
                            default: break;
                        }
                        repaint();
                        g.dispose();
                    }
                }
        );

        this.addMouseListener (
                new MouseAdapter() {
                    public void mousePressed (MouseEvent evt) {
                        prevPt = evt.getPoint();
                        System.out.println("prev"+prevPt); // debug
                        switch(evt.getButton())
                        {
                            case MouseEvent.BUTTON1: // seleciona a figura com o botão esquerdo
                                for (Figure fig: figs) {
                                    if(fig.clicked((int)prevPt.getX(), (int)prevPt.getY()))
                                    {
                                        fig.setSel(true);
                                        // troca a coordenada z(a ordem de desenho)
                                        int index = figs.indexOf(fig);
                                        int lastIndex = figs.size() - 1;
                                        Collections.swap(figs, index, lastIndex);
                                    }
                                } break;

                            case MouseEvent.BUTTON3: // desseleciona a figura com o botão direito
                                for (Figure fig: figs) {
                                    if(fig.clicked((int)prevPt.getX(), (int)prevPt.getY()))
                                    {
                                        fig.setSel(false);
                                    }
                                } break;
                            default: break;
                        }
                    }
                }
        );
        this.addMouseMotionListener(
                new MouseAdapter() {
                    public void mouseDragged(MouseEvent evt)
                    {
                        Graphics g = getGraphics();
                        Point currentPt = evt.getPoint();
                        System.out.println("current"+currentPt); // debug
                        for (Figure fig: figs) {
                            if(fig.clicked((int)prevPt.getX(), (int)prevPt.getY()) && fig.getSel() )
                            {
                                // Arrasta a figura pela tela
                                fig.drag((int) (currentPt.getX()-prevPt.getX()), (int)(currentPt.getY()-prevPt.getY()));
                            }
                        }
                        prevPt = currentPt;
                        repaint();
                        g.dispose();
                    }

                }
        );

        this.setTitle("Editor Gráfico");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setVisible(true);

    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(BackgroundColor);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        for (Figure fig: this.figs)
        {
            fig.paint(g);
        }
    }

    private void resizeFigure(int dir, char axis)
    {
        final int RESIZE = 5;
        for(Figure fig: figs)
        {
            if(fig.getSel())
            {
                switch (axis)
                {
                    case 'y':
                        fig.resizeHeight(dir * RESIZE); break;
                    case 'x':
                        fig.resizeWidth(dir * RESIZE); break;
                    default: break;
                }
            }
        }
    }

    private void changeColor(char bkg_fg)
    {
        for (Figure fig: figs)
        {
            if(fig.getSel())
            {
                JColorChooser colorChooser = new JColorChooser();
                Color newColor = JColorChooser.showDialog(null, "Escolha a cor", Color.black);
                switch(bkg_fg)
                {
                    case 'f': // Cor de contorno
                        fig.setForegroundColor(newColor);
                        break;
                    case 'b': // Cor de fundo
                        fig.setBkgColor(newColor);
                        break;
                    default: break;
                }
            }
        }
    }

}




