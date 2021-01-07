import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManageFish implements MouseListener, ActionListener{
	
	public JInternalFrame intFrame;
	JLabel lblTitle, lblFishID, lblFishName, lblFishType, lblFishPrice, lblFishStock, lblAddStock, lblNewFishID, lblNewFishName, lblNewFishType, lblNewFishPrice, lblNewFishStock;
	JTable tableFish;
	JScrollPane scrollFishTablecontainer;
	JTextField fieldFishID, fieldFishName, fieldFishPrice, fieldFishStock, fieldNewFishID, fieldNewFishName, fieldNewFishPrice;
	JComboBox chooseFishType, chooseNewFishType;
	JSpinner spinnerinputAddStock, spinnerNewFishStock;
	JButton btnUpdateFish, btnRemoveFish, btnAddStock, btnInsertFish, btnReset;
	JPanel panelUtama, panelFormKiri, panelBottom, panelFormKanan, panelTengah, panelKananBawah, panelKiriBawah;
	
	Connect con;

	public ManageFish() {
		
		con = new Connect();
		
		intFrame = new JInternalFrame("Manage Fish Form",true,true,true,true);
		panelUtama = new JPanel(new BorderLayout());
		
		lblTitle = new JLabel("Manage Fish", JLabel.CENTER);
		lblTitle.setFont(new Font("", Font.BOLD, 24));   
		
		tableFish = new JTable();
		scrollFishTablecontainer = new JScrollPane(tableFish);
		scrollFishTablecontainer.setPreferredSize(new Dimension(400, 300));
		
		initData();
		
		panelFormKiri = new JPanel(new GridLayout(5,2));
		
		lblFishID = new JLabel("Fish ID");
		lblFishName = new JLabel("Fish Name");
		lblFishType = new JLabel("Fish Type");
		lblFishPrice = new JLabel("Fish Price");
		lblFishStock = new JLabel("Fish Stock");
		
		
		fieldFishID = new JTextField();
		fieldFishID.setEditable(false);
		fieldFishID.setPreferredSize(new Dimension(150, 25));
		
		fieldFishName = new JTextField();
		fieldFishName.setPreferredSize(new Dimension(150, 25));
		
		chooseFishType = new JComboBox<>();
		chooseFishType.setPreferredSize(new Dimension(150, 25));
		
		fieldFishPrice = new JTextField();
		fieldFishPrice.setPreferredSize(new Dimension(150, 25));
		
		fieldFishStock = new JTextField();
		fieldFishStock.setPreferredSize(new Dimension(150, 25));
		fieldFishStock.setEditable(false);
		
		panelFormKiri.add(lblFishID);
		panelFormKiri.add(fieldFishID);
		panelFormKiri.add(lblFishName);
		panelFormKiri.add(fieldFishName);
		panelFormKiri.add(lblFishType);
		panelFormKiri.add(chooseFishType);
		panelFormKiri.add(lblFishPrice);
		panelFormKiri.add(fieldFishPrice);
		panelFormKiri.add(lblFishStock);
		panelFormKiri.add(fieldFishStock);
		
		
		lblNewFishID = new JLabel("New Fish ID");
		lblNewFishName = new JLabel("New Fish Name");
		lblNewFishType = new JLabel("New Fish Type");
		lblNewFishPrice = new JLabel("New Fish Price");
		lblNewFishStock = new JLabel("New Fish Stock");
		
		fieldNewFishID = new JTextField();
		
		fieldNewFishName = new JTextField();
		
		chooseNewFishType = new JComboBox<>();
		
		fieldNewFishPrice = new JTextField();
		
		spinnerNewFishStock = new JSpinner();
		
		panelFormKanan = new JPanel(new GridLayout(5,2));
		
		panelFormKanan.add(lblNewFishID);
		panelFormKanan.add(fieldNewFishID);
		panelFormKanan.add(lblNewFishName);
		panelFormKanan.add(fieldNewFishName);
		panelFormKanan.add(lblNewFishType);
		panelFormKanan.add(chooseNewFishType);
		panelFormKanan.add(lblNewFishPrice);
		panelFormKanan.add(fieldNewFishPrice);
		panelFormKanan.add(lblNewFishStock);
		panelFormKanan.add(spinnerNewFishStock);
		

		panelFormKiri.setBorder(new EmptyBorder(15,15,15,15));
		panelFormKanan.setBorder(new EmptyBorder(15,15,15,15));
		
		panelBottom = new JPanel(new GridLayout(2,2));
		panelBottom.add(panelFormKiri);
		panelBottom.add(panelFormKanan);
		
		
		btnUpdateFish = new JButton("Update Fish");
		btnUpdateFish.setPreferredSize(new Dimension(300, 35));
		btnRemoveFish = new JButton("Remove Fish");
		btnRemoveFish.setPreferredSize(new Dimension(300, 35));
		
		lblAddStock = new JLabel("Add Stock");
		spinnerinputAddStock = new JSpinner();
		spinnerinputAddStock.setPreferredSize(new Dimension(150, 25));
		btnAddStock = new JButton("Add Stock");
		
		btnInsertFish = new JButton("Insert Fish");
		btnReset = new JButton("Reset");
		
		panelKananBawah = new JPanel(new GridLayout(2, 1));
		
		panelKananBawah.add(btnInsertFish);
		panelKananBawah.add(btnReset);
		
		panelKiriBawah = new JPanel(new FlowLayout());
		
		panelKiriBawah.add(btnUpdateFish);
		panelKiriBawah.add(btnRemoveFish);
		panelKiriBawah.add(lblAddStock);
		panelKiriBawah.add(spinnerinputAddStock);
		panelKiriBawah.add(btnAddStock);
		
		
		
		panelBottom.add(panelKiriBawah);
		panelBottom.add(panelKananBawah);
		
		
		
		panelTengah = new JPanel(new BorderLayout());
		panelTengah.add(scrollFishTablecontainer, BorderLayout.NORTH);
		
		panelUtama.add(panelBottom, BorderLayout.SOUTH);
		panelUtama.add(lblTitle, BorderLayout.NORTH);
		panelUtama.add(panelTengah, BorderLayout.CENTER);
		
		panelBottom.setBackground(Color.decode("#00FFFF"));
		panelFormKanan.setBackground(Color.decode("#00FFFF"));
		panelFormKiri.setBackground(Color.decode("#00FFFF"));
		panelKananBawah.setBackground(Color.decode("#00FFFF"));
		panelKiriBawah.setBackground(Color.decode("#00FFFF"));
		panelTengah.setBackground(Color.decode("#00FFFF"));
		panelUtama.setBackground(Color.decode("#00FFFF"));
		
		
		intFrame.add(panelUtama);
		
		tableFish.addMouseListener(this);
		
		btnUpdateFish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!fieldFishID.getText().equals("")) {
					
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Please choose fish first!","Warning Message",JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fieldFishID.setText("");
				fieldFishName.setText("");
				chooseFishType.removeAllItems();
				fieldFishPrice.setText("");
				fieldFishStock.setText("");
				spinnerinputAddStock.removeAll();
				fieldNewFishID.setText("");
				fieldNewFishName.setText("");
				chooseNewFishType.removeAllItems();
				fieldNewFishPrice.setText("");
				spinnerNewFishStock.removeAll();
				
			}
		});
		
		btnRemoveFish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!fieldFishID.getText().equals("")) {
					 
					 int input = JOptionPane.showConfirmDialog(null, "Are you sure want to remove this fish?", "Select an Option...",JOptionPane.YES_NO_OPTION);
					 System.out.println(input);
					 if(input==0) {
						 removeFish();
					 }
					
				} else {
					JOptionPane.showMessageDialog(null, "Please choose fish first!","Warning Message",JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
	}
	
	public void setInternalFrame() {
		try {
			intFrame.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		intFrame.setMaximizable(false);
		intFrame.setIconifiable(false);
		intFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		intFrame.setLocation(0, 0);
		
		
		
		
	}
	
	public Vector<Object> addRow(String FishID, String FishName, String FishType, Integer FishPrice, Integer FishStock){
		Vector<Object> row = new Vector<Object>();
		row.add(FishID);
		row.add(FishName);
		row.add(FishType);
		row.add(FishPrice);
		row.add(FishStock);
		return row;
	}
	
	public void removeFish() {
//		try {
//			con.execQuery("DELETE FROM fish WHERE FishID = '"+fieldFishID.getText()+"'");
//		} catch (Exception e) {
//			// TODO: handle exception
//			
//			JOptionPane.showMessageDialog(null, e);
//		}
		con.deleteFish(fieldFishID.getText());
	}
	
	public void initData(){
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
				String FishID = String.valueOf(con.rs.getObject(1));
				String FishName = String.valueOf(con.rs.getObject(2));
				String FishType = String.valueOf(con.rs.getObject(3));
				Integer FishPrice = (Integer) con.rs.getObject(4);
				Integer FishStock = (Integer) con.rs.getObject(5);

				data.add(addRow(FishID,FishName,FishType,FishPrice,FishStock));
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
//		con.closeConnection();
		
		tableFish.setModel(new DefaultTableModel(data,column));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		fieldFishID.setText(tableFish.getValueAt(tableFish.getSelectedRow(), 0).toString());
		fieldFishName.setText(tableFish.getValueAt(tableFish.getSelectedRow(), 1).toString());
		chooseFishType.addItem(tableFish.getValueAt(tableFish.getSelectedRow(), 2).toString());
		fieldFishPrice.setText(tableFish.getValueAt(tableFish.getSelectedRow(), 3).toString());
		fieldFishStock.setText(tableFish.getValueAt(tableFish.getSelectedRow(), 4).toString());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
