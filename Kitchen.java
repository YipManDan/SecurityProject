package project.security;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Kitchen extends JPanel {

    private JLabel label1;
    private JButton kitchen;

    public Kitchen() {

        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(352, 52, 195, 195);

        // set up image in the picture panel
        setKitchenPanel();
        add(kitchen);
    }

    /**
     * Set up label for picture. Then add picture to the label. Add label to the
     * kitchen panel.
     *
     */
    public void setKitchenPanel() {

        kitchen = new JButton("Kitchen");
        kitchen.setActionCommand("kitchen");
        kitchen.setBackground(Color.CYAN);
        kitchen.setLocation(0, 0);
        kitchen.setSize(105, 30);
        kitchen.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));

    }
    public void setActionListener(ActionListener listener) {
        kitchen.addActionListener(listener);
    }

}