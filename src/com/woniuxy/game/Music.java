package com.woniuxy.game;

import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JApplet;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music {

	public static final String M_FIRE="src/audio/laser.wav";
	public static final String M_E_FIRE="src/audio/enemylaser.wav";
	public static final String M_BG="../../../audio/bgm.wav";
	public static final String M_STEP="src/audio/step.wav";
	public static final String M_ROAR="src/audio/8.wav";
	public static final String M_EXPLO="src/audio/Explo.wav";
	public static final String M_DIE="src/audio/Die.wav";
	
	public Music(){
		
	}
	public static void SE(String path){
		try {
			FileInputStream fis=new FileInputStream(path);
			AudioStream as=new AudioStream(fis);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void bgm(){
		AudioClip ac=JApplet.newAudioClip(Music.class.getResource(M_BG)); 
		ac.loop();
	}
}
