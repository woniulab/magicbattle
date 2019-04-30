package com.woniuxy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8236104580472164258L;
	public static int[][] map = new int[47][64];
	private Image wood;
	private Image water;
	private Image ice;
	private Image iron;
	private Image cao;
	private Image player;
	private Image boss;
	private Image border;
	private Image bullet;
	private Image blast;
	private Image star;
	private Image enemy1;
	private Image dragon;
	private Image gameOver;
	private Image screen;
	private Image gameStart;
	
	private static int stage=0;
	private int direct = 3;
	public static int life = 3;
	private int pose=0;
	private int sign = 0;
	private int pi = 44;
	private int pj = 24;
	private int position=0;
	private int starSign=0;
	private int bossTime=0;
	private int bossSign=0;
	public static int bpx=0;
	public static int bpy=0;
	
	private Vector<Blast>  es=new Vector<Blast>();
	private Vector<Bullet> bullets = new Vector<Bullet>();
	private Vector<Enemy1> enemies=new Vector<Enemy1>();
	private SpecialMove sp=null;
	
	public static boolean isWinner = false;
	public static boolean isOver = false;
	public static boolean isStart=false;

	public GamePanel(){
		this.setBackground(Color.BLACK);

		this.setSize(1026,864);
		this.setLocation(0, 0);
		matrix(map,Maps.getMap(stage));
		try {
			wood = ImageIO.read(new File("img/mutou.png"));
			water = ImageIO.read(new File("img/water.jpg"));
			ice = ImageIO.read(new File("img/ice.png"));
			iron = ImageIO.read(new File("img/iron.png"));
			cao = ImageIO.read(new File("img/cao.png"));
			player = ImageIO.read(new File("img/meizi.png"));
			boss = ImageIO.read(new File("img/boss.gif"));
			border = ImageIO.read(new File("img/biankuang.png"));
			bullet = ImageIO.read(new File("img/10.gif"));
			blast=ImageIO.read(new File("img/blast.png"));
			star=ImageIO.read(new File("img/star.png"));
			enemy1=ImageIO.read(new File("img/enemy1.png"));
			dragon=ImageIO.read(new File("img/dragon.png"));
			gameOver=ImageIO.read(new File("img/gameOver.jpg"));
			screen=ImageIO.read(new File("img/screen.png"));
			gameStart=ImageIO.read(new File("img/gameStart.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(wood, 1);
		mt.addImage(water, 2);
		mt.addImage(ice, 3);
		mt.addImage(iron, 4);
		mt.addImage(cao, 5);
		mt.addImage(player, 6);
		mt.addImage(boss, 7);
		mt.addImage(border, 8);
		mt.addImage(bullet, 9);
		mt.addImage(blast, 10);
		mt.addImage(star, 11);
		mt.addImage(enemy1, 12);
		mt.addImage(dragon, 13);
		mt.addImage(gameOver, 14);
		mt.addImage(gameStart, 15);
		try {
			mt.waitForAll();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new Thread(this).start();


		
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(!isOver){
					switch(e.getKeyCode()){
					case KeyEvent.VK_W:
						if(direct == 3){
							if(map[pi-1][pj+1] == 2 ||map[pi-1][pj+1] == 5 ||  map[pi-1][pj+1] == 1 || map[pi-1][pj+1] == 7||map[pi-1][pj] == 2 ||map[pi-1][pj] == 5 ||  map[pi-1][pj] == 1 || map[pi-1][pj] == 7){
								
							}
							else{
								pi -= 1;
							}
						}else{
							direct = 3;
						}
					  break;
					case KeyEvent.VK_S:
						if(direct == 0){
							if(map[pi+2][pj+1] == 2 ||map[pi+2][pj+1] == 5 ||  map[pi+2][pj+1] == 1 || map[pi+2][pj+1] == 7||map[pi+2][pj] == 2 ||map[pi+2][pj] == 5 ||  map[pi+2][pj] == 1 || map[pi+2][pj] == 7){
								
							}
							else{
								pi += 1;
							}
						}else{
							direct = 0;
						}
					  break;
					case KeyEvent.VK_A:
						if(direct == 1){
							if(map[pi+1][pj-1] == 2 ||map[pi+1][pj-1] == 5 ||  map[pi+1][pj-1] == 1 || map[pi+1][pj-1] == 7||map[pi][pj-1] == 2 ||map[pi][pj-1] == 5 ||  map[pi][pj-1] == 1 || map[pi][pj-1] == 7){
								
							}
							else{
								pj -= 1;
							}
						}else{
							direct = 1;
						}
					  break;
					case KeyEvent.VK_D:
						if(direct == 2){
							if(map[pi+1][pj+2] == 2||map[pi+1][pj+2] == 5 ||  map[pi+1][pj+2] == 1 || map[pi+1][pj+2] == 7||map[pi][pj+2] == 2||map[pi][pj+2] == 5 ||  map[pi][pj+2] == 1 || map[pi][pj+2] == 7){
								
							}
							else{
								pj += 1;
							}
						}else{
							direct = 2;
						}
					  break;
					case KeyEvent.VK_X:
						if(sp==null) {
							sp=new SpecialMove(1,pi,pj);
							if(pj==map[0].length-3)sp.pj=sp.pj-2;
							if(pj==1)sp=new SpecialMove(1,pi,pj+2);
							sp.isDraw=true;
							Music.SE(Music.M_ROAR);
						}
						break;
					case KeyEvent.VK_SPACE:
						if(bullets.size() < 3){
							Bullet b = new Bullet(pi,pj,direct);
							Music.SE(Music.M_FIRE);
							bullets.add(b);
						}
						break;
					
				}
				}else if(isStart) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						isStart=false;
						isOver=false;
					}
				}else if(isWinner){

					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						stage++;
						if(stage==Maps.maps.size())stage=0;
						restart(stage);
					}
				}else{
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						restart(stage);	
					}
				}
			}
		});


	}
	
	protected void restart(int stage) {
		// TODO Auto-generated method stub
		matrix(map,Maps.getMap(stage));
		matrix(StatePanel.tanks,StatePanel.tankCount);
		pi=44;
		pj=24;
		direct=3;
		isWinner=false;
		isOver=false;
		StatePanel.count=5;
		life=3;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
//
//		if(isStart) startGame(g);
		if(isWinner)nextStage(g);
		if(!isOver){
			drawMap(g);	
			drawEnemy(g);
			drawPlayerBullets(g);
			drawPlayerSkill(g);
			drawBlast(g);
		}
		drawPlayer(g);
		}
	
