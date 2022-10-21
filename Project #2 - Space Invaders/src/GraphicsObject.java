import java.awt.Color;
import java.awt.Graphics;

public class GraphicsObject {

    double x;
    double y;
    double speed_x;
    static int enemy_speed_x;
    double speed_y;
    int width;
    int height;
    Color color;

    public GraphicsObject() {
    }
    
    public GraphicsObject(double x, double y) {
    } 

    public GraphicsObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed_x = 0;
        this.speed_y = 0;
    }

    public GraphicsObject(double x, double y, int width, int height, double speed_x, double speed_y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed_x = speed_x;
        this.speed_y = speed_y;
    }

    /* Draw the object
     *
     * This function should never be called directly, but should be overridden
     * by subclasses.
     *
     * @param g The Graphics for the JPanel
     */
    public void draw(Graphics g) {
    }

    /*public boolean touched(GraphicsObject item){
        if 
    } */

    /* Update the object's location based on its speed
     *
     * @param pic_width   The width of the drawing window
     * @param pic_height  The height of the drawing window
     * @param frame       The number of frames since the start of the program
     */
    public boolean touching(GraphicsObject other) {
        // If the y of rectangle 1 is greater than (y+height) of rectangle 2, or (y+height) of rectangle 1 
        //is still less than y of trectangle 2, then they are absoulutely not overlapped.
        if(this.x < other.x + other.width && this.x + this.width > other.x && this.y < other.y + other.height && this.height + this.y > other.y){
            return true;
        } else {
            return false;
        }
    }
        
        
        
        /*if ((this.y > (other.y + other.height)) || ((this.y + this.height) < other.y)) {
            return false;
        }

        else if (((this.x + this.width) < other.x) ||
                (this.x > (other.x + other.width))) {
            return false;
        }
        // Except two conditions above, rectangles should overlap.
        return true;
    } */


    public void update(int pic_width, int pic_height, int frame) {
        this.x += this.speed_x;
        //System.out.println(x + "X");
        this.y += this.speed_y;
        //System.out.println(y + "Y");
    }
}
