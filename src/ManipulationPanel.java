package src;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;

public class ManipulationPanel extends WindowPanel {
	int qmanipulation;
	int amanipulation;
	ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
	int timeout;
	
	public ManipulationPanel(String label, String title, ArrayList<ArrayList<String>> questions, ArrayList<ArrayList<String>> answers, String subtitle, ArrayList<String> labels, ArrayList<Integer> presets, String button_text, String delay_message, int timeout) {
		this.label = label;
		this.questions = questions;
		this.answers = answers;
		this.timeout = timeout;
		this.setLayout(new BorderLayout());
		
		JPanel northpanel = new JPanel();
		this.add(northpanel, BorderLayout.NORTH);
		northpanel.setLayout(new FlowLayout());
		JLabel titlelabel = new JLabel(title);
		titlelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		northpanel.add(titlelabel);
		
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
		
		qmanipulation = randInt(0, questions.size() - 1);
		if (questions.size() != answers.size()){
			amanipulation = randInt(0, answers.size() - 1);
		}
		else{
			amanipulation = qmanipulation;
		}
		
		for (int i = 0; i < Math.min(questions.get(qmanipulation).size(), answers.get(amanipulation).size()); i++){
			centerpanel.add(Box.createVerticalStrut(50));
			centerpanel.add(Box.createVerticalGlue());
			JPanel fieldlabelpanel = new JPanel();
			fieldlabelpanel.setLayout(new BoxLayout(fieldlabelpanel, BoxLayout.X_AXIS));
			JLabel fieldlabel = new JLabel(questions.get(qmanipulation).get(i));
			fieldlabel.setHorizontalAlignment(SwingConstants.LEFT);
			fieldlabelpanel.add(fieldlabel);
			fieldlabelpanel.add(Box.createHorizontalGlue());
			centerpanel.add(fieldlabelpanel);
			JTextArea field = new JTextArea(answers.get(amanipulation).get(i));
//			field.setText(answers.get(manipulation).get(i));
			field.setEditable(false);
			centerpanel.add(field);
		}
		centerpanel.add(Box.createVerticalStrut(50), BorderLayout.CENTER);
		centerpanel.add(Box.createVerticalGlue(), BorderLayout.CENTER);
		
		JPanel subtitlelabelpanel = new JPanel();
		subtitlelabelpanel.setLayout(new BoxLayout(subtitlelabelpanel, BoxLayout.X_AXIS));
		JLabel subtitlelabel = new JLabel(subtitle);
		subtitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitlelabelpanel.add(subtitlelabel);
		centerpanel.add(subtitlelabelpanel);
		
		JPanel presetvalues = new JPanel();
		presetvalues.setLayout(new FlowLayout());
		for (int i = 0; i < Math.min(labels.size(),  presets.size()); i++){
			JPanel labelvaluepanel = new JPanel();
			labelvaluepanel.setLayout(new BoxLayout(labelvaluepanel, BoxLayout.Y_AXIS));
			JLabel presetlabel = new JLabel(labels.get(i));
			presetlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelvaluepanel.add(presetlabel);
			JTextField presetvalue = new JTextField("", 10);
			presetvalue.setText(Integer.toString(presets.get(i)));
			presetvalue.setHorizontalAlignment(JTextField.CENTER);
			presetvalue.setEditable(false);
			labelvaluepanel.add(presetvalue);
			presetvalues.add(labelvaluepanel);
		}

		centerpanel.add(presetvalues, BorderLayout.CENTER);
		centerpanel.add(Box.createVerticalGlue());
		centerpanel.add(Box.createVerticalStrut(50));
		
		this.add(centerpanel, BorderLayout.CENTER);
		
		JButton button = new JButton(button_text);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				model.logger.log("Label: " + ManipulationPanel.this.label + "\n");
				for (int i = 0; i < ManipulationPanel.this.questions.get(ManipulationPanel.this.qmanipulation).size(); i++){
					model.logger.log(ManipulationPanel.this.questions.get(ManipulationPanel.this.qmanipulation).get(i) + "\n");
					model.logger.log(ManipulationPanel.this.answers.get(ManipulationPanel.this.amanipulation).get(i) + "\n");
				}
				model.logger.log("Reaction time: " + Integer.toString(reactiontime) + "\n");
				timer.stop();
				model.nextPanel("button");
			}
		});

		JPanel southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		
		JLabel delay_label = new JLabel(delay_message);
		
		if (timeout == 0){
			southpanel.add(button);
		}
		else{
			southpanel.add(delay_label);
		}
		this.add(southpanel, BorderLayout.SOUTH);
		
		this.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		
		ActionListener increment = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ManipulationPanel.this.reactiontime++;
				if (ManipulationPanel.this.timeout != 0){
					if (ManipulationPanel.this.reactiontime >= ManipulationPanel.this.timeout){
						model.logger.log("Label: " + ManipulationPanel.this.label + "\n");
						for (int i = 0; i < ManipulationPanel.this.questions.get(ManipulationPanel.this.qmanipulation).size(); i++){
							model.logger.log(ManipulationPanel.this.questions.get(ManipulationPanel.this.qmanipulation).get(i) + "\n");
							model.logger.log(ManipulationPanel.this.answers.get(ManipulationPanel.this.amanipulation).get(i) + "\n");
						}
						timer.stop();
						model.nextPanel("timer");
					}
				}
			}
		};
		
		timer = new Timer(1000, increment);
	}
	
	public static int randInt(int min, int max){
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
}
