package escapeGame;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Enigma {
	private String dossier;
    private String nomFichier;
    private String chemin = dossier + nomFichier;
        
    public Enigma(String dossier, String nomFichier) {
    	this.dossier = dossier;
    	this.nomFichier = nomFichier; 	
    	this.chemin = dossier + nomFichier;
    }
    
    public String getChemin() {
    	return this.chemin;
    }
    
    
    public void creerMDP() {
    	try {
    		File repertoire = new File(this.dossier);
            if (!repertoire.exists()) {
                repertoire.mkdirs(); // Crée le dossier et les sous-dossiers
            }

            FileWriter writer = new FileWriter(chemin);
            int i=0;
            int seed;
            while (i<=6) {
            	if ((int)System.nanoTime()<0) {
            		seed = -(int)System.nanoTime();
            	}
            	else {
            		seed = (int)System.nanoTime();
            	}
            	int text = seed%26+65;
            	writer.write((char)text);
            	System.out.println(text);
            	i++;
            }
            writer.close();
     
            System.out.println("Fichier créé à l'emplacement : " + chemin);
        } 
    	catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
