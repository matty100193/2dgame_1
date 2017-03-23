package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Body extends Applet implements KeyListener, Runnable, MouseListener{

	private GameState state;
	private URL base;
	private AudioInputStream audioIn;
	private static Background bg1,bg2;
	private static HMS_Surprise player;
	private static Acheron enemy;
	private Image background,ship1,image,ship1_left,ship1_right,currentShip1,expl1,expl2,expl3,expl4,expl5;
	private Graphics second;
	public static Image tileocean;
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	private Animation anim,animExp;
	private boolean shotEnabled;
	
	enum GameState{
		Running,Finished;
	}

	
	@Override
	public void init() {
		setSize(1280, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		Frame f=(Frame)this.getParent().getParent();
		f.setLocationRelativeTo(null);
		f.setTitle("Master and Commander: Chase the Acheron");
		try{
			base=getDocumentBase();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		background=getImage(base,"data/background.png");
		ship1=getImage(base,"data/player_ship.png");
		ship1_left=getImage(base,"data/player_ship_left.png");
		ship1_right=getImage(base,"data/player_ship_right.png");
		tileocean=getImage(base,"data/waves.png");
		expl1=getImage(base,"data/explosion1.png");
		expl2=getImage(base,"data/explosion2.png");
		expl3=getImage(base,"data/explosion3.png");
		expl4=getImage(base,"data/explosion4.png");
		expl5=getImage(base,"data/explosion5.png");
		animExp=new Animation();
		animExp.addFrame(expl1, 50);
		animExp.addFrame(expl2, 50);
		animExp.addFrame(expl1, 50);
		//animExp.addFrame(expl4, 50);
		//animExp.addFrame(expl5, 50);
		
		anim=new Animation();
		anim.addFrame(ship1_left, 150);
		anim.addFrame(ship1, 400);
		anim.addFrame(ship1_right, 150);
		//anim.addFrame(ship1, 100);
		currentShip1=anim.getImage();
		shotEnabled=true;
	    try {
			audioIn =  AudioSystem.getAudioInputStream(new URL(base,"data/oldShipmate.wav"));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void start() {
		state=GameState.Running;
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		bg1=new Background(0,0);
		bg2=new Background(2160,0);
		
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 12; j++) {
				if (j == 10) {
					Tile t = new Tile(i, j, 1);
					tilearray.add(t);

				}
			}
		}
		player=new HMS_Surprise(1 ,200);     
		enemy=new Acheron(1,1000);
		Thread thread=new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		if(state==GameState.Running){
			long durationButtonPress=0;
			long increaseSpeedEnemy=0;
			while(true){
				try {
					currentShip1=anim.getImage();
					updateTiles();
					enemy.update();
					player.update();
					
					
					//System.out.println("enemy "+enemy.getSpeedX());
					
					ArrayList<Cannons> cannonsList=player.getCannonList();
					ArrayList<Cannons> cannonsListEnemy=enemy.getCannonList();
					for(int i=0;i<cannonsList.size();i++){
						if(cannonsList.get(i).isVisible())cannonsList.get(i).update();
						else cannonsList.remove(i);
					}
					
					if(!player.isMovingRight()&&player.getSpeedX()>0 && durationButtonPress<=0){
						player.stopRight();
						durationButtonPress=175;
					}
					
					//if(enemy.getCenterX()-player.getCenterX()<=800){
						//System.out.println("in range");
						
					//}
					
					for(int i=0;i<cannonsListEnemy.size();i++){
						if(cannonsListEnemy.get(i).isVisible()) cannonsListEnemy.get(i).update();
						else cannonsListEnemy.remove(i);
					}
					
					bg1.update();
					bg2.update();
					animate();
					repaint();
					if(durationButtonPress>0){
						durationButtonPress-=17;
					}
					if(increaseSpeedEnemy>=10000 && enemy.getSpeedX()<=20){
						increaseSpeedEnemy=0;
						enemy.setSpeedX(enemy.getSpeedX()+1);
					}
					
					increaseSpeedEnemy+=17;
					Thread.sleep(17);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void animate() {
		anim.update(10);
		animExp.update(10);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);
		
	}
	

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		
		ArrayList<Cannons> list=player.getCannonList();
		for(int i=0;i<list.size();i++){
			g.setColor(Color.black);
			g.fillOval((int)list.get(i).getX(), (int)list.get(i).getY(), 8, 8);
			//g.drawRect((int)list.get(i).getX(), (int)list.get(i).getY(), 8, 8);
		}
		
		ArrayList<Cannons> list1=enemy.getCannonList();
		for(int i=0;i<list1.size();i++){
			g.setColor(Color.black);
			g.fillOval((int)list1.get(i).getX(), (int)list1.get(i).getY(), 8, 8);
			//g.drawRect((int)list.get(i).getX(), (int)list.get(i).getY(), 8, 8);
		}
		
		g.drawRect((int)player.rect.getX(), (int)player.rect.getY(), (int)player.rect.getWidth(), (int)player.rect.getHeight());
		g.drawImage(currentShip1, player.getCenterX()-61,player.getCenterY()-63,this );
		g.drawImage(currentShip1, enemy.getCenterX()-61, enemy.getCenterY()-63, this);
		g.setColor(Color.yellow);
		g.drawRect((int)enemy.rect.getX(), (int)enemy.rect.getY(), (int)enemy.rect.getWidth(), (int)enemy.rect.getHeight());
		paintTiles(g);
		g.setFont(new Font("Times new roman", Font.PLAIN, 25));
		g.setColor(Color.BLACK);
		g.drawString("HMS Surprise speed: "+player.getSpeedX()+" knots", 200, 35);
		g.drawString("Acheron speed: "+enemy.getSpeedX()+" knots, approx distance: "+(enemy.getCenterX()-player.getCenterX()), 775, 35);
		
	}
	
	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_SPACE:
				if(player.isReadyToMove()){
				   player.moveRight();
				   player.setMovingRight(true);
				   player.setReadyToMove(false);
				}
				break;

		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_SPACE:
				player.setMovingRight(false);
				player.setReadyToMove(true);
				break;

		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static Background getBg1(){
		return bg1;
	}
	
	public static Background getBg2(){
		return bg2;
	}
	
	public static HMS_Surprise getPlayer(){
		return player;
	}
	
	public static Acheron getEnemy(){
		return enemy;
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(shotEnabled){
			shotEnabled=false;
			double mx=e.getX();
			double my=e.getY();
			double dx=mx-(player.getCenterX()+185);
			double dy=my-(player.getCenterY()-13);
			double dir=Math.atan2(dy, dx);
			double angle=dir*(180/Math.PI);
			System.out.println(dir);
			if(angle>=-85 && angle <=0){
				player.shoot(dir,base);
				enemy.shoot(dir, base);
				Timer shootnext=new Timer();
				shootnext.schedule(new TimerTask(){
					@Override
					public void run() {
						player.shoot(dir,base);
					}
				}, 500);
			}
			Timer shootTimer=new Timer();
			shootTimer.schedule(new TimerTask(){

				@Override
				public void run() {
					shotEnabled=true;
					
				}
				
			},2000);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
