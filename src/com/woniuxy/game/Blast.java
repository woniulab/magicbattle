package com.woniuxy.game;

public class Blast implements Runnable{
	public int px;
	public int py;
	public int count;
	public int direct;
	public int blastType;
	public boolean isBoom=false;
	Blast(Bullet b){
		this.px=b.bj*16+8;
		this.py=b.bi*16-8;
		this.isBoom=b.isBoom;
		this.direct=b.direct;
		this.blastType=b.bulletType;
		new Thread(this).start();
	}
	public Blast(int pi, int pj, int direct2,boolean b,int t) {
		// TODO Auto-generated constructor stub
		px=pi*16+8;
		py=pj*16-8;
		isBoom=b;
		direct=direct2;
		blastType=t;
		new Thread(this).start();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(isBoom){
			if(count>24) isBoom=false;
			count++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
