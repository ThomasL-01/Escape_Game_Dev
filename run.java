package escapeGame;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class run {
    public static void main(String[] args) {
    	
    	Enigma enigme1 = new Enigma("C:\\Users\\elrob\\Documents\\","enigme1.txt");
    	enigme1.creerMDP();
    	
    	Cryptage c1 = new Cryptage("bonsoir a tous",1);

        //fenêtre
        JFrame fenetre = new JFrame("ESCAPE GAME V1");
        fenetre.setLayout(null);

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
        
        // Action quand on clique sur le bouton
        bouton1.addActionListener(b -> {
        	
            String code = champTexte1.getText();
        	try {
        		BufferedReader lecteur = new BufferedReader(new FileReader(enigme1.getChemin()));
        		if (code.equals(lecteur.readLine()) ) {
        			JOptionPane.showMessageDialog(fenetre, "Code bon");
        		}
        		else {
        			JOptionPane.showMessageDialog(fenetre, "Mauvais code. Veuillez réessayer.");
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
        		JOptionPane.showMessageDialog(fenetre, "Code bon");
        	}
        	else {
        		JOptionPane.showMessageDialog(fenetre, "Mauvais code. Veuillez réessayer.");
        	}
        });
        
        fenetre.setSize(1000, 800);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        // Ajouter les composants au panneau
        fenetre.add(label1);
        fenetre.add(champTexte1);
        fenetre.add(bouton1);
        fenetre.add(label2);
        fenetre.add(champTexte2);
        fenetre.add(bouton2);
        fenetre.add(labelTime);

        fenetre.setVisible(true);
    }
    	
 
}
