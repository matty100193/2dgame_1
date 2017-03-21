package game;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

    private int tileX, tileY, speedX, type;
    public Image tileImage;

    private Background bg = Body.getBg1();
    private Rectangle r;

    public Tile(int x, int y, int typeInt) {
        tileX = x * 195;
        tileY = y * 38;
        r=new Rectangle();
        type = typeInt;

        if (type == 1) {
            tileImage = Body.tileocean;
        } 

    }

    public void update() {
        // TODO Auto-generated method stub
        if (type == 1) {
            if (bg.getSpeedX() == 0){
                speedX = -1;
            }else{
                speedX = -2;
            }

        } else {
            speedX = bg.getSpeedX()*5;
        }

        tileX += speedX;
        
        if(type!=0){
        	checkVerticalCollision(Ship.rect);
        }
    }
    
    public void checkVerticalCollision(Rectangle rObj){
    	if(rObj.intersects(r)){
    		System.out.println("collision");
    	}
    	
    }
    
    

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

}
