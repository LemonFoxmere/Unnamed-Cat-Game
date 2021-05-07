package com.source.excEnv.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.source.excEnv.main.GameMain;
import com.source.excEnv.model.jumpingPlayer;

public class GameState extends State {

	private jumpingPlayer catto;
	
	@Override
	public void init() {
		catto = new jumpingPlayer(240, 100, 50, 70, 0, 0, 2.8f, 1f);
	}

	@Override
	public void update(float delta) {
		catto.update(GameMain.GAME_HEIGHT); // testing
		catto.jumpVel = 2.8f;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		
		g2D.setColor(Color.RED);
		catto.render(g2D);
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
