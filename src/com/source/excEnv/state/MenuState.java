package com.source.excEnv.state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class MenuState extends State {
	
	@Override
	public void init() {
	}

	@Override
	public void update(float delta) {
//		internal updates of menu state
		
//		temporary code: automatically transition into gameplay state
//		there will most likely be two types of game state, but there will be just one for now
	}
	
	@Override
	public void render(Graphics g) {
		//DO NOT DELETE THIS LINE
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
	
//		rendering the UI
		g2D.drawImage(Resource.bgMenu, 0, 0, null);
	}

	@Override
	public void onClick(MouseEvent e) {
//		DO NOT PROCESS KEY PRESSES HERE UNLESS YOU WANT PAIN
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_1:
				setCurrentState(new GameStateEasy());
				break;
			case KeyEvent.VK_2:
				setCurrentState(new GameStateMedium());
				break;
			case KeyEvent.VK_3:
				setCurrentState(new GameStateHard());	
				break;
			case KeyEvent.VK_4:
				setCurrentState(new GameStateEndless());
				break;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
	}

	@Override
	public void onMousePress(MouseEvent e) {
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
//		all mouse click handeling here
	}

	@Override
	public void mouseMove(MouseEvent e) {
//		all mouse mouse handeling here
	}

}
