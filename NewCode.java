package project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class NewCode {
	
	static BufferedReader reader;
	
	
	public static void main(String[] args){
		
		LinkedHashMap<String, String> dict = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> error = new LinkedHashMap<String, String>();
		FileManage fm = new FileManage();
		DictSetting ds = new DictSetting(dict, error);
		
		Properties prop = fm.readFile("C:\\Users\\dlrbe\\eclipse-workspace\\GD\\src\\project\\PATH.properties");
		String DATA_FILE = prop.getProperty("data_file");
		String CAT_META = prop.getProperty("cat_meta");
		String ERR_META = prop.getProperty("err_meta");
		
		ds.setHeaderName(DATA_FILE);
		int a = ds.getA();
		int b = ds.getB();
		try {
			reader = new BufferedReader(new FileReader(DATA_FILE));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		while (true) {
			try {
				String line1 = reader.readLine();
				if(line1 == null) {
					break;
				}
				String[] line2 = line1.split("\t");
				String Category_fullname_ex = line2[a];
				String Category_menu_code_ex = line2[b];
				String[] Category_fullname = Category_fullname_ex.split(">");
				String[] Category_menu_code = Category_menu_code_ex.split(">");
				ds.depthSetting(Category_menu_code, Category_fullname);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		fm.makeFile(CAT_META, dict);
		fm.makeFile(ERR_META, error);
	}
}