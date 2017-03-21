package game;

import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;

public abstract class Ship {
	
	private int health;
	
	final int MOVESPEED ;
    final int GROUND = 392;
    
    private int centerX;
    private int centerY = GROUND;

    private boolean movingRight = false;
    private boolean readyToMove = true;
    private boolean fireReady=true;
	private int speedX = 0;
	private int speedY = 1;
	private ArrayList<Cannons> cannonList=new ArrayList<>();
	
	 protected static Background bg1 = Body.getBg1();                 
	 protected static Background bg2 = Body.getBg2();
	 
	 public static Rectangle rect=new Rectangle(0,0,0,0);
     
     public Ship(int movespeed,int centerX){
    	 this.MOVESPEED=movespeed;
    	 this.centerX=centerX;
     }

 	public void update(){
    	 
 		

         // Moves Character or Scrolls Background accordingly.
         if (getSpeedX() < 0) {
         	addCenterX(getSpeedX());
         }
         if (getSpeedX() == 0 || getSpeedX() < 0) {
             bg1.setSpeedX(0);
             bg2.setSpeedX(0);

         }
         if (getCenterX() <= 200 && getSpeedX() > 0) {
         	addCenterX(getSpeedX());
         }
         if (getSpeedX() > 0 && getCenterX()  > 200){
             bg1.setSpeedX(-MOVESPEED/3);
             bg2.setSpeedX(-MOVESPEED/3);
         }

         // Updates Y Position
         addCenterY(getSpeedY());
         if (getCenterY() + getSpeedY() >= GROUND) {
             setCenterY(GROUND);
         }



         // Prevents going beyond X coordinate of 0
         if (getCenterX() + getSpeedX() <= 60) {
             setCenterX(61);
         }
         
         rect.setRect(centerX-50, centerY-40, 300, 100);

         
         //System.out.println(getSpeedX());
     }

     public void moveRight() {
             speedX += MOVESPEED;
     }


     public void stopRight() {
         stop();
     }

     private void stop() {
    	 if(speedX>0){
    		 speedX-=MOVESPEED;
    	 }
     }

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

     public int getSpeedY() {
         return speedY;
     }

     public void setCenterX(int centerX) {
         this.centerX = centerX;
     }

     public void setCenterY(int centerY) {
         this.centerY = centerY;
     }
     
     public void addCenterX(int centerX) {
         this.centerX += centerX;
     }

     public void addCenterY(int centerY) {
         this.centerY += centerY;
     }


     public void setSpeedX(int speedX) {
         this.speedX = speedX;
     }
     
     public void addSpeedX(int speed) {
         this.speedX += speedX;
     }
     
     public void addSpeedY(int speed) {
         this.speedY += speed;
     }

     public void setSpeedY(int speedY) {
         this.speedY = speedY;
     }


     public boolean isMovingRight() {
         return movingRight;
     }
     

     public void setMovingRight(boolean movingRight) {
         this.movingRight = movingRight;
     }

	public boolean isReadyToMove() {
		return readyToMove;
	}

	public void setReadyToMove(boolean readyToMove) {
		this.readyToMove = readyToMove;
	}

	public boolean isFireReady() {
		return fireReady;
	}

	public void setFireReady(boolean fireReady) {
		this.fireReady = fireReady;
	}

}
