/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 15, 2021 - 11:48:50 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class AboutMeScreen extends JFrame implements ActionListener{
	private String aboutUsString = ""
			+ "<html>"
			+ "This is a slang dictionary<br>"
			+ "Owner: Do Vuong Phuc<br>"
			+ "</html>";

	JFrame frame;
	JLabel lbAboutUs = new JLabel(aboutUsString);
	JButton btnBack = new JButton("Back");
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src==btnBack) {
			this.dispose();
			new MainMenuScreen();
		}
	}
	
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.add(lbAboutUs);
		panel.setBorder(new TitledBorder("Information"));
		
		return panel;
	}
	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel();
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.RIGHT);
		panel.setLayout(layout);
		
		btnBack.addActionListener(this);
		
		panel.add(btnBack);
		
		return panel;
	}
	
	public AboutMeScreen() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("About me");
		this.setLocationRelativeTo(null);
		
		this.add(createCenterPanel(),BorderLayout.CENTER);
		this.add(createBottomPanel(),BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}
}