//	private void startGame(Graphics g) {
//		// TODO Auto-generated method stub
//		g.drawImage(gameStart, 0, 0, 1024, 864, 0, 0,1024, 768, this);
//	}

	private void nextStage(Graphics g) {
		// TODO Auto-generated method stub
		isOver=true;
		g.drawImage(screen, 0, 0, 1024, 864, 0, 0,1467, 1150, this);
		
		for(int k=0;k<enemies.size();k++){
			Enemy1 e=enemies.get(k);
				killEnemy(e);
			}
	}

	private void drawBlast(Graphics g) {
		// TODO Auto-generated method stub
		for(int i1=0;i1<es.size();i1++){
			Blast bl=es.get(i1);
			if(bl.isBoom){
				if(bl.direct==1){
					g.drawImage(blast, bl.px-24, bl.py+8, bl.px+8, bl.py+40, (bl.count%5)*192, (bl.count/5)*192-8, (bl.count%5)*192+128, (bl.count/5)*192+160,null,this);
				}else if(bl.direct==2){
					g.drawImage(blast, bl.px-4, bl.py+8, bl.px+28, bl.py+40, (bl.count%5)*192, (bl.count/5)*192-8, (bl.count%5)*192+128, (bl.count/5)*192+160,this);
				}else if (bl.direct==3){
					g.drawImage(blast, bl.px-8, bl.py-8, bl.px+24, bl.py+24, (bl.count%5)*192, (bl.count/5)*192-8, (bl.count%5)*192+128, (bl.count/5)*192+160,this);	
				}else{
					g.drawImage(blast, bl.px-8, bl.py+8, bl.px+24, bl.py+40, (bl.count%5)*192, (bl.count/5)*192-8, (bl.count%5)*192+128, (bl.count/5)*192+160,this);
				}					
			}else{
				es.remove(bl);
			}
		}

	}
	public static void matrix(int [][] m,int[][] n ){
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				m[i][j]=n[i][j];
			}
		}
		
	}
	private void drawPlayerSkill(Graphics g) {
		// TODO Auto-generated method stub
		if(sp!=null&&sp.isDraw) {
			g.drawImage(dragon, sp.pj*16, sp.pi*16, sp.pj*16+96, sp.pi*16+64, 0, 0, 155, 100, this);
			for(int k=0;k<enemies.size();k++){
				Enemy1 e=enemies.get(k);
				if(e.ej>=sp.pj-1&&e.ej<=sp.pj+7&&e.ei>=sp.pi&&e.ei<=sp.pi+4){
					killEnemy(e);
				}
			}
			if(sp.pi%2!=0){
				for(int i1=0;i1<7;i1++){
					es.add(new Blast(sp.pj-1+i1,sp.pi,0,true,1));
				}
			}
			if(sp.pi==1)sp=null;
		}
	}

	private void drawPlayerBullets(Graphics g) {
		// TODO Auto-generated method stub
		for(int i1=0;i1<bullets.size();i1++){
			Bullet b=bullets.get(i1);				
			if(b.isBoom){
				es.add(new Blast(b));
				b.isDrawBullet=false;
				bullets.remove(b);
			}else{

				if((b.bi==45||b.bi==44)&&(b.bj==31||b.bj==32)) life = 0;
				if(b.isDrawBullet){
					g.drawImage(bullet, b.bj * 16+16 , b.bi * 16+16 , b.bj*16+24, b.bi *16+24, 0,0, 15, 15, this);
				}
				for(int k=0;k<enemies.size();k++){
					Enemy1 e=enemies.get(k);
					if(b.strike(e.ei,e.ej )){
						b.isBoom=true;
						killEnemy(e);
					}
				}
			}
							
		}
	}

	private void drawPlayer(Graphics g){
		if(life==0)gameOver(g);
		if(!isOver)g.drawImage(player, pj * 16, pi * 16, pj*16 + 32, pi  * 16+32, sign * 32, direct * 48, (sign + 1) * 32, (direct + 1) * 48, this);
		
		if(map[pi][pj]==3)g.drawImage(cao, pj * 16-3, pi * 16+3, (pj + 1) * 16+3, (pi + 1) * 16+3, 0, 0, 16, 16, this);
		if(map[pi+1][pj]==3)g.drawImage(cao, pj * 16-3, (pi+1) * 16+3, (pj + 1) * 16+3, (pi + 2) * 16+3, 0, 0, 16, 16, this);
		if(map[pi][pj+1]==3)g.drawImage(cao, (pj +1)* 16-3, pi * 16+3, (pj + 2) * 16+3, (pi + 1) * 16+3, 0, 0, 16, 16, this);
		if(map[pi+1][pj+1]==3)g.drawImage(cao, (pj+1) * 16-3, (pi +1)* 16+3, (pj + 2) * 16+3, (pi + 2) * 16+3, 0, 0, 16, 16, this);
	}
	private void gameOver(Graphics g) {
		// TODO Auto-generated method stub
		isOver=true;
		g.drawImage(gameOver, 0, 0, 1024, 864, 0, 0, 961, 540, this);
		for(int k=0;k<enemies.size();k++){
			Enemy1 e=enemies.get(k);
				killEnemy(e);
			}
	}

	private void drawMap(Graphics g){

		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				switch(map[i][j]){
					case 1:
						g.drawImage(wood, j * 16, i * 16, (j + 1) * 16, (i + 1) * 16, 0, 0, 16, 16, this);
						break;
					case 2:
						g.drawImage(water, j * 16, i * 16, (j + 1) * 16, (i + 1) * 16, 0, 0, 122, 122, this);
						break;
					case 3:
						g.drawImage(cao, j * 16, i * 16, (j + 1) * 16, (i + 1) * 16, 0, 0, 16, 16, this);
						break;
					case 4:
						g.drawImage(ice, j * 16, i * 16, (j + 1) * 16, (i + 1) * 16, 0, 0, 16, 16, this);
						break;
					case 5:
						g.drawImage(iron, j * 16, i * 16, (j + 1) * 16, (i + 1) * 16, 0, 0, 16, 16, this);
						break;
					case 6:
						g.drawImage(boss, j * 16, i * 16, j  * 16+32, i * 16+32, 0, 34*bossSign, 41, 34*bossSign+34, this);
						break;
					case 7:
						g.drawImage(border, j * 16, i * 16, (j + 1) * 16, (i + 1) * 16, 0, 0, 16, 16, this);
						break;
				}
			}
		}
	}
	private void drawEnemy(Graphics g){
		for(int i1=0;i1<enemies.size();i1++){
			Enemy1 e=enemies.get(i1);
			for(int k=0;k<bullets.size();k++){
				Bullet b=bullets.get(k);
				if(b.strike(e.ei,e.ej)){
					b.isBoom=true;
					killEnemy(e);
				}
			}
			if(e.isDrawEnemy){
				if(e.isDrawStar){
					 if(starSign==13){
					 starSign=0;
					 }
					 starSign++;
					g.drawImage(star, e.ej * 16, e.ei * 16, e.ej*16+32 , e.ei*16+32,  starSign/4 * 192,0, (starSign/4 + 1) * 192, 192, this);						
				}else{
					g.drawImage(enemy1, e.ej * 16, e.ei * 16, e.ej*16 +  32, e.ei*16 +  32,  sign * 32, e.direct * 32, (sign + 1) * 32, (e.direct + 1) * 32, this);	
					if(map[e.ei][e.ej]==3)g.drawImage(cao, e.ej * 16-3, e.ei * 16+3, (e.ej + 1) * 16+3, (e.ei + 1) * 16+3, 0, 0, 16, 16, this);
					if(map[e.ei+1][e.ej]==3)g.drawImage(cao, e.ej * 16-3, (e.ei+1) * 16+3, (e.ej + 1) * 16+3, (e.ei + 2) * 16+3, 0, 0, 16, 16, this);
					if(map[e.ei][e.ej+1]==3)g.drawImage(cao, (e.ej+1) * 16-3, e.ei * 16+3, (e.ej + 2) * 16+3, (e.ei + 1) * 16+3, 0, 0, 16, 16, this);
					if(map[e.ei+1][e.ej+1]==3)g.drawImage(cao, e.ej * 16+13, e.ei * 16+19, (e.ej + 2) * 16+3, (e.ei + 2) * 16+3, 0, 0, 16, 16, this);
					}
				for(int k=0;k<e.bullets.size();k++){
					Bullet b=e.bullets.get(k);
					if(b.isBoom){
						es.add(new Blast(b));
						e.bullets.remove(b);
					}else{
						if(b.isDrawBullet){
							g.drawImage(bullet, b.bj * 16+16 , b.bi * 16+16 , b.bj*16+24, b.bi *16+24, 0,0, 15, 15, this);	
						}
						if((b.bi==45||b.bi==44)&&(b.bj==31||b.bj==32)) life =0;
						if(b.strike(pi, pj)) {
							b.isBoom=true;
							life--;
							Music.SE(Music.M_DIE);
							pi=44;
							pj=24;								
						}
					}
				}
			}
		}
	}
	private void killEnemy(Enemy1 e) {
		// TODO Auto-generated method stub
		for(int i=0;i<e.bullets.size();i++){
			e.bullets.get(i).isDrawBullet=false;
		}
		e.bullets.clear();
		e.isDrawEnemy=false;
		Music.SE(Music.M_EXPLO);
		StatePanel.isClear = true;
		enemies.remove(e);
	}

	
	public void run() {
		Music.bgm();
		while(true){
			pose++;
			if(pose >= 15){
				pose = 0;
			}
			sign=pose/5;
			
			this.repaint();
			bossTime++;
			if(bossTime==33)bossTime=0;
			bossSign=bossTime/3;
			if(enemies.size()<3 && !(isOver||isWinner)){
				if(position==3) position=0;
				Enemy1 e=new Enemy1(position);
				position++;
				enemies.add(e );
			}
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}