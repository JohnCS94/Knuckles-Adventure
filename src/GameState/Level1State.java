package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Audio.AudioPlayer;
import Main.Game;

import javax.swing.JFrame;

import com.sun.java.swing.plaf.windows.resources.windows;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;



public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Knuckles knuckles;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	
	private HUD hud;
	
	private AudioPlayer bgMusic;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(96);
		tileMap.loadTiles("/Tilesets/TileSet.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/SunSetHill1.gif", 0.0);
		
		knuckles = new Knuckles(tileMap);
		knuckles.setPosition(140, 300);
		
		populateEnemies();
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(knuckles);
		
		bgMusic = new AudioPlayer("/Music/Tee.mp3");
		bgMusic.play();
		
	}
	
	private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		
		Motobug m;
		Point[] points = new Point[] {
			new Point(200, 100),
			new Point(860, 200),
			new Point(2496, 200),
			new Point(3168, 200),
			new Point(3744, 200),
			new Point(4608, 350)
		};
		for(int i = 0; i < points.length; i++) {
			m = new Motobug(tileMap);
			m.setPosition(points[i].x, points[i].y);
			enemies.add(m);
		}
		
	}
	
	public void update() {
		
		// update player
		knuckles.update();
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - knuckles.getx(),
			GamePanel.HEIGHT / 2 - knuckles.gety()
		);
		
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies
		knuckles.checkAttack(enemies);
		
		// update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
			}
		}
		
		// update explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		knuckles.draw(g);
		
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		// draw explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
		
		// draw hud
		hud.draw(g);
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) knuckles.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) knuckles.setRight(true);
		if(k == KeyEvent.VK_UP) knuckles.setUp(true);
		if(k == KeyEvent.VK_DOWN) knuckles.setDown(true);
		if(k == KeyEvent.VK_W) knuckles.setJumping(true);
		if(k == KeyEvent.VK_E) knuckles.setGliding(true);
		if(k == KeyEvent.VK_R) knuckles.setPunching();
		if(k == KeyEvent.VK_F) knuckles.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) knuckles.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) knuckles.setRight(false);
		if(k == KeyEvent.VK_UP) knuckles.setUp(false);
		if(k == KeyEvent.VK_DOWN) knuckles.setDown(false);
		if(k == KeyEvent.VK_W) knuckles.setJumping(false);
		if(k == KeyEvent.VK_E) knuckles.setGliding(false);
	}
	
}
