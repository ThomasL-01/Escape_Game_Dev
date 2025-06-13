import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class InteractionBlock extends Block {

    private ImageIcon icon = new ImageIcon("src\\graphics\\interagir.png");
    private JButton button = new JButton(icon);

    InteractionBlock(int x, int y, JFrame frame, JLayeredPane layeredPane) {
        super(x, y, 0, frame, layeredPane ); // Type 0 pour interaction spéciale
    }

    public void appearence() {
        if (isActive) {
            if (button.getParent() == null) {
                button.setBounds(x-50, y-50, 150, 52);
                button.setFocusable(false);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.addActionListener(e -> System.out.println("Bouton cliqué"));
                layeredPane.add(button, Integer.valueOf(2));
                layeredPane.revalidate();
                layeredPane.repaint();
            }
        } else {
            if (button.getParent() != null) {
                layeredPane.remove(button);
                layeredPane.revalidate();
                layeredPane.repaint();
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}