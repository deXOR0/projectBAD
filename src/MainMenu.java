import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainMenu extends JFrame{

	
	JPanel formPanel;
	ImageIcon img;
	JMenuBar menuBar;
	JMenu menuUser, menuTransaction, menuManage;
	JMenuItem mItemProfile, mItemLogoff, mItemExit, mItemBuyFish, mItemTrxHistory, mItemManageFish;
	
	
	
	MainMenu() {
		formPanel = new JPanel();
		
		img = new ImageIcon("bg.png");
		
		add(formPanel);
		setLayout(null);
		setTitle("Fish Shop");
		setSize(623, 348);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		JLabel background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, 623, 338);
		add(background);
		
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
		
		//Menu Transaction
		menuTransaction = new JMenu("Transaction");
		mItemBuyFish = new JMenuItem("Buy Fish");
		mItemTrxHistory = new JMenuItem("View Transaction History");
		menuTransaction.add(mItemBuyFish);
		menuTransaction.add(mItemTrxHistory);		
		menuBar.add(menuTransaction);
		
		//Menu Manage
		menuManage = new JMenu("Manage");
		mItemManageFish = new JMenuItem("Manage Fish");
		menuManage.add(mItemManageFish);
		menuBar.add(menuManage);
		
		
		
		setJMenuBar(menuBar);
		
	}
	
}
