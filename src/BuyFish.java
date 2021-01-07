import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BuyFish {
	
	Connect con;
	
	public JInternalFrame intFrame;
	
	JPanel formPanel, centerPanel, inputPanel, leftInputPanel, 
	rightInputPanel, fishIDPanel, fishIDLabelPanel, fishIDTextPanel,
	fishNamePanel, fishNameLabelPanel, fishNameTextPanel, fishTypePanel,
	fishTypeLabelPanel, fishTypeTextPanel, removeIDPanel, removeIDLabelPanel,
	removeIDTextPanel, fishPricePanel, fishPriceLabelPanel, fishPriceTextPanel,
	fishStockPanel, fishStockLabelPanel, fishStockTextPanel, quantityPanel,
	quantityLabelPanel, quantitySpinnerPanel, addToCartButtonPanel,
	bottomButtonPanel, removeFishButtonPanel, clearCartButtonPanel,
	checkoutButtonPanel;
	JTable fishTable, cartTable;
	JScrollPane fishTableScrollPane, cartTableScrollPane;
	JLabel titleLabel, fishIDLabel, fishNameLabel, fishTypeLabel, fishPriceLabel, fishStockLabel, quantityLabel, removeIDLabel;
	JTextField fishIDText, fishNameText, fishTypeText, fishPriceText, fishStockText, quantityText, removeIDText;
	JSpinner quantitySpinner;
	JButton addToCartButton, removeFishButton, clearCartButton, checkoutButton;

	public BuyFish() {
		intFrame = new JInternalFrame("Buy Fish Form",true,true,true,true);
	}
	
	private Vector<Object> addRowFishTable(String fishID, String fishName, String fishType, int fishPrice, int fishStock){
		Vector<Object> row = new Vector<Object>();
		row.add(fishID);
		row.add(fishName);
		row.add(fishType);
		row.add(fishPrice);
		row.add(fishStock);
		return row;
	}
	
	private void initDataFishTable(){
		Vector<Object> column = new Vector<Object>();
		column.add("FishID");
		column.add("FishName");
		column.add("FishType");
		column.add("FishPrice");
		column.add("FishStock");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		
		try {
			con.rs = con.execQuery("SELECT * FROM fish");
			
			while (con.rs.next()) {
				String fishID = String.valueOf(con.rs.getObject(1));
				String fishName = String.valueOf(con.rs.getObject(2)); 
				String fishType = String.valueOf(con.rs.getObject(3));
				int fishPrice = Integer.valueOf(String.valueOf(con.rs.getObject(4)));
				int fishStock = Integer.valueOf(String.valueOf(con.rs.getObject(5)));

				System.out.println(fishID);
				
				data.add(addRowFishTable(fishID, fishName, fishType, fishPrice, fishStock));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, e);
		}
		
//		con.closeConnection();
		
		fishTable.setModel(new DefaultTableModel(data,column) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		});
	}
	
	public void setInternalFrame() {
		
		con = new Connect();
		
		try {
			intFrame.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		formPanel = new JPanel(new BorderLayout());
		formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		titleLabel = new JLabel("Buy Fish", JLabel.CENTER);
		formPanel.add(titleLabel, BorderLayout.NORTH);
		titleLabel.setFont(new Font("", Font.PLAIN, 20));
		
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		// Fish Table
		fishTable = new JTable();	
		initDataFishTable();
		fishTableScrollPane = new JScrollPane(fishTable);
		fishTableScrollPane.setPreferredSize(new Dimension(500, 200));
		
		// Input Panel
		inputPanel = new JPanel(new GridLayout(1, 2));
		inputPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		leftInputPanel = new JPanel(new GridLayout(4, 1));
		rightInputPanel = new JPanel(new GridLayout(4, 1));
		rightInputPanel.setBorder(new EmptyBorder(0, 160, 0, 0));
		
		// Left Input Panel
		fishIDPanel = new JPanel(new GridLayout(1, 2));
		fishIDLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fishIDTextPanel = new JPanel();
		
		fishIDLabel = new JLabel("Fish ID");
		fishIDText = new JTextField();
		fishIDText.setPreferredSize(new Dimension(250, 30));
		fishIDText.setEditable(false);
		
		fishIDLabelPanel.add(fishIDLabel);
		fishIDTextPanel.add(fishIDText);
		fishIDPanel.add(fishIDLabelPanel);
		fishIDPanel.add(fishIDTextPanel);
		leftInputPanel.add(fishIDPanel);
		
		fishNamePanel = new JPanel(new GridLayout(1, 2));
		fishNameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fishNameTextPanel = new JPanel();
		
		fishNameLabel = new JLabel("Fish Name");
		fishNameText = new JTextField();
		fishNameText.setPreferredSize(new Dimension(250, 30));
		fishNameText.setEditable(false);
		
		fishNameLabelPanel.add(fishNameLabel);
		fishNameTextPanel.add(fishNameText);
		fishNamePanel.add(fishNameLabelPanel);
		fishNamePanel.add(fishNameTextPanel);
		leftInputPanel.add(fishNamePanel);
		
		fishTypePanel = new JPanel(new GridLayout(1, 2));
		fishTypeLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fishTypeTextPanel = new JPanel();
		
		fishTypeLabel = new JLabel("Fish Type");
		fishTypeText = new JTextField();
		fishTypeText.setPreferredSize(new Dimension(250, 30));
		fishTypeText.setEditable(false);
		
		fishTypeLabelPanel.add(fishTypeLabel);
		fishTypeTextPanel.add(fishTypeText);
		fishTypePanel.add(fishTypeLabelPanel);
		fishTypePanel.add(fishTypeTextPanel);
		leftInputPanel.add(fishTypePanel);
		
		removeIDPanel = new JPanel(new GridLayout(1, 2));
		removeIDLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		removeIDTextPanel = new JPanel();
		
		removeIDLabel = new JLabel("Remove ID");
		removeIDText = new JTextField();
		removeIDText.setPreferredSize(new Dimension(250, 30));
		removeIDText.setEditable(false);
		
		removeIDLabelPanel.add(removeIDLabel);
		removeIDTextPanel.add(removeIDText);
		removeIDPanel.add(removeIDLabelPanel);
		removeIDPanel.add(removeIDTextPanel);
		leftInputPanel.add(removeIDPanel);
		
		// Right Input Panel
		fishPricePanel = new JPanel(new GridLayout(1, 2));
		fishPriceLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fishPriceTextPanel = new JPanel();
		
		fishPriceLabel = new JLabel("Fish Price");
		fishPriceText = new JTextField();
		fishPriceText.setPreferredSize(new Dimension(250, 30));
		fishPriceText.setEditable(false);
		
		fishPriceLabelPanel.add(fishPriceLabel);
		fishPriceTextPanel.add(fishPriceText);
		fishPricePanel.add(fishPriceLabelPanel);
		fishPricePanel.add(fishPriceTextPanel);
		rightInputPanel.add(fishPricePanel);
		
		fishStockPanel = new JPanel(new GridLayout(1, 2));
		fishStockLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fishStockTextPanel = new JPanel();
		
		fishStockLabel = new JLabel("Fish Stock");
		fishStockText = new JTextField();
		fishStockText.setPreferredSize(new Dimension(250, 30));
		fishStockText.setEditable(false);
		
		fishStockLabelPanel.add(fishStockLabel);
		fishStockTextPanel.add(fishStockText);
		fishStockPanel.add(fishStockLabelPanel);
		fishStockPanel.add(fishStockTextPanel);
		rightInputPanel.add(fishStockPanel);
		
		quantityPanel = new JPanel(new GridLayout(1, 2));
		quantityLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		quantitySpinnerPanel = new JPanel();
		
		quantityLabel = new JLabel("Quantity");
		quantitySpinner = new JSpinner();
		quantitySpinner.setPreferredSize(new Dimension(250, 30));
		
		quantityLabelPanel.add(quantityLabel);
		quantitySpinnerPanel.add(quantitySpinner);
		quantityPanel.add(quantityLabelPanel);
		quantityPanel.add(quantitySpinnerPanel);
		rightInputPanel.add(quantityPanel);
		
		addToCartButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		addToCartButton = new JButton("Add to Cart");
		addToCartButton.setPreferredSize(new Dimension(150, 30));
		
		addToCartButtonPanel.add(addToCartButton);
		rightInputPanel.add(addToCartButtonPanel);
		
		inputPanel.add(leftInputPanel);
		inputPanel.add(rightInputPanel);
		
		// Cart Table
		cartTable = new JTable();
		cartTableScrollPane = new JScrollPane(cartTable);
		cartTableScrollPane.setPreferredSize(new Dimension(500, 200));

		centerPanel.add(fishTableScrollPane, BorderLayout.NORTH);
		centerPanel.add(inputPanel, BorderLayout.CENTER);
		centerPanel.add(cartTableScrollPane, BorderLayout.SOUTH);
		
		formPanel.add(centerPanel, BorderLayout.CENTER);
		
		// Bottom Button Panel
		bottomButtonPanel = new JPanel(new GridLayout(1, 3));
		removeFishButtonPanel = new JPanel();
		clearCartButtonPanel = new JPanel();
		checkoutButtonPanel = new JPanel();
		
		removeFishButton = new JButton("Remove Fish");
		removeFishButton.setPreferredSize(new Dimension(180, 25));
		removeFishButtonPanel.add(removeFishButton);
		
		clearCartButton = new JButton("Clear Cart");
		clearCartButton.setPreferredSize(new Dimension(180, 25));
		clearCartButtonPanel.add(clearCartButton);
		
		checkoutButton = new JButton("Checkout");
		checkoutButton.setPreferredSize(new Dimension(180, 25));
		checkoutButtonPanel.add(checkoutButton);
		
		bottomButtonPanel.add(removeFishButtonPanel);
		bottomButtonPanel.add(clearCartButtonPanel);
		bottomButtonPanel.add(checkoutButtonPanel);
		
		formPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
		
		// Color
		formPanel.setBackground(Color.decode("#00FFFF"));
		centerPanel.setBackground(Color.decode("#00FFFF"));
		inputPanel.setBackground(Color.decode("#00FFFF"));
		leftInputPanel.setBackground(Color.decode("#00FFFF")); 
		rightInputPanel.setBackground(Color.decode("#00FFFF"));
		fishIDPanel.setBackground(Color.decode("#00FFFF"));
		fishIDLabelPanel.setBackground(Color.decode("#00FFFF"));
		fishIDTextPanel.setBackground(Color.decode("#00FFFF"));
		fishNamePanel.setBackground(Color.decode("#00FFFF"));
		fishNameLabelPanel.setBackground(Color.decode("#00FFFF"));
		fishNameTextPanel.setBackground(Color.decode("#00FFFF"));
		fishTypePanel.setBackground(Color.decode("#00FFFF"));
		fishTypeLabelPanel.setBackground(Color.decode("#00FFFF"));
		fishTypeTextPanel.setBackground(Color.decode("#00FFFF"));
		removeIDPanel.setBackground(Color.decode("#00FFFF"));
		removeIDLabelPanel.setBackground(Color.decode("#00FFFF"));
		removeIDTextPanel.setBackground(Color.decode("#00FFFF"));
		fishPricePanel.setBackground(Color.decode("#00FFFF"));
		fishPriceLabelPanel.setBackground(Color.decode("#00FFFF"));
		fishPriceTextPanel.setBackground(Color.decode("#00FFFF"));
		fishStockPanel.setBackground(Color.decode("#00FFFF"));
		fishStockLabelPanel.setBackground(Color.decode("#00FFFF"));
		fishStockTextPanel.setBackground(Color.decode("#00FFFF"));
		quantityPanel.setBackground(Color.decode("#00FFFF"));
		quantityLabelPanel.setBackground(Color.decode("#00FFFF"));
		quantitySpinnerPanel.setBackground(Color.decode("#00FFFF"));
		addToCartButtonPanel.setBackground(Color.decode("#00FFFF"));
		bottomButtonPanel.setBackground(Color.decode("#00FFFF"));
		removeFishButtonPanel.setBackground(Color.decode("#00FFFF"));
		clearCartButtonPanel.setBackground(Color.decode("#00FFFF"));
		checkoutButtonPanel.setBackground(Color.decode("#00FFFF"));
		
		intFrame.add(formPanel);
		
		intFrame.setMaximizable(false);
		intFrame.setIconifiable(false);
		intFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		intFrame.setLocation(0, 0);
	}

}
