package com.woniuxy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StatePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 422259040385445408L;

	public static final int[][] tankCount={{0,0,0,0,0,0,0},
										   {0,1,1,1,1,1,0},
										   {0,0,0,0,0,0,0},
										   {0,1,1,1,1,1,0},
										   {0,0,0,0,0,0,0},
										   {0,1,1,1,1,1,0},
										   {0,0,0,0,0,0,0},
										   {0,1,1,1,1,1,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,0,0,0,0,0},
										   {0,0,2,2,2,0,0},
										   {0,0,0,0,0,0,0}};
	public static int[][] tanks=new int[22][7];
	
	private Image tank;
	private Image player;
	
	public static boolean isClear = false;
	public static int count=5;
	
	public StatePanel(){
		this.setSize((1260 - 1026) ,864);
		this.setLocation(1026, 0);
		GamePanel.matrix(tanks, tankCount);
		try {
			tank = ImageIO.read(new File("img/enemy1.png"));
			player = ImageIO.read(new File("img/meizi.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(tank, 1);
		mt.addImage(player, 2);
		new Thread(this).start();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < 22; i++){
			for(int j = 0; j < 7; j++){
				if(tanks[i][j] == 1){
					g.drawImage(tank, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, 32, 0, 64, 32, this);
				}
				if(tanks[i][j] == 2){
					g.drawImage(player, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, 32, 0, 64, 48, this);
				}
			}
		}
		
	}

	
	public void run() {
		while(true){
			this.repaint();
			tanks[20][4-GamePanel.life]=0;
			if(isClear){				
				if(tanks[7][4] == 0){
					GamePanel.isWinner = true;
				}
				//System.out.println(GamePanel.isWinner);
				
				if(count%10==0&&!GamePanel.isWinner)count+=5;
				tanks[count/5][count%5+1] = 0;
				count++;
				isClear = false;
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
