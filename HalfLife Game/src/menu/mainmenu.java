package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Menu {
	
	public Rectangle singleButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle multiButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
	public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("HALFLIFE", Game.WIDTH / 2, 100);
		
		Font gnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Single Player", singleButton.x + 20, singleButton.y + 30);
		g2d.draw(singleButton);
		g.drawString("Multi-Players", multiButton.x + 20, multiButton.y + 30);
		g2d.draw(multiButton);
		g.drawString("Exit", exitButton.x + 20, exitButton.y + 30);
		g2d.draw(exitButton);
	}
}
	
