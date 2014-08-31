package src;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

import javax.swing.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

//		String satisfied_panel = "fin";
//
//		ArrayList<WindowPanel> panels = new ArrayList<WindowPanel>();
//
//		panels.add(new SonaIDPanel("", "Continue"));
//		
//		panels.add(new InformationPanel("", "<html><font style='center' size='5'>Welcome to the study:</font><br>"
//				+ "<font style='center' size='5'><b>Understanding Negotiation Strategies</b></font></html>", "", "Start", ""));
//		
//		panels.add(new InformationPanel("", "<html><font style='center'>In this study, you will be paired with another<br>"
//				+ "participant to complete a negotiation task.<br>"
//				+ "The purpose of this task is to help us gain a<br>"
//				+ "better understanding of the strategies that<br>"
//				+ "people use in computer-mediated negotiation.<br><br></font>"
//				+ "<font style='center' size='5'>Please read the instructions that follow<br>"
//				+ "VERY CAREFULLY</font></html>", "", "Start", ""));
//		
//		panels.add(new InformationPanel("", "<html><font style='center'>You will be negotiating the terms<br>"
//				+ "of a cell phone sale.<br>"
//				+ "In a moment, the computer will<br>"
//				+ "randomly assign an opponent to you.<br>"
//				+ "The comptuer will also determine whether you<br>"
//				+ "receive the role of <font size='5'>Buyer</font> or <font size='5'>Seller</font></font></html>", "", "Continue", ""));
//		
//		panels.add(new WaitPanel("", "Please wait while roles are being assigned", 5));
//		
//		panels.add(new InformationPanel("", "<html><font style='center'>You have been assigned the role of:<br><br><br>"
//				+ "<font size='5'>SELLER</font></font></html>", "", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>Your goal is to earn as many points as possible.<br>"
//				+ "The points you earn in this negotiation will be<br>"
//				+ "converted to raffle tickets.<br>"
//				+ "The raffle tickets give you a chance to win $100.<br>"
//				+ "Only participants who reach an agreement with<br>"
//				+ "their opponent will be entered in the raffle.</html>", "", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>Below you see your payoff chart. Your opponent<br>"
//				+ "has his/her own payoff chart that is different<br>"
//				+ "from yours.</html>", "Payoff.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>As you can see on your chart, your objective is to<br>"
//				+ "reach the best deal in terms of Price, Warranty,<br>"
//				+ "and Service</html>", "Payoff.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>In each round of the negotiation, you will enter<br>"
//				+ "3 offer values: one for Price, one for Warranty,<br>"
//				+ "and one for Service.</html>", "Payoff.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>The values you can enter range from 1 to 9.<br>"
//				+ "Each value is associated with points.<br>"
//				+ "For example, if you enter a '5' for price, you<br>"
//				+ "earn 200 points if the Buyer accepts your offer.</html>", "Payoff.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>Let's look at some more examples. As you can<br>"
//				+ "see, if you offer '7' for Warranty, you receive 30<br>"
//				+ "points if the Buyer accepts your offer.</html>", "Payoffs.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>If you offer '2' for Price, you receive 350 points<br>"
//				+ "if the Buyer accepts your offer.<br>"
//				+ "If you offer '3' for Service, you receive 180<br>"
//				+ "points if the Buyer accepts your offer.</html>", "Payoffs.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>Please let the Research Assistant know now if<br>"
//				+ "you have any questions about the payoff chart.</html>", "Payoffs.jpg", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>An additional goal of this study is to examine the<br>"
//				+ "impact of having information about an opponent's<br>"
//				+ "intentions.<br>"
//				+ "At some point during the negotiation, your<br>"
//				+ "opponent (the Buyer) will be asked some<br>"
//				+ "questions and they will also enter the offer that they<br>"
//				+ "are planning to make in the next round.<br>"
//				+ "You will receive this information, but the Buyer <u>does<br>"
//				+ "not know</u> that you receive this information.<br>"
//				+ "As a Seller, you will not be asked to provide<br>"
//				+ "information about your own intentions.", "", "Continue", ""));
//		
//		panels.add(new InformationPanel("", "<html>PLEASE WAIT<br><br><br>"
//				+ "The negotiation will begin once everyone is ready.<br><br>"
//				+ "The Research Assistant will tell you when you can continue.</html>", "", "Continue", ""));
//		
//		panels.add(new WaitPanel("", "Please wait while the Buyer submits his/her offer", 5));
//
//		String[] labels = new String[3];
//		labels[0] = "Price";
//		labels[1] = "Warranty";
//		labels[2] = "Service";
//
//		panels.add(new WaitPanel("", "Please wait while the Buyer submits his/her offer", 5));
//
//		int[] presets1 = new int[3];
//		presets1[0] = 8;
//		presets1[1] = 7;
//		presets1[2] = 8;
//		int[] satisfiers1 = new int[3];
//		satisfiers1[0] = 8;
//		satisfiers1[1] = 7;
//		satisfiers1[2] = 8;
//		panels.add(new NegotiationPanel("", 1, "Buyer's Offer", "Seller's Offer",
//				labels, presets1, 0, 10, ">=", satisfiers1, satisfied_panel, "",
//				"Continue"));
//
//		panels.add(new WaitPanel("", "Please wait while the Buyer submits his/her offer", 5));
//
//		int[] presets2 = new int[3];
//		presets2[0] = 8;
//		presets2[1] = 7;
//		presets2[2] = 7;
//		int[] satisfiers2 = new int[3];
//		satisfiers2[0] = 8;
//		satisfiers2[1] = 7;
//		satisfiers2[2] = 7;
//		panels.add(new NegotiationPanel("", 2, "Buyer's Offer", "Seller's Offer",
//				labels, presets2, 0, 10, ">=", satisfiers2, satisfied_panel, "",
//				"Continue"));
//
//		panels.add(new WaitPanel("", "Please wait while the Buyer completes a survey about their intentions", 5));
//		
//		String[][] questions = new String[12][2];
//		questions[0][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[0][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[1][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[1][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[2][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[2][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[3][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[3][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		
//		questions[4][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[4][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[5][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[5][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[6][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[6][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[7][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[7][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		
//		questions[8][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[8][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[9][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[9][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[10][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[10][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		questions[11][0] = "Before entering your intended offer below, please describe how you feel right now.";
//		questions[11][1] = "Please use the space below for any additional information you would like to provide to the researchers.";
//		
//		String[][] answers = new String[12][2];
//		answers[0][0] = "Angry";
//		answers[0][1] = "I feel angry but it has nothing to do with the negotiation";
//		answers[1][0] = "Angry";
//		answers[1][1] = "I feel angry but it has nothing to do with the negotiation";
//		answers[2][0] = "Angry";
//		answers[2][1] = "I feel angry but it has nothing to do with the negotiation";
//		answers[3][0] = "Angry";
//		answers[3][1] = "I feel angry but it has nothing to do with the negotiation";
//		
//		answers[4][0] = "Happy";
//		answers[4][1] = "I feel happy but it has nothing to do with the negotiation";
//		answers[5][0] = "Happy";
//		answers[5][1] = "I feel happy but it has nothing to do with the negotiation";
//		answers[6][0] = "Happy";
//		answers[6][1] = "I feel happy but it has nothing to do with the negotiation";
//		answers[7][0] = "Happy";
//		answers[7][1] = "I feel happy but it has nothing to do with the negotiation";
//		
//		answers[8][0] = "Guilty";
//		answers[8][1] = "I feel guilty but it has nothing to do with the negotiation";
//		answers[9][0] = "Guilty";
//		answers[9][1] = "I feel guilty but it has nothing to do with the negotiation";
//		answers[10][0] = "Guilty";
//		answers[10][1] = "I feel guilty but it has nothing to do with the negotiation";
//		answers[11][0] = "Guilty";
//		answers[11][1] = "I feel guilty but it has nothing to do with the negotiation";
//		
//		panels.add(new ManipulationPanel("", "Buyer's Intentions", questions, answers, "Please enter what you intend to offer in the next round:", labels, presets2, "Continue"));
//
//		int[] presets3 = new int[3];
//		presets3[0] = 8;
//		presets3[1] = 6;
//		presets3[2] = 7;
//		int[] satisfiers3 = new int[3];
//		satisfiers3[0] = 8;
//		satisfiers3[1] = 6;
//		satisfiers3[2] = 7;
//		panels.add(new NegotiationPanel("", 3, "Buyer's Offer", "Seller's Offer",
//				labels, presets3, 0, 10, ">=", satisfiers3, satisfied_panel, "",
//				"Continue"));
//
//		panels.add(new WaitPanel("", "Please wait while the Buyer submits his/her offer", 5));
//
//		int[] presets4 = new int[3];
//		presets4[0] = 8;
//		presets4[1] = 6;
//		presets4[2] = 7;
//		int[] satisfiers4 = new int[3];
//		satisfiers4[0] = 8;
//		satisfiers4[1] = 6;
//		satisfiers4[2] = 7;
//		panels.add(new NegotiationPanel("", 4, "Buyer's Offer", "Seller's Offer",
//				labels, presets4, 0, 10, ">=", satisfiers4, satisfied_panel, "",
//				"Continue"));
//
//		panels.add(new WaitPanel("", "Please wait while the Buyer submits his/her offer", 5));
//
//		int[] presets5 = new int[3];
//		presets5[0] = 8;
//		presets5[1] = 6;
//		presets5[2] = 7;
//		int[] satisfiers5 = new int[3];
//		satisfiers5[0] = 8;
//		satisfiers5[1] = 6;
//		satisfiers5[2] = 7;
//		panels.add(new NegotiationPanel("", 5, "Buyer's Offer", "Seller's Offer",
//				labels, presets5, 0, 10, ">=", satisfiers5, satisfied_panel, "",
//				"Continue"));
//
//		panels.add(new WaitPanel("", "Please wait while the Buyer submits his/her offer", 5));
//
//		int[] presets6 = new int[3];
//		presets6[0] = 8;
//		presets6[1] = 6;
//		presets6[2] = 7;
//		int[] satisfiers6 = new int[3];
//		satisfiers6[0] = 8;
//		satisfiers6[1] = 6;
//		satisfiers6[2] = 7;
//		panels.add(new NegotiationPanel("", 6, "Buyer's Offer", "Seller's Offer",
//				labels, presets6, 0, 10, ">=", satisfiers6, satisfied_panel, "",
//				"Continue"));
//
//		panels.add(new InformationPanel("fin", "<html>The negotiation has ended. Please click on the button<br>"
//				+ "below to be taken to a short survey.<br>"
//				+ "<br>"
//				+ "Please do NOT close this window, just minimize it and complete<br>"
//				+ "the survey in the new browser window that opens.", "", "Finish", "http://www.google.ca"));

		ArrayList<WindowPanel> panels;
		try {
			panels = ConfigParser.parse("config.config");
		} catch (IOException e) {
			panels = new ArrayList<WindowPanel>();
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Negotiation");

		StateModel model = new StateModel(frame, panels);

		int width = 1000;
		int height = 800;
		
		BufferedReader br;
		try {
			File f = new File(System.getProperty("java.class.path"));
			File dir = f.getAbsoluteFile().getParentFile();
			String path = dir.toString();
			br = new BufferedReader(new FileReader(path + "\\config.config"));
			String line = br.readLine();
			if (line.split("=")[0].trim() == "width"){
				width = Integer.parseInt(line.split("=")[1].trim());
			}
			else if (line.split("=")[0].trim() == "height"){
				height = Integer.parseInt(line.split("=")[1].trim());
			}
			line = br.readLine();
			if (line.split("=")[0].trim() == "width"){
				width = Integer.parseInt(line.split("=")[1].trim());
			}
			else if (line.split("=")[0].trim() == "height"){
				height = Integer.parseInt(line.split("=")[1].trim());
			}
			br.close();
		} catch (FileNotFoundException e) {} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setContentPane(panels.get(0));
		frame.setSize(width, height);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}