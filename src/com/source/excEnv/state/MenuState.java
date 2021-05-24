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

public class MenuState extends State {
	
	private difficultyBtn easyBtn, normBtn, hardBtn, infBtn, credit;
	private boolean showingCredit;
	
	@Override
	public void init() {
//		initialize all buttons
		int btnY = 200;
		
		easyBtn = new difficultyBtn(GameMain.GAME_WIDTH/2-110/2, btnY, 110, 39);
		normBtn = new difficultyBtn(GameMain.GAME_WIDTH/2-110/2, btnY + 50, 110, 39);
		hardBtn = new difficultyBtn(GameMain.GAME_WIDTH/2-110/2, btnY + 100, 110, 39);
		infBtn = new difficultyBtn(GameMain.GAME_WIDTH/2-110/2, btnY + 150, 110, 39);
		credit = new difficultyBtn(GameMain.GAME_WIDTH/2-110/2, btnY + 200, 110, 39);
		
		showingCredit = false; 
		
		Resource.menu.loop();
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
		g2D.drawImage(Resource.bgMenu, 0, 0, null);

//		rendering Text
		g2D.setColor(Color.BLACK);
		g2D.setFont(Resource.font42);
		FontMetrics fm = g2D.getFontMetrics();
		String scoreText = "Unnamed Cat Game";
		int x = GameMain.GAME_WIDTH/2 - fm.stringWidth(scoreText)/2;
		int y = fm.getAscent() + 100;
		g2D.setColor(new Color(255, 255, 255, 245));
		g2D.fillRect(x-15, y-fm.getAscent()-12, fm.stringWidth(scoreText)+30, fm.getAscent()+30);
		g2D.setColor(Color.BLACK);
		g2D.drawString(scoreText, x, y);
		
		
		g2D.setColor(new Color(231, 182, 103));
		g2D.setStroke(new BasicStroke(5, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g2D.drawRect(x-10, y-fm.getAscent()-10, fm.stringWidth(scoreText)+20, fm.getAscent()+25);
		g2D.setColor(Color.BLACK);
		g2D.drawRect(x-15, y-fm.getAscent()-12, fm.stringWidth(scoreText)+30, fm.getAscent()+30);
		
		scoreText = "Warning: Please Be Careful While Use Headphones in ENDLESS Mode!";
		g2D.setColor(Color.BLACK);
		g2D.setFont(Resource.font20);
		fm = g2D.getFontMetrics();
		x = GameMain.GAME_WIDTH/2 - fm.stringWidth(scoreText)/2;
		y = fm.getAscent() + 165;
		g2D.drawString(scoreText, x, y);
		
//		render all buttons
		easyBtn.render(g2D, Resource.btn1, Resource.btn2);
		normBtn.render(g2D, Resource.btn3, Resource.btn4);
		hardBtn.render(g2D, Resource.btn5, Resource.btn6);
		infBtn.render(g2D, Resource.btn7, Resource.btn8);
		credit.render(g2D, Resource.btn9, Resource.btn10);
		
		g2D.drawImage(Resource.dirL, 20, GameMain.GAME_HEIGHT-Resource.dirL.getHeight()-110, null);
		
		g2D.drawImage(Resource.dirR, 40 + Resource.dirL.getWidth(), GameMain.GAME_HEIGHT-Resource.dirR.getHeight()-110, null);
		
		g2D.drawImage(Resource.dirS, GameMain.GAME_WIDTH - Resource.dirS.getWidth()- 20, GameMain.GAME_HEIGHT-Resource.dirS.getHeight()-110, null);
		
		if(showingCredit) {		
			g2D.setColor(new Color(0, 0, 0, 120));
			g2D.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
			g2D.setColor(new Color(255, 255, 255, 255));
			g2D.fillRect(50, 50, GameMain.GAME_WIDTH-100, GameMain.GAME_HEIGHT-100);
			
			g2D.setStroke(new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
			g2D.setColor(new Color(231, 182, 103));
			g2D.drawRect(55, 55, GameMain.GAME_WIDTH-110, GameMain.GAME_HEIGHT-110);
			g2D.setStroke(new BasicStroke(5, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
			g2D.setColor(Color.BLACK);
			g2D.drawRect(50, 50, GameMain.GAME_WIDTH-100, GameMain.GAME_HEIGHT-100);
			
			g2D.setColor(Color.BLACK);
			g2D.setFont(Resource.font32);
			FontMetrics f = g2D.getFontMetrics();
			String text = "Credits";
			int x1 = GameMain.GAME_WIDTH/2 - fm.stringWidth(text)/2;
			int y1 = fm.getAscent() + 80;
			g2D.drawString(text, x1, y1);

			g2D.setFont(Resource.font28);
			g2D.drawString("Our Dev Team", 90, y1+35);
			
			y1 = fm.getAscent() + 110;
			g2D.setFont(Resource.font24);
			f = g2D.getFontMetrics();
			g2D.drawString("Justin Li (Game Logic & Framework Development)", 70, y1+35);
			y1 += 35;
			g2D.drawString("Ronald Lac (Image Assets & Music/SFX Creation)", 70, y1+25);
			y1 += 25;
			g2D.drawString("Joseph Sung (Image Assets & Music)", 70, y1+25);
			y1 += 25;
			g2D.drawString("Brian Ni (Game Logic & Sketches)", 70, y1+25);
			
			y1 += 60;
			
			g2D.setFont(Resource.font28);
			g2D.drawString("External Assets Used", 90, y1+25);
			
			y1 += 20;
			g2D.setFont(Resource.font24);
			
			g2D.drawString("Trance - 009 Sound System Dreamscape", 70, y1+35);
			y1 += 35;
			g2D.drawString("Bobby Cole - Trance Music for Racing Game", 70, y1+25);
			y1 += 50;
			g2D.drawString("Carro Whatsapp Full (Whatsapp Drip)", 70, y1);
			
			g2D.setFont(Resource.font16);
			text = "Copyright (C) 2021  Justin Li, Ronald Lac, Joseph Sung, Brian Ni";
			f = g2D.getFontMetrics();
			x1 = GameMain.GAME_WIDTH/2 - f.stringWidth(text)/2;
			y1 = fm.getAscent() + GameMain.GAME_HEIGHT-83;
			g2D.drawString(text, x1, y1);
			
			text = "Press ESC to return to menu";
			f = g2D.getFontMetrics();
			x1 = GameMain.GAME_WIDTH/2 - f.stringWidth(text)/2;
			y1 = fm.getAscent() + GameMain.GAME_HEIGHT-100;
			g2D.drawString(text, x1, y1);
		}
		
	}

	@Override
	public void onClick(MouseEvent e) {
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_1:
				Resource.menu.stop();
				setCurrentState(new GameStateEasy());
				break;
			case KeyEvent.VK_2:
				Resource.menu.stop();
				setCurrentState(new GameStateMedium());
				break;
			case KeyEvent.VK_3:
				Resource.menu.stop();
				setCurrentState(new GameStateHard());	
				break;
			case KeyEvent.VK_4:
				Resource.menu.stop();
				setCurrentState(new GameStateEndless());
				break;
			case KeyEvent.VK_ESCAPE:
				showingCredit = false;
				break;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
	}

	@Override
	public void onMousePress(MouseEvent e) {
		easyBtn.isPressed(e);
		normBtn.isPressed(e);
		hardBtn.isPressed(e);
		infBtn.isPressed(e);
		credit.isPressed(e);
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		if(easyBtn.isReleased(e)) {
			Resource.click.play();
			setCurrentState(new GameStateEasy());
			Resource.menu.stop();
		}if(normBtn.isReleased(e)) {
			Resource.click.play();
			setCurrentState(new GameStateMedium());
			Resource.menu.stop();
		}if(hardBtn.isReleased(e)) {
			Resource.click.play();
			setCurrentState(new GameStateHard());
			Resource.menu.stop();
		}if(infBtn.isReleased(e)) {
			Resource.click.play();
			setCurrentState(new GameStateEndless());
			Resource.menu.stop();
		}if(credit.isReleased(e)) {
			Resource.click.play();
			showingCredit = true;
		}
	}

	@Override
	public void mouseMove(MouseEvent e) {
	}

}
