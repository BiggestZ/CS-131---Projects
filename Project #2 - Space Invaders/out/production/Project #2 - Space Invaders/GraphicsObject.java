import java.awt.Color;
import java.awt.Graphics;

public class GraphicsObject {

    double x;
    double y;
    double speed_x;
    double speed_y;
    int width;
    int height;

    public GraphicsObject() {
    } 

    public GraphicsObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed_x = 0;
        this.speed_y = 0;
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
    /*
    public boolean touched(GraphicsObject item){
        if 
    }
    */
    /* Update the object's location based on its speed
     *
     * @param pic_width   The width of the drawing window
     * @param pic_height  The height of the drawing window
     * @param frame       The number of frames since the start of the program
     */
    public void update(int pic_width, int pic_height, int frame) {
        this.x += this.speed_x;
        System.out.println(x + "X");
        this.y += this.speed_y;
        System.out.println(y + "Y");
    }
}
