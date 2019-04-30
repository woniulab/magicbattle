package com.woniuxy.game;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;



public class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6064466177408231076L;

	public GameFrame(){

		
		this.setSize(1260,790);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		//获取当前系统长宽分辨率
		int width = (int) (dm.getWidth() - 1250) / 2;
		int height = 0;
		this.setLocation(width, height);
		this.setContentPane(new ContentPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
