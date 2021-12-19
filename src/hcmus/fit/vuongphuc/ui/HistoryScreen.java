/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 19, 2021 - 4:42:19 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

import hcmus.fit.vuongphuc.model.History;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class HistoryScreen extends JFrame implements ActionListener {

	DefaultListModel<String> model = new DefaultListModel<String>();
	JList<String> lsHistory = new JList<>(model);
	JButton btnBack = new JButton("Back");
	JButton btnDelete = new JButton("Delete");
	History history = History.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src==btnBack) {
			this.dispose();
			new MainMenuScreen();
		}
		else if (src==btnDelete) {
			int index = lsHistory.getSelectedIndex();
			MyDialog dialog;
			if (index==-1) {
				dialog = new MyDialog(this, "Delete history", "Please select a history");
			} else {
				history.remove(index);
				try {
					dialog = new MyDialog(this, "Delete history", "Delete successfully");
					history.saveHistory();
					loadHistory();
				} catch (IOException e1) {
					dialog = new MyDialog(this, "Delete history", "Cannot access file");
					e1.printStackTrace();
				}
			}
			dialog.setVisible(true);
		}
	}
	
	private void loadHistory() throws IOException {
		model.clear();
		model.addAll(History.getInstance().loadHistory());
	}
	
	private JPanel createCenter() {
		JPanel panel = new JPanel();

		JScrollPane scroll = new JScrollPane(lsHistory);
		scroll.setBorder(new TitledBorder("History"));
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
		
		btnDelete.setMaximumSize(btnSize);
		btnBack.setMaximumSize(btnSize);
		
		btnDelete.addActionListener(this);
		btnBack.addActionListener(this);
		
		panel.add(btnDelete);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnBack);
		
		return panel;
	}
	
	public HistoryScreen() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		try {
			loadHistory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.add(createCenter(),BorderLayout.CENTER);
		this.add(createRight(),BorderLayout.EAST);
		this.setVisible(true);
	}
	
}
