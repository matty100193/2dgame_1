package game;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Cannons {
	
	private double speedX,distance;
	private double x, y;
	private double velocityX,velocityY;
	private boolean visible;
	private static AudioInputStream audioIn;
	private static Clip clip;
	
	public Cannons(int startX,int startY,double dir, URL base){
		x=startX;
		y=startY;
		
		Random rand=new Random();
		speedX=(int)rand.nextInt(3)+6;
		visible=true;

		velocityX=speedX*Math.cos(dir);
		velocityY=speedX*Math.sin(dir);
		
		if(audioIn==null){
			setUpAudio(base);
		}
		
		try {
			clip.setFramePosition(0);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setUpAudio(URL base){
		try {
			audioIn =  AudioSystem.getAudioInputStream(new URL(base,"data/cannon.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		x+=velocityX;
		y+=velocityY;
		velocityY+=0.075;
		
		
		//if(distance<50)y++;;
		if(y>=400){
			visible=false;
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
