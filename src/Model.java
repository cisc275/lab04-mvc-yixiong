
import java.util.Random;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    int frameWidth = 500;
    int frameHeight = 300;
    int imgWidth = 165;
    int imgHeight = 165;
    Direction direction;
    Direction[] directions = {Direction.EAST,Direction.NORTH,Direction.NORTHEAST,Direction.NORTHWEST,
			Direction.SOUTH,Direction.SOUTHEAST,Direction.SOUTHWEST,Direction.WEST};
    
    public Model(int frameWidth, int frameHeight, int imgWidth, int imgHeight) {
    	this.frameWidth = frameWidth;
    	this.frameHeight = frameHeight;
    	this.imgWidth = imgWidth;
    	this.imgHeight = imgHeight;
    	this.direction = Direction.SOUTHEAST;
    	
    }
    
    public int getX() {
    	return xloc;
    }
    
    public int getY() {
    	return yloc;
    }
    
    public Direction getDirect() {
    	return direction;
    }
    
    public void newDirection(String hit) {  	
    	
    	Random rand = new Random();  	
    	int index = rand.nextInt(directions.length);
    	direction = directions[index];
    	
    	if(direction.getName().contains(hit)) {
    		newDirection(hit);
    	}
    	else {
    		return;
    	}
    }    
    public void updateLocationAndDirection() {
    	String hit = "";
    	int eastWall = frameWidth - imgWidth;
    	int westWall = 0;
    	int northWall = 0;
    	int southWall = frameHeight -imgWidth;
    	
    	
    	if(xloc > eastWall) {
    		hit = "east";
    	}
    	else if(xloc < westWall) {
    		hit = "west";
    	}	
    	else if(yloc < northWall) {
    		hit = "north";
    	}
    	else if(yloc > southWall) {
    		hit = "south";
    	}
    	
    	if(!hit.equals("")){
    		newDirection(hit);
    	}
    	
    	if(direction.getName().contains("east")) {    		
    		xloc = xloc + xIncr;
    	}
    	else if(direction.getName().contains("west")) {
    		xloc = xloc - xIncr;
    	}
    	if(direction.getName().contains("north")) {
    		yloc = yloc - yIncr;
    	}
    	else if(direction.getName().contains("south")) {
    		yloc = yloc + yIncr;
    	}
    	
    	
    	
    }
    
}