/**
 * @package hcmus.fit.vuongphuc.model
 * @author VuongPhuc
 *
 * Dec. 19, 2021 - 5:25:43 p.m.
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
import java.util.List;

import hcmus.fit.vuongphuc.constant.Constant;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class History {

	private static History instance = null;
	private List<String> histories;
	
	private History() {
		histories = new ArrayList<String>();
	}
	
	public static History getInstance() {
		if (instance==null) {
			instance = new History();
		}
		return instance;
	}
	
	public void remove(int index) {
		histories.remove(index);
	}
	
	public void add(String slang) {
		histories.add(slang);
	}
	
	public void clear() {
		histories.clear();
	}
	
	public void saveHistory() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(Constant.HISTORY_PATH));
		
		for (String h:histories) {
			writer.write(h+"\n");
		}
		
		writer.close();
	}
	
	public List<String> loadHistory() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Constant.HISTORY_PATH));
		String line;
		histories.clear();
		while ((line=reader.readLine())!=null) {
			histories.add(line);
		}
		reader.close();
		return histories;
	}
}
