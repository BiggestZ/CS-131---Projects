import java.awt.Color;
import java.awt.*;

public class Enemy extends GraphicsObject{
    double speed_y = 10; // How fast the enemy will drop
    double speed_x = 10; // How fast enemy will move side to side
    boolean isVisible; //Want to be true, until hit by player bullet
    private Color color = Color.BLUE;
    int width;
    int height;
    //In SpaceInvaders.java we want 10 enemies

    public Enemy(){ //Default Constructor
        this.x = 0;
        this.y = 0;
        this.speed_x = 0;
        this.speed_y = 0;
        this.width = 40;
        this.height = 40;
    }

    public Enemy (double x, double y, int width, int height, double speed_x, double speed_y){ //Constructor for Enemy
        super(x,y,width,height);
        this.speed_x = speed_x;
        this.speed_y = speed_y;
    } 

    public void setSpeed(double xSpeed, double ySpeed){ //Allows user to change speed of object
        this.speed_x = xSpeed;
        this.speed_y = ySpeed;
    }
    
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public void draw (Graphics g){
        g.setColor(color);
        g.fillRect((int)x,(int)y,width, height);
    }

    @Override
    public void update(int pic_width, int pic_height, int frame) {
        super.update(pic_width, pic_height, frame);
        if(this.x < 5 || this.x + this.width > pic_width - 5 ){ //Makes Aliens change direction
            this.speed_x = -this.speed_x;
        }
    }
}
