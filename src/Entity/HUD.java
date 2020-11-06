package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD {
	
	private Knuckles knuckles;
	
	private BufferedImage image;
	private Font font;
	
	public HUD(Knuckles p) {
		knuckles = p;
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/HUD/hud.gif"
				)
			);
			font = new Font("Arial", Font.PLAIN, 14);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {

	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(
			knuckles.getHealth() + "/" + knuckles.getMaxHealth(),
			55,
			45
		);
		
	}
	
}













