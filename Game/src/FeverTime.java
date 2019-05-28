import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FeverTime extends JPanel{
	
	public FeverTime(int BOARDWIDTH, int BOARDHEIGHT, Graphics g){
	    System.out.println("This is Fever");

	    endGame(g, BOARDWIDTH, BOARDHEIGHT);
	}
	
	void endGame(Graphics g, int BOARDWIDTH, int BOARDHEIGHT) {
//	     Create a message telling the player the game is over
	    String message = "Game over";

	    // Create a new font instance
	    Font font = new Font("Times New Roman", Font.BOLD, 14);
	    FontMetrics metrics = getFontMetrics(font);

	    // Set the color of the text to red, and set the font
	    g.setColor(Color.red);
	    g.setFont(font);

	    // Draw the message to the board
	    g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
	            BOARDHEIGHT / 2);

	    System.out.println("Game Ended");
	}

}