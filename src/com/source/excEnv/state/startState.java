package com.source.excEnv.state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.source.excEnv.main.GameMain;
import com.source.excEnv.main.Resource;
import com.source.excEnv.model.*;

public class startState extends State {
	
	@Override
	public void init() {
		Resource.chungus.play();
	}

	@Override
	public void update(float delta) {
	}
	
	@Override
	public void render(Graphics g) {
		//DO NOT DELETE THIS LINE
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));		
		g2D.drawImage(Resource.story, 0, 0, null);	
		
		g2D.setColor(Color.BLACK);
		g2D.setFont(Resource.font42);
		FontMetrics f = g2D.getFontMetrics();
		String text = "Click Anywhere To Continue";
		int x1 = GameMain.GAME_WIDTH/2 - f.stringWidth(text)/2;
		int y1 = GameMain.GAME_HEIGHT - f.getHeight() - 30;
		g2D.drawString(text, x1, y1);
	}

	@Override
	public void onClick(MouseEvent e) {
		Resource.chungus.stop();
		setCurrentState(new MenuState());
	}

	@Override
	public void onKeyPress(KeyEvent e) {
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
	}

	@Override
	public void onMousePress(MouseEvent e) {
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
	}

	@Override
	public void mouseMove(MouseEvent e) {
	}

}
