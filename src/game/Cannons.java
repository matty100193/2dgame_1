package game;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
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
	private static AudioInputStream audioIn1,audioIn2;
	private static Clip clip;
	private static Clip clipSplash1,clipSplash2;
	public Rectangle r;
	private static int frameFinal1,frameFinal2;
	private static boolean ready1=true,ready2=true;
	
	
	public Cannons(int startX,int startY,double dir, URL base){
		x=startX;
		y=startY;
		
		r=new Rectangle(0,0,0,0);
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
		if(audioIn1==null){
			setUpAudio(base,"no slow down");
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
	
	public static void setUpAudio(URL base,String text){
		try{
		audioIn1=AudioSystem.getAudioInputStream(new URL(base,"data/splash1.wav"));
		audioIn2=AudioSystem.getAudioInputStream(new URL(base,"data/splash1.wav"));
		clipSplash1 = AudioSystem.getClip();
		clipSplash1.open(audioIn1);
		clipSplash2 = AudioSystem.getClip();
		clipSplash2.open(audioIn2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(){
		x+=velocityX;
		y+=velocityY;
		velocityY+=0.075;
		r.setBounds((int)x,(int) y, 8, 8);
		
		if(!ready1){
			if(frameFinal1!=0 && clipSplash1.getFramePosition()==frameFinal1 )ready1=true;
		}
		if(!ready2){
			if(frameFinal2!=0 && clipSplash2.getFramePosition()==frameFinal2 )ready2=true;
		}
		if(y>=390){
			if(this.r!=null){
				handleLoadingSound();
			}
			visible=false;
			this.r=null;
		}
		if(y<400){
			checkCollision();
		}
	}

	private static void handleLoadingSound() {
		try{
			
			if(ready1){
				ready1=false;
				clipSplash1.setFramePosition(0);
				clipSplash1.start();
				frameFinal1=clipSplash1.getFrameLength();
				System.out.println("ready1");
			}
			else if(ready2){
				ready2=false;
				clipSplash2.setFramePosition(0);
				clipSplash2.start();
				frameFinal2=clipSplash2.getFrameLength();
				System.out.println("ready2");
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void checkCollision() {

		
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
