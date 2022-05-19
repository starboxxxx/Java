package Java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class FileSet {
	
	static LinkedHashMap<String, String> dict = new LinkedHashMap<String, String>();
	static LinkedHashMap<String, String> error = new LinkedHashMap<String, String>();
	static int a;
	static int b;
	
	public void setHeaderName(String datafile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datafile));
			String data = reader.readLine();
			String[] splitdata = data.split("\t");
			if (Arrays.asList(splitdata).contains("Category_fullname") || Arrays.asList(splitdata).contains("Category_menu_code")) {
				a = Arrays.asList(splitdata).indexOf("Category_fullname");
				String CAT_NAME_FIELD = splitdata[a];
				b = Arrays.asList(splitdata).indexOf("Category_menu_code");
				String CAT_ID_FIELD = splitdata[b];
				dict.put((String)CAT_ID_FIELD, (String)CAT_NAME_FIELD);
				error.put((String)CAT_ID_FIELD, (String)CAT_NAME_FIELD);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}
	
	public LinkedHashMap<String, String> getDict() {
		return dict;
	}

	public LinkedHashMap<String, String> getError() {
		return error;
	}

	public void contentSetting(String x, String y) {
		if (dict.containsKey(x) == true) {
			if (dict.containsValue(y) == false) {
				error.put(x, y);
			}
		}	
		else {
			dict.put(x, y);
		}
	}
	
	public void depthSetting(String[] Category_menu_code, String[] Category_fullname) {
		
		if (Arrays.asList(Category_fullname).indexOf("키즈랜드") == 0) {
			int s = Category_fullname.length;
			if(s > 4) {
				for(int i = 1; i < 4; i++) {
					contentSetting(Category_menu_code[i], Category_fullname[i]);
				}
			}
			else if (s <= 4){
				if(Category_menu_code[s - 1].startsWith("CV")) {
					for(int i = 1; i < s-1; i++) {
						contentSetting(Category_menu_code[i], Category_fullname[i]);
					}
				}
				else {
					for (int i = 1; i < s; i++) {
						contentSetting(Category_menu_code[i], Category_fullname[i]);
					}
				}
			}
		}
	}
}
