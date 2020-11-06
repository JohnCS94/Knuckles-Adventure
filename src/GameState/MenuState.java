package GameState;

import Audio.AudioPlayer;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class MenuState extends GameState {
	
	private Background bg;
	private BufferedImage icon;

	private Font font;
	private AudioPlayer bgMusic;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
try {
			
			//Loads Background
			bg = new Background("/Menu/Island_Background.gif", 1);
			bg.setVector(-0.125, 0);
			//Loads Emblem
			icon = ImageIO.read(getClass().getResourceAsStream("/Menu/Emblem2.gif"));
			font = new Font("Times New Roman", Font.PLAIN, 13);
					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void init() {
		
		bgMusic = new AudioPlayer("/Music/Intro.mp3");
		bgMusic.play();
		
	}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		g.drawImage(icon, 0, 0, null);

		g.setFont(font);
		g.drawString("CSE 2102", 10, 510);
		
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(GameStateManager.LEVEL1STATE);}
		}
	
	public void keyReleased(int k) {}
	
}










