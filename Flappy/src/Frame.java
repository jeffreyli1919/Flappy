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
	BlueInvader blue = new BlueInvader(0, 0);
	GreenInvader green = new GreenInvader(100, 0);
	YellowInvader yellow = new YellowInvader(200, 0);
	YellowInvader[] yellows = new YellowInvader[10];
	GreenInvader[][] greens = new GreenInvader[2][10];
	BlueInvader[][] blues = new BlueInvader[2][10];
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//d.paint(g);
		b.paint(g);
		p.paint(g);
		//laser.paint(g);
		blue.paint(g);
		green.paint(g);
		yellow.paint(g);
		
		for (Laser thisLaser : lasers) {
			thisLaser.paint(g);
			thisLaser.setSpeedY(thisLaser.getSpeedY());
		}
		
		for (int i = 0; i < yellows.length; i++) {
			yellows[i] = new YellowInvader(i*80, 100);
			yellows[i].paint(g);
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
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	
	}
	
	
	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		Laser temp = new Laser(m.getX(), m.getY());
		
		
		lasers.add(temp);
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