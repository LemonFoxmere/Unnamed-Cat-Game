package com.source.excEnv.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import com.source.excEnv.main.GameMain;

public class JumpingPlayer extends RectModel{
	public float velX, velY;
	public float screenWidth, screenHeight;
	public float gravity = 0.01f;
	public float jumpVel = 0;
	public float xDecayEpsilon = 0.01f;
	public float xSpeed = 1;
	public boolean moving = false; // if the control is off, start epsilon decay
	public boolean ctrlL = false, ctrlR = false; // for solving key delays
	
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
		
		velY += gravity; // falling down
		
		if(y > screenHeight-h && velY > 0) { // if it is falling down, and it hits the ground
			jump();
		} else if(y < 0) { // if it hits the ceiling
			velY = -velY;
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

	public void jump() {
		velY = -jumpVel; // make it jump back
	}

	private void updateXPos() {
		// update velX controls
		if(ctrlL) {
			velX = -1;
		} else if(ctrlR) {
			velX = 1;
		}
		
		// create x position loop
		if(x < -10-w) {
			x = screenWidth;
		}
		if(x > screenWidth + 10) {
			x = 0-w;
		}
	}
	
	@Override
	public void render(Graphics2D g2D, Color c) {
		g2D.setColor(c);
		g2D.fillRect((int)x, (int)y, (int)w, (int)h);
//		TODO: ADD CUSTOM RENDERING 
	}
	
	public void onKeyPress(KeyEvent e) {
//		set moving to true, and set a velocity X
		moving = true;
		int keycode = e.getKeyCode();
		if(keycode == e.VK_LEFT) {
			ctrlL = true;
		} else if(keycode == e.VK_RIGHT) {
			ctrlR = true;
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
