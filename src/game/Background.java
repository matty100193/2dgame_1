package game;

public class Background {
	 
	private int bgX,bgY,speedX;
	
	public Background(int bgX,int bgY){
		this.bgX=bgX;
		this.bgY=bgY;
		this.speedX=0;
	}
	
	public int getBgX() {
		return bgX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void update(){
		bgX+=speedX;
		if(bgX<=-2160){
			bgX+=4320;
		}
	}
}