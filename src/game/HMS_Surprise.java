package game;

import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HMS_Surprise extends Ship {
	
    private boolean movingRight = false;
    private boolean readyToMove = true;
	
	public HMS_Surprise(int movespeed, int centerX) {
		super(movespeed, centerX);
		//speedX=2;
	}
	


	@Override
	public void shoot(double dir,URL base) {
	 Cannons c=new Cannons(getCenterX()+185,getCenterY()-13,dir,base);
	 getCannonList().add(c);
	}
	
	@Override
	public void update(){
		
		
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
	             bg1.setSpeedX(-MOVESPEED);
	             bg2.setSpeedX(-MOVESPEED);
	         }

	         // Prevents going beyond X coordinate of 0
	         if (getCenterX() + getSpeedX() <= 60) {
	             setCenterX(61);
	         }

	         //System.out.println(getCenterX()+getSpeedX());
	         
	         rect.setRect(getCenterX()-50, getCenterY()-40, 300, 100);

        
        //System.out.println(getSpeedX());
    }
	
    public void moveRight() {
    	speedX+=1;
    }


    public void stopRight() {
    	stop();
    }

    private void stop() {
    	if(getSpeedX()>0){
    		decreaseSpeedX(MOVESPEED);
    	}
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

 }