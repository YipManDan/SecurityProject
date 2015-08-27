package project.security;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LivingRoom extends JPanel {

    private JLabel label1;
    private JButton livingRoom;

    public LivingRoom(){

        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(352, 252, 190, 290);


        //set up image in the picture panel
        setLivingRoomPanel();
        add(livingRoom);

    }


    /**
     * Set up label for picture. Then add picture to the label.
     * Add label to the kitchen panel.
     *
     */
    public void setLivingRoomPanel(){


        livingRoom = new JButton("Living Room");
        livingRoom.setBackground(Color.CYAN);
        livingRoom.setLocation(0, 0);
        livingRoom.setSize(120, 30);
        livingRoom.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));

    }
}
