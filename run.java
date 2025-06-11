//LE MAIN

package escapeGame;

import javax.swing.*;

public class run {
    public static void main(String[] args) {
    	
    	SwingUtilities.invokeLater(() -> {
    		Fenetre fenetre1 = new Fenetre();
    		fenetre1.setVisible(true);
    		
    	});
    }
}
