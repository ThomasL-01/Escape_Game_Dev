
//CONSTRUCTEUR DE LA FENETRE (ATTENTION A PAS TOUT CASSER)

package escapeGame;

import javax.swing.*;

//import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Fenetre extends JFrame{

	
	public Fenetre() { 
		
		//Paramètres divers
		setTitle("ESCAPE GAME V1");
		setLayout(null);
	    setSize(Toolkit.getDefaultToolkit().getScreenSize()); //Taille de l'écran
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	    //Image de fond
	    ImageIcon imageFond = new ImageIcon(System.getProperty("user.home")+"\\Documents\\arp.jpg");
        JLabel background = new JLabel(imageFond);
        setContentPane(background);

		
	    
	    //Les méthodes principales du jeu
    	Enigma enigme1 = new Enigma(System.getProperty("user.home")+"\\Documents\\","enigme1.txt");
    	enigme1.creerMDP();
    	
    	Cryptage c1 = new Cryptage("bonsoir a tous",1);
    	
        //label, champ de texte, bouton
        JLabel label1 = new JLabel("Entrez le code à 6 lettres en majuscules :");
        JTextField champTexte1 = new JTextField(20);
        JButton bouton1 = new JButton("Valider");
        label1.setBounds(150,100,250,20);
        champTexte1.setBounds(400,100,150,20);
        bouton1.setBounds(550,100,100,20);
        
        //2e
        JLabel label2 = new JLabel("Le code est [ " + c1.decalageModifier() + " ] crypté avec un décalage de " + c1.getDecalage() +". Entrez le code décrypté pour continuer : ");
        JTextField champTexte2 = new JTextField(20);
        JButton bouton2 = new JButton("Valider");
        label2.setBounds(100,300,700,20);
        champTexte2.setBounds(600,350,150,20);
        bouton2.setBounds(750,350,100,20);
        
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
        
        // Actions quand on clique sur les boutons
        bouton1.addActionListener(b -> {
        	
            String code = champTexte1.getText();
        	try {
        		BufferedReader lecteur = new BufferedReader(new FileReader(enigme1.getChemin()));
        		if (code.equals(lecteur.readLine()) ) {
        			JOptionPane.showMessageDialog(this, "Code bon");
        		}
        		else {
        			JOptionPane.showMessageDialog(this, "Mauvais code. Veuillez réessayer.");
        		}
            	lecteur.close();
        	} 
        	catch (IOException e) {
        		System.out.println("Erreur : " + e.getMessage());
    		}
        });
        
        bouton2.addActionListener(b -> {
        	
            String code = champTexte2.getText();
        	if (code.equals(c1.getMessage()) ) {
        		JOptionPane.showMessageDialog(this, "Code bon");
        	}
        	else {
        		JOptionPane.showMessageDialog(this, "Mauvais code. Veuillez réessayer.");
        	}
        });
        
        //Connstucteur de formes
        Builder bloc1 = new Builder();
        bloc1.setPosition(500,500);
        

        // Ajouter les composants créés précédemment
        background.setVisible(true);
        add(bloc1);
        bloc1.requestFocusInWindow(); //Force le focus
        add(label1);
        add(champTexte1);
        add(bouton1);
        add(label2);
        add(champTexte2);
        add(bouton2);
        add(labelTime);
	}
	
	
}
