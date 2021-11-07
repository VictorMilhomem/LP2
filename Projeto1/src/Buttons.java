import ivisible.IVisible;
import figuras.Figure;
import java.awt.*;

public class Buttons implements IVisible {

    private static int SPC = 10;
    private static int DIM = 30;
    private static int PAD = 4;

    private  int    idx;
    private Figure fig;

    public Buttons (int idx, Figure fig, int factor) {
        this.idx = idx;
        this.fig = fig;
        this.fig.setX(PAD+SPC);
        this.fig.setY(PAD+factor*SPC + idx*DIM);
        this.fig.setWidth(DIM-PAD*2);
        this.fig.setHeight(DIM-PAD*2);
    }

    public boolean clicked (int x, int y) {
        return SPC<=x && x<=SPC+DIM && SPC+this.idx*DIM<=y && y<=SPC+this.idx*DIM+DIM;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(focused ? Color.WHITE : Color.GRAY);
        g2d.fillRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        this.fig.paint(g, false);
    }

    public int getIdx() {
        return this.idx;
    }

}