/**
 * 
 */
package databaseInteractor;

import java.sql.ResultSet;

import models.Item;

/**
 * @author cave
 *
 */
public final class ItemDBI extends GameDBInteractor {

	/**
	 * 
	 */
	public ItemDBI() {
		super();
		// Protected Members
		tableName = "items";
		keyName = "itemID";
		defaultSearchField = "description";
		defaultSortField = "description";	}
	

	public static Object createFromRecord(ResultSet rs) {

		int id;
		String text;
		Item temp = null;
		try {
			id = rs.getInt("itemID");
			text = rs.getString("description");
			temp = new Item(id, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}


}
