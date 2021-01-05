import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

public class ManageFish {
	
	public JInternalFrame intFrame = new JInternalFrame("Manage Fish Form",true,true,true,true);

	public ManageFish() {
		
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
