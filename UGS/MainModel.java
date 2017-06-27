import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainModel {
	private int idStudent;
	private SimpleIntegerProperty ID;
	private SimpleStringProperty Name;
	private SimpleDoubleProperty QMarks;
	private SimpleDoubleProperty A1;
	private SimpleDoubleProperty A2;
	private SimpleDoubleProperty A3;
	private SimpleDoubleProperty ExamMarks;
	private SimpleDoubleProperty Result;
	private SimpleStringProperty Grade;

	public MainModel() {

	}

	//Constructor
	public MainModel(Integer id, String name, double qMarks, double a1, double a2, double a3, double examMarks,
			double result, String grade) {
		super();
		this.ID = new SimpleIntegerProperty(id);
		this.Name = new SimpleStringProperty(name);
		this.QMarks = new SimpleDoubleProperty(qMarks);
		this.A1 = new SimpleDoubleProperty(a1);
		this.A2 = new SimpleDoubleProperty(a2);
		this.A3 = new SimpleDoubleProperty(a3);
		this.ExamMarks = new SimpleDoubleProperty(examMarks);
		this.Result = new SimpleDoubleProperty(result);
		this.Grade = new SimpleStringProperty(grade);
	}

	//Constructor which is used to update the student record
	public MainModel(int idStudent, Integer id, String name, double qMarks, double a1, double a2, double a3,
			double examMarks, double result, String grade) {
		super();
		this.idStudent = idStudent;
		this.ID = new SimpleIntegerProperty(id);
		this.Name = new SimpleStringProperty(name);
		this.QMarks = new SimpleDoubleProperty(qMarks);
		this.A1 = new SimpleDoubleProperty(a1);
		this.A2 = new SimpleDoubleProperty(a2);
		this.A3 = new SimpleDoubleProperty(a3);
		this.ExamMarks = new SimpleDoubleProperty(examMarks);
		this.Result = new SimpleDoubleProperty(result);
		this.Grade = new SimpleStringProperty(grade);
	}

	public Integer getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public Integer getID() {
		return ID.get();
	}

	public String getName() {
		return Name.get();
	}

	public double getQMarks() {
		return QMarks.get();
	}

	public double getA1() {
		return A1.get();
	}

	public double getA2() {
		return A2.get();
	}
	public double getA3() {
		return A3.get();
	}

	public double getExamMarks() {
		return ExamMarks.get();
	}

	public Double getResult() {
		return Result.get();
	}

	public String getGrade() {
		return Grade.get();
	}

	public void setID(int iD) {
		ID = new SimpleIntegerProperty(iD);
	}

	

}
