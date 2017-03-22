package game;

import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;

public abstract class Ship {
	
	private int health;
	
	final int MOVESPEED ;
    final int GROUND = 392;
    
    protected int centerX;
    protected int centerY = GROUND;


    private boolean fireReady=true;
	protected int speedX = 0;
	private ArrayList<Cannons> cannonList=new ArrayList<>();
	
	 protected static Background bg1 = Body.getBg1();                 
	 protected static Background bg2 = Body.getBg2();
	 
	 public Rectangle rect=new Rectangle(0,0,0,0);
     
     public Ship(int movespeed,int centerX){
    	 this.MOVESPEED=movespeed;
    	 this.centerX=centerX;
     }

 	abstract public void update();


     abstract public void shoot(double dir,URL base);
     

     public ArrayList<Cannons> getCannonList() {
		return cannonList;
	}

	public void setCannonList(ArrayList<Cannons> cannonList) {
		this.cannonList = cannonList;
	}

	public int getCenterX() {
         return centerX;
     }

     public int getCenterY() {
         return centerY;
     }


     public int getSpeedX() {
         return speedX;
     }


     public void setCenterX(int center) {
         this.centerX = center;
     }

     public void setCenterY(int center) {
         this.centerY = center;
     }
     
     public void addCenterX(int center) {
         this.centerX += center;
     }

     public void addCenterY(int center) {
         this.centerY += center;
     }


     public void setSpeedX(int speed) {
         this.speedX = speed;
     }
     
     public void addSpeedX(int speed) {
          speedX += speed;
     }
     
     public void decreaseSpeedX(int speed) {
         this.speedX -= speed;
     }

	public boolean isFireReady() {
		return fireReady;
	}

	public void setFireReady(boolean fireReady) {
		this.fireReady = fireReady;
	}

}
