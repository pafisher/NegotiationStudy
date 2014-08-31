package src;

import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.net.*;

public class StateModel{
	private JFrame mainframe;
	private ArrayList<WindowPanel> panels = new ArrayList<WindowPanel>();
	private int currentPanel = 0;
	Logger logger;

	public StateModel(JFrame mainframe, ArrayList<WindowPanel> panels){
		this.mainframe = mainframe;
		this.panels = panels;
		for (int i = 0; i < panels.size(); i++){
			panels.get(i).setStateModel(this);
		}
	}

	public void nextPanel(String info){
		currentPanel += 1;
		mainframe.setContentPane(panels.get(currentPanel));
		mainframe.revalidate();
		mainframe.repaint();
		((WindowPanel)panels.get(currentPanel)).timer.start();
	}

	public void gotoPanel(String label, String info){
		int i = 0;
		for (WindowPanel panel : panels){
			if (panel.label.equals(label)){
				currentPanel = i;
				break;
			}
			i++;
		}
		mainframe.setContentPane(panels.get(currentPanel));
		mainframe.revalidate();
		mainframe.repaint();
		((WindowPanel)panels.get(currentPanel)).timer.start();
	}
	
	public void addPanel(WindowPanel panel){
		panels.add(panel);
		panel.setStateModel(this);
	}
}