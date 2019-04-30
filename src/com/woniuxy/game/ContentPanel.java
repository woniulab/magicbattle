package com.woniuxy.game;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3239037095668215860L;

	public ContentPanel(){
		this.setLayout(null);
		this.add(new GamePanel());
		this.add(new StatePanel());
		this.setVisible(true);
	}

}
