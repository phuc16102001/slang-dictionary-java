/**
 * @package hcmus.fit.vuongphuc.model
 * @author VuongPhuc
 *
 * Dec. 17, 2021 - 1:23:01 a.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hcmus.fit.vuongphuc.constant.Constant;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class MyDictionary extends HashMap<String, MyDefinitionList> {
	
	private static MyDictionary instance = null;
	private MyDictionary() {}
	
	public static MyDictionary getInstance() {
		if (instance==null) {
			instance = new MyDictionary();
		}
		return instance;
	}
	
	public MyDefinitionList searchSlang(String slang) {
		return this.get(slang);
	}
	
	public void loadFromFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
	
		String line = null;
		while ((line=reader.readLine())!=null) {
//			System.out.println(line);
			String[] args1 = line.split(Constant.SLANG_DEFINITION_DELIMITER);
			if (args1.length<2) {
				continue;
			}
			String slang = args1[0];
			String[] args2 = args1[1].split(Constant.CURRENT_DICT_PATH);
			
			MyDefinitionList definitions = new MyDefinitionList(args2);
			this.put(slang, definitions);
		}
		
		reader.close();
	}
	
	public void storeToFile(String path) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		
		for (Map.Entry<String, MyDefinitionList> item:this.entrySet()) {			
			String line = String.format("%s%s%s\n",	item.getKey(), Constant.SLANG_DEFINITION_DELIMITER, item.getValue().toString());
			writer.write(line);
		}
		
		writer.close();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, MyDefinitionList> item:this.entrySet()) {			
			builder.append(item.getKey());
			builder.append(Constant.SLANG_DEFINITION_DELIMITER);
			builder.append(item.getValue().toString());
			builder.append(Constant.ENDLINE);
		}
		return builder.toString();
	}
	
}
