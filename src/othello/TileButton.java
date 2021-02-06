package othello;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {

    /**
     * Create the panel
     */
    public TileButton(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon icon = new ImageIcon(
                TileButton.class.getResource("/othello/resources/BlackIcon.png"));
        icon.paintIcon(this, g, 10 , 10);

    }
}

