package escapeGame;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class Builder extends JPanel{
	private int x,y;  //position

    public Builder() {
        setOpaque(false); 
        Dimension ecran = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, ecran.width, ecran.height); //les mêmes que la fenêtre
        setFocusable(true); // Récupère le clavier
        requestFocusInWindow(); // Assure que le panneau a le focus
        
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int input = e.getKeyCode();
                if (input == KeyEvent.VK_D&&x<ecran.width-200) {       //déplacement en ZQSD
                    x += 10;
                } else if (input == KeyEvent.VK_Q&&x>200) {
                    x -= 10;
                } else if (input == KeyEvent.VK_S&&y<ecran.height-200) {
                    y += 10;
                } else if (input == KeyEvent.VK_Z&&y>200) {
                    y -= 10;
                }
                repaint();
            }
        });
    }
	
	public void setPosition(int x,int y) {
		this.x=x;
		this.y=y;
		repaint();
	}
	
	Image image = new ImageIcon(System.getProperty("user.home")+"\\Documents\\baton.png").getImage();
			
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,x,y,100,100,null);
	}
	
	public void run() {
		repaint();
	}
}
