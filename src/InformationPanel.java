package src;

//import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class InformationPanel extends WindowPanel {
	String link;
	URL completedurl;
	public InformationPanel(String label, String message, String imagepath, String button_text, String link) {
		super();
		this.label = label;
		this.proceed = "button";
		this.link = link;
		
		if (link != ""){
			try {
				this.completedurl = new URL(link);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel borderlayout = new JPanel();
		borderlayout.setLayout(new BorderLayout());
		this.add(borderlayout);
		
		JPanel centerpanel = new JPanel();
//		centerpanel.setLayout(new FlowLayout());
		centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
		JPanel labelpanel = new JPanel();
		labelpanel.setLayout(new FlowLayout());
		borderlayout.add(centerpanel, BorderLayout.CENTER);
		
//		centerpanel.add(new JLabel(message));
		labelpanel.add(new JLabel(message));
		centerpanel.add(labelpanel);
		
		if (imagepath != ""){
			BufferedImage myPicture;
			try {
				File f = new File(System.getProperty("java.class.path"));
				File dir = f.getAbsoluteFile().getParentFile();
				String path = dir.toString();
				myPicture = ImageIO.read(new File(path + "\\" + imagepath));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				JPanel picpanel = new JPanel();
				picpanel.setLayout(new FlowLayout());
				picpanel.add(picLabel);
				centerpanel.add(picpanel);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		centerpanel.add(Box.createVerticalStrut(20));
		centerpanel.add(Box.createVerticalGlue());
		
		JButton button = new JButton(button_text);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				model.logger.log("Label: " + InformationPanel.this.label + "\n");
				model.logger.log("Reaction time: " + Integer.toString(reactiontime) + "\n");
				timer.stop();
				if (InformationPanel.this.link == ""){
					model.nextPanel("button");
				}
				else{
					openWebpage(completedurl);
					return;
				}
			}
		});

		JPanel southpanel = new JPanel();
		southpanel.setLayout(new FlowLayout());
		southpanel.add(button);
		borderlayout.add(southpanel, BorderLayout.SOUTH);

		JPanel northpanel = new JPanel();
		northpanel.setLayout(new BoxLayout(northpanel, BoxLayout.Y_AXIS));
		northpanel.add(Box.createVerticalStrut(20));
		borderlayout.add(northpanel, BorderLayout.NORTH);
		
		borderlayout.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		borderlayout.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		
		ActionListener increment = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				InformationPanel.this.reactiontime++;
			}
		};
		
		timer = new Timer(1000, increment);
	}
	
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}
}
