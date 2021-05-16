package com.source.excEnv.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.source.excEnv.main.GameMain;
import com.source.excEnv.model.JumpingPlayer;
import com.source.excEnv.model.Platform;

public class GameState extends State {

	private JumpingPlayer catto;
	private ArrayList<Platform> platforms;
	private float moveSpeed = 0.5f;
	private float score = 0;
	
	@Override
	public void init() {
		catto = new JumpingPlayer(240, 100, 50, 50, 0, 0, 2.8f, 1f);
		
//		initilize platforms
		platforms = new ArrayList<>();
		int totalPlatforms = 8;
		int platWidth = 100;
		int totalPlatWidth = totalPlatforms * platWidth;
		int gapCount = totalPlatforms + 1;
		int gapWidth = (GameMain.GAME_WIDTH-totalPlatWidth) / gapCount;
		
		for(int i = 0; i < totalPlatforms; i++) {			
			platforms.add(new Platform(i*platWidth + (i+1)*gapWidth,
					(float) (GameMain.GAME_HEIGHT/2-(Math.random() * GameMain.GAME_HEIGHT*1.5)),
					platWidth,
					30,
					moveSpeed));
		}
	}

	@Override
	public void update(float delta) {
		if(!catto.isDead) {			
			updateGameplay();
		}
	}

	private void updateGameplay() {
		catto.update(GameMain.GAME_HEIGHT); // testing
		catto.jumpVel = 2.8f;
		
		for(Platform p : platforms) {
			p.update();
			p.moveSpeed = moveSpeed;
		}
		
		moveSpeed = 0.2f;
		
		score += moveSpeed / 200f;
		
		// update Collision
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

		for(Platform p : platforms) {
			p.render(g2D);
		}
		
		// render player
		g2D.setColor(Color.RED);
		catto.render(g2D);
		
		renderText(g2D);
	}

	private void renderText(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("Poppins", Font.BOLD, 32));
		FontMetrics fm = g2D.getFontMetrics();
		String text = "Score: " + (int)score;
		int x = GameMain.GAME_WIDTH/2 - fm.stringWidth(text)/2;
		int y = fm.getAscent() + 10;
		g2D.drawString(text, x, y);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		catto.onKeyPress(e);		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		catto.onKeyRelease(e);
	}

	@Override
	public void onMousePress(MouseEvent e) {
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
