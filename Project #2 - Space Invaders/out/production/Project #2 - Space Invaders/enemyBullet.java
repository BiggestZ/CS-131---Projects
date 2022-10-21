import java.awt.*;

public class enemyBullet extends Player{
    public enemyBullet(double x, double y, int width, int height, double speedY) {
        super(x, y, width, height);
        speed_y = speedY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0)); //Black Bullets
        g.fillRect((int)x,(int)y,width,height);
    }
}
