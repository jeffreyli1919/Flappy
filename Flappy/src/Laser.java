import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Laser {
	private int x, y;
	private Image img; 	
	private AffineTransform tx;
	private double speedY = 5;
	

	public Laser(int x, int y) {
		img = getImage("/imgs/newLaser.png"); //load the image for Tree
		this.x = x;
		this.y = y;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
		update();
		speedY = 5;
	}
	
	
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	
	//method for checking shot collision with blue enemies
	public boolean collide(BlueInvader other) {
		int xLaser = this.x;
		int yLaser = this.y;
		
		//center of enemies
		int xBlue = (other.getX()*2+40) / 2;
		int yBlue = (other.getY()*2+25) / 2;
		
		int distance = (int) (Math.sqrt(Math.pow(xLaser-xBlue, 2) + Math.pow(yLaser-yBlue, 2)));
	    if (distance < 22) {
	    	return true;
	    }
	    return false;
	}
	
	//method for checking shot collision with green enemies
		public boolean collide(GreenInvader other) {
			int xLaser = this.x;
			int yLaser = this.y;
			
			//center of enemies
			int xGreen = (other.getX()*2+40) / 2;
			int yGreen = (other.getY()*2+25) / 2;
			
			
			int distance = (int) (Math.sqrt(Math.pow(xLaser-xGreen, 2) + Math.pow(yLaser-yGreen, 2)));
		    if (distance < 22) {
		    	return true;
		    }
		    return false;
		}
		
		//method for checking shot collision with yellow enemies
		public boolean collide(YellowInvader other) {
			int xLaser = this.x;
			int yLaser = this.y;
			
			//center of enemies
			int xYellow = (other.getX()*2+40) / 2;
			int yYellow = (other.getY()*2+25) / 2;
			
			
			int distance = (int) (Math.sqrt(Math.pow(xLaser-xYellow, 2) + Math.pow(yLaser-yYellow, 2)));
		    if (distance < 22) {
		    	return true;
		    }
		    return false;
		}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		y = y - 3;
		init(x, y);
		update();
		

	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//setters for location
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getSpeedY() {
		return speedY;
	}
	
	public void setSpeedY(double d) {
		speedY = d;
	}
	
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.2, .2);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Laser.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}