package project.security;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bedroom1 extends JPanel {

    private JLabel label1,label2;
    private JButton room1;


    public Bedroom1(){


        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(52, 52, 194, 194);


        //set up image in the picture panel
        setBedroom1Panel();
        //add(label1);
        add(room1);
        add(label2);

        //Kitchen.setBorder(BorderFactory.createLineBorder(Color.black));
    }


    /**
     * Set up label for picture. Then add picture to the label.
     * Add label to the kitchen panel.
     *
     */
    public void setBedroom1Panel(){

        //create and set up label
        //label1 = new JLabel();
        room1 = new JButton("Bedroom1");

		/*label1.setPreferredSize(new Dimension(80, 50));
		label1.setBackground(Color.red);

		label1.setLocation(0, 0);
		label1.setSize(80, 50);
		label1.setText("Bedroom1");
		label1.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 14));*/


        //room1.setBorderPainted(false);
        room1.setBackground(Color.CYAN);
        room1.setLocation(0, 0);
        room1.setSize(105, 30);
        //room1.setForeground(Color.BLUE);
        room1.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 12));


        label2 = new JLabel();

        label2.setPreferredSize(new Dimension(100, 100));
        //label2.setBackground(Color.red);

        label2.setLocation(0, 0);
        label2.setSize(120, 160);
        //label1.setForeground(Color.GRAY);
        // add image to the label
        Util.setPicturetoLabel(label2, "/images/bedroom1.jpg") ;



    }



}
