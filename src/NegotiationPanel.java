package src;

import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import java.io.*;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.event.*;

public class NegotiationPanel extends WindowPanel {
	int minimum;
	int maximum;
	String operator;
	ArrayList<String> labels = new ArrayList<String>();
	ArrayList<Integer> presets = new ArrayList<Integer>();
	String satisfied_panel;
	ArrayList<Integer> satisfiers = new ArrayList<Integer>();
	ArrayList<JLabel> inputlabelsarray;
	ArrayList<JTextField> inputvaluesarray;
	
	int round;
	
	public NegotiationPanel(String label, int round, String title1, String title2, ArrayList<String> labels, ArrayList<Integer> presets, int minimum, int maximum, String operator, ArrayList<Integer> satisfiers, String satisfied_panel, String imagepath, String button_text) {
		super();
		this.label = label;
		this.minimum = minimum;
		this.maximum = maximum;
		this.round = round;
		this.presets = presets;
		this.satisfiers = satisfiers;
		this.satisfied_panel = satisfied_panel;
		this.proceed = "button";
		int fieldcount = labels.size();
		this.operator = operator;
		reactiontime = 0;
		
		inputlabelsarray = new ArrayList<JLabel>();
		inputvaluesarray = new ArrayList<JTextField>();
		
		this.setLayout(new BorderLayout());
		JPanel northpanel = new JPanel();
		this.add(northpanel, BorderLayout.NORTH);
		northpanel.setLayout(new BoxLayout(northpanel, BoxLayout.X_AXIS));
		JLabel roundlabel = new JLabel("Round " + Integer.toString(round));
		if (round > 0){
			northpanel.add(roundlabel);
		}
		northpanel.add(Box.createHorizontalGlue());
		
		if (imagepath != ""){
			BufferedImage myPicture;
			try {
				File f = new File(System.getProperty("java.class.path"));
				File dir = f.getAbsoluteFile().getParentFile();
				String path = dir.toString();
				myPicture = ImageIO.read(new File(path + "\\" + imagepath));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				this.add(picLabel, BorderLayout.EAST);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		JPanel centerpanel = new JPanel();
		this.add(centerpanel, BorderLayout.CENTER);
		
		centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
		JLabel title1label = new JLabel(title1);
		title1label.setAlignmentX(Component.CENTER_ALIGNMENT);
		title1label.setHorizontalAlignment(SwingConstants.CENTER);
		centerpanel.add(Box.createVerticalGlue());
		centerpanel.add(Box.createVerticalStrut(20));
		centerpanel.add(title1label);
		
		JPanel presetvalues = new JPanel();
		presetvalues.setLayout(new FlowLayout());
		for (int i = 0; i < fieldcount; i++){
			JPanel labelvaluepanel = new JPanel();
			labelvaluepanel.setLayout(new BoxLayout(labelvaluepanel, BoxLayout.Y_AXIS));
			JLabel presetlabel = new JLabel(labels.get(i));
			presetlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			presetlabel.setHorizontalAlignment(SwingConstants.CENTER);
			labelvaluepanel.add(presetlabel);
			JTextField presetvalue = new JTextField("", 10);
			presetvalue.setText(Integer.toString(presets.get(i)));
			presetvalue.setHorizontalAlignment(JTextField.CENTER);
			presetvalue.setEditable(false);
			labelvaluepanel.add(presetvalue);
			presetvalues.add(labelvaluepanel);
		}

		centerpanel.add(presetvalues);

		JLabel title2label = new JLabel(title2);
		title2label.setAlignmentX(Component.CENTER_ALIGNMENT);
		title2label.setHorizontalAlignment(SwingConstants.CENTER);
		centerpanel.add(Box.createVerticalGlue());
		centerpanel.add(Box.createVerticalStrut(20));
		centerpanel.add(title2label);
		
		JPanel inputvalues = new JPanel();
		inputvalues.setLayout(new FlowLayout());
		for (int i = 0; i < fieldcount; i++){
			JPanel labelvaluepanel = new JPanel();
			labelvaluepanel.setLayout(new BoxLayout(labelvaluepanel, BoxLayout.Y_AXIS));
			JLabel inputlabel = new JLabel(labels.get(i));
			inputlabelsarray.add(inputlabel);
			inputlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			inputlabel.setHorizontalAlignment(SwingConstants.CENTER);
			labelvaluepanel.add(inputlabel);
			JTextField inputvalue = new JTextField("", 10);
			inputvaluesarray.add(inputvalue);
			inputvalue.setHorizontalAlignment(JTextField.CENTER);
			labelvaluepanel.add(inputvalue);
			inputvalues.add(labelvaluepanel);
		}

		centerpanel.add(inputvalues);
		
		JButton button = new JButton(button_text);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				boolean good = true;
				for (Component c : NegotiationPanel.this.inputvaluesarray){
					if (!NegotiationPanel.verifyInput((JTextField)c, NegotiationPanel.this.maximum, NegotiationPanel.this.minimum)){
//						c.setForeground(Color.RED);
						c.requestFocus();
						good = false;
						break;
					}
					else{
//						c.setForeground(Color.BLACK);
					}
				}
				if (good){
					int i = 0;
					model.logger.log("Label: " + NegotiationPanel.this.label + "\n");
					model.logger.log("Round: " + Integer.toString(NegotiationPanel.this.round) + "\n");
					for (Component c : NegotiationPanel.this.inputvaluesarray){
							model.logger.log(((JLabel)NegotiationPanel.this.inputlabelsarray.get(i)).getText() + "\n");
							i++;
							model.logger.log(((JTextField)c).getText() + "\n");
					}
					model.logger.log("Reaction time: " + Integer.toString(reactiontime) + "\n");
					boolean satisfied = true;
					i = 0;
					for (Component c : NegotiationPanel.this.inputvaluesarray){
						if (NegotiationPanel.this.operator.equals("=")){
							satisfied = satisfied && (Integer.parseInt(((JTextField)c).getText()) == NegotiationPanel.this.satisfiers.get(i));
						}
						else if (NegotiationPanel.this.operator.equals(">")){
							satisfied = satisfied && (Integer.parseInt(((JTextField)c).getText()) > NegotiationPanel.this.satisfiers.get(i));
						}
						else if (NegotiationPanel.this.operator.equals("<")){
							satisfied = satisfied && (Integer.parseInt(((JTextField)c).getText()) < NegotiationPanel.this.satisfiers.get(i));
						}
						else if (NegotiationPanel.this.operator.equals(">=")){
							satisfied = satisfied && (Integer.parseInt(((JTextField)c).getText()) >= NegotiationPanel.this.satisfiers.get(i));
						}
						else if (NegotiationPanel.this.operator.equals("<=")){
							satisfied = satisfied && (Integer.parseInt(((JTextField)c).getText()) <= NegotiationPanel.this.satisfiers.get(i));
						}
						i++;
					}
					if (satisfied){
						timer.stop();
						model.gotoPanel(NegotiationPanel.this.satisfied_panel, "button");
					}
					else{
						timer.stop();
						model.nextPanel("button");
					}
				}
			}
		});
		
		JPanel southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		southpanel.add(button);
		this.add(southpanel, BorderLayout.SOUTH);
		
		ActionListener increment = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				NegotiationPanel.this.reactiontime++;
			}
		};
		
		timer = new Timer(1000, increment);
	}

	public static boolean verifyInput(JTextField tf, int maximum, int minimum){
		if (!isInteger(tf.getText())){
			return false;
		}
		int tfvalue = Integer.parseInt(tf.getText());
		if (tfvalue < minimum || tfvalue > maximum){
			return false;
		}
		return true;
	}

	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
}
