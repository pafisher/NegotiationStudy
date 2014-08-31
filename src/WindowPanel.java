package src;

//import java.awt.*;
import javax.swing.*;
//import java.util.ArrayList;

public class WindowPanel extends JPanel {
	public StateModel model = null;
	public String proceed;
	public Timer timer;
	int reactiontime;
	public String label;
	
	public WindowPanel(){
		this.reactiontime = 0;
	}
	
	public void setStateModel(StateModel model){
		this.model = model;
	}
}
