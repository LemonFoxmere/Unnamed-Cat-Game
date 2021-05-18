package com.source.excEnv.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.source.excEnv.main.GameMain;
import com.source.excEnv.main.Resource;

public class JumpingPlayer extends RectModel{
	public float velX, velY;
	public float screenWidth, screenHeight;
	public float gravity = 0f;
	public float jumpVel = 0;
	public float xSpeed = 1, maxSpeed = 10f;
	public float xDecayEpsilon = 0.01f;
	public boolean moving = false; // if the control is off, start epsilon decay
	public boolean ctrlL = false, ctrlR = false; // for solving key delays
	public boolean isDead = false, started = true, won = false;
	private float rocketDuration = 10f, rocketCountDown = 0.03f, currentCountDown = 0, rocketAccel = 0.3f;
	public int availRocket;
	public boolean facingLeft = false;
	public boolean hasDied = false;
	
	public JumpingPlayer(float x, float y, float w, float h, float velX, float velY, float jumpForce, float xSpeed) {
		// initialize all fields
		super(x,y,w,h);
		this.velX = velX;
		this.velY = velY;
		this.jumpVel = jumpForce;
		this.xSpeed = xSpeed;
		// constants
		screenWidth = GameMain.GAME_WIDTH;
		screenHeight = GameMain.GAME_HEIGHT;
		availRocket = 3;
		xDecayEpsilon = xSpeed/2;
	}
	
	public JumpingPlayer(float x, float y, float w, float h, float velX, float velY, float jumpForce, float xSpeed, float maxXSpeed) {
		// initialize all fields
		super(x,y,w,h);
		this.velX = velX;
		this.velY = velY;
		this.jumpVel = jumpForce;
		this.xSpeed = xSpeed;
		// constants
		screenWidth = GameMain.GAME_WIDTH;
		screenHeight = GameMain.GAME_HEIGHT;
		availRocket = 3;
		xDecayEpsilon = xSpeed/2;
		maxSpeed = maxXSpeed;
	}
	
	public JumpingPlayer(float x, float y, float w, float h, float velX, float velY, float jumpForce, float xSpeed, int availRocket) {
		// initialize all fields
		super(x,y,w,h);
		this.velX = velX;
		this.velY = velY;
		this.jumpVel = jumpForce;
		this.xSpeed = xSpeed;
		// constants
		screenWidth = GameMain.GAME_WIDTH;
		screenHeight = GameMain.GAME_HEIGHT;
		this.availRocket = availRocket;
		xDecayEpsilon = xSpeed/2;
	}

	public void update() { // takes in collision Y position
				
		/*
		 * __________
		 * |        |
		 * |        |
		 * |        |
		 * |        |
		 * |________| <-- base of character
		 * ---------- <-- collision Y position
		 */
		
		if(currentCountDown > 0) {
			gravity = 0;
			if(velY > -30) {
				velY -= rocketAccel;
			}
			currentCountDown -= rocketCountDown;
		} else {
			gravity = 0.2f;	
			Resource.rocket.stop();
		}
		
		velY += gravity; // falling down
		
		if(started) {			
			if(y > screenHeight-h && velY > 0) { // if it is falling down, and it hits the ground
				jump(false);
			}
		} else {
			if(y > screenHeight+h+10 && velY > 0) { // if it is falling down, and it hits the ground, ur dead
				kill();
			}
		}
		
		if(!moving) {
//			execute epsilon decay
			if(velX > 0) {
				velX -= xDecayEpsilon;
			} else if(velX < 0) {
				velX += xDecayEpsilon;				
			}
		}
		
		updateXPos();
		
		y += velY;
		x += velX; // updated all position based on velocity
	}
	
	public void kill() { // when it dies
		isDead = true;
		
		Resource.hard.stop();
		
		double x = Math.random();
		if(!hasDied) {			
			if(x < 0.2) {
				Resource.death1.play();
			} else if(x < 0.4) {
				Resource.death2.play();
			} else if(x < 0.6) {
				Resource.death3.play();
			} else if(x < 0.8) {
				Resource.death4.play();
			} else {
				Resource.death.play();
			}
		} else {
			if(x < 0.25) {
				Resource.death1.play();
			} else if(x < 0.5) {
				Resource.death2.play();
			} else if(x < 0.75) {
				Resource.death3.play();
			} else {
				Resource.death4.play();
			}
		}
		
		hasDied = true;
	}

	private void updateXPos() {
		// update velX controls
		if(ctrlL) {
			velX -= xSpeed;
		} else if(ctrlR) {
			velX += xSpeed;
		}
		
		if(velX < -maxSpeed) {
			velX = -maxSpeed;
		} else if(velX > maxSpeed) {
			velX = maxSpeed;
		}
		
		// create x position loop
		if(x < -10-w) {
			x = screenWidth;
		}
		if(x > screenWidth + 10) {
			x = 0-w;
		}
	}

	public void jump(boolean isSnow) {
		velY = -jumpVel;
		if(isSnow) {
			Resource.jumpSnow.play();
		} else {			
			Resource.jumpNorm.play();
		}
	}
	
	public void jump(float offset) {
		velY = -jumpVel - offset;
		Resource.jumpSlime.play();
	}
	
	@Override
	public void render(Graphics2D g2D, Color c) {
//		g2D.setColor(c);
//		g2D.fillRect((int)x, (int)y, (int)w, (int)h);
//		render based on direction
		if(currentCountDown <= 0) {
			if(facingLeft) {
				g2D.drawImage(Resource.playerLeft, (int) x, (int) y, null);
			} else {
				g2D.drawImage(Resource.playerRight, (int) x, (int) y, null);
			}
		} else {
			if(facingLeft) {
				g2D.drawImage(Resource.playerLeftR, (int) x, (int) y, null);
			} else {
				g2D.drawImage(Resource.playerRightR, (int) x, (int) y, null);
			}
		}
	}
	
	public void onKeyPress(KeyEvent e) {
//		set moving to true, and set a velocity X
		moving = true;
		int keycode = e.getKeyCode();
		if(keycode == e.VK_LEFT) {
			ctrlL = true;
			facingLeft = true;
		} else if(keycode == e.VK_RIGHT) {
			ctrlR = true;
			facingLeft = false;
		}
		
		if(keycode == e.VK_SPACE) { // used rocket
			if(currentCountDown <= 0 && availRocket > 0) {				
				Resource.rocket.play();
			}
			if(availRocket > 0) {				
				currentCountDown = rocketDuration;
				availRocket --; 
			}
		}
	}
	
	public void onKeyRelease(KeyEvent e) {
//		set moving to false, and let x slow down
		int keycode = e.getKeyCode();
		if(keycode == e.VK_LEFT) {
			ctrlL = false;
		} if(keycode == e.VK_RIGHT) {
			ctrlR = false;
		}
		
//		only if both ctrl L and R are false, make moving false
		if(!ctrlL || !ctrlR) {			
			moving = false;
		}
	}
}
