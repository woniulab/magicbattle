package com.woniuxy.game;

import java.util.Random;
import java.util.Vector;

public class Enemy1 implements Runnable{

	public int direct=0;
	public int ei=1;
	public int ej=0;
	public boolean isDrawStar=true;
	public boolean isDrawEnemy=true;
	public boolean isBoom=false;
	public Vector<npcBullet> bullets=new Vector<npcBullet>();
	
	private int time=0;
	private Random rd=new Random();
	
	
	Enemy1(int position){
		switch(position){
		case 0:
			ej=1;
			break;
		case 1:
			ej=GamePanel.map[0].length/2;
			break;
		case 2:
			ej=GamePanel.map[0].length-3;
			break;
		}
		new Thread(this).start();
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		while(isDrawEnemy){
			
			if(isDrawStar){
				if(time>=20) {
					isDrawStar=false;
				}
				time++;
			}else{
				if(time >=30){
					direct=rd.nextInt(4);
					time=0;
				}
				if(bullets.size()<3){
					if(time%4==0){
						Music.SE(Music.M_E_FIRE);
						bullets.add(new npcBullet(ei,ej,direct));
					}
				}
				time++;
				switch(direct){
				case 0:
					if((GamePanel.map[ei+2][ej+1]==0||GamePanel.map[ei+2][ej+1]==3||GamePanel.map[ei+2][ej+1]==4)&&(GamePanel.map[ei+2][ej]==0||GamePanel.map[ei+2][ej]==3||GamePanel.map[ei+2][ej]==4)){
						ei+=1;
					}else{
						direct=rd.nextInt(4);
					}
					break;
				case 1:
					if((GamePanel.map[ei+1][ej-1]==0||GamePanel.map[ei+1][ej-1]==3||GamePanel.map[ei+1][ej-1]==4)&&(GamePanel.map[ei][ej-1]==0||GamePanel.map[ei][ej-1]==3||GamePanel.map[ei][ej-1]==4)){
						ej-=1;
					}else{
						direct=rd.nextInt(4);
					}
					break;
				case 3:
					if((GamePanel.map[ei-1][ej+1]==0||GamePanel.map[ei-1][ej+1]==3||GamePanel.map[ei-1][ej+1]==4)&&(GamePanel.map[ei-1][ej]==0||GamePanel.map[ei-1][ej]==3||GamePanel.map[ei-1][ej]==4)){
						ei-=1;
					}else{
						direct=rd.nextInt(4);
					}
					break;
				case 2: 
					if((GamePanel.map[ei+1][ej+2]==0||GamePanel.map[ei+1][ej+2]==3||GamePanel.map[ei+1][ej+2]==4)&&(GamePanel.map[ei][ej+2]==0||GamePanel.map[ei][ej+2]==3||GamePanel.map[ei][ej+2]==4)){
						ej+=1;
					}else{
						direct=rd.nextInt(4);
					}
					break;
				}				
			}
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
