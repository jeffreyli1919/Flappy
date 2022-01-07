import java.awt.Color;
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
	Player p = new Player(200, 700);
	//Laser laser = new Laser();
	private ArrayList<Laser> lasers = new ArrayList<Laser>();
	BlueInvader blue = new BlueInvader(0, 0, 4);
	GreenInvader green = new GreenInvader(100, 0, 3);
	YellowInvader yellow = new YellowInvader(200, 0, 2);
	YellowInvader[] yellows = new YellowInvader[10];
	GreenInvader[][] greens = new GreenInvader[2][10];
	BlueInvader[][] blues = new BlueInvader[2][10];
	
	{
		for (int i = 0; i < yellows.length; i++) {
			yellows[i] = new YellowInvader(i*80, 100, 2);
			
		}
		
		for (int i = 0; i < greens.length; i++) {
			for (int j = 0; j < greens[0].length; j++) {
				if (i == 0) {
					greens[i][j] = new GreenInvader(j * 80, 150, 2);
					blues[i][j] = new BlueInvader(j * 80, 250, 2);
					
				}
				
				if (i == 1) {
					greens[i][j] = new GreenInvader(j * 80, 200, 2);
					blues[i][j] = new BlueInvader(j * 80, 300, 2);
				}
			}
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		p.paint(g);
		
		blue.paint(g);
		green.paint(g);
		yellow.paint(g);
		
		
		//painting lasers
		for (Laser thisLaser : lasers) {
			thisLaser.paint(g);
		}
		
		//movement and boundaries for yellows
		for (int i = 0; i < yellows.length; i++) {
			yellows[i].paint(g);
			
			if (yellows[i].getX() >= 950) {
				yellows[i].setSpeedX(-2);
				yellows[i].setY(yellows[i].getY() + 15);
			}
			
			if (yellows[i].getX() <= 0) {
				yellows[i].setSpeedX(2);
				yellows[i].setY(yellows[i].getY() + 15);
			}
			
		}
		
		//movement and boundaries for greens and blues
		for (int i = 0; i < greens.length; i++) {
			for (int j = 0; j < greens[0].length; j++) {
				greens[i][j].paint(g);
				blues[i][j].paint(g);
				
				if (greens[i][j].getX() >= 950) {
					greens[i][j].setSpeedX(-2);
					greens[i][j].setY(greens[i][j].getY() + 15);
				}
				
				if (blues[i][j].getX() >= 950) {
					blues[i][j].setSpeedX(-2);
					blues[i][j].setY(blues[i][j].getY() + 15);
				}
				
				if (greens[i][j].getX() <= 0) {
					greens[i][j].setSpeedX(2);
					greens[i][j].setY(greens[i][j].getY() + 15);
				}
				
				if (blues[i][j].getX() <= 0) {
					blues[i][j].setSpeedX(2);
					blues[i][j].setY(blues[i][j].getY() + 15);
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
		System.out.println(m.getKeyCode());
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
			lasers.add(temp);
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