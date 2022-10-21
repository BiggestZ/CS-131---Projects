import java.awt.*;

public class enemyBullet extends Player{
    public enemyBullet(double x, double y, int width, int height, double speedX, double speedY) {
        super(x, y, width, height);
        speed_x = 0;
        speed_y = speedY;
    }

    public void setSpeed(double xSpeed, double ySpeed){ //Allows user to change speed of object
        this.speed_x = xSpeed;
        this.speed_y = ySpeed;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN); //Black Bullets
        g.fillRect((int)x,(int)y,width,height);
    }

    @Override
    public void update(int pic_width, int pic_height, int frame) {
        super.update(pic_width,pic_height,frame);
    }
}
