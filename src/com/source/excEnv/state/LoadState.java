package com.source.excEnv.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.source.excEnv.main.Resource;

public class LoadState extends State {

	@Override
	public void init() {
		Resource.load();
		System.out.println("Loaded all files successfully");
	}

	@Override
	public void update(float delta) {
		//change current state to menu state
		setCurrentState(new startState());
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public void onClick(MouseEvent e) {
	}

	@Override
	public void onKeyPress(KeyEvent e) {
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
	}

	@Override
	public void onMousePress(MouseEvent e) {
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
	}

	@Override
	public void mouseMove(MouseEvent e) {
	}

}
