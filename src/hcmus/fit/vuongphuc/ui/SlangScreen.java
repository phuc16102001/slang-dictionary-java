/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 17, 2021 - 10:21:30 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import hcmus.fit.vuongphuc.model.MyDictionary;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class SlangScreen extends JFrame implements ActionListener {
	
	private String slang;
	private List<String> definitions;
	
	private JList<String> lsDefintion;
	private JButton btnAddDefinition = new JButton("Add definition");
	private JButton btnRemoveDefinition = new JButton("Remove definition");
	private JButton btnBack = new JButton("Back");

	private JPanel createCenter() {
		JPanel panel = new JPanel();
		
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		
		panel.add(new JLabel(String.format("Selected slang: %s", this.slang)));
		
		JScrollPane scroll = new JScrollPane(lsDefintion);
		scroll.setBorder(new TitledBorder("Definitions"));
		panel.add(scroll);
		
		return panel;
	}
	
	private JPanel createRight() {
		JPanel panel = new JPanel();

		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.setBorder(new TitledBorder("Legend"));
		
		Dimension btnSize = new Dimension(200,100);
		Dimension boxSize = new Dimension(10,10);
		
		btnAddDefinition.setMaximumSize(btnSize);
		btnRemoveDefinition.setMaximumSize(btnSize);
		btnBack.setMaximumSize(btnSize);
		
		btnAddDefinition.addActionListener(this);
		btnRemoveDefinition.addActionListener(this);
		btnBack.addActionListener(this);
		
		panel.add(btnAddDefinition);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnRemoveDefinition);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnBack);
		
		return panel;
	}
	
	private void loadDefinition() {
		this.definitions = MyDictionary.getInstance().get(slang);
		lsDefintion = new JList<>(this.definitions.toArray(new String[0]));
	}
	
	public SlangScreen(String slang) {
		this.slang = slang;
		loadDefinition();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("Slang screen");
		this.setLocationRelativeTo(null);
		
		this.add(createCenter(), BorderLayout.CENTER);
		this.add(createRight(),BorderLayout.EAST);
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src==btnBack) {
			this.dispose();
			new DictionaryMenuScreen();
		} 
		else if (src==btnAddDefinition) {
			
		} 
		else if (src==btnRemoveDefinition) {
			
		}
	}
}
