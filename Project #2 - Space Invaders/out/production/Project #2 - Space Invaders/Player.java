import java.awt.Color;
import java.awt.Graphics;
public class Player extends GraphicsObject{

    Color color = new Color(0, 0, 0);

    public Player () {
        this.x = 275;
        this.y = 350;
        this.speed_x = 0;
        this.speed_y = 0;
        this.width = 40;
        this.height = 40;
    }

    public Player(double x, double y, int width, int height) {
        super(x,y, width, height);
        this.speed_x = 0;
        this.speed_y = 0;
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
        g.setColor(color);
        //System.out.println((int)x + "D");
        g.fillRect((int)x,(int)y,width,height);
        //System.out.println(x + "D");
    }
    @Override
    public void update(int pic_width, int pic_height, int frame) {
        this.x += this.speed_x;
        //System.out.println(x + "X");
        this.y += this.speed_y;
        //System.out.println(y + "Y");
        
        if(this.x < 5){ this.x = 5; } //Sets the left bound
        
        if (this.x + this.width > pic_width - 5){
            this.x = pic_width - this.width - 5; //sets the right bound
        }
        super.update(pic_width,pic_height,frame); 
        //grabs the rest of data from GraphicsObject 
    }
}
