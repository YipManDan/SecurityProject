package project.security;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bathroom extends JPanel {

    private JLabel label1;
    private JButton bathroom;


    public Bathroom(){


        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(252, 405, 95, 140);

        //set up image in the picture panel
        setBathroomPanel();
        add(bathroom);

    }


    /**
     * Set up label for picture. Then add picture to the label.
     * Add label to the kitchen panel.
     *
     */
    public void setBathroomPanel(){

        bathroom = new JButton("Bathroom");
        bathroom.setActionCommand("bathroom");
        bathroom.setBackground(Color.CYAN);
        bathroom.setLocation(0, 0);
        bathroom.setSize(95, 30);
        bathroom.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));

    }
    public void setActionListener(ActionListener listener) {
        bathroom.addActionListener(listener);
    }



}

