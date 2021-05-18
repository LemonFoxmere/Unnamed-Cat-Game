package com.source.excEnv.model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.source.excEnv.main.GameMain;
import com.source.excEnv.main.Resource;

public class ParallaxPanel {
	private BufferedImage img;
	public float x = 0, y = 0, speed = 0;
	private boolean changeX = false;
	
	public ParallaxPanel(BufferedImage bf, float y, float speed, boolean changeX) {
		img = bf;
		this.y = y;
		x = changeX ? (float) ((Math.random() * (GameMain.GAME_WIDTH-200))+100) : 0;
		this.changeX = changeX;
		this.speed = speed;
	}
	
	public void moveDown() {
		y += speed;
		if(y > GameMain.GAME_HEIGHT) { // pop back
			y = 50-img.getHeight();
			if (changeX) x = (float) ((Math.random() * (GameMain.GAME_WIDTH-200))+100);
		}
		
	}
	
	public void moveDown(float multiplier) {
		y += speed * multiplier;
		if(y > GameMain.GAME_HEIGHT) {
			y = 50 - img.getHeight();
			if (changeX) x = (float) ((Math.random() * (GameMain.GAME_WIDTH-200))+100);
		}
	}
	
	public void render(Graphics2D g2D) {
		g2D.drawImage(img, (int) x, (int) y, null);
	}
}
