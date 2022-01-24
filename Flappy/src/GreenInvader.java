import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class GreenInvader {
	private int x, y;
	private Image img; 	
	private AffineTransform tx;
	private double speedX;
	private boolean alive;
	

	public GreenInvader(int x, int y, double speedX, boolean alive) {
		img = getImage("/imgs/greenInvader.png"); //load the image for Tree
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.alive = alive;
		this.speedX = speedX;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		x += speedX;
		update();

	}
	
	//getters and setters for x,y location, speed, alive
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public double getSpeedX() {
		return speedX;
	}
	
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.07, .08);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.09, .1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = GreenInvader.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}