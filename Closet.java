package project.security;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

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
        add(label1);
        add(closet);

    }


    /**
     * Set up label for picture. Then add picture to the label.
     * Add label to the kitchen panel.
     *
     */
    public void setClosetPanel(){

        closet = new JButton("Closet");
        closet.setActionCommand("closet");
        closet.setBackground(Color.CYAN);
        closet.setLocation(0, 0);
        closet.setSize(95, 30);
        closet.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));
        label1 = new JLabel();
        label1.setPreferredSize(new Dimension(100, 100));
        label1.setLocation(0, 0);
        label1.setSize(120, 160);
        Util.setPicturetoLabel(label1, "/images/closet.jpg") ;

    }
    public void setActionListener(ActionListener listener) {
        closet.addActionListener(listener);
    }

}

