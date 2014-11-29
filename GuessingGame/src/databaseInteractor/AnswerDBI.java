/**
 * 
 */
package databaseInteractor;

import java.sql.ResultSet;

import models.Answer;

/**
 * @author cave
 *
 */
public final class AnswerDBI extends GameDBInteractor {

	public AnswerDBI() {
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
		Answer temp = null;
		try {
			id = rs.getInt("answerID");
			text = rs.getString("text");
			temp = new Answer(id, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

}
