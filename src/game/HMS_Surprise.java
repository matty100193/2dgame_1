package game;

import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HMS_Surprise extends Ship {
	
	public HMS_Surprise(int movespeed, int centerX) {
		super(movespeed, centerX);
	}


	@Override
	public void shoot(double dir,URL base) {
	 Cannons c=new Cannons(getCenterX()+185,getCenterY()-13,dir,base);
	 getCannonList().add(c);
	}


 }