package com.source.excEnv.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.net.URL;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resources;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.source.framework.util.AudioPlayer;

public class Resource {
	public static BufferedImage iconimage, platNormal, platLava, platCloud,
	platSlime, bgM, fgM, bgE, bgH, playerLeft, playerRight, playerLeftR, playerRightR,
	restartNorm, restartPressed, planet1, planet2, planet3, planet4, cloud1, cloud2, bgMenu;
	
	/*
	 * these are for high quality playback that requires pausing and repeating. Songs and commetary are advised
	 * to be used here. However, buffer and loading time may slow down frame rate if used too rapidly.
	 */
//	public static AudioPlayer start; 
	// this does not work. i dont know why. I do not have to wonder why. I do not have to ask why.
	// but this shit won't work unless we do this terriblness
	
	/*
	 * these are for medium quality playback that requires speed and no pausing. Usually sound effects or 
	 * other short term music. Buffer will be almost instantanious, but usage will be limited.
	 */
	public static AudioClip jumpNorm, jumpSlime, jumpSnow, rocket, death, death1, death2, death3, death4, start;
	
	//load all files
	public static void load() {
		System.out.println("[DEBUG] loading images...");
		iconimage = loadImage("iconimage.png");
		platSlime = loadImage("plat-slime.png");
		platCloud = loadImage("plat-cloud.png");
		platLava = loadImage("plat-lava.png");
		platNormal = loadImage("plat-stone.png");
		bgM = loadImage("bg-m.png");
		bgE = loadImage("bg-e.png");
		bgH = loadImage("bg-h.png");
		fgM = loadImage("fg-m.png"); // particle effect
		playerLeft = loadImage("player-left.png");
		playerRight = loadImage("player-right.png");
		playerLeftR = loadImage("player-left-fly.png");
		playerRightR = loadImage("player-right-fly.png");
		restartNorm = loadImage("restart1.png");
		restartPressed = loadImage("restart2.png");
		planet1 = loadImage("planet1.png");
		planet2 = loadImage("planet2.png");
		planet3 = loadImage("planet1-h.png");
		planet4 = loadImage("planet2-h.png");
		cloud1 = loadImage("cloud1.png");
		cloud2 = loadImage("cloud2.png");
		bgMenu = loadImage("bg-menu.png");
		
		System.out.println("[DEBUG] loading sounds...");
		jumpNorm = loadSimpleSound("jump1.wav");
		jumpSlime = loadSimpleSound("jump2.wav");
		jumpSnow = loadSimpleSound("jump3.wav");
		rocket = loadSimpleSound("rocket.wav");
		death = loadSimpleSound("death5.wav");
		death1 = loadSimpleSound("death1.wav");
		death2 = loadSimpleSound("death2.wav");
		death3 = loadSimpleSound("death3.wav");
		death4 = loadSimpleSound("death4.wav");
		start = loadSimpleSound("start.wav");
	}
	
	//load sound
	private static AudioPlayer loadSound(String filename, boolean loop) {
		try {
			return new AudioPlayer(filename, loop);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static AudioClip loadSimpleSound(String filename) {
		URL fileURL = Resources.class.getResource("/resource/" + filename);	//get sound
		return Applet.newAudioClip(fileURL);	//return sound
	}
	
	//get image, as buffered type
	public static BufferedImage loadImage(String filename) {
		BufferedImage img = null;
//		System.out.println(File.separator + "resource" + File.separator + filename);
		try {
			//read and get image
			img = ImageIO.read(Resources.class.getResourceAsStream("/resource/" + filename));
		} catch (Exception e) {
			//if failed, print err
			System.err.println("[WARNING] failed to load [" + filename + "]");
			e.printStackTrace();
			return null;
		}
		//return the buffered img type
		return img;
	}
	
}
