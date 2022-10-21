import java.awt.Color;
import java.awt.Graphics;

public class Win extends GraphicsObject {
    public Win(double x, double y){
        super(x,y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)x,(int)y,600,400);
        g.setColor(Color.BLACK);
        g.drawString("You Win!!!!!", 220, 200);
    }
}
