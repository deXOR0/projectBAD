import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

public class Profile {

	public JInternalFrame intFrame;

	public Profile() {
		intFrame = new JInternalFrame("Profile Form",true,true,true,true);
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
