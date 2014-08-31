package src;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class WaitPanel extends WindowPanel {
	JLabel wait_label;
	String wait_message;

	int seconds_passed = 0;
	int delay;
	
	public WaitPanel(String label, String wait_message, int delay){
		super();
		this.label = label;
		this.wait_message = wait_message;
		this.wait_label = new JLabel(wait_message);
		this.delay = delay;
		this.proceed = "timer";
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel borderlayout = new JPanel();
		borderlayout.setLayout(new BorderLayout());
		this.add(borderlayout);
		
		borderlayout.add(wait_label, BorderLayout.CENTER);
		wait_label.setHorizontalAlignment(JLabel.CENTER);
		
		ActionListener wait_loop = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wait_label.setText(WaitPanel.this.wait_message + String.format(String.format("%%0%dd</html>", (seconds_passed % 4) + 1), 0).replace("0","."));
				if (seconds_passed < WaitPanel.this.delay){
					seconds_passed++;
				}
				else{
					timer.stop();
					model.nextPanel("timer");
				}
			}
		};
		
		this.timer = new Timer(1000, wait_loop);
	}
}
