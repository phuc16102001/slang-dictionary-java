/**
 * @package hcmus.fit.vuongphuc.model
 * @author VuongPhuc
 *
 * Dec. 17, 2021 - 1:22:42 a.m.
 * @since 2021
 * @version
 */
package hcmus.fit.vuongphuc.model;

import java.util.ArrayList;
import java.util.List;

import hcmus.fit.vuongphuc.constant.Constant;

/**
 * Description:
 *
 * @author VuongPhuc
 * @see 
 */
public class MyDefinitionList extends ArrayList<String> {
	
	public MyDefinitionList(String definition) {
		this(new String[] {definition});
	}
	
	public MyDefinitionList(String[] listDefinition) {
		super();
		for (String definition:listDefinition) {
			this.add(definition);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int nSize = this.size();
		for (int i=0;i<nSize;i++) {
			builder.append(this.get(i));
			if (i<nSize-1) {
				builder.append(Constant.DEFINITION_DEFINITION_DELIMITER);
			}
		}
		return builder.toString();
	}
}
