package figuras;

import java.awt.*;



public abstract class Figure{
    protected int x, y, h, w;
    protected Color c, bkg;
    protected boolean selected;

    public Figure(int x, int y, int w, int h, Color c, Color bkg, boolean selected)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.c = c;
        this.bkg = bkg;
        this.selected = selected;
    }

    public abstract void paint(Graphics g);

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public int getWidth()
    {
        return this.w;
    }
    public int getHeight()
    {
        return this.h;
    }

    // Foreground Color setter
    public void setForegroundColor(Color newColor)
    {
        this.c = newColor;
    }

    // Background Color setter
    public void setBkgColor(Color newColor)
    {
        this.bkg = newColor;
    }

    // Getters e setters para o atributo selected
    public boolean getSel(){ return this.selected; }
    public void setSel(boolean selected){ this.selected = selected; }

    // Checa se a figura foi clicada
    public boolean clicked(int x, int y)
    {
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }

    // Move a figura
    public void drag(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }

    // Altera o atributo largura
    public void resizeWidth(int dw)
    {
        this.w += dw;
    }

    // Altera o atributo altura
    public void resizeHeight(int dh)
    {
        this.h += dh;
    }

}
