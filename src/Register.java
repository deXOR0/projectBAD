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
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Register extends JFrame implements ActionListener{


	JPanel formPanel, inputPanel, genderButtonPanel, idLabelPanel,
	idTextPanel, fullNameLabelPanel, fullNameTextPanel,
	emailLabelPanel, emailTextPanel, phoneLabelPanel, 
	phoneTextPanel, passwordLabelPanel, passwordFieldPanel,
	roleLabelPanel, userRoleComboPanel, addressLabelPanel,
	genderLabelPanel, southPanel, buttonPanel;
	JLabel titleLabel, idLabel, fullNameLabel, emailLabel, 
	phoneLabel, addressLabel, passwordLabel, genderLabel, 
	roleLabel, signInLabel;
	JTextField idTextField, fullNameTextField, emailTextField, phoneTextField;
	JTextArea addressArea;
	JPasswordField passwordField;
	JRadioButton maleButton, femaleButton;
	ButtonGroup genderGroup;
	JComboBox<String> userRoleCombo;
	JButton registerButton;
	
	Connect con;
	
	//User data
	private String id, fullName, email, phone, address, password, gender, role;
	
	private boolean checkDuplicate(String key, String value) {
		
		con.rs = con.execQuery("SELECT UserID FROM users WHERE " + key + " = '" + value + "'");
		
		try {
			while (con.rs.next()) {
				return true;			
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
	
	private String generateID() {
		
		Random random = new Random();
		
		String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		
		do {
			id = "US-";
			for (int i = 0; i < 6; i++) {
				id += String.valueOf(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
			} 
		} while (checkDuplicate("UserID", id));
		
		
		return id;
	}
	
	public Register() {
		
		con = new Connect();
		
		formPanel = new JPanel(new BorderLayout());
		formPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		titleLabel = new JLabel("Register Form", JLabel.CENTER);
		titleLabel.setFont(new Font("", Font.PLAIN, 20));
		formPanel.add(titleLabel, BorderLayout.NORTH);
		
		//Input
		
		inputPanel = new JPanel(new GridLayout(8, 2));
		inputPanel.setBorder(new EmptyBorder(30, 30, 20, 30));
		
		idLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idTextPanel = new JPanel();
		
		idLabel = new JLabel("ID");
		idTextField = new JTextField();
		idTextField.setText(generateID());
		idTextField.setEditable(false);
		idTextField.setPreferredSize(new Dimension(210, 30));
		
		idLabelPanel.add(idLabel);
		idTextPanel.add(idTextField);
		
		fullNameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fullNameTextPanel = new JPanel();
		
		fullNameLabel = new JLabel("Full Name");
		fullNameTextField = new JTextField();
		
		fullNameLabelPanel.add(fullNameLabel);
		fullNameTextPanel.add(fullNameTextField);
		fullNameTextField.setPreferredSize(new Dimension(210, 30));
		
		emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailTextPanel = new JPanel();
		
		emailLabel = new JLabel("Email");
		emailTextField = new JTextField();
		emailTextField.setPreferredSize(new Dimension(210, 30));
		
		emailLabelPanel.add(emailLabel);
		emailTextPanel.add(emailTextField);
		
		phoneLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phoneTextPanel = new JPanel();
		
		phoneLabel = new JLabel("Phone");
		phoneTextField = new JTextField();
		phoneTextField.setPreferredSize(new Dimension(210, 30));
		
		phoneLabelPanel.add(phoneLabel);
		phoneTextPanel.add(phoneTextField);
		
		addressLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		addressLabel = new JLabel("Address");
		addressArea = new JTextArea();
		
		addressLabelPanel.add(addressLabel);
		
		passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordLabelPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		passwordFieldPanel = new JPanel();
		passwordFieldPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(210, 30));
		
		passwordLabelPanel.add(passwordLabel);
		passwordFieldPanel.add(passwordField);
		
		genderLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderLabelPanel.setBorder(new EmptyBorder(13, 0, 0, 0));
		genderButtonPanel = new JPanel(new GridLayout(1, 2));
		genderButtonPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		
		genderLabel = new JLabel("Gender");
		//Radio Button Group
		genderGroup = new ButtonGroup();
		
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");

		genderLabelPanel.add(genderLabel);
		genderButtonPanel.add(maleButton);
		genderButtonPanel.add(femaleButton);
		
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		
		roleLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		userRoleComboPanel = new JPanel();
		
		roleLabel = new JLabel("Role");
		String[] choice = { "-Choose One-", "User", "Admin" };
		userRoleCombo = new JComboBox<>(choice);
		userRoleCombo.setPreferredSize(new Dimension(210, 30));
		
		roleLabelPanel.add(roleLabel);
		userRoleComboPanel.add(userRoleCombo);
		
		inputPanel.add(idLabelPanel);
		inputPanel.add(idTextPanel);
		inputPanel.add(fullNameLabelPanel);
		inputPanel.add(fullNameTextPanel);
		inputPanel.add(emailLabelPanel);
		inputPanel.add(emailTextPanel);
		inputPanel.add(phoneLabelPanel);
		inputPanel.add(phoneTextPanel);
		inputPanel.add(addressLabelPanel);
		inputPanel.add(addressArea);
		inputPanel.add(passwordLabelPanel);
		inputPanel.add(passwordFieldPanel);
		inputPanel.add(genderLabelPanel);
		inputPanel.add(genderButtonPanel);
		inputPanel.add(roleLabelPanel);
		inputPanel.add(userRoleComboPanel);
		
		formPanel.add(inputPanel, BorderLayout.CENTER);
		
		southPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(this);
		
		buttonPanel.add(registerButton);
		
		signInLabel = new JLabel("Sign in here.", JLabel.CENTER);
		signInLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                redirectToLogin();
            }
        });
		
		southPanel.add(buttonPanel);
		southPanel.add(signInLabel);
		
		formPanel.add(southPanel, BorderLayout.SOUTH);
		
		//Color
		formPanel.setBackground(Color.decode("#00FFFF"));
		inputPanel.setBackground(Color.decode("#00FFFF"));
		idLabelPanel.setBackground(Color.decode("#00FFFF"));
		idTextPanel.setBackground(Color.decode("#00FFFF"));
		fullNameLabelPanel.setBackground(Color.decode("#00FFFF"));
		fullNameTextPanel.setBackground(Color.decode("#00FFFF"));
		emailLabelPanel.setBackground(Color.decode("#00FFFF"));
		emailTextPanel.setBackground(Color.decode("#00FFFF"));
		phoneLabelPanel.setBackground(Color.decode("#00FFFF"));
		phoneTextPanel.setBackground(Color.decode("#00FFFF"));
		addressLabelPanel.setBackground(Color.decode("#00FFFF"));
		passwordLabelPanel.setBackground(Color.decode("#00FFFF"));
		passwordFieldPanel.setBackground(Color.decode("#00FFFF"));
		genderLabelPanel.setBackground(Color.decode("#00FFFF"));
		genderButtonPanel.setBackground(Color.decode("#00FFFF"));
		maleButton.setBackground(Color.decode("#00FFFF"));
		femaleButton.setBackground(Color.decode("#00FFFF"));
		roleLabelPanel.setBackground(Color.decode("#00FFFF"));
		userRoleComboPanel.setBackground(Color.decode("#00FFFF"));
		buttonPanel.setBackground(Color.decode("#00FFFF"));
		southPanel.setBackground(Color.decode("#00FFFF"));
		// Frame Initialization
		
		add(formPanel);
		setTitle("Register Form");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private boolean validateFullName() {
		fullName = fullNameTextField.getText().trim();
		if (fullName.length() >= 5 && fullName.length() <= 30) {
			return true;
		}
		else {
			
			JOptionPane.showMessageDialog(null, "Please fill with valid full name!","Warning Message",JOptionPane.WARNING_MESSAGE); 
			
			return false;
		}
	}
	
	private boolean wrongEmail() {
		JOptionPane.showMessageDialog(null, "Please fill with valid email!","Warning Message",JOptionPane.WARNING_MESSAGE); 
		return false;
	}
	
	private boolean validateEmail() {
		email = emailTextField.getText().trim();
		
		if (checkDuplicate("UserEmail", email)) {
			JOptionPane.showMessageDialog(null, "Email is already taken!","Warning Message",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (email.length() < 1) {
			return wrongEmail();
		}
		
		boolean username = false, domain = false;
		int atIndex = -1;
		
		username = (Character.isAlphabetic(email.charAt(0)) || Character.isDigit(email.charAt(0)));
		atIndex = email.indexOf('@');
		
		if (!username || atIndex < 0 || atIndex + 1 >= email.length()) {
			return wrongEmail();
		}
		if (email.substring(atIndex + 1, atIndex + 2).equals(".")) {
				return wrongEmail();
		}
		else {
			
			if (email.substring(atIndex + 2).contains(".") && !email.endsWith(".")) {				
				return true;
			}
			
		}
		
		return wrongEmail();
		
	}
	
	private boolean validatePhone() {
		phone = phoneTextField.getText().trim();
		
		if (checkDuplicate("UserPhone", phone)) {
			JOptionPane.showMessageDialog(null, "Phone number is already taken!","Warning Message",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			if (phone.length() < 10 || phone.length() > 12) {
				JOptionPane.showMessageDialog(null, "Please fill with valid phone number!","Warning Message",JOptionPane.WARNING_MESSAGE);
				return false;
			}
			else {
				return true;
			}
		}
		
	}
	
	private boolean validateAddress() {
		address = addressArea.getText().trim();
		
		if (address.length() < 10 || !address.endsWith(" Street")) {
			JOptionPane.showMessageDialog(null, "Please fill with valid address!","Warning Message",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			return true;
		}
		
	}
	
	private boolean validatePassword() {
		password = String.valueOf(passwordField.getPassword());
		
		if (password.length() >= 8 && password.length() <= 20) {
			boolean hasDigit = false, hasAlpha = false;
			for (int i = 0; i < password.length(); i++) {
				if (Character.isAlphabetic(password.charAt(i)) && !hasAlpha) {
					hasAlpha = true;
				}
				else if (Character.isDigit(password.charAt(i)) && !hasDigit) {
					hasDigit = true;
				}
				if (hasAlpha && hasDigit) {
					return true;
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "Please fill with valid password!","Warning Message",JOptionPane.WARNING_MESSAGE);
		return false;
		
	}
	
	private boolean validateGender() {
		if (maleButton.isSelected() || femaleButton.isSelected()) {
			gender = (maleButton.isSelected()) ? "Male" : "Female";
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Please select a gender!","Warning Message",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	private boolean validateRole() {
		role = userRoleCombo.getSelectedItem().toString();
		if (role.equals("-Choose One-")) {
			JOptionPane.showMessageDialog(null, "Please select a role!","Warning Message",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean validateInput() {
		return (validateFullName() && validateEmail() && validatePhone() && validateAddress() && validatePassword() && validateGender() && validateRole());
	}
	
	private void redirectToLogin() {		
		dispose();
		Login login = new Login();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == registerButton) {
			if (validateInput()) {
				con.insertIntoUsers(id, fullName, email, phone, password, gender, address, role);
				JOptionPane.showMessageDialog(null, "Register Success!");
				redirectToLogin();
			}
		}
	}

}
