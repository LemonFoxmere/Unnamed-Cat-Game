package com.source.excEnv.model;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.source.excEnv.main.GameMain;

public class John {
	float velX, velY;
	float x, y, w, h;
	float screenWidth, screenHeight;
	float gravity = 0.01f;
	float friction = 0;
	
	public John() {
		velX = 1;
		velY = 2;
		x = 0;
		y = 0;
		w = 100;
		h = 100;
		screenWidth = GameMain.GAME_WIDTH;
		screenHeight = GameMain.GAME_HEIGHT;
	}
	
	public John(float velX, float velY, float x, float y, float w, float h) {
		this.velX = velX;
		this.velY = velY;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		screenWidth = GameMain.GAME_WIDTH;
		screenHeight = GameMain.GAME_HEIGHT;
	}
	
	public void update() {
		velY += gravity;
		
		if(x+w > screenWidth || x < 0) {
			velX = -velX;
		}
		
		if(y > screenHeight-h && -velY+friction > 0) {
//			y = screenHeight-h-1;
			velY = 0;
		} else if(y+h > screenHeight) {
			velY = -4 + friction;
		}
		
		y += velY;
		x += velX;
	}
	
	public void keyPress(KeyEvent e) {
		if(e.getKeyCode() == e.VK_UP) {
			if(velX > 0) {
				velX++;
			} else if(velX < 0) {
				velX--;
			} else {
				velX--;
			}
		} else if(e.getKeyCode() == e.VK_DOWN) {
			if(velX > 0 ) {
				velX--;
			} else if(velX < 0) {
				velX++;
			} else {
				velX++;
			}
		}
	}
	
	public void render(Graphics2D g2D) {
		g2D.fillRect((int)x, (int)y, (int)w, (int)h);
	}
}
