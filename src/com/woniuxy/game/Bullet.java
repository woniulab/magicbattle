package com.woniuxy.game;

public class Bullet implements Runnable {
	
	public int bi;
	public int bj;
	public int direct;
	public boolean isDrawBullet = true;
	public boolean isBoom = false;
	public int bulletType=1;

	public Bullet(int pi, int pj, int direct) {
		this.bi = pi;
		this.bj = pj;
		this.direct = direct;
		new Thread(this).start();
	}
	public boolean strike(int i,int j){
		boolean flag=false;
		switch(this.direct){
		case 0:
			if(bi+1==i&&bj==j) flag=true;
			if(bi+1==i&&bj+1==j)flag=true;
			break;
		case 1:
			if(bi==i&&bj-1==j)flag=true;
			if(bi+1==i&&bj-1==j)flag=true;
			break;
		case 2:
			if(bi==i&&bj+1==j)flag=true;
			if(bi+1==i&&bj+1==j)flag=true;
			break;
		case 3:
			if(bi-1==i&&bj==j) flag=true;
			if(bi-1==i&&bj+1==j)flag=true;
			break;
	}
		return flag;
	}

	public void run() {


		while(isDrawBullet){
			switch(direct){
				case 0:
					if(GamePanel.map[bi + 1][bj+1] == 1||GamePanel.map[bi + 1][bj+1] == 5||GamePanel.map[bi + 1][bj+1] == 7||GamePanel.map[bi + 1][bj] == 1||GamePanel.map[bi + 1][bj] == 5||GamePanel.map[bi + 1][bj] == 7){
						if(GamePanel.map[bi + 1][bj] == 1) 	GamePanel.map[bi + 1][bj] = 0;
						if(GamePanel.map[bi + 1][bj+1] == 1) 	GamePanel.map[bi + 1][bj+1] = 0;
						isDrawBullet = false;
						isBoom = true;
					}else{
						this.bi += 1;
					}
					break;
				case 1:
					if(GamePanel.map[bi+1][bj - 1] == 1||GamePanel.map[bi+1][bj-1] == 5||GamePanel.map[bi+1][bj-1] ==7||GamePanel.map[bi][bj - 1] == 1||GamePanel.map[bi][bj-1] == 5||GamePanel.map[bi ][bj-1] ==7 ){
						if(GamePanel.map[bi][bj - 1] == 1)GamePanel.map[bi][bj - 1] = 0;
						if(GamePanel.map[bi+1][bj - 1] == 1)GamePanel.map[bi+1][bj - 1] = 0;
						isDrawBullet = false;
						isBoom = true;
					}else{
						this.bj -= 1;
					}
					break;
				case 2:
					if(GamePanel.map[bi+1][bj + 1] == 1||GamePanel.map[bi+1][bj + 1] == 5||GamePanel.map[bi+1][bj + 1] == 7||GamePanel.map[bi][bj + 1] == 1||GamePanel.map[bi][bj + 1] == 5||GamePanel.map[bi][bj + 1] == 7){
						if(GamePanel.map[bi][bj + 1] == 1)GamePanel.map[bi][bj + 1] = 0;
						if(GamePanel.map[bi+1][bj + 1] == 1)GamePanel.map[bi+1][bj + 1] = 0;
						isDrawBullet = false;
						isBoom = true;
					}else{
						this.bj += 1;
					}
					break;
				case 3:
					if(GamePanel.map[bi - 1][bj+1] == 1||GamePanel.map[bi - 1][bj+1] == 5||GamePanel.map[bi - 1][bj+1] == 7||GamePanel.map[bi - 1][bj] == 1||GamePanel.map[bi - 1][bj] == 5||GamePanel.map[bi - 1][bj] == 7){
						if(GamePanel.map[bi - 1][bj] == 1) GamePanel.map[bi - 1][bj] = 0;
						if(GamePanel.map[bi - 1][bj+1] == 1) GamePanel.map[bi - 1][bj+1] = 0;
						isDrawBullet = false;
						isBoom = true;
					}else{
						this.bi -= 1;
					}
					break;
			}
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}