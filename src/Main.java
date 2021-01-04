import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main implements ActionListener{

	//Instantiation
	JFrame frame = new JFrame();
	JLabel usernameLabel, passwordLabel, registerLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	JPanel registerPanel;
	JButton registerButton;
	
	Connect con;
	
	public Main() {
		
//		// Frame initialization
//		frame.setTitle("My Application");
//		frame.setSize(300, 150);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		
//		register();
		
		Login login = new Login();
		
	}
	
	void register() {
		
		
		//Component initialization
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		registerLabel = new JLabel("Register", JLabel.CENTER);
		registerButton = new JButton("Submit");
		registerButton.addActionListener(this);
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		
		//Panel initialization
		registerPanel = new JPanel(new GridLayout(2,2));
		
		registerPanel.add(usernameLabel);
		registerPanel.add(usernameField);
		registerPanel.add(passwordLabel);
		registerPanel.add(passwordField);
		
		frame.setTitle("Register");
		frame.setLayout(new BorderLayout());
		frame.add(registerLabel, BorderLayout.NORTH);
		frame.add(registerPanel, BorderLayout.CENTER);
		frame.add(registerButton, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Main();
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == registerButton) {
			JOptionPane.showConfirmDialog(null, "Kontil");
		}
	}

}
