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
    private Figure selected = null;
    private Point mousePt;

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
                        Graphics g = getGraphics();
                        int w = 50; int h = 50;

                        switch (evt.getKeyCode())
                        {
                            case KeyEvent.VK_R: // Cria um retangulo
                                createFigure('r', mousePt, w, h);
                                break;
                            case KeyEvent.VK_E: // Cria uma elipse
                                createFigure('e', mousePt, w, h);
                                break;
                            case KeyEvent.VK_T: // Cria uma elipse
                                createFigure('t', mousePt, w, h);
                                break;
                            case KeyEvent.VK_P:
                                createFigure('p', mousePt, w, h);
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.remove(selected);
                                break;
                            case KeyEvent.VK_UP: // aumenta altura
                                resizeFigure(selected, 1, 'y');
                                break;
                            case KeyEvent.VK_DOWN: // diminui altura
                                resizeFigure(selected,-1, 'y');
                                break;
                            case KeyEvent.VK_LEFT: // diminui largura
                                resizeFigure(selected,-1, 'x');
                                break;
                            case KeyEvent.VK_RIGHT: // aumenta largura
                                resizeFigure(selected,1, 'x');
                                break;
                            case KeyEvent.VK_Q: // mudar a cor de contorno
                                changeColor('f', selected);
                                break;
                            case KeyEvent.VK_W: // mudar a cor de fundo
                                changeColor('b', selected);
                                break;
                            case KeyEvent.VK_F: // mudar a cor de fundo da tela
                                JColorChooser colorChooser = new JColorChooser();
                                BackgroundColor =
                                        JColorChooser.showDialog(null,"Escolha a cor de fundo da tela",Color.black);
                                break;
                            default: break;
                        }
                        repaint();
                    }
                }
        );

        this.addMouseListener (
                new MouseAdapter() {
                    public void mousePressed (MouseEvent evt) {
                        prevPt = evt.getPoint();

                        switch(evt.getButton())
                        {
                            case MouseEvent.BUTTON1: // seleciona a figura com o botão esquerdo 
                                selected = null;
                                for (Figure fig: figs) {
                                    if(fig.clicked((int)prevPt.getX(), (int)prevPt.getY()))
                                    {
                                        selected = fig;
                                        // troca a coordenada z(a ordem de desenho)
                                        int index = figs.indexOf(fig);
                                        int lastIndex = figs.size() - 1;
                                        Collections.swap(figs, index, lastIndex);
                                    }
                                }
                                break;
                            default: break;
                        }
                        repaint();
                    }
                }
        );
        this.addMouseMotionListener(
                new MouseAdapter() {
                    public void mouseMoved(MouseEvent evt){
                        mousePt = evt.getPoint();
                    }
                    public void mouseDragged(MouseEvent evt)
                    {
                        Graphics g = getGraphics();
                        Point currentPt = evt.getPoint();
                        if (selected != null){
                            selected.drag((int) (currentPt.getX()-prevPt.getX()), (int)(currentPt.getY()-prevPt.getY()));
                            prevPt = currentPt;
                            repaint();
                            g.dispose();

                        }
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
            if (fig.equals(selected)){
                fig.paint(g, true);
            }
            else{
                 fig.paint(g, false);
            }
        }
    }

    private void createFigure(char type, Point point, int w, int h){
        switch (type){
            case 'r':
                Rect2D r = new Rect2D((int)point.getX(),(int)point.getY(), w,h, Color.BLACK,Color.WHITE);
                figs.add(r); break;
            case 't':
                Triangle t = new Triangle((int)point.getX(), (int)point.getY(), w, h, Color.BLACK, Color.WHITE);
                figs.add(t);
                break;
            case 'e':
                Ellipse e = new Ellipse((int)point.getX(), (int)point.getY(), w, h, Color.BLACK, Color.WHITE);
                figs.add(e);
                break;
            case 'p':
                Pentagon p = new Pentagon((int)point.getX(), (int)point.getY(), w, h, Color.BLACK, Color.WHITE);
                figs.add(p);
                break;
            default:
                break;
        }
    }

    private void resizeFigure(Figure fig, int dir, char axis)
    {
        final int RESIZE = 5;
        switch (axis)
        {
            case 'y':
                fig.resizeHeight(dir * RESIZE); break;
            case 'x':
                fig.resizeWidth(dir * RESIZE); break;
            default: break;
        }
    }

    private void changeColor(char bkg_fg, Figure fig)
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




