import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Main extends JPanel implements KeyListener {
    private Image imageFond;
    private Personnage personnage;
    Set<Character> touches = new HashSet<>();

    ArrayList<Block> blocks = new ArrayList<>();


    public Main() {
        this.personnage = new Personnage(500, 500);
        setFocusable(true);
        addKeyListener(this);

        try {
            imageFond = ImageIO.read(new File("src\\graphics\\bg.png")); // mets ton chemin exact ici
        } catch (IOException e) {
            System.out.println("Erreur chargement fond : " + e.getMessage());
        }


        // Rafraîchissement automatique pour animation
        Timer timer = new Timer(35, e -> repaint());
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

         if (imageFond != null) {
            g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), null);
        }

        personnage.dessiner(g);
        personnage.mettreAJour(touches);

        for (Block b : blocks) {
            b.dessiner(g);
        }
        isCollidingWithBlock();
    }

    public void keyPressed(KeyEvent e) {
        char touche = Character.toLowerCase(e.getKeyChar());
        if ("zqsd".indexOf(touche) != -1) {
            touches.add(touche);
        }
    }

    public void keyReleased(KeyEvent e) {
        char touche = Character.toLowerCase(e.getKeyChar());
        touches.remove(touche);
    }
    public void keyTyped(KeyEvent e) {}

    
    public void isCollidingWithBlock() {
        for (Block b : blocks) {
            if (personnage.getBounds().intersects(b.getBounds())) {
                switch (b.getType()) {
                    case 1: // Type 1 = mur
                        System.out.println("⚠ Collision avec un mur !");
                        break;
                    // Ajouter d'autres types de blocs si nécessaire
                    default:
                        System.out.println("⚠ Collision avec un bloc de type " + b.getType() + " !");
                }            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jeu escape game");
        Main panel = new Main();
        
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
