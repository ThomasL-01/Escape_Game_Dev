import java.awt.*;
import javax.swing.*;


class Block {
    protected int taille = 32;
    protected int x;
    protected int y;
    protected int type; // Identifiant du bloc, utile pour la gestion des collisions et des interactions: type 1 = mur
    protected boolean isActive = false; // Indique si le bloc est actif

    protected JFrame frame;
    protected JLayeredPane layeredPane;

    Block(int x, int y, int type, JFrame frame, JLayeredPane layeredPane) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.frame = frame;
        this.layeredPane = layeredPane;
    }

    void dessiner(Graphics g) { }

    Rectangle getBounds() {
        return new Rectangle(x, y, taille, taille);
    }

    public int getType() {
        return type;
    }

    public int getTaille() {
        return taille;
    }
  
    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void appearence() {}

    public boolean isActive() {
        return false; // Par défaut, le bloc n'est pas actif
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }

}