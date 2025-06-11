import java.awt.*;

class Block {
    int taille = 32;
    int x;
    int y;
    int type; // Identifiant du bloc, utile pour la gestion des collisions et des interactions: type 1 = mur

    Block(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    void dessiner(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, taille, taille);
    }

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

}