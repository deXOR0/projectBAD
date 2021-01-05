import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame{

	JLabel background;
	JPanel formPanel;
	ImageIcon img;
	
	MainMenu() {
		formPanel = new JPanel();
		
		img = new ImageIcon("bg.png");
		

		
		add(formPanel);
		setLayout(null);
		setSize(623, 338);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, 623, 338);
		add(background);
		
	}
	
}
