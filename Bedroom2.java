package project.security;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bedroom2 extends JPanel {

    private JLabel label1;
    private JButton room2;

    public Bedroom2(){


        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(52, 254, 194, 290);


        //set up image in the picture panel
        setBedroom2Panel();
        add(room2);

    }


    /**
     * Set up label for picture. Then add picture to the label.
     * Add label to the kitchen panel.
     *
     */
    public void setBedroom2Panel(){

        room2 = new JButton("Bedroom2");
        room2.setActionCommand("room2");
        room2.setBackground(Color.CYAN);
        room2.setLocation(0, 0);
        room2.setSize(105, 30);
        room2.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));



    }
    public void setActionListener(ActionListener listener) {
        room2.addActionListener(listener);
    }



}
