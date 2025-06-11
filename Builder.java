package escapeGame;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class Builder extends JPanel{
	private int x,y;  //position
	private boolean bloqueur = false;

    public Builder() {
        setOpaque(false); 
        setBounds(0, 0, 1280,720); //les mêmes que la fenêtre
        setFocusable(true); // Récupère le clavier
        requestFocusInWindow(); // Assure que le panneau a le focus
        
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int input = e.getKeyCode();
                if (bloqueur) {
                	return;
                }
                if (input == KeyEvent.VK_D&&x<1200) {       //déplacement en ZQSD
                    x += 10;
                } else if (input == KeyEvent.VK_Q&&x>20) {
                    x -= 10;
                } else if (input == KeyEvent.VK_S&&y<650) {
                    y += 10;
                } else if (input == KeyEvent.VK_Z&&y>200) {
                    y -= 10;
                }
                repaint();
            }
        });
    }
    
    public void setBloqueur(boolean bloqueur) {
    	this.bloqueur=bloqueur;
    }
	
	public void setPosition(int x,int y) {
		this.x=x;
		this.y=y;
		repaint();
	}
	
	public int getPositionX() {
		return this.x;
	}
	
	public int getPositionY() {
		return this.y;
	}
	
	Image image = new ImageIcon(System.getProperty("user.home")+"\\Documents\\perso.png").getImage();
			
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,x,y,40,50,null);
	}
	
	public void run() {
		repaint();
	}
}
