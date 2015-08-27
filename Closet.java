package project.security;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Closet extends JPanel {

    private JLabel label1;
    private JButton closet;


    public Closet(){


        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(252, 52, 95, 240);

        //set up image in the picture panel
        setClosetPanel();
        add(closet);

    }


    /**
     * Set up label for picture. Then add picture to the label.
     * Add label to the kitchen panel.
     *
     */
    public void setClosetPanel(){

        closet = new JButton("Closet");
        closet.setBackground(Color.CYAN);
        closet.setLocation(0, 0);
        closet.setSize(95, 30);
        closet.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));

    }

}

