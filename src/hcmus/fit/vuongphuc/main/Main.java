/**
 * @package hcmus.fit.vuongphuc.main
 * @author VuongPhuc
 *
 * Dec. 15, 2021 - 11:52:00 p.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.main;

import java.io.IOException;

import hcmus.fit.vuongphuc.constant.Constant;
import hcmus.fit.vuongphuc.model.MyDictionary;
import hcmus.fit.vuongphuc.ui.MainMenuScreen;
import hcmus.fit.vuongphuc.utils.MyDialog;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean load = true;
		try {
			MyDictionary.getInstance().loadFromFile(Constant.CURRENT_DICT_PATH);
		} catch (IOException e) {
			try {
				MyDictionary.getInstance().loadFromFile(Constant.DEFAULT_DICT_PATH);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		if (load) {			
			new MainMenuScreen();
		}
	}

}
