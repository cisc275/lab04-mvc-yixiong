import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics;
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    //boolean ifEast = true;
    //boolean ifSouth = true;
    ArrayList<BufferedImage[]> orcs = new ArrayList<>(8);
    JFrame frame = new JFrame();
    
	public View(){
	BufferedImage img = null;    	
	for(int k = 0; k < 8; k++) {
		img = createImage().get(k);
		pics = new BufferedImage[10];
		for(int i = 0; i < frameCount; i++) {
			pics[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
		orcs.add(pics);
	}
	
	
	frame.getContentPane().add(this);
	frame.setBackground(Color.gray);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(frameWidth, frameHeight);
	frame.setVisible(true);
	
}	
	private ArrayList<BufferedImage> createImage(){
    	ArrayList<BufferedImage> bufferedImage = new ArrayList<>();
    	
    	try {		
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_southeast.png")));
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_northeast.png")));
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_southwest.png")));
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_northwest.png")));
    		
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_south.png")));
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_north.png")));
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_west.png")));
    		bufferedImage.add(ImageIO.read(new File("src/images/orc/orc_forward_east.png")));
    		
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	

    }
	
	public void update(int xloc,int yloc, Direction direction) {
		
		if(direction.getName().equals("southwest")) {    		
    		pics = orcs.get(2);   		
    	}
    	else if(direction.getName().equals("northwest")){	
    		pics = orcs.get(3);
    	}
    	else if(direction.getName().equals("southeast")) {
    		pics = orcs.get(0); 
    	}
    	else if(direction.getName().equals("northeast")){
    		pics = orcs.get(1); 
    	}
    	else if(direction.getName().equals("south")){
    		pics = orcs.get(4); 
    	}
    	else if(direction.getName().equals("north")){
    		pics = orcs.get(5); 
    	}
    	else if(direction.getName().equals("west")){
    		pics = orcs.get(6); 
    	}
    	else if(direction.getName().equals("east")){
    		pics = orcs.get(7); 
    	}
		
		this.xloc = xloc;
		this.yloc = yloc;
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(pics[picNum], xloc, yloc, Color.gray, this);
	}
	public int getImageWidth() {	
		return imgWidth;
	}
	public int getImageHeight() {	
		return imgHeight;
	}
	
	
}