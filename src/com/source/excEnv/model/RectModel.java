package com.source.excEnv.model;

import java.awt.Color;
import java.awt.Graphics2D;

// Just here so we can do collision detection later
public class RectModel{
	public float x, y, w, h;
	public RectModel(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void render(Graphics2D g2D, Color c) {
		g2D.setColor(c);
		g2D.fillRect((int)x, (int)y, (int)w, (int)h);
	}
}
