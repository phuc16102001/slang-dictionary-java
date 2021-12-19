/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 19, 2021 - 3:29:56 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class DefinitionScreen extends JDialog implements ActionListener {

	List<String> definitions;
	JList<String> lsSlang;
	JScrollPane scroll;
	JButton btnOk = new JButton("OK");
	
	public DefinitionScreen(List<String> definitions) {
		this.definitions = definitions;
		
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		
		lsSlang = new JList<>(this.definitions.toArray(new String[0]));
		scroll = new JScrollPane(lsSlang);
		scroll.setBorder(new TitledBorder("Slang words"));
		panel.add(scroll);
		
		btnOk.addActionListener(this);
		panel.add(btnOk);
		
		this.add(panel);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src==btnOk) {
			this.dispose();
		}
	}
	
	
}
