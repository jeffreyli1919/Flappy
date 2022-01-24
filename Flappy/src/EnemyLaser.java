import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class EnemyLaser {
	private int x, y;
	private Image img; 	
	private AffineTransform tx;
	private int speedY;
	

	public EnemyLaser(int x, int y) {
		img = getImage("/imgs/redLaser.png"); //load the image for Tree
		this.x = x;
		this.y = y;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
		update();
		speedY = 8;
	}
	
	
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		y = (int) (y + speedY);
		init(x, y);
		update(); 
		

	}
	
	//method for checking shot collision with player
		public boolean collide(Player other) {
			int xLaser = this.x;
			int yLaser = this.y;
			
			//center of enemies
			int xPlayer = (other.getX()+100);
			int yPlayer = (other.getY()+10);
			
			int distance = (int) (Math.sqrt(Math.pow(xLaser-xPlayer, 2) + Math.pow(yLaser-yPlayer, 2)));
		    if (distance < 20) {
		    	return true;
		    }
		    return false;
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
	
	public void setSpeedY(int d) {
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
			URL imageURL = EnemyLaser.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}