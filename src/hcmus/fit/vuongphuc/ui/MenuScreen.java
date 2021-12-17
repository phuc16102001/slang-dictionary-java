/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 15, 2021 - 11:48:57 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */

public class MenuScreen implements ActionListener{

	JFrame frame;
	
	JLabel lbHeading = new JLabel("Slang dictionary");
	Font fontHeading = new Font("Consola",Font.BOLD,18);

	JButton btnViewDict = new JButton("View dictionary");
	JButton btnAboutMe = new JButton("About me");
	JButton btnExit = new JButton("Exit");
	
	JLabel lbVersion = new JLabel("Version 1.0");
	
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		Object src = e.getSource();
		if (src==btnViewDict) {			
			frame.dispose();
		} 
		else if (src==btnExit) {
			frame.dispose();
		}
		else if (src==btnAboutMe) {
			frame.dispose();
			new AboutMeScreen();
		}
	}
	
	private JPanel createMenuPanel() {
		JPanel panel = new JPanel();
		
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		
		panel.setBorder(new EmptyBorder(new Insets(100,250,100,250)));
		panel.setLayout(layout);

		btnViewDict.setMaximumSize(new Dimension(200,100));
		btnViewDict.addActionListener(this);
		
		btnAboutMe.setMaximumSize(new Dimension(200,100));
		btnAboutMe.addActionListener(this);
		
		btnExit.setMaximumSize(new Dimension(200,100));
		btnExit.addActionListener(this);

		// Load data
		
		panel.add(btnViewDict);
		panel.add(Box.createRigidArea(new Dimension(10,10)));
		panel.add(btnAboutMe);
		panel.add(Box.createRigidArea(new Dimension(10,10)));
		panel.add(btnExit);
		
		return panel;
	}
	
	private JPanel createUnderPanel() {
		JPanel panel = new JPanel();
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		
		panel.setLayout(layout);
		panel.add(lbVersion);
		
		return panel;
	}
	
	private JPanel createHeadingPanel() {
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(new Insets(50,0,50,0)));
		
		lbHeading.setFont(fontHeading);
		
		panel.add(lbHeading);
		
		return panel;
	}
	
	public MenuScreen() {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("Menu");

		frame.add(createHeadingPanel(), BorderLayout.NORTH);
		frame.add(createUnderPanel(), BorderLayout.SOUTH);
		frame.add(createMenuPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}
}