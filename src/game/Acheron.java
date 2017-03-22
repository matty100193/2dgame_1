package game;

import java.net.URL;


public class Acheron extends Ship{
	private Background bg=Body.getBg1();
	private HMS_Surprise player=Body.getPlayer();
	private int movementSpeed;
	
	public Acheron(int movespeed, int centerX) {
		super(movespeed, centerX);
		setSpeedX(1);
		movementSpeed=movespeed;
	}

	@Override
	public void shoot(double dir, URL base) {
		// TODO Auto-generated method stub
		
	}
	private boolean stay=false;
	@Override
	public void update(){
		
		int currentSpeed=getSpeedX();
		
		
		if(player.getSpeedX()>getSpeedX() && (getCenterX()+getSpeedX()>900)){
			currentSpeed=getSpeedX()-player.getSpeedX();
			stay=false;
		}
		else if(getSpeedX()>player.getSpeedX() || player.getSpeedX()==0){
			speedX=movementSpeed;
			stay=false;
		}
		else if((getSpeedX()==player.getSpeedX() || getCenterX()+getSpeedX()<=900) &&  (player.getSpeedX()>0)){
			stay=true;
		}
		if(!stay){
			if(currentSpeed<0)centerX+=currentSpeed;
			else centerX+=getSpeedX();
		}
		//System.out.println("player: "+player.getSpeedX()+" : enemy speed: "+speedX+" enemy pos: "+getCenterX());

        rect.setRect(getCenterX()-50, getCenterY()-40, 300, 100);

    }

	@Override
	public int getSpeedX() {
		// TODO Auto-generated method stub
		return movementSpeed;
	}

	@Override
	public void setSpeedX(int speed) {
		movementSpeed=speed;
	}
	
	
	

}
