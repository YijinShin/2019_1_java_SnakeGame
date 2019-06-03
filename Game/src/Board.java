package Game.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

// TODO: Implement a way for the player to win

// Holds height and width of the window
private final static int BOARDWIDTH = 800;
private final static int BOARDHEIGHT = 800;

// Used to represent pixel size of food & our snake's joints
private final static int PIXELSIZE = 10;

// The total amount of pixels the game could possibly have.
// We don't want less, because the game would end prematurely.
// We don't more because there would be no way to let the player win.

private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT)
        / (PIXELSIZE * PIXELSIZE);

// Check to see if the game is running
private int inGame = 1;
private int oldTime;
private int nowTime;

private boolean fstPlayerWin = false;
private boolean sndPlayerWin = false;
public static boolean check = false;
public static boolean check_2 = false;
private boolean fever = true;

// Timer used to record tick times
private Timer timer;

// Used to set game speed, the lower the #, the faster the snake travels
// which in turn
// makes the game harder.
private static int speed = 40;

// Instances of our snake & food so we can use their methods
private Snake snake = new Snake();
private Snake snake2 = new Snake();
private Food food = new Food();
private ArrayList<Poison> poison = new ArrayList<Poison>();
// Collision 媛앹껜 (以��쁽)
private Collisions collision = new Collisions(snake, snake2, food, poison);
private FeverTime feverTime = new FeverTime(food,  BOARDWIDTH, BOARDHEIGHT, PIXELSIZE, snake, snake2);

// Rank
Rank rk = new Rank();

public Board() {

    addKeyListener(new Keys());
    setBackground(Color.black);
    setFocusable(true);

    setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

    initializeGame();
}

// Used to paint our components to the screen
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!check) {
        startGame(g, BOARDWIDTH, BOARDHEIGHT);
    }
    if (check) {
        draw(g);
    }
    if (check && check_2) {
    	printRanking(g, snake, snake2);
    }
}

// Draw our Snake & Food (Called on repaint()).
void draw(Graphics g) {

    // Only draw if the game is running / the snake is alive
    if (inGame == 1) {
    	// Draw scores
    	printScore(g);
    	
    	// Set object sizes
        g.setColor(Color.RED);
        g.fillRect(food.getFoodX(), food.getFoodY(), PIXELSIZE, PIXELSIZE); // food
        g.setColor(Color.blue);
        for (int i = 0; i < poison.size(); i++) {
            g.fillRect(poison.get(i).getPoisonX(), poison.get(i).getPoisonY(), PIXELSIZE, PIXELSIZE); // poison
        }
        // Draw our snake.
        for (int i = 0; i < snake.getJoints(); i++) {
            // Snake's head
            if (i == 0) {
                g.setColor(Color.darkGray);
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
                // Body of snake
            } else {
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
            }
        }
        for (int i = 0; i < snake2.getJoints(); i++) {
            // Snake's head
            if (i == 0) {
                g.setColor(Color.white);

                // Body of snake
                g.fillRect(snake2.getSnakeX(i), snake2.getSnakeY(i), PIXELSIZE, PIXELSIZE);
            } else {

                g.fillRect(snake2.getSnakeX(i), snake2.getSnakeY(i), PIXELSIZE, PIXELSIZE);
            }
        }

        // Sync our graphics together
        Toolkit.getDefaultToolkit().sync();
    }
    else if(inGame == 2) {
    	
    	if(fever) {
    		feverTime.EndFever(g, collision.getFstPlayerWin(), collision.getSndPlayerWin());
    		fever = false;
    	}
    	
    	collision.checkManyFoodCollisions(BOARDWIDTH/PIXELSIZE, BOARDHEIGHT/PIXELSIZE,PIXELSIZE);

        for(int i = 0; i < BOARDHEIGHT/PIXELSIZE; i++) {
            for(int j = 0; j < BOARDWIDTH/PIXELSIZE; j++) {
            	if(food.foodPosition[i][j] == 1) {
            		g.setColor(Color.RED);
            		g.fillRect(i*10, j*10, PIXELSIZE, PIXELSIZE); // food
            	}else {
            		g.setColor(Color.BLACK);
            		g.fillRect(i*10, j*10, PIXELSIZE, PIXELSIZE); // food

            	}
            }
        }
        
        // Draw scores
        printScore(g);
        
        // Draw our snake.
        for (int i = 0; i < snake.getJoints(); i++) {
            // Snake's head
            if (i == 0) {
                g.setColor(Color.darkGray);
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE);
                // Body of snake
            } else {
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE);
            }
        }
        for (int i = 0; i < snake2.getJoints(); i++) {
            // Snake's head
            if (i == 0) {
                g.setColor(Color.white);

                // Body of snake
                g.fillRect(snake2.getSnakeX(i), snake2.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE);
            } else {

                g.fillRect(snake2.getSnakeX(i), snake2.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE);
            }
        }

        // Sync our graphics together
        Toolkit.getDefaultToolkit().sync();


    }
    else if(inGame == 0) {
    	feverTime.endGame(g, BOARDWIDTH, BOARDHEIGHT, collision.getFstPlayerWin(), collision.getSndPlayerWin());
    	 	 
    		
        //System.out.println("꺄라ㅏ라라라라락 ");
    }
    
    else if (inGame == 3) {
    	printRanking(g, snake, snake2);
    }
    
}

