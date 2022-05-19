package Java;
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

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		FileManage fm = new FileManage();
		FileSetting fs = new FileSetting();
		
		Properties prop = fm.readFile("C:\\Users\\dlrbe\\eclipse-workspace\\GD\\src\\Java\\PATH.properties");
		String DATA_FILE = prop.getProperty("data_file");
		String CAT_META = prop.getProperty("cat_meta");
		String ERR_META = prop.getProperty("err_meta");
		
		BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
	
		fs.setHeaderName(DATA_FILE);
		int a = fs.getA();
		int b = fs.getB();
		
		while (true) {
				String line1 = reader.readLine();
				if(line1 == null) {
					break;
				}
				String[] line2 = line1.split("\t");
				String Category_fullname_ex = line2[a];
				String Category_menu_code_ex = line2[b];
				String[] Category_fullname = Category_fullname_ex.split(">");
				String[] Category_menu_code = Category_menu_code_ex.split(">");
				
				fs.depthSetting(Category_menu_code, Category_fullname);
		}
		
		LinkedHashMap<String, String> dict = fs.getDict();
		LinkedHashMap<String, String> error = fs.getError();
		
		fm.makeFile(CAT_META, dict);
		fm.makeFile(ERR_META, error);
	}
}
