import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	//Duck d = new Duck();
	Background b = new Background(0, 0);
	Player p = new Player(200, 600);
	//Laser laser = new Laser();
	private ArrayList<Laser> lasers = new ArrayList<Laser>();
	BlueInvader blue = new BlueInvader(0, 0, 0, true);
	GreenInvader green = new GreenInvader(100, 0, 0, true);
	YellowInvader yellow = new YellowInvader(200, 0, 0, true, true);
	YellowInvader[] yellows = new YellowInvader[10];
	private ArrayList<YellowInvader> yellowInvaders = new ArrayList<YellowInvader>();
	GreenInvader[][] greens = new GreenInvader[2][10];
	BlueInvader[][] blues = new BlueInvader[2][10];
	private ArrayList<EnemyLaser> badLasers = new ArrayList<EnemyLaser>();
	int laserCount = 0;
	Player[] lives = new Player[3];
	
	{
		//array for yellow enemies
		for (int i = 0; i < yellows.length; i++) {
			
			yellows[i] = new YellowInvader((i*80)+2, 100, 3, true, true);

			
		}
		
		//arrays for green and blue enemies
		for (int i = 0; i < greens.length; i++) {
			for (int j = 0; j < greens[0].length; j++) {
				if (i == 0) {

					greens[i][j] = new GreenInvader(j * 80, 150, 3, true);
					blues[i][j] = new BlueInvader(j * 80, 250, 3, true);

					
				}
				
				if (i == 1) {

					greens[i][j] = new GreenInvader(j * 80, 200, 3, true);
					blues[i][j] = new BlueInvader(j * 80, 300, 3, true);

				}
			}
		}
		//array for lives
		for (int i = 0; i < lives.length; i++) {
			lives[i] = new Player(i * 40, 700);
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		p.paint(g);
		
		blue.paint(g);
		green.paint(g);
		yellow.paint(g);
		
		for (int i = 0; i < lives.length; i++) {
			lives[i].paint(g);
		}
		Font c = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(c);
		g.setColor(Color.WHITE);
		g.drawString("Lives:", 20, 750);
		
		//blue and green enemy shooting
		for (int i = 0; i < blues.length; i++) {
			for (int j = 0; j < blues[0].length; j++) {
				if (blues[i][j].getAlive()) {
					int random = (int) (Math.random()*900);
					if (random == 2) {
						EnemyLaser temp = new EnemyLaser(blues[i][j].getX() + 15, blues[i][j].getY() + 10);
						badLasers.add(temp);
					}
				}
				if (greens[i][j].getAlive()) {
					int random = (int) (Math.random()*700);
					if (random == 2) {
						EnemyLaser temp = new EnemyLaser(greens[i][j].getX() + 15, greens[i][j].getY() + 10);
						badLasers.add(temp);
					}
				} 
				if (i == 1 && yellows[j].getAlive()) {
					int random = (int) (Math.random()*350);
					if (random == 2) {
						EnemyLaser temp = new EnemyLaser(yellows[j].getX() + 15, yellows[j].getY() + 10);
						badLasers.add(temp);
					}
				}
			}  
		}
		
		for (int i = 0; i < badLasers.size(); i++) {
			if (badLasers.get(i).collide(p)) {
				System.out.println("hi");
			}
		}
		

		//painting player and enemy lasers
				for (Laser thisLaser : lasers) {
					thisLaser.paint(g);
				}
				
				for (EnemyLaser thisLaser : badLasers) {
					thisLaser.paint(g);
				}
		
		
		//rightMax is leftmost enemy still alive on screen
		//leftMin is rightmost enemy still alive on screen
		int rightMax = 0;
		int leftMin = 9;
		
		//painting yellows if they're alive and setting onScreen
		for (int i = 0; i < yellows.length; i++) {
			if (yellows[i].getAlive()) {
				yellows[i].paint(g);
				if (i > rightMax) {
					rightMax = i;
				}
				if (i < leftMin) {
					leftMin = i;
				}
				if (yellows[i].getX() < -50 || yellows[i].getX() > 1000) {
					yellows[i].setOnScreen(false);
				} else {
					yellows[i].setOnScreen(true);
				}
			}
		}
		
		//painting greens and blues
		for (int i = 0; i < greens.length; i++) {
			for (int j = 0; j < greens[0].length; j++) {
				
				//painting blue enemies if they're alive
				if (blues[i][j].getAlive()) {
					blues[i][j].paint(g);
					if (j > rightMax) {
						rightMax = j;
					}
					if (j < leftMin) {
						leftMin = j;
					}
				}
				
				//painting green enemies if they're alive
				if (greens[i][j].getAlive()) {
					greens[i][j].paint(g);
					if (j > rightMax) {
						rightMax = j;
					}
					if (j < leftMin) {
						leftMin = j;
					}
				}
			}
		}
		
		//checking boundaries to decide when all enemies turn
				for (int k = 0; k < 2; k++) {
					if (blues[k][leftMin].getX() >= 950 || greens[k][leftMin].getX() >= 950) {
						for (int i = 0; i < blues.length; i++) {
							for (int j = 0; j < blues[0].length; j++) {
								blues[i][j].setSpeedX(-3);
								blues[i][j].setY(blues[i][j].getY() + 15);
								greens[i][j].setSpeedX(-3);
								greens[i][j].setY(greens[i][j].getY() + 15);
								if (i == 1 ) { 
									yellows[j].setSpeedX(-3);
									yellows[j].setY(yellows[j].getY() + 15);
								}
							}
						}
					}
					
					if (blues[k][rightMax].getX() <= 0 || greens[k][rightMax].getX() <= 0) {
						for (int i = 0; i < blues.length; i++) {
							for (int j = 0; j < blues[0].length; j++) {
								blues[i][j].setSpeedX(3);
								blues[i][j].setY(blues[i][j].getY() + 15);
								greens[i][j].setSpeedX(3);
								greens[i][j].setY(greens[i][j].getY() + 15);
								if (i == 1) { 
									yellows[j].setSpeedX(3);
									yellows[j].setY(yellows[j].getY() + 15);
								}
							}
						}
					}

					
				}

//		boolean allLeftScreen = true;
//		boolean allRightScreen = true;
//		//checking if all alive enemies are off screen on the left
//		//add on screen attribute for all enemies?
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 10; j++) {	
//				if (blues[i][j].getAlive() && allLeftScreen) {
//					if (blues[i][j].getX() > -50) {
//						allLeftScreen = false;
//					}
//				}
//				if (greens[i][j].getAlive() && allLeftScreen) {
//					if (greens[i][j].getX() < -50) {
//						allLeftScreen = false;
//					}
//				}
//				if (yellows[j].getAlive() && allLeftScreen) {
//					allLeftScreen = false;
//				}
//				if (blues[i][j].getAlive() && allRightScreen) {
//					if (blues[i][j].getX() > 1000) {
//						allRightScreen = false;
//					}
//				}
//				if (greens[i][j].getAlive() && allRightScreen) {
//					if (greens[i][j].getX() > 1000) {
//						allRightScreen = false;
//					}
//				}
//				if (yellows[j].getAlive() && allRightScreen) {
//					if (yellows[j].getX() > 1000)
//					allRightScreen = false;
//				}
//			}
//		}
//		if (allLeftScreen) {
//			System.out.println("left");
//			for (int i = 0; i < blues.length; i++) {
//				for (int j = 0; j < blues[0].length; j++) {
//					blues[i][j].setSpeedX(3);
//					greens[i][j].setSpeedX(3);
//					if (i == 1) { 
//						yellows[j].setSpeedX(3);
//					}
//				}
//			}
//		}
		boolean offScreen = true;
				
		for (int i = 0 ; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				if (yellows[j].getOnScreen() == true) {
					offScreen = false;
				}
			}
		}
		if (offScreen) {
			for (int i = 0; i < 10; i++) {
				if (yellows[i].getX() < -10) {
					yellows[i].setSpeedX(3);
				} else if (yellows[i].getX() > 980) {
					yellows[i].setSpeedX(-3);
				}
			}
		}
		
				
		
		
		//collisions between lasers and all enemies 
		for (int i = 0; i < lasers.size(); i++) {
			if (lasers.get(i).getX() != 10000 && lasers.get(i).getY() >= 0) {
				laserCount++;
			} else if (laserCount > 0){
				laserCount--;
			}
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 10; k++) {
					if (lasers.get(i).collide(blues[j][k]) && blues[j][k].getAlive() == true) {
						lasers.get(i).setX(10000);
						//blues[j][k].setX(-10000);
						blues[j][k].setAlive(false);
					} else if (lasers.get(i).collide(greens[j][k]) && greens[j][k].getAlive() == true) {
						lasers.get(i).setX(10000);
						greens[j][k].setAlive(false);
					} else if (lasers.get(i).collide(yellows[k]) && yellows[k].getAlive() == true) {
						lasers.get(i).setX(10000);
						yellows[k].setAlive(false);
					}
					
				}
			}
		}
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(1000, 800));
		f.setBackground(Color.black);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(7, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	
	}
	
	
	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		System.out.println("x: " + m.getX());
		System.out.println("y: " + m.getY());

		
		EnemyLaser temp = new EnemyLaser(yellow.getX() + 15, yellow.getY() + 10);
		badLasers.add(temp);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent m) {
		// 37  = left
		// 39 = right
		//38 = up
		//65 a 
		//68 d
		if (m.getKeyCode() == 65) {
			p.setX(p.getX() - 10);
		} else if (m.getKeyCode() == 68) {
			p.setX(p.getX() + 10);
		}
		
		if (m.getKeyCode() == 87) {
			Laser temp = new Laser(p.getX() + 97, p.getY() + 2);
			if (laserCount < 1) {
				lasers.add(temp);
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}