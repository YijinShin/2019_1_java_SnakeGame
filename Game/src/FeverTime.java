package Game.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class FeverTime extends JPanel{
	//Graphics g;
	Food food;
	int BOARDWIDTH, BOARDHEIGHT;
	//boolean fstPlayerWin, sndPlayerWin;
	int PIXELSIZE;
	Snake snake1, snake2;
	Timer timer;

	public FeverTime(Food food, int BOARDWIDTH, int BOARDHEIGHT, int PIXELSIZE, Snake sn_1, Snake sn_2){
	    System.out.println("This is Fever");

	    //this.g = g;
	    this.food = food;
	    this.BOARDWIDTH = BOARDWIDTH;
	    this.BOARDHEIGHT = BOARDHEIGHT;
	    this.PIXELSIZE = PIXELSIZE;
	    this.snake1 = sn_1;
	    this.snake2 = sn_2;
	}


void EndFever(Graphics g, boolean fstPlayerWin, boolean sndPlayerWin) {	// 留덉�留� �뵾踰꾪��엫�슜 �븿�닔

	int widthNum = BOARDWIDTH/PIXELSIZE;
	int heightNum = BOARDHEIGHT/PIXELSIZE;
	food.createManyFoods(widthNum, heightNum);
	
    }

void endGame(Graphics g, int BOARDWIDTH, int BOARDHEIGHT, boolean fstPlayerWin, boolean sndPlayerWin) {

    // Create a message telling the player the game is over
    String message = "Game over";
    String winMessage = "Wins!!";
    String drawMessage = "Draws!!";
    String fstPlayer = "First Player";
    String sndPlayer = "Second Player";
    String score = "Score: ";
    String rank = "Press 'Space' to view Rankings";

    // Create a new font instance
    Font font = new Font("Times New Roman", Font.BOLD, 25);
    FontMetrics metrics = getFontMetrics(font);

    // Set the color of the text to red, and set the font
    g.setColor(Color.red);
    g.setFont(font);

    // Draw the message to the board
    g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
            BOARDHEIGHT / 2);

    //If first player wins the game
    if (fstPlayerWin == true && sndPlayerWin == false) {    
    g.drawString(fstPlayer, (BOARDWIDTH - metrics.stringWidth(fstPlayer)) / 2,
            BOARDHEIGHT / 2 + 40);
    g.drawString(winMessage, (BOARDWIDTH - metrics.stringWidth(winMessage)) / 2,
            BOARDHEIGHT / 2 + 60);
    g.drawString(score, (BOARDWIDTH - metrics.stringWidth(score)) / 2 - 60, BOARDHEIGHT / 2 + 100);
    g.drawString(String.valueOf(snake1.getScore()), (BOARDWIDTH - metrics.stringWidth(String.valueOf(snake1.getScore()))) / 2,
    		 BOARDHEIGHT / 2 + 100);
    }

    //if second player wins
    else if (sndPlayerWin == true && fstPlayerWin == false) {        
        g.drawString(sndPlayer, (BOARDWIDTH - metrics.stringWidth(sndPlayer)) / 2,
                BOARDHEIGHT / 2 + 20);
        g.drawString(winMessage, (BOARDWIDTH - metrics.stringWidth(winMessage)) / 2,
                BOARDHEIGHT / 2 + 40);
        g.drawString(score, (BOARDWIDTH - metrics.stringWidth(score)) / 2 - 60, BOARDHEIGHT / 2 + 100);
        g.drawString(String.valueOf(snake2.getScore()), (BOARDWIDTH - metrics.stringWidth(String.valueOf(snake2.getScore()))) / 2,
       		 BOARDHEIGHT / 2 + 100);
        }

    else if (fstPlayerWin ==true && sndPlayerWin == true) {    	
        g.drawString(drawMessage, (BOARDWIDTH - metrics.stringWidth(drawMessage)) / 2,
                BOARDHEIGHT / 2 + 20);
    }
    
    g.drawString(rank , (BOARDWIDTH - metrics.stringWidth(String.valueOf(rank))) / 2 , BOARDHEIGHT / 10 * 9); 

}


}
