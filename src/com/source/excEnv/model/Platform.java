package com.source.excEnv.model;

import java.awt.Color;
import java.awt.Graphics2D;

import com.source.excEnv.main.GameMain;

public class Platform {
	public enum Identity{
		NORMAL,
		LAVA,
		ICE,
		SLIME
	}

	public float x, y, w, h, moveSpeed;
	public Identity iden;
	
	public Platform(float x, float y, float w, float h, float moveSpeed) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.moveSpeed = moveSpeed;
		iden = Identity.NORMAL; // all starts off as normal
	}
	
	public void update() {
//		move platform down, and when below screen width, come back up
		y += moveSpeed;
		if(y > GameMain.GAME_HEIGHT + h) {
			y = (float) (-h - (Math.random() * GameMain.GAME_HEIGHT)); // teleport to a randomized height
			
			// randomize platform types
			// 5% slime 60% normal 10% lava 25% ice
			double x = Math.random();
			
			if(x < 0.6) {
				iden = Identity.NORMAL;
			} else if (x < 0.85) {
				iden = Identity.ICE;
			} else if (x < 0.95) {
				iden = Identity.LAVA;
			} else {
				iden = Identity.SLIME;
			}
		}
	}
	
	public void render(Graphics2D g2d) {
		// render color based off of Identity
		switch(iden) {
			case NORMAL:
				g2d.setColor(Color.GRAY);
				g2d.fillRect((int) x, (int) y, (int) w, (int) h);
				break;
			case LAVA:
				g2d.setColor(Color.ORANGE);
				g2d.fillRect((int) x, (int) y, (int) w, (int) h);
				break;
			case ICE:
				g2d.setColor(Color.BLUE);
				g2d.fillRect((int) x, (int) y, (int) w, (int) h);
				break;
			case SLIME:
				g2d.setColor(Color.GREEN);
				g2d.fillRect((int) x, (int) y, (int) w, (int) h);
				break;
			default:
				g2d.setColor(Color.BLACK);
				g2d.fillRect((int) x, (int) y, (int) w, (int) h);
		}
	}
}
