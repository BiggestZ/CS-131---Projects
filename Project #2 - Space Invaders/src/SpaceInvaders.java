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

    Random Rand = new Random();
    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;
    private int shot_frame = 0;

    // FIXME list your game objects here
    private Player player; // The Player
    // private Enemy evil;
    private ArrayList<playerBullet> playerBullets = new ArrayList<playerBullet>(); //Player's Bullets
    private ArrayList <Enemy> enemies = new ArrayList<Enemy>(); //All new enemies
    private ArrayList<enemyBullet> eBullet = new ArrayList <enemyBullet>(); // Enemy Bullets
    private Win Winner = new Win (0,0); // Win Screen
    private Loss Loser = new Loss (0,0); // Loss Screen


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

        for(int i = 0; i < 15; i++){
            System.out.println("\n");
        }
        this.player = new Player(275,350, 40, 40);
        for(int i = 0; i< 10; i++) {
            for(int j = 0 ; j < 5; j++) {
                int width = 25;
                int height = 25;
                int gapX = 15;
                int gapY = 10;
                enemies.add(new Enemy( 50 + i * (width + gapX), 25 + j * (height + gapY), width, height, 3, 0));
            }
        }
    }
    
    /* Start the game */
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
    }

    public void right(){ //Should move sprite right
        player.setSpeed(5,0); // The speed is changing, need the screen to reflect it
    }


    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setSpeed(0,0);
            //if (!hasWonGame() && !hasLostGame()) {  } // Checks if the game is over
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setSpeed(0,0);
            ///if (!hasWonGame() && !hasLostGame()) {   } // Checks if the game is over
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
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
            left(); //Will move character to the left
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(playerBullets.size() < 5) {
                double bX = player.getX();
                double bY = player.getY();
                playerBullets.add( new playerBullet(bX + 10, bY + 15, 5, 20, 0, -10));
                //System.out.println(playerBullets.size());
            }
            //This will shoot the bullet
        }
    }
    /* Update the game objects
     */
    private void update() {
        //Update Enemies 
        for(int i = 0; i < enemies.size(); i++){   
            if(hasLostGame()){
                for(int j = 0; j < enemies.size(); j++){  
                    enemies.get(j).speed_x = 0;
                    enemies.get(j).speed_y = 0;
                }
            }

            if(enemies.get(i).x > 570){ // Makes all enemies bounce off the right wall
                for(int j = 0; j < enemies.size(); j++){  
                    enemies.get(j).speed_x = -enemies.get(j).speed_x;
                    enemies.get(j).x -= 1.5;
                    enemies.get(j).y += 2;
                }
            }

            if(enemies.get(i).x < 20){ // Makes all enemies bounce off the left wall
                for(int c = 0; c < enemies.size(); c++){
                    enemies.get(c).speed_x = -enemies.get(c).speed_x;
                    enemies.get(c).x += 1.5;
                    enemies.get(c).y += 2;
                }
            }

            for(Enemy enemy : enemies) {
                if(eBullet.size() < 5){Random rand = new Random();
                    Enemy randomEnemy = enemies.get(rand.nextInt(enemies.size()));
                    double spawnX = randomEnemy.getX() + 12;
                    double spawnY = randomEnemy.getY() + enemy.height + 5;
                    eBullet.add(new enemyBullet(spawnX, spawnY, 5, 15, 0, 2));}
            }
            enemies.get(i).update(this.canvasWidth, this.canvasHeight, this.frame);
        }

        //Update Player
        player.update(this.canvasWidth, this.canvasHeight, this.frame);
        
        //Update Player Bullets
        for(int i = 0; i < playerBullets.size(); i++){
            playerBullet b = playerBullets.get(i); //Var for the current player bullet in the list
            if(playerBullets.size() > 0) { 
                if (playerBullets.get(i).y < 0){
                    playerBullets.remove(b);
                }
                b.update(this.canvasWidth, this.canvasHeight, this.frame);  

                for(int c = 0; c < enemies.size(); c++){
                    Enemy e = enemies.get(c); //Var for the selected enemy in the list
                    if(e.touching(b)){
                        playerBullets.remove(b); //Remove bullet
                        enemies.remove(e);  //Remove the enemy
                    }   
                }
            }
        }

        for(int i = 0; i < eBullet.size(); i++){
            enemyBullet e = eBullet.get(i); //sets the bullet to a variable
            if (eBullet.size() > 0) {
                e.update(this.canvasWidth, this.canvasHeight, this.frame);
            }
            if(e.y > 400){
                eBullet.remove(e);
            }
        }

        /*for (enemyBullet e_shot : eBullet){
            if (eBullet.size() > 0) {
                e_shot.update(this.canvasWidth, this.canvasHeight, this.frame);
            }
            if(e_shot.y > 400){
                eBullet.remove(e_shot);
            }
        } */
        /*    
        for(enemyBullet e_shot : eBullet) {
                System.out.println("APPLE");
                if(e_shot.y > 400){
                    eBullet.remove(e_shot);
                }
                if(eBullet.size() < 5){
                    System.out.println("STAPLER");
                    Random rand = new Random();
                    Enemy randomEnemy = enemies.get(rand.nextInt(enemies.size()));
                    double spawnX = randomEnemy.getX() + 12;
                    double spawnY = randomEnemy.getY() + 30;
                    eBullet.add(new enemyBullet(spawnX, spawnY, 5, 15, 0, 1));
                }
                e_shot.update(this.canvasWidth, this.canvasHeight, this.frame); 
            }
*/
        /*
            for(int i = 0; i < enemies.size(); i++){
                System.out.println(eBullet.size() + "APPLE");
                if(eBullet.size() < 5){ 
                    Random rand = new Random();
                    Enemy randomEnemy = enemies.get(rand.nextInt(enemies.size()));
                    double spawnX = randomEnemy.getX() + 12;
                    double spawnY = randomEnemy.getY() + 30;
                    eBullet.add(new enemyBullet(spawnX, spawnY, 5, 15,0, 10));
                }
                eBullet.get(i).update(this.canvasWidth, this.canvasHeight, this.frame); 
            } 
        */
        //int e_shot = Rand.nextInt(enemies.size()-1); //A Random # for all enemies in enemy
        
        /*for (int i = 0; i < enemies.size(); i++){ 
            enemyBullet j = eBullet.get(i);  //Assigns each bullet to a variable
            j.update(this.canvasWidth, this.canvasHeight, this.frame);
            if(eBullet.size() > 0){
                if(shot_frame == 300){
                    eBullet.add(new enemyBullet(j.x, j.y, 5, 15, 10));
                    shot_frame = 0;
            }
            
            }
        }*/ 

        //Update Enemy Bullets

        // What does this function do, how does it work?
        // FIXME update game objects here
    }

    /* Check if the player has lost the game
     * 
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).y >= 390 ){ //Greater Than
                return true; // Enemy gets to bottom: Game Over
            }

            if(enemies.get(i).touching(player)){ //Enemy Touches Player
                return true;
            }

            for (enemyBullet Ebullet : eBullet){ //Enemy shoots Player
                if(Ebullet.touching(player)){
                    Ebullet.speed_x = 0;
                    Ebullet.speed_y = 0;
                    return true;
                }
            }
        }
        

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
        if(enemies.size() == 0){
            return true;
        } else {
            return false;
        }
        // True when all aliens die
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) { //tHIS IS WHERE THE OBJ ARE DRAWN
        
        //evil.draw(g); //Test

        //Draws all the enemies
        for (Enemy e : enemies) {
            e.draw(g);
        }
        
        //Draws the Player
        player.draw(g);

        //Draws the Players Bullets
        for (playerBullet bullet: playerBullets){
            bullet.draw(g);
        }
        
        //Draws the bullets of all the enemies
        for (enemyBullet Ebullet : eBullet) {
            Ebullet.draw(g);
        }
        // FIXME draw game objects here

    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        // Draws the Winner Screen
        Winner.draw(g);
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        // Draws the Loser Screen
        Loser.draw(g);
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
} 
