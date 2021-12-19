/**
 * @package hcmus.fit.vuongphuc.ui
 * @author VuongPhuc
 *
 * Dec. 20, 2021 - 1:47:03 a.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
public class QuizScreen extends JFrame implements ActionListener {

	private final int nAnswer = 4;
	private List<String> answers;
	private String question;
	private int indexAnswer;

	JLabel txtQuestion;
	JButton[] btnAnswer;
	
	public QuizScreen(String question, List<String> answers, int indexAnswer) {
		this.question = question;
		this.answers = answers;
		this.indexAnswer = indexAnswer;
		
		this.txtQuestion = new JLabel(this.question);
		this.btnAnswer = new JButton[nAnswer];
		for (int i=0;i<nAnswer;i++) {
			btnAnswer[i] = new JButton(this.answers.get(i));
		}
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Quiz");

		this.add(createTop(),BorderLayout.NORTH);
		this.add(createCenter(),BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}

	private JPanel createTop() {
		JPanel panel = new JPanel();

		panel.setBorder(new TitledBorder("Question"));
		panel.add(txtQuestion);
		
		return panel;
	}

	private JPanel createCenter() {
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		Dimension btnSize = new Dimension(300,100);
		
		gbc.insets = new Insets(2,2,2,2);
		for (int i=0;i<2;i++) {
			for (int j=0;j<2;j++) {
				gbc.gridx=i;
				gbc.gridy=j;
				int k = 2*i+j;
				btnAnswer[k].setPreferredSize(btnSize);
				btnAnswer[k].addActionListener(this);
				panel.add(btnAnswer[k],gbc);
			}
		}
		
		return panel;
	}

	private class OpenMenuScreen implements Runnable {

		JFrame context;
		
		public OpenMenuScreen(JFrame context) {
			this.context=context;
		}
		
		@Override
		public void run() {
			context.dispose();
			new MainMenuScreen();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		for (int i=0;i<nAnswer;i++) {
			if (src==btnAnswer[i]) {
				MyDialog dialog;
				if (i==this.indexAnswer) {
					dialog = new MyDialog(null,"Quiz","You are correct! \\(OvO)/");
				} else {
					dialog = new MyDialog(null,"Quiz","Oh no, try again later (T_T)\"");	
				}
				dialog.setCallback(new OpenMenuScreen(this));
				dialog.setVisible(true);
			}
		}
	}
	
}
