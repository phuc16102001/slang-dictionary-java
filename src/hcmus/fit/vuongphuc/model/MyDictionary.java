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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

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
	
	public String random() {
		String[] keys = this.keySet().toArray(new String[0]);
		int index = new Random().nextInt(keys.length);
		return keys[index];
	}
	
	public MyDefinitionList searchSlang(String slang) {
		return this.get(slang);
	}
	
	public void removeDefinition(String slang, int definitionIndex) {
		this.get(slang).remove(definitionIndex);
	}
	
	public void addSlang(String slang, String definition) {
		MyDefinitionList listDefinition = this.get(slang);
		if (listDefinition==null) {
			setSlang(slang,definition);
			return;
		}
		listDefinition.add(definition);
	}
	
	public void setSlang(String slang, String definition) {
		MyDefinitionList newList = new MyDefinitionList(definition);
		this.put(slang, newList);
	}
	
	public List<String> searchDefinition(String searchKey) {
		List<String> slangs = new ArrayList<>();

		for (Map.Entry<String, MyDefinitionList> item:this.entrySet()) {	
			String[] values = item.getValue().toArray(new String[0]);
			for (String definition:values) {
				if (definition.toLowerCase().contains(searchKey.toLowerCase())) {
					slangs.add(item.getKey());
					break;
				}
			}
		}
		return slangs;
	}
	
	public void loadFromFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
	
		String line = null;
		while ((line=reader.readLine())!=null) {
			String[] args1 = line.split(Constant.SLANG_DEFINITION_DELIMITER);
			if (args1.length<2) {
				continue;
			}
			String slang = args1[0];
			String[] args2 = args1[1].split(Pattern.quote(Constant.DEFINITION_DEFINITION_DELIMITER));
			
			MyDefinitionList definitions = new MyDefinitionList(args2);
			// System.out.println(definitions);
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
