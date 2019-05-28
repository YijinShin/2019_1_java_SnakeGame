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
		
	// 피버타임을 구할때는 그래픽, 보드 크기 등등이 필요하므로 파라미터로 가져옴 
	public FeverTime(Graphics g, int BOARDWIDTH, int BOARDHEIGHT, boolean fstPlayerWin, boolean sndPlayerWin){
	    System.out.println("This is Fever");
	    
	    this.g = g;
	    this.BOARDWIDTH = BOARDWIDTH;
	    this.BOARDHEIGHT = BOARDHEIGHT;
	    this.fstPlayerWin = fstPlayerWin;
	    this.sndPlayerWin = sndPlayerWin;
	    
	    //endGame(g, BOARDWIDTH, BOARDHEIGHT, fstPlayerWin, sndPlayerWin);
	}
	
void MiddleFever() {	// 중간 피버타임용 함수 
	
}

void EndFever() {	// 마지막 피버타임용 함수 
	
	endGame(g, BOARDWIDTH, BOARDHEIGHT, fstPlayerWin, sndPlayerWin);
}

void endGame(Graphics g, int BOARDWIDTH, int BOARDHEIGHT, boolean fstPlayerWin, boolean sndPlayerWin) {

	// 이부분은 보드클래스에서 가져온 endGame임. 하영이가 구현한것임! 
    // Create a message telling the player the game is over
    String message = "Game over";
    String winMessage = "Wins!!";
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
    if (fstPlayerWin == false && sndPlayerWin == true) {
        g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2);
        g.drawString(sndPlayer, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2 + 20);
        g.drawString(winMessage, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2 + 40);
        }

    System.out.println("Game Ended");

}


}