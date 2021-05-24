package com.source.excEnv.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.source.excEnv.main.Game;
import com.source.excEnv.main.GameMain;
import com.source.excEnv.main.Resource;
import com.source.excEnv.model.Button;
import com.source.excEnv.model.JumpingPlayer;
import com.source.excEnv.model.ParallaxPanel;
import com.source.excEnv.model.Platform;
import com.source.excEnv.model.Platform.Identity;
import com.source.excEnv.model.RestartBtn;
import com.source.framework.util.CollisionHandler;

public class GameStateMedium extends State {

	private JumpingPlayer catto;
	private Button menuBtn;
	private ArrayList<Platform> platforms;
	private ArrayList<ParallaxPanel> bgPanels, effectPanel, planetPanels;
	private float moveSpeed = 2f;
	private float score = 0;
	private float topMargin = 60;
	private int winningScore = 1000;
	private boolean paused = false;
	int fps = 0;
	
	@Override
	public void init() {
		catto = new JumpingPlayer(240, 150, 50, 50, 0, 0, 12, 0.7f, 15f);
		menuBtn = new RestartBtn(GameMain.GAME_WIDTH/2-70, GameMain.GAME_HEIGHT/1.7f, 150, 51, false);
		
//		initilize platforms
		platforms = new ArrayList<>();
		int totalPlatforms = 8;
		int platWidth = 90;
		int totalPlatWidth = totalPlatforms * platWidth;
		int gapCount = totalPlatforms + 1;
		int gapWidth = (GameMain.GAME_WIDTH-totalPlatWidth) / gapCount;
		
		for(int i = 0; i < totalPlatforms; i++) {			
			platforms.add(new Platform(i*platWidth + (i+1)*gapWidth,
					(float) (GameMain.GAME_HEIGHT/2-(Math.random() * GameMain.GAME_HEIGHT*1)),
					platWidth,
					30,
					moveSpeed));
		}
		
		totalPlatforms = 8;
		totalPlatWidth = totalPlatforms * platWidth;
		gapCount = totalPlatforms + 1;
		gapWidth = (GameMain.GAME_WIDTH-totalPlatWidth) / gapCount;
		
		for(int i = 0; i < totalPlatforms-2; i++) {			
			platforms.add(new Platform(i*platWidth + (i+1)*gapWidth,
					(float) (GameMain.GAME_HEIGHT/2-(Math.random() * GameMain.GAME_HEIGHT*1) + GameMain.GAME_HEIGHT/1.5),
					platWidth,
					30,
					moveSpeed));
		}
		
		bgPanels = new ArrayList<>();
		BufferedImage panelImg = Resource.bgM; // set panel image
		bgPanels.add(new ParallaxPanel(panelImg, 0, 0.5f, false));
		bgPanels.add(new ParallaxPanel(panelImg, panelImg.getHeight(), 0.5f, false));
		
		panelImg = Resource.fgM;
		effectPanel = new ArrayList<>();
		effectPanel.add(new ParallaxPanel(panelImg, 0, 5f, false));
		effectPanel.add(new ParallaxPanel(panelImg, panelImg.getHeight(), 5f, false));
		effectPanel.add(new ParallaxPanel(panelImg, panelImg.getHeight()*2, 5f, false));
		
		planetPanels = new ArrayList<>();
		planetPanels.add(new ParallaxPanel(Resource.planet1, 0, 0.7f, true));
		planetPanels.add(new ParallaxPanel(Resource.planet2, (float) (GameMain.GAME_HEIGHT/1.5), 0.7f, true));
		
		Resource.normal.loop();
	}

	@Override
	public void update(float delta) {
		if(!(catto.isDead || catto.won || paused)) {			
			updateGameplay();
		} else if(!paused) {
			menuBtn.activated = true;
		}
		fps = (int) (1/delta);
	}

