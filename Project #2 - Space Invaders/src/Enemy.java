import java.awt.*;
public class Enemy extends  GraphicsObject{

    public Enemy(double x, double y, int width, int height, double speed_x, double speed_y) {
        super(x,y, width, height, speed_x, speed_y);       
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,width,height);
    }

    public double getX(){return x;} //Returns x position of player

    public double getY(){return y;} //Returns y position of player

    @Override
    public void update(int pic_width, int pic_height, int frame) {
        super.update(pic_width, pic_height, frame);

        //this.x += Enemy.enemy_speed_x;
        //this.y += Enemy.enemy_speed_y;

        /*if(this.x < 5 || this.x + this.width > pic_width - 5 ){ //Makes Aliens change direction
            this.speed_x = -this.speed_x;
        } */
        /*if (this.y < 0 || this.y + this.height > pic_height) {
            this.speed_y = -this.speed_y;
        } */
    }
}


/*import java.awt.Color;
import java.awt.*;

public class Enemy extends GraphicsObject{
    int width = 0;
    int height = 0;
    Color color = new Color(255,105,180);

    public Enemy(double x, double y, int width, int height, double speed_x, double speed_y, Color color) {
        super(x, y, width, height, speed_x,speed_y,color);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect((int)this.x, (int)this.y, this.width, this.height);
    }

    @Override
    public void update(int pic_width, int pic_height, int frame) {
        if (this.x < 0 || this.x + this.width > pic_width) {
            this.speed_x = -this.speed_x;
        }
        if (this.y < 0 || this.y + this.height > pic_height) {
            this.speed_y = -this.speed_y;
        }
        super.update(pic_width, pic_height, frame);
        System.out.println(x + "X");
        System.out.println(y + "Y");
    }

}
*/





/*

private double speed_y = 0; // How fast the enemy will drop
    private double speed_x = 0; // How fast enemy will move side to side
    //boolean isVisible; //Want to be true, until hit by player bullet
    private Color color = new Color(30,144,255);
    private int width = 0;
    private int height = 0;
    //In SpaceInvaders.java we want 10 enemies

    public Enemy(){ //Default Constructor
        this.x = 200;
        this.y = 200;
        this.speed_x = 1;
        this.speed_y = 1;
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
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect((int)x,(int)y,width,height);
    }

    @Override
    public void update(int pic_width, int pic_height, int frame) {
        this.x += this.speed_x;
        this.y += this.speed_y;
        
        if (this.x < 0 || this.x + this.width > pic_width) { //Bounces off LR walls
            this.speed_x = -this.speed_x;
        }
        if (this.y < 0 || this.y + this.height > pic_height) { //Bounces off UD walls
            this.speed_y = -this.speed_y;
        }

        //Random randy = new Random();
        this.color = Color.blue;
        super.update(pic_width, pic_height, frame);
        System.out.println(x + "X");
        System.out.println(y + "Y");
        /*this.x += this.speed_x;
        this.y += this.speed_y;
        if(this.x < 5 || this.x + this.width > pic_width - 5 ){ //Makes Aliens change direction
            this.speed_x = -this.speed_x;
        }
        super.update(pic_width, pic_height, frame);}*/
    /*    }
} */
