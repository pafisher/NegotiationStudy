package src;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.event.*;

public class DemographicsPanel extends WindowPanel {
	JTextField idfield;
	
	public DemographicsPanel(String label, ArrayList<String> demographic_labels, String button_text) {
		super();
		this.label = label;
		this.proceed = "button";
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel borderlayout = new JPanel();
		borderlayout.setLayout(new BorderLayout());
		this.add(borderlayout);
		
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
		borderlayout.add(centerpanel, BorderLayout.CENTER);

		for (int i = 0; i < demographic_labels.size(); i++){
			centerpanel.add(Box.createVerticalStrut(20));
			centerpanel.add(Box.createVerticalGlue());
			JPanel fieldlabelpanel = new JPanel();
			fieldlabelpanel.setLayout(new BoxLayout(fieldlabelpanel, BoxLayout.X_AXIS));
			JLabel fieldlabel = new JLabel(demographic_labels.get(i));
			fieldlabel.setName("label");
			fieldlabelpanel.add(fieldlabel);
			fieldlabelpanel.add(Box.createHorizontalGlue());
			centerpanel.add(fieldlabelpanel);
			JTextArea field = new JTextArea();
			field.setName("input");
			centerpanel.add(field);
		}
		
		JButton button = new JButton(button_text);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				model.logger.log("Label: " + DemographicsPanel.this.label + "\n");
				for (Component c : DemographicsPanel.this.getComponents()){
					if (c.getName() == "label"){
						model.logger.log(((JLabel)c).getText() + "\n");
					}
					if (c.getName() == "input"){
						model.logger.log(((JTextArea)c).getText() + "\n");
					}
				}
				model.logger.log("Reaction time: " + Integer.toString(reactiontime) + "\n");
				timer.stop();
				model.nextPanel("button");
			}
		});
		
		JPanel southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		southpanel.add(button);
		borderlayout.add(southpanel, BorderLayout.SOUTH);
		
		borderlayout.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		borderlayout.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		
		ActionListener increment = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DemographicsPanel.this.reactiontime++;
			}
		};
		
		timer = new Timer(1000, increment);
	}
}
