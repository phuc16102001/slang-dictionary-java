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

import hcmus.fit.vuongphuc.constant.Constant;
import hcmus.fit.vuongphuc.model.MyDictionary;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
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
	
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> lsDefintion = new JList<>(model);
	private JButton btnAddDefinition = new JButton("Add definition");
	private JButton btnRemoveDefinition = new JButton("Remove definition");
	private JButton btnBack = new JButton("Back");
	
	MyDictionary dict = MyDictionary.getInstance();

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
		model.clear();
		model.addAll(definitions);
	}
	
	public SlangScreen(String slang) {
		this.slang = slang;
		loadDefinition();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("Slang screen");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			addDefinition();
		} 
		else if (src==btnRemoveDefinition) {
			removeDefinition();
		}
	}
	
	private void removeDefinition() {
		int index=lsDefintion.getSelectedIndex();
		MyDialog dialog;

		if (index==-1) {
			dialog = new MyDialog(this,"Remove definition","Please select a definition");
		} else {
			try {
				dict.removeDefinition(slang, index);
				dict.storeToFile(Constant.CURRENT_DICT_PATH);
				dialog = new MyDialog(this,"Remove definition","Remove successfully");
				loadDefinition();
			} catch (IOException e) {
				dialog = new MyDialog(this,"Remove definition","Cannot access file");
				e.printStackTrace();
			}
		}

		dialog.setVisible(true);
	}
	
	private void addDefinition() {
		String definition = JOptionPane.showInputDialog("What is the definition?");
		MyDialog dialog;
		try {
			dict.addSlang(slang, definition);
			dict.storeToFile(Constant.CURRENT_DICT_PATH);
			loadDefinition();
			dialog = new MyDialog(this,"Add new definition","Add successfully");
		} catch (IOException e) {
			dialog = new MyDialog(this,"Add new definition","Cannot access file");
			e.printStackTrace();
		}
		dialog.setVisible(true);
	}
}