void initializeGame() {
    snake.setJoints(10); // set our snake's initial size
    snake2.setJoints(10);
    
    collision.setFstPlayerWin(false);
    collision.setSndPlayerWin(false);
    

    // Create our snake's body
    for (int i = 0; i < snake.getJoints(); i++) {
        snake.setSnakeX(BOARDWIDTH / 4);
        snake.setSnakeY(BOARDHEIGHT / 4);
        snake2.setSnakeX(BOARDWIDTH / 4 * 3);
        snake2.setSnakeY(BOARDHEIGHT / 4 * 3);
    }
    // Start off our snake moving right
    snake.setMovingRight(true);
    snake2.setMovingLeft(true);

    // Generate our first 'food'
    food.createFood();
    for (int i = 0; i < 10; i++) {
        System.out.println("사과 생성중 " + poison.size());
        poison.add(new Poison());
        poison.get(i).createPoison();
    }

    // set the timer to record our game's speed / make the game move
    timer = new Timer(speed, this);
    timer.start();
}

// Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == 1) {

        collision.checkFoodCollisions();	// �궗怨쇱� 遺��뵬移섎뒗 吏�(以��쁽)
        collision.checkPoisonCollisions();	// �뜦�� �궗怨쇱� 遺��뵬移섎뒗 吏�(以��쁽)
        inGame=collision.checkPoisonCollisions();	// �뜦�� �궗怨쇱� 遺��뵬移섎뒗 吏�(以��쁽)

        inGame = collision.checkCollisions(BOARDWIDTH, BOARDHEIGHT); // 諭��겮由� 遺��뵬移섎뒗(以��쁽)
	    // -> 諛붾줈 �쐵�씪�씤�씠 width, height �쐞移� 援щ퀎�빐�꽌 諭� �쐞移� 議곗젙�븯�뒗�뜲 �씠 湲곕뒫 �뵶�뜲濡� 媛��빞�븷 �벏 �닔�젙 �븘�슂�븿 (以��쁽)
	    // If the game has ended, then we can stop our timer



        snake.move();
        snake2.move();

        //System.out.println("뱀1 :" +snake.getSnakeX(0) + " " + snake.getSnakeY(0) + ", 뱀2 :" + snake2.getSnakeX(0)
        //        + " " + snake2.getSnakeY(0) + ", 사과 :" + food.getFoodX() + ", " + food.getFoodY()
        //        + ", 독사과 :" + poison.getPoisonX() +", " + poison.getPoisonY());
        oldTime = (int) System.currentTimeMillis() / 1000;
    }
    else if (inGame == 2) {
    	//inGame = 여기 뭔가 들어가

    	if(collision.getFstPlayerWin() == true && collision.getSndPlayerWin() == false)
    		snake.move();
    	else if(collision.getFstPlayerWin() == false && collision.getSndPlayerWin() == true)
    		snake2.move();
    	collision.checkManyFoodCollisions(BOARDWIDTH/PIXELSIZE, BOARDHEIGHT/PIXELSIZE,PIXELSIZE);

        // System.out.println("뱀1 :" +snake.getSnakeX(0) + " " + snake.getSnakeY(0) + ", 뱀2 :" + snake2.getSnakeX(0)
        // + " " + snake2.getSnakeY(0) + ", 사과 :" + food.getFoodX() + ", " + food.getFoodY()
        // + ", 독사과 :" + poison.getPoisonX() +", " + poison.getPoisonY());

        nowTime = (int) System.currentTimeMillis() / 1000;
        int cc = nowTime - oldTime;
        System.out.println("Timer : " + cc );

        if(nowTime - oldTime == 5)
            inGame = 0;


        if (inGame == 0) {
            timer.stop();
        }
    }
    // Repaint or 'render' our screen
    repaint();
}
private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int startEvent = e.getKeyCode();
        int key = e.getKeyCode();
        int key2 = e.getKeyCode();

        if (startEvent == KeyEvent.VK_ENTER) {
            Board.check = true;
        }
        

        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key2 == KeyEvent.VK_A) && (!snake2.isMovingRight())) {
            snake2.setMovingLeft(true);
            snake2.setMovingUp(false);
            snake2.setMovingDown(false);
        }

        if ((key2 == KeyEvent.VK_D) && (!snake2.isMovingLeft())) {
            snake2.setMovingRight(true);
            snake2.setMovingUp(false);
            snake2.setMovingDown(false);
        }

        if ((key2 == KeyEvent.VK_W) && (!snake2.isMovingDown())) {
            snake2.setMovingUp(true);
            snake2.setMovingRight(false);
            snake2.setMovingLeft(false);
        }

        if ((key2 == KeyEvent.VK_S) && (!snake2.isMovingUp())) {
            snake2.setMovingDown(true);
            snake2.setMovingRight(false);
            snake2.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_ENTER) && (((inGame == 0) || (inGame ==3)))) {

            inGame = 1;

            // stop snakes' movement
            snake.setMovingDown(false);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setMovingUp(false);

            snake2.setMovingDown(false);
            snake2.setMovingRight(false);
            snake2.setMovingLeft(false);
            snake2.setMovingUp(false);

            // initialize the win-value
            fstPlayerWin = false;
            sndPlayerWin = false;
            
            // initialize the score
            snake.setScore(0);
            snake2.setScore(0);
            
            fever = true;
            
            check_2 = false;

            initializeGame();
        }
              
    }
}


    void startGame(Graphics g, int BOARDWIDTH, int BOARDHEIGHT) {
        // Create a message telling the player the game is over
        String gameTitle = "== Snake Game ==";
        String startmessage = "Press the Enter";
        String explanation1 = "1p : use a,w,d,s  	2p : user ◀,▲,▶,▼";
        // String explanation2 ="2p : user ◀,▲,▶,▼";

        // Create a new font instance
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);

        // Set the color of the text to red, and set the font
        g.setColor(Color.red);
        g.setFont(font);

        g.drawString(gameTitle, (BOARDWIDTH - metrics.stringWidth(gameTitle)) / 2, BOARDHEIGHT / 2 - 40);
        g.drawString(startmessage, (BOARDWIDTH - metrics.stringWidth(startmessage)) / 2, BOARDHEIGHT / 2);
        // g.setColor(Color.gray);
        g.drawString(explanation1, (BOARDWIDTH - metrics.stringWidth(explanation1)) / 2, BOARDHEIGHT / 2 + 40);
        for (int i = 0; i < 10; i++) {
            g.setColor(Color.darkGray);
            g.fillRect(metrics.stringWidth(explanation1) / 2 + 10 * i, BOARDHEIGHT / 2 + 70, PIXELSIZE, PIXELSIZE);
        }
    }

    private void printScore(Graphics g) {
        int intScore_1P = snake.getScore();
        int intScore_2P = snake2.getScore();

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);

        // Set the color of the text to red, and set the font
        g.setColor(Color.white);
        g.setFont(font);

        // Draw the message to the board
        g.drawString(String.valueOf(intScore_1P), 
        		(BOARDWIDTH - metrics.stringWidth(String.valueOf(intScore_1P))) / 4, 50);
        g.drawString(String.valueOf(intScore_2P),
                (BOARDWIDTH - metrics.stringWidth(String.valueOf(intScore_2P))) * 3 / 4, 50);

    }
    
    private void printRanking(Graphics g, Snake sn_1, Snake sn_2) {
    	
    	int intScore_1P = sn_1.getScore();
   	    int intScore_2P = sn_2.getScore();
   	    String name_1P = sn_1.getName();
   	    String name_2P = sn_2.getName();
        
        
     	if(collision.getFstPlayerWin() == true && collision.getSndPlayerWin() == false) {
      	    rk.addScore(name_1P, intScore_1P);
     	 }
     	else if(collision.getFstPlayerWin() == false && collision.getSndPlayerWin() == true) {
      		rk.addScore(name_2P, intScore_2P);
     	}
    	
    	Font font = new Font("Times New Roman", Font.BOLD, 30);
        FontMetrics metrics = getFontMetrics(font);
        int height = BOARDHEIGHT / 15;
        String space = "               ";
        String restart = "Press 'Enter' to restart!";

        // Set the color of the text to red, and set the font
        g.setColor(Color.white);
        g.setFont(font);
        
        
        for (int i = 1; i < 10; i += 2) {
        	g.drawString( i +". " + rk.getScores().get(i - 1).getName() + space +
        rk.getScores().get(i - 1).getScore(), 50, height * i + height);
        }
        for (int i = 2; i < 11; i += 2) {
        	g.drawString( i +". " + rk.getScores().get(i - 1).getName() + space +
        rk.getScores().get(i - 1).getScore(), 415, height * i);
        }
        
        g.drawString(restart , (BOARDWIDTH - metrics.stringWidth(String.valueOf(restart))) / 2 , height * 13);      
        
        System.out.print(rk.getHighscoreString());
        
    }


public static int getAllDots() {
    return TOTALPIXELS;
}

public static int getDotSize() {
    return PIXELSIZE;
}
}
