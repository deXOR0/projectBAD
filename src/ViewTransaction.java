import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewTransaction implements MouseListener{
	
	Integer grandTotal=0;
	public JInternalFrame intFrame;
	JPanel panelHeader,panelDetail,panelSelectedID,panelGrandTotal,panelUtama;
	JLabel labelTitle, labelSelectedID, labelGrandTotal;
	JScrollPane scrollHeader, scrollDetail;
	JTextField fieldSelectedID, FieldGrandTotal;
	JTable tabelHeader = new JTable();
	JTable tabelDetail = new JTable();
	Connect con;

	public ViewTransaction() {

		intFrame = new JInternalFrame("Transaction Form",true,true,true,true);
		
	}	
	
	public Vector<Object> addRowHeader(String transactionID, String userID, String transactionDate){
		Vector<Object> row = new Vector<Object>();
		row.add(transactionID);
		row.add(userID);
		row.add(transactionDate);
		return row;
	}
	
	public void initDataHeader(){
		Vector<Object> column = new Vector<Object>();
		column.add("TransactionID");
		column.add("UserID");
		column.add("TransactionDate");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		
		try {
			con.rs = con.execQuery("SELECT * FROM headertransaction");
			
			while (con.rs.next()) {
				String transactionID = String.valueOf(con.rs.getObject(1));
				String userID = String.valueOf(con.rs.getObject(2));
				String transactionDate = String.valueOf(con.rs.getObject(3));

				data.add(addRowHeader(transactionID,userID,transactionDate));
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
//		con.closeConnection();
		
		tabelHeader.setModel(new DefaultTableModel(data,column));
	}
	
	
	public Vector<Object> addRowDetail(String transactionID, String FishID, Integer Quantity, String FishName, String FishType, Integer FishPrice, Integer subtotal){
		Vector<Object> row = new Vector<Object>();
		row.add(transactionID);
		row.add(FishID);
		row.add(Quantity);
		row.add(FishName);
		row.add(FishType);
		row.add(FishPrice);
		row.add(subtotal);
		return row;
	}
	
	
	public void initDataDetail(){
		grandTotal = 0;
		Vector<Object> column = new Vector<Object>();
		column.add("TransactionID");
		column.add("FishID");
		column.add("FishName");
		column.add("FishType");
		column.add("FishPrice");
		column.add("Quantity");
		column.add("Subtotal");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		
		try {
			con.rs = con.execQuery("SELECT TransactionID, detailtransaction.FishID, Quantity,  FishName, FishType, FishPrice, fish.FishID FROM detailtransaction JOIN fish ON detailtransaction.FishID = fish.FishID where detailtransaction.TransactionID='"+fieldSelectedID.getText()+"'");

			while (con.rs.next()) {
				String transactionID = con.rs.getString("TransactionID");
				String FishID = con.rs.getString("FishID");
				Integer Quantity = con.rs.getInt("Quantity");
				String FishName = con.rs.getString("FishName");
				String FishType = con.rs.getString("FishType");
				Integer FishPrice = con.rs.getInt("FishPrice");
				
				Integer subtotal = Quantity * FishPrice;
				grandTotal += subtotal;
				
				data.add(addRowDetail(transactionID,FishID,Quantity, FishName,FishType,FishPrice, subtotal));

			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
//		con.closeConnection();
		
		tabelDetail.setModel(new DefaultTableModel(data,column));
		
		FieldGrandTotal.setText(grandTotal.toString());
	}
	

	
	public void setInternalFrame() {
		con = new Connect();
		
		panelUtama = new JPanel(new BorderLayout());
		panelUtama.setBorder(new EmptyBorder(10,10,10,10));
		
		labelTitle = new JLabel("Transaction History", JLabel.CENTER);
		labelTitle.setFont(new Font("", Font.BOLD, 24));      
        
		
//		tabelHeader.setBounds(0,0,200,300);
		scrollHeader = new JScrollPane(tabelHeader);
		scrollHeader.setPreferredSize(new Dimension(500,250));
		initDataHeader();
		
		labelSelectedID = new JLabel("Selected ID");
		fieldSelectedID = new JTextField();
		fieldSelectedID.setEditable(false);
		fieldSelectedID.setPreferredSize(new Dimension(150, 25));
		panelSelectedID = new JPanel(new FlowLayout());
		panelSelectedID.add(labelSelectedID);
		panelSelectedID.add(fieldSelectedID);
		panelSelectedID.setBorder(new EmptyBorder(10,10,10,10));
		
		panelHeader = new JPanel(new BorderLayout());
		panelHeader.add(scrollHeader, BorderLayout.NORTH);
		panelHeader.add(panelSelectedID, BorderLayout.WEST);
		panelHeader.setBorder(new EmptyBorder(5,15,5,15));
		
		

//		tabelDetail.setBounds(30, 40, 200, 300);
		scrollDetail = new JScrollPane(tabelDetail);
		scrollDetail.setPreferredSize(new Dimension(500,250));

		
		labelGrandTotal = new JLabel("Grand Total");
		FieldGrandTotal = new JTextField();
		FieldGrandTotal.setEditable(false);
		FieldGrandTotal.setPreferredSize(new Dimension(150,25));
		panelGrandTotal = new JPanel(new FlowLayout());
		panelGrandTotal.add(labelGrandTotal);
		panelGrandTotal.add(FieldGrandTotal);
		panelGrandTotal.setBorder(new EmptyBorder(10,10,10,10));

		
		panelDetail = new JPanel(new BorderLayout());
		panelDetail.add(scrollDetail, BorderLayout.NORTH);
		panelDetail.add(panelGrandTotal, BorderLayout.EAST);
		panelDetail.setBorder(new EmptyBorder(5,15,5,15));

		
//		intFrame.add(scrollDetail);
		
		panelUtama.add(labelTitle, BorderLayout.NORTH);
		panelUtama.add(panelHeader, BorderLayout.CENTER);
		panelUtama.add(panelDetail, BorderLayout.SOUTH);
		
		panelUtama.setBackground(Color.decode("#00FFFF"));
		panelDetail.setBackground(Color.decode("#00FFFF"));
		panelGrandTotal.setBackground(Color.decode("#00FFFF"));
		panelHeader.setBackground(Color.decode("#00FFFF"));
		panelSelectedID.setBackground(Color.decode("#00FFFF"));
		
		
		intFrame.add(panelUtama);
		
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
		tabelHeader.addMouseListener(this);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		
		fieldSelectedID.setText(tabelHeader.getValueAt(tabelHeader.getSelectedRow(), 0).toString());
		initDataDetail();
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

}
