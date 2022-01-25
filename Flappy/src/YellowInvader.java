import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class YellowInvader {
	private int x, y;
	private Image img; 	
	private AffineTransform tx;
	private double speedX;
	private boolean alive;
	private boolean onScreen;
	

	public YellowInvader(int x, int y, double speedX, boolean alive, boolean onScreen) {
		img = getImage("/imgs/yellowInvader.png"); //load the image for Tree
		this.x = x;
		this.y = y;
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
	
	public boolean getOnScreen() {
		return onScreen;
	}
	
	public void setOnScreen(boolean onScreen) {
		this.onScreen = onScreen;
	}
	
	
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.14, .13);
		x += speedX;
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.14, .13);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = YellowInvader.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}