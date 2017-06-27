
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
	DBConnector db;

	// Create Table in MySql Database
	public boolean createTableName(String tableName) throws Exception {
		CallableStatement myStmt = null;
		try {
			db = new DBConnector();
			myStmt = db.conn.prepareCall("{CALL createTable(?)}");
			myStmt.setString(1, tableName);
			return myStmt.execute();
		} finally {
			myStmt.close();
		}

	}

	// Save the data in table
	public List<MainModel> saveStudent(int id, String Name, Double QMark, Double A1Mark, Double A2Mark, Double A3Mark,
			Double ExamMark, Double Result, String Grade) throws Exception {
		List<MainModel> list = new ArrayList<>();

		ResultSet myRs = null;
		Statement myStmt = null;
		try {
			db = new DBConnector();
			myStmt = db.conn.createStatement();
			String query = "INSERT INTO " + TableNameStatic.getTableName()
					+ " (StudentID,StudentName,QMark,Assignment1,Assignment2,Assignment3,Exam,Results,Grade)VALUES('"
					+ id + "','" + Name + "','" + QMark + "','" + A1Mark + "','" + A3Mark + "','" + A3Mark + "','"
					+ ExamMark + "','" + Result + "','" + Grade + "')";

			myStmt.executeUpdate(query);
			myRs = myStmt.executeQuery("SELECT * FROM " + TableNameStatic.getTableName());
			while (myRs.next()) {
				MainModel mainModel = new MainModel(myRs.getInt("id"), myRs.getInt("StudentID"),
						myRs.getString("StudentName"), myRs.getDouble("QMark"), myRs.getDouble("Assignment1"),
						myRs.getDouble("Assignment2"), myRs.getDouble("Assignment3"), myRs.getDouble("Exam"),
						myRs.getDouble("Results"), myRs.getString("Grade"));
				list.add(mainModel);
			}
			return list;

		} finally {
			myRs.close();
			myStmt.close();
		}
	}

	// Fetch all student record from database
	public List<MainModel> getAllStudents() throws Exception {
		List<MainModel> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			db = new DBConnector();
			myStmt = db.conn.createStatement();
			myRs = myStmt.executeQuery("Select * from " + TableNameStatic.getTableName());
			while (myRs.next()) {
				MainModel mainModel = new MainModel(myRs.getInt("id"), myRs.getInt("StudentID"),
						myRs.getString("StudentName"), myRs.getDouble("QMark"), myRs.getDouble("Assignment1"),
						myRs.getDouble("Assignment2"), myRs.getDouble("Assignment3"), myRs.getDouble("Exam"),
						myRs.getDouble("Results"), myRs.getString("Grade"));
				list.add(mainModel);
			}

			return list;
		} finally {
			myStmt.close();
			myRs.close();
		}

	}

	// Search selected data
	public List<MainModel> searchStudent(String cmb, String searchvalue) throws Exception {
		List<MainModel> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			db = new DBConnector();
			myStmt = db.conn.createStatement();
			myRs = myStmt.executeQuery(
					"SELECT * FROM " + TableNameStatic.getTableName() + " WHERE StudentID=" + searchvalue);
			while (myRs.next()) {
				MainModel mainModel = new MainModel(myRs.getInt("id"), myRs.getInt("StudentID"),
						myRs.getString("StudentName"), myRs.getDouble("QMark"), myRs.getDouble("Assignment1"),
						myRs.getDouble("Assignment2"), myRs.getDouble("Assignment3"), myRs.getDouble("Exam"),
						myRs.getDouble("Results"), myRs.getString("Grade"));
				list.add(mainModel);
			}
			return list;
		} finally {
			myStmt.close();
			myRs.close();
		}
	}

	// Update the student record
	public void updateStudentDetails(int idStudent, int studentID, String Name, Double qMark, Double A1, Double A2,
			Double A3, Double ExamMark, Double Result, String Grade) throws Exception {
		Statement myStmt = null;
		try {
			db = new DBConnector();
			myStmt = db.conn.createStatement();
			String Query = "UPDATE " + TableNameStatic.getTableName() + " SET StudentID='" + studentID
					+ "',StudentName='" + Name + "',QMark='" + qMark + "',Assignment1='" + A1 + "',Assignment2='" + A2
					+ "',Assignment3='" + A3 + "',Exam='" + ExamMark + "',Results='" + Result + "',Grade='" + Grade
					+ "' where id='" + idStudent + "'";
			myStmt.executeUpdate(Query);
		} finally {
			myStmt.close();
		}
	}
}
