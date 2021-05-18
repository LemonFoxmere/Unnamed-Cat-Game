package com.source.excEnv.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.source.excEnv.main.GameMain;

public class Button extends RectModel{
	
	public boolean activated = true;
	protected boolean pressed = false;
	
	public Button(float x, float y, float w, float h) {
		super(x, y, w, h);
	}
	
	public Button(float x, float y, float w, float h, boolean active) {
		super(x, y, w, h);
		activated = active;
	}
	
	public boolean isPressed(MouseEvent e) {
		if(activated) { // only update if activated
			// check bounds
			if(e.getX() > x && e.getX() < x+w && e.getY() > y && e.getY() < y+h) {
				pressed = true;
				return true; // is within bound
			}
			pressed = false;
			return false;
		}
		pressed = false;
		return false;
	}
	
	public boolean isReleased(MouseEvent e) {
		if(activated) { // only update if activated
			// check bounds
			if(e.getX() > x && e.getX() < x+w && e.getY() > y && e.getY() < y+h) {
				pressed = false;
				return true; // is within bound
			}
			pressed = false;
		}
		pressed = false;
		return false;
	}

	public void render(Graphics2D g2D) {
		if(activated) { // only render when activated			
			if(!pressed) {
//			render state 1
				g2D.setColor(Color.BLACK);
				g2D.fillRect((int) x, (int) y, (int) w, (int) h);
			} else {
//			render state 2
				g2D.setColor(Color.RED);
				g2D.fillRect((int) x, (int) y, (int) w, (int) h);
			}
		}
	}
	
}
