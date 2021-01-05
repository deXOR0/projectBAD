import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener{

	
	JPanel formPanel;
	Image img;
	JMenuBar menuBar;
	JMenu menuUser, menuTransaction, menuManage;
	JMenuItem mItemProfile, mItemLogoff, mItemExit, mItemBuyFish, mItemTrxHistory, mItemManageFish;
	
	private String userID, role;
	
	Profile profile;
	BuyFish buyFish;
	ViewTransaction viewTransaction;
	ManageFish manageFish;
	
	MainMenu(String userID, String role) {
		
//		System.out.print(userID);
		
		this.userID = userID;
		this.role = role;
		
		formPanel = new JPanel();
		
		// Draw background
		try {
			img = ImageIO.read(new File("bg-resized.png"));
			setContentPane(new JPanel(new BorderLayout()) {
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(img, 0, 0, null);
		        }
		    });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		add(formPanel);
		setLayout(null);
		setTitle("Fish Shop");
		setSize(1370, 765);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		menuBar = new JMenuBar();
		
		//Menu User
		menuUser = new JMenu("User");
		mItemProfile = new JMenuItem("Profile");
		mItemLogoff = new JMenuItem("Logoff");
		mItemExit = new JMenuItem("Exit");
		menuUser.add(mItemProfile);
		menuUser.add(mItemLogoff);
		menuUser.add(mItemExit);
		menuBar.add(menuUser);
		
		switch (role) {
		case "Admin" :
			//Menu Manage
			menuManage = new JMenu("Manage");
			mItemManageFish = new JMenuItem("Manage Fish");
			menuManage.add(mItemManageFish);
			menuBar.add(menuManage);
			
			mItemManageFish.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					closeAllInternalFrame(); // Close other internalFrame
					
					// TODO Auto-generated method stub
					manageFish = new ManageFish(); // Create profile instance
					setLayout(null); // Prepare frame
					add(manageFish.intFrame); // Add internalFrame to frame
					manageFish.intFrame.setVisible(true); // Set internalFrame to visible
				}
			});
			
			break;
		case "User" :
			//Menu Transaction
			menuTransaction = new JMenu("Transaction");
			mItemBuyFish = new JMenuItem("Buy Fish");
			mItemTrxHistory = new JMenuItem("View Transaction History");
			menuTransaction.add(mItemBuyFish);
			menuTransaction.add(mItemTrxHistory);		
			menuBar.add(menuTransaction);
			
			mItemBuyFish.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					closeAllInternalFrame(); // Close other internalFrame
					
					// TODO Auto-generated method stub
					buyFish = new BuyFish(); // Create profile instance
					setLayout(null); // Prepare frame
					add(buyFish.intFrame); // Add internalFrame to frame
					buyFish.intFrame.setVisible(true); // Set internalFrame to visible
				}
			});
			
			mItemTrxHistory.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					closeAllInternalFrame(); // Close other internalFrame
					
					// TODO Auto-generated method stub
					viewTransaction = new ViewTransaction(); // Create profile instance
					setLayout(null); // Prepare frame
					add(viewTransaction.intFrame); // Add internalFrame to frame
					viewTransaction.intFrame.setVisible(true); // Set internalFrame to visible
				}
			});
			
			break;
		}
		
		setJMenuBar(menuBar);
		
		mItemProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// redirect to menu profile

				closeAllInternalFrame(); // Close other internalFrame
				
				profile = new Profile(); // Create profile instance
				setLayout(null); // Prepare frame
				add(profile.intFrame); // Add internalFrame to frame
				profile.intFrame.setVisible(true); // Set internalFrame to visible
			}
		});
		
		mItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		mItemLogoff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Login login = new Login();
			}
		});
		
		
	}

	private void closeAllInternalFrame() {
		try {
			profile.intFrame.dispose();
		} catch (Exception e) {
		}
		
		try {
			buyFish.intFrame.dispose();			
		} catch (Exception e) {
		}
		
		try {			
			viewTransaction.intFrame.dispose();
		} catch (Exception e) {
		}
		
		try {
			manageFish.intFrame.dispose();
		} catch (Exception e) {
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
