package com.woniuxy.game;

public class SpecialMove implements Runnable{
	public int type;
	public int count=0;
	public int pi;
	public int pj;
	public boolean isDraw=false; 
	SpecialMove(int t,int i,int j){
		this.type=t;
		this.pi=i;
		this.pj=j-2;
		new Thread(this).start();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(isDraw){
			changeGround();
			pi--;
			count++;
			if(pi==0)isDraw=false;
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void changeGround(){
		for(int i=0;i<6;i++){
			if(GamePanel.map[pi][pj+i]==1) GamePanel.map[pi][pj+i]=0;	
			if(GamePanel.map[pi][pj+i]==4) GamePanel.map[pi][pj+i]=2;	
			if(GamePanel.map[pi][pj+i]==5) GamePanel.map[pi][pj+i]=0;			
			if(GamePanel.map[pi][pj+i]==3) GamePanel.map[pi][pj+i]=1;
		}
		
	}

}
