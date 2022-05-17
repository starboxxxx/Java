package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

public class FileManage {
	
	public Properties readFile(String FilePath) {
		
		try {	
			
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(FilePath);
			prop.load(new java.io.BufferedInputStream(fis));
			return prop;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void makeFile(String filePath, LinkedHashMap<String,String>map) {
		try {
			File file = new File(filePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for(String key : map.keySet()) {
				writer.write(key + "\t" + map.get(key)+"\n");
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
