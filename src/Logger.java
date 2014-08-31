package src;

import java.io.*;

public class Logger {
	public String file = "";
	
	public Logger(String file){
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		this.file = path + "\\" + file;
	}
	
	public void log(String str){
		try{
			if (file != ""){
				FileWriter fw = new FileWriter(this.file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(str);
				bw.close();
			}
		}
		catch(IOException e){
			System.out.println("FileWriter failed to write: " + str);
		}
	}
}