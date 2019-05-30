package Game.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FeverTime extends JPanel{
	Graphics g;
	int BOARDWIDTH, BOARDHEIGHT;
	boolean fstPlayerWin, sndPlayerWin;
	int PIXELSIZE;
	
	// �뵾踰꾪��엫�쓣 援ы븷�븣�뒗 洹몃옒�뵿, 蹂대뱶 �겕湲� �벑�벑�씠 �븘�슂�븯誘�濡� �뙆�씪誘명꽣濡� 媛��졇�샂 
	public FeverTime(Graphics g, int BOARDWIDTH, int BOARDHEIGHT, boolean fstPlayerWin, boolean sndPlayerWin, int PIXELSIZE){
	    System.out.println("This is Fever");
	    
	    this.g = g;
	    this.BOARDWIDTH = BOARDWIDTH;
	    this.BOARDHEIGHT = BOARDHEIGHT;
	    this.fstPlayerWin = fstPlayerWin;
	    this.sndPlayerWin = sndPlayerWin;
	    this.PIXELSIZE = PIXELSIZE;
	}
	
void MiddleFever() {	// 以묎컙 �뵾踰꾪��엫�슜 �븿�닔 
	
}

void EndFever() {	// 留덉�留� �뵾踰꾪��엫�슜 �븿�닔
	int [][] foodPosition;

//	
//	Food food = new Food(); 
//	food.createManyFoods(BOARDWIDTH, BOARDHEIGHT);
//	foodPosition = food.getFoods();
//	
//    g.setColor(Color.RED);
//    for(int i = 0; i < BOARDWIDTH; i++) {
//        for(int j = 0; j < BOARDHEIGHT; j++) {
//        	if(foodPosition[i][j] == 1)
//        		g.fillRect(i, j, PIXELSIZE, PIXELSIZE); // food
//        }
//    }
//	
	endGame(g, BOARDWIDTH, BOARDHEIGHT, fstPlayerWin, sndPlayerWin);
}

void endGame(Graphics g, int BOARDWIDTH, int BOARDHEIGHT, boolean fstPlayerWin, boolean sndPlayerWin) {

	// �씠遺�遺꾩� 蹂대뱶�겢�옒�뒪�뿉�꽌 媛��졇�삩 endGame�엫
    // Create a message telling the player the game is over
    String message = "Game over";
    String winMessage = "Wins!!";
    String drawMessage = "Draws!!";
    String fstPlayer = "First Player";
    String sndPlayer = "Second Player";

    // Create a new font instance
    Font font = new Font("Times New Roman", Font.BOLD, 25);
    FontMetrics metrics = getFontMetrics(font);

    // Set the color of the text to red, and set the font
    g.setColor(Color.red);
    g.setFont(font);

    // Draw the message to the board

    //If fisrt player wins the game
    if (fstPlayerWin == true && sndPlayerWin == false) {
    g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
            BOARDHEIGHT / 2);
    g.drawString(fstPlayer, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
            BOARDHEIGHT / 2 + 40);
    g.drawString(winMessage, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
            BOARDHEIGHT / 2 + 60);
    }

    //if second player wins
    else if (sndPlayerWin == true && fstPlayerWin == false) {
        g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2);
        g.drawString(sndPlayer, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2 + 20);
        g.drawString(winMessage, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2 + 40);
        }
    
    else if (fstPlayerWin ==true && sndPlayerWin == true) {
    	g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2);
        g.drawString(drawMessage, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2 + 20);
    }

    //System.out.println("Game Ended");

}


}