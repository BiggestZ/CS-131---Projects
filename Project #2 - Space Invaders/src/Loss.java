import java.awt.Color;
import java.awt.Graphics;

public class Loss extends GraphicsObject {
    public Loss(double x, double y){
        super(x,y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,600,400);
        g.setColor(Color.BLACK);
        g.drawString("Game Over! Reload Program To Try Again.", 180, 200);
    }
}
