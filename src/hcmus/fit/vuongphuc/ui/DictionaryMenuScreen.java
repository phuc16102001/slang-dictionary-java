/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 18, 2021 - 12:23:29 a.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import hcmus.fit.vuongphuc.constant.Constant;
import hcmus.fit.vuongphuc.model.History;
import hcmus.fit.vuongphuc.model.MyDefinitionList;
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
public class DictionaryMenuScreen extends JFrame implements ActionListener {

	JLabel lbHeading = new JLabel("Dictionary menu");
	Font fontHeading = new Font("Consola",Font.BOLD,18);
	
	JButton btnSearchSlang = new JButton("Search slang word");
	JButton btnSearchDefinition = new JButton("Search definition");
	JButton btnAddSlang = new JButton("Add new slang");
	JButton btnRemoveSlang = new JButton("Remove a slang");
	JButton btnRandomSlang = new JButton("Random slang");
	JButton btnReset = new JButton("Reset dictionary");
	JButton btnBack = new JButton("Back");
	
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
		else if (src==btnReset) {
			resetDictionary();
		}
		else if (src==btnRandomSlang) {
			randomSlang();
		}
		else if (src==btnRemoveSlang) {
			removeSlang();
		}
		else if (src==btnBack) {
			this.dispose();
			new MainMenuScreen();
		}
		else if (src==btnAddSlang) {
			addSlang();
		}
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
		btnRemoveSlang.addActionListener(this);
		btnRandomSlang.addActionListener(this);
		btnReset.addActionListener(this);
		
		btnSearchSlang.setMaximumSize(btnSize);
		btnSearchDefinition.setMaximumSize(btnSize);
		btnAddSlang.setMaximumSize(btnSize);
		btnRemoveSlang.setMaximumSize(btnSize);
		btnRandomSlang.setMaximumSize(btnSize);
		btnReset.setMaximumSize(btnSize);
		
		panel.add(btnSearchSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnSearchDefinition);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnAddSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnRemoveSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnRandomSlang);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnReset);
		
		return panel;
	}
	
	private JPanel createUnderPanel() {
		JPanel panel = new JPanel();
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		
		panel.setLayout(layout);
		
		btnBack.addActionListener(this);
		panel.add(btnBack);
		
		return panel;
	}
	
	private JPanel createHeadingPanel() {
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(new Insets(20,0,20,0)));
		
		lbHeading.setFont(fontHeading);
		
		panel.add(lbHeading);
		
		return panel;
	}
	
	public DictionaryMenuScreen() {		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Dictionary");
		this.setLocationRelativeTo(null);

		this.add(createHeadingPanel(), BorderLayout.NORTH);
		this.add(createUnderPanel(), BorderLayout.SOUTH);
		this.add(createMenuPanel(), BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	
	private void searchSlang() {
		String slang = JOptionPane.showInputDialog("Input slang");
		if (slang==null) {
			return;
		}
		
		if (dict.get(slang)==null) {
			MyDialog dialog = new MyDialog(this,"Error","Slang word not found");
			dialog.setVisible(true);
		} else {
			try {
				History.getInstance().add(slang);
				History.getInstance().saveHistory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			this.dispose();
			new SlangScreen(slang);
		}
	}
	
	private void searchDefinition() {
		String definition = JOptionPane.showInputDialog("Input definition");
		List<String> res = dict.searchDefinition(definition);
		DefinitionScreen dialog = new DefinitionScreen(res);
		dialog.setVisible(true);
	}

	private void randomSlang() {
		this.dispose();
		new SlangScreen(dict.random());
	}
	
	private void addSlang() {
		JTextField txtSlang = new JTextField(5);
		JTextField txtDefinition = new JTextField(5);
		
		JPanel panelAdd = new JPanel();
		panelAdd.add(new JLabel("Slang word:"));
		panelAdd.add(txtSlang);
		panelAdd.add(Box.createHorizontalStrut(15));
		panelAdd.add(new JLabel("Definition:"));
		panelAdd.add(txtDefinition);
		
		int result = JOptionPane.showConfirmDialog(null, panelAdd, "Add new slang", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String slang = txtSlang.getText();
			String definition = txtDefinition.getText();
			
			MyDefinitionList lsDefinition = dict.get(slang);
			if (lsDefinition==null) {
				dict.addSlang(slang, definition);
			} else {
				String[] option = new String[] {"Append", "Overwrite"};
				int index = JOptionPane.showOptionDialog(null,
						"That slang have existed, what do you want?",
						"Add new slang",
					    JOptionPane.DEFAULT_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    option,
					    option[0]);
				if (index==0) {
					dict.addSlang(slang, definition);
				} else {
					dict.setSlang(slang, definition);
				}
			}
			
			MyDialog resultDialog;
			try {
				dict.storeToFile(Constant.CURRENT_DICT_PATH);
				resultDialog = new MyDialog(this, "Add slang", "Add successfully");
			} catch (IOException e) {
				resultDialog = new MyDialog(this, "Add slang", "Cannot access file");
				e.printStackTrace();
			}
			resultDialog.setVisible(true);
		}
	}
	
	private void removeSlang() {
		String slang = JOptionPane.showInputDialog("Input slang word");
		if (slang == null) {
			return;
		}
		
		MyDefinitionList definitions = dict.get(slang);
		MyDialog dialog;
		if (definitions==null) {
			dialog = new MyDialog(this, "Error", "Slang not found");
		} else {
			try {
				dict.remove(slang);
				dict.storeToFile(Constant.CURRENT_DICT_PATH);
				
				dialog = new MyDialog(this, "Error", "Remove successfully");
			} catch (IOException e) {
				dialog = new MyDialog(this, "Error", "Cannot access to file");
				e.printStackTrace();
			}
		}
		dialog.setVisible(true);
	}
	
	private void resetDictionary() {
		MyDialog dialog;
		try {
			dict.loadFromFile(Constant.DEFAULT_DICT_PATH);
			dict.storeToFile(Constant.CURRENT_DICT_PATH);
			
			dialog = new MyDialog(this, "Result", "Reset successfully");
		} catch (IOException e) {
			dialog = new MyDialog(this, "Result", "Reset failed");
			e.printStackTrace();
		}
		dialog.setVisible(true);
	}
	
}
