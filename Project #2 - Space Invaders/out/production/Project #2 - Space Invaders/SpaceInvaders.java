// utility
import java.util.ArrayList;
import java.util.Random;

// graphics
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    // FIXME list your game objects here
    private Player player;
    private ArrayList<playerBullet> playerBullets = new ArrayList<playerBullet>();
    private ArrayList <Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<enemyBullet> eBullet = new ArrayList <enemyBullet>();

    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders() {
        // fix the window size and background color
        this.canvasWidth = 600;
        this.canvasHeight = 400;
        this.backgroundColor = Color.WHITE;
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);

        // FIXME initialize your game objects
        //this.playerBullet = new playerBullet(290,350, 5, 10);
        this.player = new Player(275,350, 40, 40);
        for (int i = 0; i< 10; i++) {
            for(int j = 0 ; j < 5; j++) {
                int width = 25;
                int height = 25;
                int xGap = 10;
                int yGap = 10;
                enemies.add(new Enemy( 50 + i * (width+ xGap), 25 + j * (height + yGap), width, height, 0.6, 0.1));
            }
        }
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();

        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        update(); 
        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);
        // increment the frame counter
        this.frame++;
    }

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);
        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     * 
     * @param e  An object describing what key was released
     */

    public void left(){ //Should move sprite left
        player.setSpeed(-5,0); // The speed is changing, need the screen to reflect it
        //playerBullet.setSpeed(-5,0);
        //System.out.println(player.speed_x);
    }

    public void right(){ //Should move sprite right
        player.setSpeed(5,0); // The speed is changing, need the screen to reflect it
        //playerBullet.setSpeed(5,0);
        //System.out.println(player.speed_x);
    }


    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setSpeed(0,0);
            //playerBullet.setSpeed(0,0);
            //if (!hasWonGame() && !hasLostGame()) {  } // Checks if the game is over
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setSpeed(0,0);
            //playerBullet.setSpeed(0,0);
            ///if (!hasWonGame() && !hasLostGame()) {   } // Checks if the game is over
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //shoot(); 
        }
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // FIXME what happens when left arrow is pressed
            left(); //Will move character to the left
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // FIXME what happens when right arrow is pressed
            right();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            double bX = player.getX();
            double bY = player.getY();
            playerBullets.add( new playerBullet(bX + 10, bY + 15, 5, 20, 0, -10));
            // FIXME what happens when space bar is pressed
            //This will shoot the bullet
        }
    }
    /* Update the game objects
     */
    private void update() {
        //Update Player
        player.update(this.canvasWidth, this.canvasHeight, this.frame);
        
        //Update Player Bullets
        for (playerBullet bullet : playerBullets){
            bullet.update(this.canvasWidth, this.canvasHeight, this.frame);
        } //Need to add what happens if the bullets hit the enemy

        //Update Enemy

        //Update Enemy Bullets

        // What does this function do, how does it work?
        // FIXME update game objects here
    }

    /* Check if the player has lost the game
     * 
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        // If Aliens shoot player
        // If Aliens touch player 
        // If Aliens touch bottom of screen
        return false; // FIXME delete this when ready
    }

    /* Check if the player has won the game
     * 
     * @returns  true if the player has won, false otherwise
     */
    private boolean hasWonGame() {
        // If all aliens are dead
        return false; // FIXME delete this when ready
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) { //tHIS IS WHERE THE OBJ ARE DRAWN
        //Draws the Player
        player.draw(g);

        //Draws the Players Bullets
        for (playerBullet bullet: playerBullets){
            bullet.draw(g);
        }
        
        //Draws all the enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        //Draws the bullets of all the enemies
        for (enemyBullet bullet : eBullet) {
            bullet.draw(g);
        }
        // FIXME draw game objects here

    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        // FIXME draw the win screen here
        g.drawString("You Win!", 275,200);
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        // FIXME draw the game over screen here
        g.drawString("You Lost!", 275,200);
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
} 
