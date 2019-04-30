package com.woniuxy.game;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScene extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4773969650319040564L;
	private JPanel startPanel;
	private JLabel startLabel;
	private JLabel button;
	
	public StartScene(){
		this.setSize(800, 336);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		//获取当前系统长宽分辨率
		int x = (int) (dm.getWidth() - 800) / 2;
		int y =(int) (dm.getHeight() - 336) / 2;
		this.setLocation(x, y);
		this.setContentPane(getStartPanel());
//		try {
//			this.setIconImage(ImageIO.read(new File("img/startScene.gif")));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		this.setVisible(true);
	}

	private JPanel getStartPanel() {
		if(null == startPanel){
			startPanel = new JPanel();
			startPanel.setLayout(null);
			startPanel.add(getStartLabel());
		}
		return startPanel;
	}

	private JLabel buttonLabel() {
		if(null == button){
			button = new JLabel();
			button.setSize(150, 55);
			button.setLocation(635,245);
			button.setIcon(new ImageIcon("img/startButton2.jpg"));
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			button.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e) {
					startGame();
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					button.setIcon(new ImageIcon("img/startButton1.jpg"));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					button.setIcon(new ImageIcon("img/startButton2.jpg"));
				}

			});
		}
		return button;
	}

	private void startGame() {
		this.setVisible(false);
		new GameFrame();		
	}

	private JLabel getStartLabel() {
		if(null == startLabel){
			startLabel = new JLabel();
			startLabel.setSize(800, 336);
			startLabel.setLocation(0, 0);
			startLabel.setIcon(new ImageIcon("img/startScene.gif"));
			startLabel.add(buttonLabel());
		}
		return startLabel;
	}
	
	
	
	public static void main(String[] args) {
		new StartScene();
	}

}
