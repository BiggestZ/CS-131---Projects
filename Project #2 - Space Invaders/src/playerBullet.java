import java.awt.Color;
import java.awt.Graphics;

public class playerBullet extends Player{
    int width = 3; //Dimensions of Bullet
    int height = 15; //Dimensions of Bullet
    Color color = new Color(255, 0, 0); //Red
    
    double speed_y = 0; //How fast the bullet will move up
    // Do not need speed_x for bullet, only moves vertically

    public playerBullet(double x, double y, int width, int height, double speed_x, double speed_y){
        super(x,y,width,height); //x should be same as player. Y will be changing over time.
        this.speed_x = speed_x;
        this.speed_y = speed_y;
    }

    public void setSpeed(double xSpeed, double ySpeed){ //Allows user to change speed of object
        this.speed_x = xSpeed;
        this.speed_y = ySpeed;
    }

    public void draw(Graphics g) { //draw the shape of the bullet/ the color
        g.setColor(color);
        g.fillRect((int)x,(int)y,width,height);
    }

    @Override
    public void update(int pic_width, int pic_height, int frame) {
        //System.out.println(x + "X");
        //System.out.println(y + "Y");

        this.y += this.speed_y;

        if(this.x < 22){ this.x = 22; } //Sets the left bound
        if (this.x > 575){
            this.x = 570  ; //sets the right bound
        }
        super.update(pic_width,pic_height,frame); 
        //grabs the rest of data from GraphicsObject 
    }
}