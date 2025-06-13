package escapeGame;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Enigme2 extends JPanel{
	
	private ImageIcon statue1, statue2, statue3, statue4, statue5;                                            
	private JButton bouton = new JButton("Valider");							//bouton (doit être utilisable dans fenetre)
	private ArrayList<Integer> ordre = new ArrayList<Integer>();				//liste qui donne l'ordre des statues
	private int indexStatueActive = 0;											
	private int verifOrdre = -1;												//0 si ok, 1 si mauvais (reset ensuite)
	private ArrayList<ImageIcon> listeStatues = new ArrayList<ImageIcon>();		//liste d'images
    private ArrayList<JLabel> listeLabels = new ArrayList<>();					//liste des labels pour afficher les images
	
	public Enigme2() {
        setLayout(null);
        setBounds(0,0,1280,720);
        //ajout images
		this.statue1 = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\statue_9.png");
		this.statue2 = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\statue_6.png");
		this.statue3 = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\statue_10.png");
		this.statue4 = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\statue_8.png");
		this.statue5 = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\statue_7.png");
		listeStatues.add(statue1);
		listeStatues.add(statue2);
		listeStatues.add(statue3);
		listeStatues.add(statue4);
		listeStatues.add(statue5);
		//mise en place de l'ordre
		ordre.add(4);
		ordre.add(1);
		ordre.add(5);
		ordre.add(3);
		ordre.add(2);
		//affichage des images et des labels dans une liste similaire
        int x = -10;
        for (ImageIcon statue : listeStatues) {
            JLabel label = new JLabel(statue);
            label.setBounds(x, 250, 256, 256);  
            listeLabels.add(label);
            add(label);
            x += 256;  // Décalage horizontal
        }
        
        //bouton de validation
		bouton.setBounds(680,600,80,80);
		add(bouton);				
		//texte au-dessus	
		ImageIcon texte2 = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\text2.png");
		JLabel labelTexte = new JLabel(texte2);
		labelTexte.setBounds(0,-20,1280,200);
		add(labelTexte);
		//bouton pour changer la sélection
		JButton btnSuivant = new JButton("Statue suivante");
		btnSuivant.setBounds(50, 600, 150, 30);
		add(btnSuivant);
		//bouton pour déplacer l'image à gauche
		JButton btnGauche = new JButton("⬅️");
		btnGauche.setBounds(220, 600, 80, 30);
		add(btnGauche);
		//bouton pour déplacer l'image à droite
		JButton btnDroite = new JButton("➡️");
		btnDroite.setBounds(320, 600, 80, 30);
		add(btnDroite);
		//image de fond (à mettre en dernier sinon on voit pas le reste
        ImageIcon imageFond = new ImageIcon(System.getProperty("user.home")+"\\Documents\\torche\\enigme_2.png");
        JLabel labelFond = new JLabel(imageFond);
        labelFond.setBounds(0,0,1280,720);
        add(labelFond);
        //surlignage au début
        surlignerStatueActive();
        //augmente l'index de sélection (donc uniquement de gauche à droite mais arrivé au bout repart au début
        btnSuivant.addActionListener(e -> {
            indexStatueActive = (indexStatueActive + 1) % 5;
            surlignerStatueActive();
        });
        //magie noire de la bibliothèque collections qui permet d'échanger les deux index si on est pas au début
        btnGauche.addActionListener(e -> {
            if (indexStatueActive > 0) {
                Collections.swap(ordre, indexStatueActive, indexStatueActive - 1);
                Collections.swap(listeLabels, indexStatueActive, indexStatueActive - 1);
                indexStatueActive--; // Suivre la statue
                updatePositions();
                surlignerStatueActive();
            }
        });
      //pareil mais bloque à la fin
        btnDroite.addActionListener(e -> {
            if (indexStatueActive < 4) {
                Collections.swap(ordre, indexStatueActive, indexStatueActive + 1);
                Collections.swap(listeLabels, indexStatueActive, indexStatueActive + 1);
                indexStatueActive++;
                updatePositions();
                surlignerStatueActive();
            }
        });
              
	}
	
	//surligne en affichant une bordure jaune
    private void surlignerStatueActive() {
        for (int i = 0; i < listeLabels.size(); i++) {
            listeLabels.get(i).setBorder(i == indexStatueActive ? BorderFactory.createLineBorder(Color.YELLOW, 3) : null);
        }
    }
    //pour gérer les boutons gauche et droite
    private void updatePositions() {
        int x = -10;
        for (JLabel label : listeLabels) {
            label.setBounds(x, 250, 256, 256);
            x += 256;
        }
        repaint();
    }
	//récupère le bouton valider dans fenêtre
	public JButton getBouton() {
		return this.bouton;
	}
	//récupère la vérification si le jeu est réussi
	public int getVerifOrdre() {
		return this.verifOrdre;
	}
	//permet de remettre le jeu comme non fait si on quitte
	public void setVerifOrdre(int verifOrdre) {
		this.verifOrdre=verifOrdre;
	}
	//vérifie le jeu 
	public void verifier2() {
    	for (int i=0;i<4;i++) {
    		if (ordre.get(i)>ordre.get(i+1)) {
    			verifOrdre++;
    			break;
    		}
    	}
    	verifOrdre++;
	}
}
