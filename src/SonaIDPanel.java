package src;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class SonaIDPanel extends WindowPanel {
	JTextField idfield;
	
	public SonaIDPanel(String label, String button_text) {
		super();
		this.label = label;
		this.proceed = "button";
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel borderlayout = new JPanel();
		borderlayout.setLayout(new BorderLayout());
		this.add(borderlayout);
		
		idfield = new JTextField();
		idfield.setColumns(10);

		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new FlowLayout());
		borderlayout.add(centerpanel, BorderLayout.CENTER);
		
		centerpanel.add(new JLabel("Please enter your Sona ID:"));
		
		centerpanel.add(idfield);

		centerpanel.add(Box.createVerticalStrut(20));
		centerpanel.add(Box.createVerticalGlue());
		
		JButton button = new JButton(button_text);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				if (!SonaIDPanel.this.idfield.getText().equals("")){
					model.logger = new Logger(SonaIDPanel.this.idfield.getText() + ".log");
					timer.stop();
					model.nextPanel("button");
				}
				else{
					SonaIDPanel.this.idfield.requestFocus();
				}
			}
		});

		JPanel southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		southpanel.add(button);
		borderlayout.add(southpanel, BorderLayout.SOUTH);

		JPanel northpanel = new JPanel();
		northpanel.setLayout(new BoxLayout(northpanel, BoxLayout.Y_AXIS));
		northpanel.add(Box.createVerticalStrut(200));
		borderlayout.add(northpanel, BorderLayout.NORTH);
		
		borderlayout.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		borderlayout.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		
		ActionListener increment = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SonaIDPanel.this.reactiontime++;
			}
		};
		
		timer = new Timer(1000, increment);
	}
}