import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener{

	JPanel formPanel, inputPanel, emailPanel, emailLabelPanel, emailTextPanel, passwordPanel, passwordLabelPanel, 
	passwordFieldPanel, soutPanel, buttonPanel, signUpPanel;
	JLabel loginLabel, emailOrPhoneLabel, passwordLabel, signUpLabel;
	JTextField emailOrPhoneTextField;
	JPasswordField passwordField;
	JButton loginButton;
	
	Connect con;
	
	public Login() {
		
		con = new Connect();
		
		// Component Initialization
		formPanel = new JPanel(new BorderLayout());
		formPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
		
		loginLabel = new JLabel("Login Form", JLabel.CENTER);
		loginLabel.setFont(new Font("", Font.BOLD, 24));
		formPanel.add(loginLabel, BorderLayout.NORTH);
		
		inputPanel = new JPanel(new BorderLayout());
		inputPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		inputPanel.setBackground(Color.decode("#00FFFF"));
		
		emailPanel = new JPanel(new GridLayout(1, 2));
		emailPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailTextPanel = new JPanel();
		
		emailOrPhoneLabel = new JLabel("Email or Phone");
		emailOrPhoneTextField = new JTextField();
		emailOrPhoneTextField.setPreferredSize(new Dimension(220, 30));
		emailLabelPanel.add(emailOrPhoneLabel);
		emailTextPanel.add(emailOrPhoneTextField);
		
		emailPanel.add(emailLabelPanel);
		emailPanel.add(emailTextPanel);
		
		passwordPanel = new JPanel(new GridLayout(1, 2));
		passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordFieldPanel = new JPanel();
		
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(220, 30));
		passwordLabelPanel.add(passwordLabel);
		passwordFieldPanel.add(passwordField);
		
		passwordPanel.add(passwordLabelPanel);
		passwordPanel.add(passwordFieldPanel);
		
		inputPanel.add(emailPanel, BorderLayout.NORTH);
		inputPanel.add(passwordPanel, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(150, 30));
		loginButton.addActionListener(this);
		
		signUpPanel = new JPanel();
		signUpPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		signUpLabel = new JLabel("Sign up here.");
		signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                redirectToRegister();
            }
        });
		signUpPanel.add(signUpLabel);
		
		buttonPanel.add(loginButton);
		
		inputPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		formPanel.add(inputPanel, BorderLayout.CENTER);
		formPanel.add(signUpPanel, BorderLayout.SOUTH);
		
		// Set color
		formPanel.setBackground(Color.decode("#00FFFF"));
		emailPanel.setBackground(Color.decode("#00FFFF"));
		emailLabelPanel.setBackground(Color.decode("#00FFFF"));
		emailTextPanel.setBackground(Color.decode("#00FFFF"));
		passwordPanel.setBackground(Color.decode("#00FFFF"));
		passwordLabelPanel.setBackground(Color.decode("#00FFFF"));
		passwordFieldPanel.setBackground(Color.decode("#00FFFF"));
		buttonPanel.setBackground(Color.decode("#00FFFF"));
		signUpPanel.setBackground(Color.decode("#00FFFF"));
		
		// Frame Initialization
		
		add(formPanel);
		
		setTitle("Login Form");
		setSize(500, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private boolean validateInput() {
		return validateEmail() && validatePassword();
	}
	
	private boolean validateEmail() {
		String emailOrPhone = emailOrPhoneTextField.getText();
		return (emailOrPhone.trim().length() > 0);
	}
	
	private boolean validatePassword() {
		String password = String.valueOf(passwordField.getPassword());
		return (password.trim().length() > 0);
	}
	
	private String[] validateUser() {
		String emailOrPhone = emailOrPhoneTextField.getText();
		String password = String.valueOf(passwordField.getPassword());
		
		con.rs = con.execQuery("SELECT UserID, UserFullName, UserPassword FROM users WHERE UserEmail = '" + emailOrPhone + "' OR UserPhone = '" + emailOrPhone + "'");
		
		if (con.rs == null) {
			return null;
		}
		
		try {
			while (con.rs.next()) {
				try {
					String inputPassword = String.valueOf(con.rs.getObject(3));
					if (password.equals(inputPassword)) {
						String[] value = new String[2];
						value[0] = String.valueOf(con.rs.getObject(1));
						value[1] = String.valueOf(con.rs.getObject(2));
						return value;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	private void redirectToRegister() {
		dispose();
		Register register = new Register();
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == loginButton) {
			if (validateInput()) {		
				String[] user = validateUser();
				if (user != null) {					
					JOptionPane.showMessageDialog(null, "Login Success, Welcome, " + user[1] + "!");
					// Redirect here, pass user id
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Failed!","Warning Message",JOptionPane.WARNING_MESSAGE); 
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Login Failed!\nPlease fill all of the data!","Warning Message",JOptionPane.WARNING_MESSAGE); 
			}
		}
	}

}
