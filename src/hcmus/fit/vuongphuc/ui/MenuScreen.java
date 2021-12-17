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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hcmus.fit.vuongphuc.model.MyDictionary;
import hcmus.fit.vuongphuc.utils.MyDialog;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */

public class MenuScreen extends JFrame implements ActionListener{
	
	JLabel lbHeading = new JLabel("Slang dictionary");
	Font fontHeading = new Font("Consola",Font.BOLD,18);

	JButton btnSearchSlang = new JButton("Search slang word");
	JButton btnSearchDefinition = new JButton("Search definition");
	JButton btnAddSlang = new JButton("Add new slang");
	JButton btnReset = new JButton("Reset dictionary");
	JButton btnRandomSlang = new JButton("Random slang");
	JButton btnQuizSlang = new JButton("Quiz slang");
	JButton btnQuizDefinition = new JButton("Quiz definition");
	JButton btnAboutMe = new JButton("About me");
	JButton btnExit = new JButton("Exit");
	
	JLabel lbVersion = new JLabel("Version 1.0");
	
	MyDictionary dict = MyDictionary.getInstance();
	
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		Object src = e.getSource();
		if (src==btnSearchSlang) {	
			searchSlang();
		} 
		else if (src==btnSearchDefinition) {
			searchDefinition();
		}
		else if (src==btnExit) {
			this.dispose();
		}
		else if (src==btnAboutMe) {
			this.dispose();
			new AboutMeScreen();
		}
	}
	
	private void searchSlang() {
		String slang = JOptionPane.showInputDialog("Input slang");
		if (dict.get(slang)==null) {
			MyDialog dialog = new MyDialog(this,"Error","Slang word not found");
			dialog.setVisible(true);
		} else {
			this.dispose();
			new SlangScreen(slang);
		}
	}
	
	private void searchDefinition() {
		String slang = JOptionPane.showInputDialog("Input definition");
	}
	
	private void addSlang() {
		
	}
	
	private void resetDictionary() {
		
		
	}
	
	private void randomSlang() {
		
	}
	
	private void quizSlang() {
		
	}
	
	private void quizDefinition() {
		
	}
	
	private JPanel createMenuPanel() {
		JPanel panel = new JPanel();
		
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		
		Dimension btnSize = new Dimension(200,100);
		Dimension boxSize = new Dimension(10,10);
		
		
		panel.setBorder(new EmptyBorder(new Insets(50,250,50,250)));
		panel.setLayout(layout);

		btnSearchSlang.addActionListener(this);
		btnSearchDefinition.addActionListener(this);
		btnAddSlang.addActionListener(this);
		btnReset.addActionListener(this);
		btnRandomSlang.addActionListener(this);
		btnQuizSlang.addActionListener(this);
		btnQuizDefinition.addActionListener(this);
		btnAboutMe.addActionListener(this);
		btnExit.addActionListener(this);
		
		btnSearchSlang.setMaximumSize(btnSize);
		btnSearchDefinition.setMaximumSize(btnSize);
		btnAddSlang.setMaximumSize(btnSize);
		btnReset.setMaximumSize(btnSize);
		btnRandomSlang.setMaximumSize(btnSize);
		btnQuizSlang.setMaximumSize(btnSize);
		btnQuizDefinition.setMaximumSize(btnSize);
		btnAboutMe.setMaximumSize(btnSize);
		btnExit.setMaximumSize(btnSize);

		panel.add(btnSearchSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnSearchDefinition);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnAddSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnReset);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnRandomSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnQuizSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnQuizDefinition);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnAboutMe);
		panel.add(Box.createRigidArea(boxSize));
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
		
		panel.setBorder(new EmptyBorder(new Insets(20,0,20,0)));
		
		lbHeading.setFont(fontHeading);
		
		panel.add(lbHeading);
		
		return panel;
	}
	
	public MenuScreen() {		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("Menu");
		this.setLocationRelativeTo(null);

		this.add(createHeadingPanel(), BorderLayout.NORTH);
		this.add(createUnderPanel(), BorderLayout.SOUTH);
		this.add(createMenuPanel(), BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
}