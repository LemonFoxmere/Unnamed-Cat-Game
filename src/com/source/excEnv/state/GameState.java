package com.source.excEnv.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.source.excEnv.main.GameMain;
import com.source.excEnv.model.JumpingPlayer;
import com.source.excEnv.model.Platform;
import com.source.excEnv.model.RectModel;

public class GameState extends State {

	private JumpingPlayer catto;
	private ArrayList<Platform> platforms;
	
	@Override
	public void init() {
		catto = new JumpingPlayer(240, 100, 50, 70, 0, 0, 2.8f, 1f);
		
//		initilize platforms
		platforms = new ArrayList<>();
		platforms.add(new Platform(100, 100, 100, 20));
	}

	@Override
	public void update(float delta) {
		catto.update(); 
		catto.jumpVel = 2.8f;
		platforms.get(0).y = 400;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		
//		render platforms first
		for(Platform p : platforms) {
			p.render(g2D, Color.BLUE);
		}
		
		catto.render(g2D, Color.RED);
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
