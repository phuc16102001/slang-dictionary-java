/**
 * @package hcmus.fit.vuongphuc.utils
 * @author VuongPhuc
 *
 * Dec. 15, 2021 - 11:51:29 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class MyDialog extends JDialog implements ActionListener {
	
	JLabel lbMessage;
	JButton btnOk = new JButton("Ok");
	JPanel panel = new JPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src==btnOk) {
			this.dispose();
		}
	}

	public MyDialog(JFrame frame, String title, String message, Boolean useOk) {
		super(frame,title);
		this.setLocationRelativeTo(null);
		
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.setBorder(new CompoundBorder(new TitledBorder("Notification"), new EmptyBorder(10,10,10,10)));
		
		btnOk.addActionListener(this);
		lbMessage = new JLabel(message);
		
		btnOk.setAlignmentX(CENTER_ALIGNMENT);
		lbMessage.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(lbMessage);
		if (useOk) {			
			panel.add(Box.createRigidArea(new Dimension(0,10)));
			panel.add(btnOk);
		}
		
		this.add(panel);
		this.pack();
	}
	
	public MyDialog(JFrame frame, String title, String message) {
		this(frame,title,message,true);
	}
	
}
