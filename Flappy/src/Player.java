import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Player {
	private int x, y;
	private Image img; 	
	private AffineTransform tx;
	private double speedX;
	private double speedY;
	

	public Player(double speedX, double speedY) {
		img = getImage("jet.png"); //load the image for Tree
		x = (int) Math.random()*400+400;
		y = (int) Math.random()*300+300;
		this.speedX = speedX;
		this.speedY = speedY;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void collide(int x, int y) {
		if (x >= 730) {
			speedX *= -1;
		}
		if (y >= 460) {
			speedY *= -1;
		}
	
		if (x <= 0) {
			speedX *= -1;
		}
		if (y <= 0) {
			speedY *= -1;
		}
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		x += speedX;
		y += speedY;
		update();

	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	//setters for speed
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.07, .07);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Player.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}