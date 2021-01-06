import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.concurrent.Flow;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Profile implements ActionListener{

	public JInternalFrame intFrame;
	
	Connect con;
	
	JPanel formPanel, profilePanel, passwordPanel, profileInputPanel,
	passwordInputPanel, fullNameLabelPanel, fullNameTextPanel,
	emailLabelPanel, emailTextPanel, phoneLabelPanel, phoneTextPanel,
	addressLabelPanel, addressAreaPanel, genderLabelPanel, genderButtonPanel,
	updateProfileButtonPanel, oldPasswordLabelPanel, oldPasswordFieldPanel,
	newPasswordLabelPanel, newPasswordFieldPanel, confirmationPasswordLabelPanel, 
	confirmationPasswordFieldPanel, changePasswordButtonPanel;
	JLabel profileTitleLabel, fullNameLabel, emailLabel, 
	phoneLabel, addressLabel, genderLabel, changePasswordTitleLabel,
	oldPasswordLabel, newPasswordLabel, confirmationPasswordLabel;
	JTextField fullNameText, emailText, phoneText;
	JTextArea addressArea;
	JRadioButton maleButton, femaleButton;
	ButtonGroup genderGroup;
	JPasswordField oldPasswordField, newPasswordField, confirmPasswordField;
	JButton updateProfileButton, changePasswordButton;
	
	// User Data
	private String userID;
	private String fullName, email, phone, address, gender, password;
	
	public Profile(String userID) {
		
		this.userID = userID;
		
		intFrame = new JInternalFrame("Profile Form",true,true,true,true);
	}
	
	public void getUserData() {
		
		con = new Connect();
		
		con.rs = con.execQuery("SELECT UserFullName, UserEmail, UserPhone, UserAddress, UserGender, UserPassword FROM users WHERE UserID = '" + userID + "'");
		
		try {
			while (con.rs.next()) {
				try {
					
					fullName = String.valueOf(con.rs.getObject(1));
					email = String.valueOf(con.rs.getObject(2));
					phone = String.valueOf(con.rs.getObject(3));
					address = String.valueOf(con.rs.getObject(4));
					gender = String.valueOf(con.rs.getObject(5));
					password = String.valueOf(con.rs.getObject(6));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setInternalFrame() {
		
		getUserData();
		
		try {
			intFrame.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		formPanel = new JPanel(new GridLayout(1,2));
		formPanel.setBorder(new EmptyBorder(40, 40, 100, 0));
		
		// Profile
		profilePanel = new JPanel(new BorderLayout());
		
		profileTitleLabel = new JLabel("Profile");
		
		profilePanel.add(profileTitleLabel, BorderLayout.NORTH);
		profileTitleLabel.setFont(new Font("", Font.PLAIN, 20));
		
		profileInputPanel = new JPanel(new GridLayout(5,2));
		profileInputPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		
		fullNameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fullNameTextPanel = new JPanel();
		
		fullNameLabel = new JLabel("Full Name");
		fullNameText = new JTextField();
		fullNameText.setPreferredSize(new Dimension(250, 40));
		fullNameText.setText(fullName);
		
		fullNameLabelPanel.add(fullNameLabel);
		fullNameTextPanel.add(fullNameText);
		
		emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailTextPanel = new JPanel();
		
		emailLabel = new JLabel("Email");
		emailText = new JTextField();
		emailText.setPreferredSize(new Dimension(250, 40));
		emailText.setText(email);
		
		emailLabelPanel.add(emailLabel);
		emailTextPanel.add(emailText);
		
		phoneLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phoneTextPanel = new JPanel();
		
		phoneLabel = new JLabel("Phone");
		phoneText = new JTextField();
		phoneText.setPreferredSize(new Dimension(250, 40));
		phoneText.setText(phone);
		
		phoneLabelPanel.add(phoneLabel);
		phoneTextPanel.add(phoneText);
		
		addressLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addressAreaPanel = new JPanel();
		
		addressLabel = new JLabel("Address");
		addressArea = new JTextArea();
		addressArea.setPreferredSize(new Dimension(250, 80));
		addressArea.setText(address);
		
		addressLabelPanel.add(addressLabel);
		addressAreaPanel.add(addressArea);
		
		genderLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderLabelPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		genderButtonPanel = new JPanel(new GridLayout(1, 2));
		genderButtonPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		
		genderLabel = new JLabel("Gender");
		genderGroup = new ButtonGroup();
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		
		genderLabelPanel.add(genderLabel);
		genderButtonPanel.add(maleButton);
		genderButtonPanel.add(femaleButton);
		
		if (gender.equals("Male")) {
			maleButton.setSelected(true);
		}
		else {
			femaleButton.setSelected(true);
		}
		
		profileInputPanel.add(fullNameLabelPanel);
		profileInputPanel.add(fullNameTextPanel);
		profileInputPanel.add(emailLabelPanel);
		profileInputPanel.add(emailTextPanel);
		profileInputPanel.add(phoneLabelPanel);
		profileInputPanel.add(phoneTextPanel);
		profileInputPanel.add(addressLabelPanel);
		profileInputPanel.add(addressAreaPanel);
		profileInputPanel.add(genderLabelPanel);
		profileInputPanel.add(genderButtonPanel);
		
		profilePanel.add(profileInputPanel, BorderLayout.CENTER);
		
		updateProfileButtonPanel = new JPanel();
		
		updateProfileButton = new JButton("Update Profile");
		updateProfileButton.setPreferredSize(new Dimension(250, 40));
		updateProfileButton.addActionListener(this);
		
		updateProfileButtonPanel.add(updateProfileButton);
		
		profilePanel.add(updateProfileButtonPanel, BorderLayout.SOUTH);
		
		// Password
		passwordPanel = new JPanel(new BorderLayout());
		
		changePasswordTitleLabel = new JLabel("Change Password");
		changePasswordTitleLabel.setFont(new Font("", Font.PLAIN, 20));
		
		passwordPanel.add(changePasswordTitleLabel, BorderLayout.NORTH);
		
		passwordInputPanel = new JPanel(new GridLayout(5, 2));
		passwordInputPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		
		oldPasswordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		oldPasswordFieldPanel = new JPanel();
		
		oldPasswordLabel = new JLabel("Old Password");
		oldPasswordField = new JPasswordField();
		oldPasswordField.setPreferredSize(new Dimension(250, 40));
		
		oldPasswordLabelPanel.add(oldPasswordLabel);
		oldPasswordFieldPanel.add(oldPasswordField);
		
		newPasswordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newPasswordFieldPanel = new JPanel();
		
		newPasswordLabel = new JLabel("New Password");
		newPasswordField = new JPasswordField();
		newPasswordField.setPreferredSize(new Dimension(250, 40));
		
		newPasswordLabelPanel.add(newPasswordLabel);
		newPasswordFieldPanel.add(newPasswordField);
		
		confirmationPasswordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		confirmationPasswordFieldPanel = new JPanel();
		
		confirmationPasswordLabel = new JLabel("Confirmation Password");
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setPreferredSize(new Dimension(250, 40));
		
		confirmationPasswordLabelPanel.add(confirmationPasswordLabel);
		confirmationPasswordFieldPanel.add(confirmPasswordField);
		
		passwordInputPanel.add(oldPasswordLabelPanel);
		passwordInputPanel.add(oldPasswordFieldPanel);
		passwordInputPanel.add(newPasswordLabelPanel);
		passwordInputPanel.add(newPasswordFieldPanel);
		passwordInputPanel.add(confirmationPasswordLabelPanel);
		passwordInputPanel.add(confirmationPasswordFieldPanel);
		
		passwordPanel.add(passwordInputPanel, BorderLayout.CENTER);
		
		changePasswordButtonPanel = new JPanel();
		
		changePasswordButton = new JButton("Change Password");
		changePasswordButton.setPreferredSize(new Dimension(250, 40));
		changePasswordButton.addActionListener(this);
		
		changePasswordButtonPanel.add(changePasswordButton);
	
		passwordPanel.add(changePasswordButtonPanel, BorderLayout.SOUTH);
		
		formPanel.add(profilePanel);
		formPanel.add(passwordPanel);
		
		intFrame.add(formPanel);
		
		// Color
		
		formPanel.setBackground(Color.decode("#00FFFF"));
		profilePanel.setBackground(Color.decode("#00FFFF"));
		passwordPanel.setBackground(Color.decode("#00FFFF"));
		profileInputPanel.setBackground(Color.decode("#00FFFF"));
		passwordInputPanel.setBackground(Color.decode("#00FFFF"));
		fullNameLabelPanel.setBackground(Color.decode("#00FFFF"));
		fullNameTextPanel.setBackground(Color.decode("#00FFFF"));
		emailLabelPanel.setBackground(Color.decode("#00FFFF"));
		emailTextPanel.setBackground(Color.decode("#00FFFF"));
		phoneLabelPanel.setBackground(Color.decode("#00FFFF"));
		phoneTextPanel.setBackground(Color.decode("#00FFFF"));
		addressLabelPanel.setBackground(Color.decode("#00FFFF"));
		addressAreaPanel.setBackground(Color.decode("#00FFFF"));
		genderLabelPanel.setBackground(Color.decode("#00FFFF"));
		genderButtonPanel.setBackground(Color.decode("#00FFFF"));
		updateProfileButtonPanel.setBackground(Color.decode("#00FFFF"));
		oldPasswordLabelPanel.setBackground(Color.decode("#00FFFF"));
		oldPasswordFieldPanel.setBackground(Color.decode("#00FFFF"));
		newPasswordLabelPanel.setBackground(Color.decode("#00FFFF"));
		newPasswordFieldPanel.setBackground(Color.decode("#00FFFF")); 
		confirmationPasswordLabelPanel.setBackground(Color.decode("#00FFFF")); 
		confirmationPasswordFieldPanel.setBackground(Color.decode("#00FFFF"));
		changePasswordButtonPanel.setBackground(Color.decode("#00FFFF"));
		maleButton.setBackground(Color.decode("#00FFFF"));
		femaleButton.setBackground(Color.decode("#00FFFF"));
		
		intFrame.setMaximizable(false);
		intFrame.setIconifiable(false);
		intFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		intFrame.setLocation(0, 0);
	}
	
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
	
	private boolean validateFullName() {
		fullName = fullNameText.getText().trim();
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
		String currentEmail = email;
		email = emailText.getText().trim();
		
		if (!email.equals(currentEmail)) {
			if (checkDuplicate("UserEmail", email)) {
				JOptionPane.showMessageDialog(null, "Email is already taken!", "Warning Message",
						JOptionPane.WARNING_MESSAGE);
				return false;
			} 
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
	
	private boolean allDigits(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean validatePhone() {
		String currentPhone = phone;
		phone = phoneText.getText().trim();
		
		if (!phone.equals(currentPhone)) {
			if (checkDuplicate("UserPhone", phone)) {
				JOptionPane.showMessageDialog(null, "Phone number is already taken!", "Warning Message",
						JOptionPane.WARNING_MESSAGE);
				return false;
			} 
		}
		if ((phone.length() < 10 || phone.length() > 12) && allDigits(phone)) {
			JOptionPane.showMessageDialog(null, "Please fill with valid phone number!","Warning Message",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			return true;
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
	
	private boolean validatePassword(String password) {
		
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
	
	private boolean validateProfileChange() {
		return (validateFullName() && validateEmail() && validatePhone() && validateAddress() && validateGender());
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == updateProfileButton) {
			if (validateProfileChange()) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update your profile?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					
					con.updateUserData(userID, fullName, email, phone, gender, address);
				}
			}
		}
		else if (a.getSource() == changePasswordButton) {
			String oldPassword = String.valueOf(oldPasswordField.getPassword());
			String newPassword = String.valueOf(newPasswordField.getPassword());
			String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
			
			if (oldPassword.equals(password)) {
				if (validatePassword(newPassword)) {
					if (newPassword.equals(confirmPassword)) {
						int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your password?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
						
						if (confirm == JOptionPane.YES_OPTION) {
							
							con.updateUserPassword(userID, newPassword);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Password and confirmation password must match!","Warning Message",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter the correct old password!","Warning Message",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

}
