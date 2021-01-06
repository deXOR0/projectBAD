import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

public class BuyFish {
	
	public JInternalFrame intFrame;

	public BuyFish() {
		intFrame = new JInternalFrame("Buy Fish Form",true,true,true,true);
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

}