	private void updateGameplay() {
		catto.update();
		
		for(Platform p : platforms) {
			p.update(0.5f, 0.35f, 0.05f);
			p.moveSpeed = moveSpeed;
			// update Collision
			if(catto.velY > 0) {
				// detect collision
				if(CollisionHandler.collided(catto, p, catto.velY)) {
					if(p.iden == Identity.SLIME) {						
						catto.jump(20);
					} else if(p.iden == Identity.LAVA) {
						catto.kill();
					} else if(p.iden == Identity.ICE) {						
						catto.jump(true);
					} else {
						catto.jump(false);
					}
					if(p.iden == Identity.ICE) {
						p.y = GameMain.GAME_HEIGHT;
					}
					catto.started = false;
				}
			}
		}
		
		//update panel movements
		for(ParallaxPanel p : bgPanels) {
			p.moveDown();
		}
		for(ParallaxPanel p : planetPanels) {
			p.moveDown();
		}
//		for(ParallaxPanel p : effectPanel) {
//			p.moveDown();
//		}
		
		if(score >= winningScore) {
			catto.won = true;
		}
		
		// move camera with cat
		if(catto.y < topMargin) {
			catto.y -= catto.velY;
			for(Platform p : platforms) {
				p.y-=catto.velY;
			}
			score += -catto.velY/ 100f;
			//update panel movements
			for(ParallaxPanel p : bgPanels) {
				p.moveDown(-catto.velY);
			}
			for(ParallaxPanel p : planetPanels) {
				p.moveDown(-catto.velY);
			}
//			for(ParallaxPanel p : effectPanel) {
//				p.moveDown(-catto.velY);
//			}
			return;
		}
		
		if(!catto.started) {			
			score += moveSpeed / 100f;
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		// this is inefficient... Too Bad!
		
//		--------- ALL PANELS
		for(ParallaxPanel p : bgPanels) {
			p.render(g2D);
		}
		for(ParallaxPanel p : planetPanels) {
			p.render(g2D);			
		}

//		--------- render enviroment
		for(Platform p : platforms) {
			p.render(g2D);
		}
		
		catto.render(g2D, Color.RED);
		
//		for(ParallaxPanel p : effectPanel) {
//			p.render(g2D);
//		}

//		--------- ALL OVERLAYS
		if(!(catto.isDead || catto.won)) {	
			renderText(g2D);
		} else if (catto.isDead){
			
			// render overlay
			g2D.setColor(new Color(255,0,0,125));
			g2D.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		} else if (catto.won){
			// render overlay
			g2D.setColor(new Color(50,50,50,125));
			g2D.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		}
		
		if(paused) {
			g2D.setColor(new Color(50,50,50,125));
			g2D.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		}
		
		
		renderDeathWinMsg(g2D);
		menuBtn.render(g2D);
	}

	private void renderDeathWinMsg(Graphics2D g2D) {
		if(catto.isDead) { // death message
			g2D.setColor(Color.WHITE);
			g2D.setFont(Resource.font42);
			FontMetrics fm = g2D.getFontMetrics();
			String text = "You Died!"; // died
			int x = GameMain.GAME_WIDTH/2 - fm.stringWidth(text)/2;
			int y = GameMain.GAME_HEIGHT/2 - fm.getAscent()/2;
			g2D.drawString(text, x, y);
			
			String scoreText = "Score: " + (int)score + " / " + (int)winningScore; // score
			y = GameMain.GAME_HEIGHT/2 + fm.getAscent()/2 +10;
			g2D.setFont(Resource.font28);
			fm = g2D.getFontMetrics();
			x = GameMain.GAME_WIDTH/2 - fm.stringWidth(scoreText)/2;
			g2D.drawString(scoreText, x, y);
		} else if(catto.won) { // death message
			g2D.setColor(Color.WHITE);
			g2D.setFont(Resource.font42);
			FontMetrics fm = g2D.getFontMetrics();
			String text = "You Won!"; // died
			int x = GameMain.GAME_WIDTH/2 - fm.stringWidth(text)/2;
			int y = GameMain.GAME_HEIGHT/2 - fm.getAscent()/2;
			g2D.drawString(text, x, y);
			
			String scoreText = "Score: " + (int)score; // score
			y = GameMain.GAME_HEIGHT/2 + fm.getAscent()/2 +10;
			g2D.setFont(Resource.font28);
			fm = g2D.getFontMetrics();
			x = GameMain.GAME_WIDTH/2 - fm.stringWidth(scoreText)/2;
			g2D.drawString(scoreText, x, y);
		} else if(paused) {
			g2D.setColor(Color.WHITE);
			g2D.setFont(Resource.font42);
			FontMetrics fm = g2D.getFontMetrics();
			String text = "Paused"; // paused
			int x = GameMain.GAME_WIDTH/2 - fm.stringWidth(text)/2;
			int y = GameMain.GAME_HEIGHT/2 - fm.getAscent()/2;
			g2D.drawString(text, x, y);
			
			String text2 = "Click anywhere or Press ESC to resume"; // score
			y = GameMain.GAME_HEIGHT/2 + fm.getAscent()/2 + 10;
			g2D.setFont(Resource.font20);
			fm = g2D.getFontMetrics();
			x = GameMain.GAME_WIDTH/2 - fm.stringWidth(text2)/2;
			g2D.drawString(text2, x, y);
		}
	}

	private void renderText(Graphics2D g2D) {
		g2D.setColor(Color.WHITE);
		g2D.setFont(Resource.font32);
		FontMetrics fm = g2D.getFontMetrics();
		String scoreText = "Score: " + (int)score + " / " + (int)winningScore;
		int x = GameMain.GAME_WIDTH/2 - fm.stringWidth(scoreText)/2;
		int y = fm.getAscent() + 10;
		g2D.drawString(scoreText, x, y);
		
		String rocketText = "Rockets Left: " + catto.availRocket;
		y = fm.getAscent()*2 + 10;
		g2D.setFont(Resource.font16);
		fm = g2D.getFontMetrics();
		x = GameMain.GAME_WIDTH/2 - fm.stringWidth(rocketText)/2;
		g2D.drawString(rocketText, x, y);
		
		String pauseText = "Press ESC To Pause";
		g2D.setFont(Resource.font16);
		fm = g2D.getFontMetrics();
		y = fm.getAscent()+15;
		x = 15;
		g2D.drawString(pauseText, x, y);
		
		String fpsText = "FPS: " + fps + " / 125";
		g2D.setFont(Resource.font16);
		fm = g2D.getFontMetrics();
		y = fm.getAscent()+15;
		x = GameMain.GAME_WIDTH-fm.stringWidth(fpsText)-10;
		g2D.drawString(fpsText, x, y);
	}

	@Override
	public void onClick(MouseEvent e) {
		if(paused) {
			Resource.rocket.play();
		}
		paused = false;
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		catto.onKeyPress(e);	
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		catto.onKeyRelease(e);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !catto.isDead) {
			paused = !paused; // pause or unpause
			if(paused) {
				Resource.rocket.stop();
			} else {
				Resource.rocket.play();
			}
		}
	}

	@Override
	public void onMousePress(MouseEvent e) {
		menuBtn.isPressed(e);
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub
		if(menuBtn.isReleased(e)) {
			Resource.click.play();
			setCurrentState(new MenuState());
			Resource.death1.stop();
			Resource.death2.stop();
			Resource.death3.stop();
			Resource.death4.stop();
			Resource.death.stop();
			Resource.hard.stop();
			Resource.normal.stop();
			Resource.easy.stop();
			Resource.infinite.stop();
		};
	}

	@Override
	public void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
