import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

public class Personnage {
    private int x, y;
    private final int width = 51;
    private final int height = 84;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private final int frameDelay = 100;

    private Image[] idle;
    private Image[] leftFrames;
    private Image[] rightFrames;

    private String etat = "idle";
    private String dernierEtat = "";


    public Personnage(int x, int y) {
        this.x = x;
        this.y = y;

        idle = chargerAnimation("src\\graphics\\personnage\\idle", 7);
        leftFrames = chargerAnimation("src\\graphics\\personnage\\left", 8);
        rightFrames = chargerAnimation("src\\graphics\\personnage\\right", 8);
    }

    private Image[] chargerAnimation(String dossier, int nbFrames) {
        Image[] frames = new Image[nbFrames];
        for (int i = 0; i < nbFrames; i++) {
            try {
                frames[i] = ImageIO.read(new File(dossier + "\\" + (i + 1) + ".png"));
            } catch (IOException e) {
                System.out.println("Erreur chargement image : " + dossier + "\\" + (i + 1) + ".png");
                frames[i] = null;
            }
        }
        return frames;
    }

    public void dessiner(Graphics g) {
        Image[] animationActuelle;

        switch (etat) {
            case "right": animationActuelle = rightFrames; break;
            case "idle": animationActuelle = idle; break;
            case "left": animationActuelle = leftFrames; break;
            default: animationActuelle = idle; break;
        }

        if (!etat.equals(dernierEtat)) {
            currentFrame = 0;
            dernierEtat = etat;
        }

        if (System.currentTimeMillis() - lastFrameTime > frameDelay) {
            currentFrame = (currentFrame + 1) % animationActuelle.length;
            lastFrameTime = System.currentTimeMillis();
        }

        Image frame = animationActuelle[currentFrame];
        if (frame != null) {
            g.drawImage(frame, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    public void mettreAJour(Set<Character> touches) {
        int vitesse = 10;
        boolean deplace = false;
        boolean gauche = false;
        boolean droite = false;
        boolean haut = false;
        boolean bas = false;

        if ((touches.contains('z')) && this.y > 200 && !touches.contains('s')) {
            y -= vitesse;
            deplace = true;
            haut = true;
        }
        if ((touches.contains('s')) && this.y < 680 - height && !touches.contains('z')) {
            y += vitesse;
            deplace = true;
            bas = true;
        }
        if ((touches.contains('q')) && this.x > 20 && !touches.contains('d')) {
            x -= vitesse;
            deplace = true;
            gauche = true;
        }
        if ((touches.contains('d'))&& this.x < 1245 - width && !touches.contains('q'))  {
            x += vitesse;
            deplace = true;
            droite = true;
        }

        if (gauche) {
            etat = "left";
        } else if (droite) {
            etat = "right";
        } else if (haut) {
           etat = "right";
        } else if (bas) {
            etat = "left";
        } else if (!deplace) {
            // Si aucune touche pressée
            if (etat.equals("left")) {
                etat = "idle";
            } else if (etat.equals("right")) {
                etat = "idle";
            }
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
