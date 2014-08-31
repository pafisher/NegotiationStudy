package src;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.io.*;

public class ConfigParser {
	public static ArrayList<WindowPanel> parse(String filename) throws IOException{
		ArrayList<WindowPanel> returnlist = new ArrayList<WindowPanel>();
		
		BufferedReader br;
		Boolean bigdebug = false;
		try {
			File f = new File(System.getProperty("java.class.path"));
			File dir = f.getAbsoluteFile().getParentFile();
			String path = dir.toString();
			br = new BufferedReader(new FileReader(path + "\\" + filename));
		} catch (FileNotFoundException e) {
			DEBUG("File not found", true);
			returnlist.add(new InformationPanel("", "Could not find config file. Make sure it's named config.config and saved in the same folder as the .jar file", "", "OK", ""));
			return returnlist;
		}
		
		returnlist.add(new SonaIDPanel("", "Continue"));
		
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (!line.equals("") && !line.substring(0, Math.min(line.length(), 2)).equals("//")){
			   if (line.equals("#INFORMATION")){
				   DEBUG("INFORMATION", bigdebug);
				   String label = "";
				   String message = "<html>";
				   String imagepath = "";
				   String button_text = "Next";
				   String link = "";
				   String infoline;
				   while(true){
					   br.mark(1000);
					   if ((infoline = br.readLine()) == null){
						   returnlist.add(new InformationPanel(label, message, imagepath, button_text, link));
						   break;
					   }
					   infoline = infoline.trim();
					   if (!infoline.equals("") && !infoline.substring(0, Math.min(infoline.length(), 2)).equals("//")){
						   if (infoline.equals("[message]")){
							   String messageline;
							   while((messageline = br.readLine()) != null){
								   messageline = messageline.trim();
								   if (!messageline.equals("") && !messageline.substring(0, Math.min(messageline.length(), 2)).equals("//")){
									   if (messageline.equals("[/message]")){
										   message = message + "</html>";
										   break;
									   }
									   else{
										   message = message + messageline;
									   }
								   }
							   }
						   }
						   else if (infoline.equals("#INFORMATION") || infoline.equals("#DELAY") || infoline.equals("#NEGOTIATION") || infoline.equals("#MANIPULATION") || infoline.equals("#QUESTIONS")){
							   returnlist.add(new InformationPanel(label, message, imagepath, button_text, link));
							   br.reset();
							   break;
						   }
						   else{
							   String[] splitline = infoline.split("=", 2);
							   if (splitline[0].equals("label")){
								   label = splitline[1];
							   }
							   else if (splitline[0].equals("image")){
								   imagepath = splitline[1];
							   }
							   else if (splitline[0].equals("button")){
								   button_text = splitline[1];
							   }
							   else if (splitline[0].equals("link")){
								   link = splitline[1];
							   }
						   }
					   }
				   }
			   }
			   else if (line.equals("#DELAY")){
				   DEBUG("DELAY", bigdebug);
				   String label = "";
				   String wait_message = "<html>";
				   int delay = 5;
				   String infoline;
				   while(true){
					   br.mark(1000);
					   if ((infoline = br.readLine()) == null){
						   returnlist.add(new WaitPanel(label, wait_message, delay));
						   break;
					   }
					   infoline = infoline.trim();
					   if (!infoline.equals("") && !infoline.substring(0, Math.min(infoline.length(), 2)).equals("//")){
						   if (infoline.equals("[message]")){
							   String messageline;
							   while((messageline = br.readLine()) != null){
								   messageline = messageline.trim();
								   if (!messageline.equals("") && !messageline.substring(0, Math.min(messageline.length(), 2)).equals("//")){
									   if (messageline.equals("[/message]")){
										   break;
									   }
									   else{
										   wait_message = wait_message + messageline;
									   }
								   }
							   }
						   }
						   else if (infoline.equals("#INFORMATION") || infoline.equals("#DELAY") || infoline.equals("#NEGOTIATION") || infoline.equals("#MANIPULATION") || infoline.equals("#QUESTIONS")){
							   returnlist.add(new WaitPanel(label, wait_message, delay));
							   br.reset();
							   break;
						   }
						   else{
							   String[] splitline = infoline.split("=", 2);
							   if (splitline[0].equals("label")){
								   label = splitline[1];
							   }
							   else if (splitline[0].equals("timer")){
								   delay = Integer.parseInt(splitline[1]);
							   }
						   }
					   }
				   }
			   }
			   else if (line.equals("#NEGOTIATION")){
				   DEBUG("NEGOTIATION", bigdebug);
				   String label = "";
				   int round = 0;
				   String title1 = "";
				   String title2 = "";
				   ArrayList<String> labels = new ArrayList<String>();
				   ArrayList<Integer> presets = new ArrayList<Integer>();
				   int minimum = 1;
				   int maximum = 9;
				   String operator = ">=";
				   ArrayList<Integer> satisfiers = new ArrayList<Integer>();
				   String satisfied_panel = "";
				   String imagepath = "";
				   String button_text = "Next";
				   String infoline;
				   while(true){
					   br.mark(1000);
					   if ((infoline = br.readLine()) == null){
						   returnlist.add(new NegotiationPanel(label, round, title1, title2, labels, presets, minimum, maximum, operator, satisfiers, satisfied_panel, imagepath, button_text));
						   break;
					   }
					   infoline = infoline.trim();
					   if (!infoline.equals("") && !infoline.substring(0, Math.min(infoline.length(), 2)).equals("//")){
						   if (infoline.equals("[labels]")){
							   String labelline;
							   while((labelline = br.readLine()) != null){
								   labelline = labelline.trim();
								   if (!labelline.equals("") && !labelline.substring(0, Math.min(labelline.length(), 2)).equals("//")){
									   if (labelline.equals("[/labels]")){
										   break;
									   }
									   else{
										   labels.add("<html>" + labelline + "</html>");
									   }
								   }
							   }
						   }
						   else if (infoline.equals("[presets]")){
							   String presetline;
							   while((presetline = br.readLine()) != null){
								   presetline = presetline.trim();
								   if (!presetline.equals("") && !presetline.substring(0, Math.min(presetline.length(), 2)).equals("//")){
									   if (presetline.equals("[/presets]")){
										   break;
									   }
									   else{
										   presets.add(Integer.parseInt(presetline));
									   }
								   }
							   }
						   }
						   else if (infoline.equals("[satisfiers]")){
							   String satline;
							   while((satline = br.readLine()) != null){
								   satline = satline.trim();
								   if (!satline.equals("") && !satline.substring(0, Math.min(satline.length(), 2)).equals("//")){
									   if (satline.equals("[/satisfiers]")){
										   break;
									   }
									   else{
										   satisfiers.add(Integer.parseInt(satline));
									   }
								   }
							   }
						   }
						   else if (infoline.equals("#INFORMATION") || infoline.equals("#DELAY") || infoline.equals("#NEGOTIATION") || infoline.equals("#MANIPULATION") || infoline.equals("#QUESTIONS")){
							   returnlist.add(new NegotiationPanel(label, round, title1, title2, labels, presets, minimum, maximum, operator, satisfiers, satisfied_panel, imagepath, button_text));
							   br.reset();
							   break;
						   }
						   else{
							   String[] splitline = infoline.split("=", 2);
							   if (splitline[0].equals("label")){
								   label = splitline[1];
							   }
							   else if (splitline[0].equals("round")){
								   round = Integer.parseInt(splitline[1]);
							   }
							   else if (splitline[0].equals("title1")){
								   title1 = "<html><div style='text-align: center;'>" + splitline[1] + "</html>";
							   }
							   else if (splitline[0].equals("title2")){
								   title2 = "<html><div style='text-align: center;'>" + splitline[1] + "</html>";
							   }
							   else if (splitline[0].equals("minimum")){
								   minimum = Integer.parseInt(splitline[1]);
							   }
							   else if (splitline[0].equals("maximum")){
								   maximum = Integer.parseInt(splitline[1]);
							   }
							   else if (splitline[0].equals("comparison")){
								   operator = splitline[1];
							   }
							   else if (splitline[0].equals("jumpto")){
								   satisfied_panel = splitline[1];
							   }
							   else if (splitline[0].equals("image")){
								   imagepath = splitline[1];
							   }
							   else if (splitline[0].equals("button")){
								   button_text = splitline[1];
							   }
						   }
					   }
				   }
			   }
			   else if (line.equals("#MANIPULATION")){
				   DEBUG("MANIPULATION", bigdebug);
				   String label = "";
				   String title = "";
				   ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
				   ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
				   String subtitle = "<html>";
				   ArrayList<String> labels = new ArrayList<String>();
				   ArrayList<Integer> presets = new ArrayList<Integer>();
				   String button_text = "Next";
				   String delay_message = "<html>";
				   int timeout = 0;
				   String infoline;
				   while(true){
					   br.mark(1000);
					   if ((infoline = br.readLine()) == null){
						   returnlist.add(new ManipulationPanel(label, title, questions, answers, subtitle, labels, presets, button_text, delay_message, timeout));
						   break;
					   }
					   infoline = infoline.trim();
					   if (infoline.equals("[message]")){
						   String messageline;
						   while((messageline = br.readLine()) != null){
							   messageline = messageline.trim();
							   if (!messageline.equals("") && !messageline.substring(0, Math.min(messageline.length(), 2)).equals("//")){
								   if (messageline.equals("[/message]")){
									   delay_message = delay_message + "</html>";
									   break;
								   }
								   else{
									   delay_message = delay_message + messageline;
								   }
							   }
						   }
					   }
					   else if (!infoline.equals("") && !infoline.substring(0, Math.min(infoline.length(), 2)).equals("//")){
						   if (infoline.equals("[labels]")){
							   String labelline;
							   while((labelline = br.readLine()) != null){
								   labelline = labelline.trim();
								   if (!labelline.equals("") && !labelline.substring(0, Math.min(labelline.length(), 2)).equals("//")){
									   if (labelline.equals("[/labels]")){
										   break;
									   }
									   else{
										   labels.add("<html>" + labelline + "</html>");
									   }
								   }
							   }
						   }
						   else if (infoline.equals("[presets]")){
							   String presetline;
							   while((presetline = br.readLine()) != null){
								   presetline = presetline.trim();
								   if (!presetline.equals("") && !presetline.substring(0, Math.min(presetline.length(), 2)).equals("//")){
									   if (presetline.equals("[/presets]")){
										   break;
									   }
									   else{
										   presets.add(Integer.parseInt(presetline));
									   }
								   }
							   }
						   }
						   else if (infoline.equals("[instructions]")){
							   String instructionline;
							   while((instructionline = br.readLine()) != null){
								   instructionline = instructionline.trim();
								   if (!instructionline.equals("") && !instructionline.substring(0, Math.min(instructionline.length(), 2)).equals("//")){
									   if (instructionline.equals("[/instructions]")){
										   subtitle = subtitle + "</html>";
										   break;
									   }
									   else{
										   subtitle = subtitle + instructionline;
									   }
								   }
							   }
						   }
						   else if (infoline.equals("[qgroup]")){
							   String qgroupline;
							   ArrayList<String> qgroup = new ArrayList<String>();
							   while((qgroupline = br.readLine()) != null){
								   qgroupline = qgroupline.trim();
								   if (!qgroupline.equals("") && !qgroupline.substring(0, Math.min(qgroupline.length(), 2)).equals("//")){
									   if (qgroupline.equals("[/qgroup]")){
										   questions.add(qgroup);
										   break;
									   }
									   else{
										   if (qgroupline.equals("[question]")){
											   String questionline;
											   String buildq = "<html>";
											   while ((questionline = br.readLine()) != null){
												   questionline = questionline.trim();
												   if (!questionline.equals("") && !questionline.substring(0, Math.min(questionline.length(), 2)).equals("//")){
													   if (questionline.equals("[/question]")){
														   qgroup.add(buildq + "</html>");
														   break;
													   }
													   else{
														   buildq = buildq + questionline;
													   }
												   }
											   }
										   }
									   }
								   }
							   }
						   }
						   else if (infoline.equals("[agroup]")){
							   String agroupline;
							   ArrayList<String> agroup = new ArrayList<String>();
							   while((agroupline = br.readLine()) != null){
								   agroupline = agroupline.trim();
								   if (!agroupline.equals("") && !agroupline.substring(0, Math.min(agroupline.length(), 2)).equals("//")){
									   if (agroupline.equals("[/agroup]")){
										   answers.add(agroup);
										   break;
									   }
									   else{
										   if (agroupline.equals("[answer]")){
											   String answerline;
											   String builda = "";
											   while ((answerline = br.readLine()) != null){
												   answerline = answerline.trim();
												   if (!answerline.equals("") && !answerline.substring(0, Math.min(answerline.length(), 2)).equals("//")){
													   if (answerline.equals("[/answer]")){
														   agroup.add(builda);
														   break;
													   }
													   else{
														   builda = builda + answerline;
													   }
												   }
											   }
										   }
									   }
								   }
							   }
						   }
						   else if (infoline.equals("#INFORMATION") || infoline.equals("#DELAY") || infoline.equals("#NEGOTIATION") || infoline.equals("#MANIPULATION") || infoline.equals("#QUESTIONS")){
							   returnlist.add(new ManipulationPanel(label, title, questions, answers, subtitle, labels, presets, button_text, delay_message, timeout));
							   br.reset();
							   break;
						   }
						   else{
							   String[] splitline = infoline.split("=", 2);
							   if (splitline[0].equals("label")){
								   label = splitline[1];
							   }
							   else if (splitline[0].equals("title")){
								   title = "<html>" + splitline[1] + "</html>";
							   }
							   else if (splitline[0].equals("button")){
								   button_text = splitline[1];
								   timeout = 0;
								   delay_message = "";
							   }
							   else if (splitline[0].equals("timer")){
								   timeout = Integer.parseInt(splitline[1]);
								   button_text = "";
							   }
						   }
					   }
				   }
			   }
			   else if (line.equals("#QUESTIONS")){
				   DEBUG("QUESTIONS", bigdebug);
				   String label = "";
				   ArrayList<String> demographic_labels = new ArrayList<String>();
				   String button_text = "Next";
				   String infoline;
				   while(true){
					   br.mark(1000);
					   if ((infoline = br.readLine()) == null){
						   returnlist.add(new DemographicsPanel(label, demographic_labels, button_text));
						   break;
					   }
					   infoline = infoline.trim();
					   if (!infoline.equals("") && !infoline.substring(0, Math.min(infoline.length(), 2)).equals("//")){
						   if (infoline.equals("[question]")){
							   String questionline;
							   String question = "<html>";
							   while((questionline = br.readLine()) != null){
								   questionline = questionline.trim();
								   if (!questionline.equals("") && !questionline.substring(0, Math.min(questionline.length(), 2)).equals("//")){
									   if (questionline.equals("[/question]")){
										   demographic_labels.add(question + "</html>");
										   break;
									   }
									   else{
										   question = question + questionline;
									   }
								   }
							   }
						   }
						   else if (infoline.equals("#INFORMATION") || infoline.equals("#DELAY") || infoline.equals("#NEGOTIATION") || infoline.equals("#MANIPULATION") || infoline.equals("#QUESTIONS")){
							   returnlist.add(new DemographicsPanel(label, demographic_labels, button_text));
							   br.reset();
							   break;
						   }
						   else{
							   String[] splitline = infoline.split("=", 2);
							   if (splitline[0].equals("label")){
								   label = splitline[1];
							   }
							   else if (splitline[0].equals("button")){
								   button_text = splitline[1];
							   }
						   }
					   }
				   }
			   }
			}
		}
		br.close();
		
		return returnlist;
	}
	public static void DEBUG(String message, Boolean flag){
		if (flag){
			System.out.println(message);
		}
	}
}
