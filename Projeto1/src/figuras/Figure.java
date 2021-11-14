package figuras;

import java.awt.*;
import ivisible.IVisible;
import java.io.Serializable;

public abstract class Figure implements IVisible, Serializable{
    protected int x, y, h, w, id;
    protected Color c, bkg;

    public Figure(int x, int y, int w, int h, Color c, Color bkg)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.c = c;
        this.bkg = bkg;
    }

    public Figure(Color c, Color bkg)
    {
        this.c = c;
        this.bkg = bkg;
    }

    public void setX(int newX)
    {
        this.x = newX;
    }

    public void setY(int newY)
    {
        this.y = newY;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setWidth(int newWidth)
    {
        this.w = newWidth;
    }

    public void setHeight(int newHeight)
    {
        this.h = newHeight;
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
