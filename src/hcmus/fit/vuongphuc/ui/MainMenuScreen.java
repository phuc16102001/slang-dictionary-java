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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hcmus.fit.vuongphuc.model.MyDefinitionList;
import hcmus.fit.vuongphuc.model.MyDictionary;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */

public class MainMenuScreen extends JFrame implements ActionListener{
	
	JLabel lbHeading = new JLabel("Slang dictionary");
	Font fontHeading = new Font("Consola",Font.BOLD,18);

	JButton btnDictionary = new JButton("Dictionary");
	JButton btnHistory = new JButton("History");
	JButton btnQuizSlang = new JButton("Quiz slang");
	JButton btnQuizDefinition = new JButton("Quiz definition");
	JButton btnAboutMe = new JButton("About me");
	JButton btnExit = new JButton("Exit");
	
	JLabel lbVersion = new JLabel("Version 1.0");
	
	MyDictionary dict = MyDictionary.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		Object src = e.getSource();
		if (src==btnExit) {
			this.dispose();
		}
		else if (src==btnDictionary) {
			this.dispose();
			new DictionaryMenuScreen();
		}
		else if (src==btnHistory) {
			this.dispose();
			new HistoryScreen();
		}
		else if (src==btnAboutMe) {
			this.dispose();
			new AboutMeScreen();
		}
		else if (src==btnQuizSlang) {
			quizSlang();
		}
		else if (src==btnQuizDefinition) {
			quizDefinition();
		}
	}
	
	private void quizSlang() {
		List<String> slangs = new ArrayList<>();
		while (slangs.size()<4) {
			String newSlang = dict.random();
			if (!slangs.contains(newSlang)) {
				slangs.add(newSlang);
			}
		}
		
		int answer = new Random().nextInt(4);
		String question = slangs.get(answer);
		
		List<String> answers = new ArrayList<>();
		for (int i=0;i<4;i++) {
			MyDefinitionList lsDefinition = dict.get(slangs.get(i));
			int index = new Random().nextInt(lsDefinition.size());
			answers.add(lsDefinition.get(index));
		}
		
		this.dispose();
		new QuizScreen(question,answers,answer);
	}
	
	private void quizDefinition() {
		List<String> slangs = new ArrayList<>();
		while (slangs.size()<4) {
			String newSlang = dict.random();
			if (!slangs.contains(newSlang)) {
				slangs.add(newSlang);
			}
		}
		
		int answer = new Random().nextInt(4);
		MyDefinitionList lsDefinition = dict.get(slangs.get(answer));
		int index = new Random().nextInt(lsDefinition.size());
		String question = lsDefinition.get(index);
		
		this.dispose();
		new QuizScreen(question,slangs,answer);
	}
	
	private JPanel createMenuPanel() {
		JPanel panel = new JPanel();
		
		BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		
		Dimension btnSize = new Dimension(200,100);
		Dimension boxSize = new Dimension(10,10);
		
		
		panel.setBorder(new EmptyBorder(new Insets(50,250,50,250)));
		panel.setLayout(layout);
		
		btnDictionary.addActionListener(this);
		btnHistory.addActionListener(this);
		btnQuizSlang.addActionListener(this);
		btnQuizDefinition.addActionListener(this);
		btnAboutMe.addActionListener(this);
		btnExit.addActionListener(this);

		btnDictionary.setMaximumSize(btnSize);
		btnHistory.setMaximumSize(btnSize);
		btnQuizSlang.setMaximumSize(btnSize);
		btnQuizDefinition.setMaximumSize(btnSize);
		btnAboutMe.setMaximumSize(btnSize);
		btnExit.setMaximumSize(btnSize);

		panel.add(btnDictionary);
		panel.add(Box.createRigidArea(boxSize));
		panel.add(btnHistory);
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
	
	public MainMenuScreen() {		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Menu");
		this.setLocationRelativeTo(null);

		this.add(createHeadingPanel(), BorderLayout.NORTH);
		this.add(createUnderPanel(), BorderLayout.SOUTH);
		this.add(createMenuPanel(), BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
}