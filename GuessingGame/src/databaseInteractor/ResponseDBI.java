/**
 * 
 */
package databaseInteractor;

import java.sql.ResultSet;

import models.*;

/**
 * @author cave
 *
 */
public final class ResponseDBI extends OldGameDBInteractor {

	public ResponseDBI() {
		super();
		// Protected Members
		tableName = "responses";
		keyName = "responseID";
		defaultSearchField = "responseID";
		defaultSortField = "responseID";
	}

	public static Object createFromRecord(ResultSet rs) {

		int id;
		int itemID;
		int questionID;
		int answerID;
		int count;
		Response temp = null;
		try {
			id 			= rs.getInt("responseID");
			itemID 		= rs.getInt("itemID");
			questionID 	= rs.getInt("questionID");
			answerID 	= rs.getInt("answerID");
			count 		= rs.getInt("count");
			temp = new Response( id, itemID, questionID, answerID, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

}
