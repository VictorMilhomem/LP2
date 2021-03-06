import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;
import figuras.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;



public class Main {
    public static void main (String[] args) {
        new MainFrame();
    }
}

class MainFrame extends JFrame {
    static final int WIDTH = 720;
    static final int HEIGHT = 640;
    private static final int MINXDRAWPOS = 85;
    private ArrayList<Figure> figs = new ArrayList<>();
    private Figure selected = null;
    private Buttons bSelected = null;
    private int buttonIndex;
    private Point prevPt;
    private Color BackgroundColor = Color.white;
    private Point mousePt;
    private ArrayList<Buttons> buttons = new ArrayList<>();

    public MainFrame () {
    
        try{
            FileInputStream fp = new FileInputStream("proj.bin");
            ObjectInputStream obj = new ObjectInputStream(fp);
            this.figs = (ArrayList<Figure>) obj.readObject();
            obj.close();

        }catch(Exception e){
            System.out.println("Error");
        }

        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        try{
                            FileOutputStream fp = new FileOutputStream("proj.bin");
                            ObjectOutputStream obj = new ObjectOutputStream(fp);
                            obj.writeObject(figs);
                            obj.flush();
                            obj.close(); 

                        }catch (Exception ex){

                        }
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
                                if(selected != null){
                                    resizeFigure(selected, 1, 'y');
                                }
                                break;
                            case KeyEvent.VK_DOWN: // diminui altura
                                if(selected != null){
                                    resizeFigure(selected,-1, 'y');
                                }
                                break;
                            case KeyEvent.VK_LEFT: // diminui largura
                                if(selected != null){
                                    resizeFigure(selected,-1, 'x');
                                }
                                break;
                            case KeyEvent.VK_RIGHT: // aumenta largura
                                if(selected != null){
                                    resizeFigure(selected,1, 'x');
                                }
                                break;
                            case KeyEvent.VK_Q: // mudar a cor de contorno
                                if(selected != null){
                                    changeColor('f', selected);
                                }
                                break;
                            case KeyEvent.VK_W: // mudar a cor de fundo
                                if(selected != null){
                                    changeColor('b', selected);
                                }
                                break;
                            case KeyEvent.VK_F: // mudar a cor de fundo da tela
                                JColorChooser colorChooser = new JColorChooser();
                                BackgroundColor =
                                        JColorChooser.showDialog(null,"Escolha a cor de fundo da tela",BackgroundColor);
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
                            case MouseEvent.BUTTON1: // seleciona a figura com o bot??o esquerdo
                                selected = null;
                                bSelected = null;
                                for (Figure fig: figs) {
                                    if(fig.clicked((int)prevPt.getX(), (int)prevPt.getY()))
                                    {
                                        selected = fig;
                                    }
                                }

                                for (Buttons b: buttons) {
                                    if (b.clicked((int)prevPt.getX(), (int)prevPt.getY())){
                                        bSelected = b;
                                        buttonIndex = b.getIdx();
                                    }
                                }

                                // cria as figuras com os bot??es
                                if (buttonIndex != -1 && bSelected == null && (int)mousePt.getX() > MINXDRAWPOS){
                                    buttonsFunctionality(buttonIndex); // cria as figuras
                                    buttonIndex = -1;
                                }



                                // troca a coordenada z(a ordem de desenho)
                                if (selected != null){
                                    int index = figs.indexOf(selected);
                                    int lastIndex = figs.size() - 1;
                                    Collections.swap(figs, index, lastIndex);

                                }
                                break;
                            case MouseEvent.BUTTON3:
                                if (selected != null){
                                    if(selected.clicked((int)prevPt.getX(), (int)prevPt.getY()))
                                    {
                                        selected = null;
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
                        int aux = ((int)currentPt.getX() > MINXDRAWPOS) ?  (int)(currentPt.getX()-prevPt.getX()) : 0;
                        if (selected != null ){
                            selected.drag(aux, (int)(currentPt.getY()-prevPt.getY()));
                            prevPt = currentPt;
                            repaint();
                            g.dispose();

                        }
                    }

                }
        );

        this.setTitle("Editor Gr??fico");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setVisible(true);

        // cria????o dos bot??es
        Buttons RectButton = new Buttons(1, new Rect2D(Color.BLACK, Color.WHITE), 1.0f);
        Buttons EllipseButton = new Buttons(2, new Ellipse(Color.BLACK, Color.WHITE), 1.0f);
        Buttons PentagonButton = new Buttons(3, new Pentagon(Color.BLACK, Color.WHITE),2.3f);
        Buttons TriangleButton = new Buttons(4, new Triangle(Color.BLACK, Color.WHITE),1.0f);
        buttons.add(RectButton);
        buttons.add(EllipseButton);
        buttons.add(PentagonButton);
        buttons.add(TriangleButton);
        buttonIndex = -1;

    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(BackgroundColor);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);


        for (Buttons b: this.buttons)
        {
            if (b.equals(bSelected)){
                b.paint(g, true);
            }
            else{
                 b.paint(g, false);
            }
        }

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
        // checa se a posi????o do mouse est?? na posi????o onde os bot??es est??o colocados
        if (point.getX() > MINXDRAWPOS){

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
    }

    private void buttonsFunctionality(int idx){
        switch(idx){
            case 1: createFigure('r', mousePt, 50, 50); break;
            case 2: createFigure('e', mousePt, 50, 50); break;
            case 3: createFigure('p', mousePt, 50, 50); break;
            case 4: createFigure('t', mousePt, 50, 50); break;
               
            default: break;
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



