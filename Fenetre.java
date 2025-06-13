
//CONSTRUCTEUR DE LA FENETRE (ATTENTION A PAS TOUT CASSER)

package escapeGame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Fenetre extends JFrame{
	public boolean checkBouton1, checkBouton2 = false;
	
	public Fenetre() { 
		
		//Paramètres divers
		setTitle("ESCAPE GAME V1");
		setLayout(null);
	    setSize(1280,720); //Taille de l'écran
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	    //Image de fond
	    ImageIcon imageFond = new ImageIcon(System.getProperty("user.home")+"\\Documents\\fond.png");
        JLabel background = new JLabel(imageFond);
        setContentPane(background);
    
        
        //timer 
        JLabel labelTime = new JLabel("Temps écoulé : 0 s");
        labelTime.setBounds(20,20,200,20);
        final int[] secondes = {0};
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondes[0]++;
                labelTime.setText("Temps écoulé : " + secondes[0] + " s");
            }
        });
        timer.start();
        
        
        JButton bouton1 = new JButton("Interagir");
        bouton1.setBounds(160,160,100,20);
        bouton1.setVisible(false);
        add(bouton1);
        
        
        
        JButton bouton2 = new JButton("Interagir");
        bouton2.setBounds(950,160,100,20);
        bouton2.setVisible(false);
        add(bouton2);
        
        ImageIcon interaction = new ImageIcon(System.getProperty("user.home")+"\\Documents\\interagir.png");
        
        
        Enigme2 enigme2Panel = new Enigme2();
        
        //Personnage
        Builder perso = new Builder();
        perso.setPosition(700,200);
        
        add(perso);
        perso.requestFocusInWindow(); //Force le focus

	    //check si on veut quitter ou pas
	    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        getRootPane().getActionMap().put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(background);
                background.setIcon(new ImageIcon(System.getProperty("user.home") + "\\Documents\\fond.png"));
                perso.setBloqueur(false);
                checkBouton1=false;
                checkBouton2=false;
        		perso.setVisible(true);
                perso.requestFocusInWindow();
            }
        });
        
        
       
        //Vérification de la position du joueur pour afficher les énigmes grâce à un timer
        Timer timer2 = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = perso.getPositionX();
                int y = perso.getPositionY();

                if (x>=170&&x<=190&&y<=210){	
                	if(!checkBouton1) {
                		bouton1.setVisible(true);
                	}
                	else {
                		bouton1.setVisible(false);
                	}
                } 
                else if (x>=970&&x<=990&&y<=210) {
                	if(!checkBouton2) {
                		bouton2.setVisible(true);
                	}
                	else {
                		bouton2.setVisible(false);
                	}
                }
                else {
                	bouton1.setVisible(false);
                	bouton2.setVisible(false);
                	background.setIcon(new ImageIcon(System.getProperty("user.home")+"\\Documents\\fond.png"));
                }      
            }
        });
        timer2.start();

        		
        bouton1.addActionListener(b -> {
        	
        	background.setIcon(new ImageIcon(System.getProperty("user.home")+"\\Documents\\enigme1.png"));
        	perso.setBloqueur(true);
            checkBouton1 = true;
            perso.setVisible(false);
        });
        
        bouton2.addActionListener(b -> {
        	if (enigme2Panel.getVerifOrdre()==-1) {
        		perso.setBloqueur(true);
        		checkBouton2 = true;
        		perso.setVisible(false);
        		setContentPane(enigme2Panel);
        		revalidate();
        		repaint();
        	}
        	else {
        		bouton2.setVisible(false);
        	}
        });
        
        ImageIcon deux = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\vert2.png");
        JLabel labelZ = new JLabel(deux);
        labelZ.setBounds(590,100,100,100);
        labelZ.setVisible(false);
        
        enigme2Panel.getBouton().addActionListener (b -> {
        	enigme2Panel.verifier2();
        	System.out.println(enigme2Panel.getVerifOrdre());
        	if(enigme2Panel.getVerifOrdre()==0) {
        		labelZ.setVisible(true);
        	}
        	if (enigme2Panel.getVerifOrdre()>0) {
        		enigme2Panel.setVerifOrdre(-1);
        	}
        });

        
              

        // Ajouter les composants créés précédemment
        background.setVisible(true);
        add(labelTime);
        add(labelZ);
	}
}
