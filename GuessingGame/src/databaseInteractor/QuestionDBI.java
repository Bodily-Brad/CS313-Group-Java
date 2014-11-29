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
public final class QuestionDBI extends GameDBInteractor {

	public QuestionDBI() {
		super();
		// Protected Members
		tableName = "answers";
		keyName = "answerID";
		defaultSearchField = "text";
		defaultSortField = "answerID";
	}

	public static Object createFromRecord(ResultSet rs) {

		int id;
		String text;
		Question temp = null;
		try {
			id = rs.getInt("questionID");
			text = rs.getString("text");
			temp = new Question(id, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

}
