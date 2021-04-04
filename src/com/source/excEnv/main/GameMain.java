package com.source.excEnv.main;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameMain {
	
	//variables
	private static final String titleName = "Unnamed-Cat-Game";
	
	public static int GAME_WIDTH;
	public static int GAME_HEIGHT;
	public static Game sGame;
	public static JFrame frame;
	
	public static void main(String[] args) {
		//inital frame
		Toolkit tk = Toolkit.getDefaultToolkit();
		frame = new JFrame(titleName);	//make new frame
//		GAME_WIDTH = ((int) tk.getScreenSize().getWidth());
//		GAME_HEIGHT = ((int) tk.getScreenSize().getHeight());
		GAME_WIDTH = 500;
		GAME_HEIGHT = 1000;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//colse operation
		frame.setResizable(false);	//non-resizable
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);	//set fullscreen
		//add panel into frame
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(sGame);
		frame.pack();
		frame.setVisible(true);	//visibility is true
		frame.setIconImage(Resource.iconimage);	//setting icon image		
	}
}
