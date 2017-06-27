import java.awt.Toolkit;
import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class addController {
	DataAccess dataAccess = new DataAccess();
	MainModel modelMain = new MainModel();
	private int selectedIndex;

	// controller for Add new form
	private TableView<MainModel> tableMain;
	private CustomTableView customTableView = new CustomTableView();
	private static ObservableList<MainModel> mainModel = FXCollections.observableArrayList();

	// Customize text field
	private RestrictiveTextField txtId;
	private RestrictiveTextField txtStudentName;
	private RestrictiveTextField txtqMark;
	private RestrictiveTextField txtA1;
	private RestrictiveTextField txtA2;
	private RestrictiveTextField txtA3;
	private RestrictiveTextField txtExamMarks;
	private Button btnStudentMarks;
	private Button btnUpdateStudent;
	private Button btnClear;

	// label to display message
	private Label lblIDmsg;
	private Label lblNamemsg;
	private Label lblQuizemsg;
	private Label lblA1msg;
	private Label lblA2msg;
	private Label lblA3msg;
	private Label lblExammsg;
	private String actionToPerform = "ADD";

	public void addNewStudentGUI(BorderPane root) throws Exception {
		tableMain = new TableView<MainModel>();

		VBox vBox = new VBox();

		GridPane gridPane = new GridPane();
		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		gridPane.setHgap(10);
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(30, 0, 20, 50));

		// Grid Pane for top
		gridPane.add(new Label("Student ID (must be 8 digits)"), 0, 0);

		txtId = new RestrictiveTextField();
		txtId.setPromptText("########");
		txtId.setMaxLength(8);// limit character
		txtId.setRestrict("[0-9]");// only accepts number
		lblIDmsg = new Label();
		gridPane.add(txtId, 1, 0);
		gridPane.add(lblIDmsg, 2, 0);

		gridPane.add(new Label("Student Name"), 0, 1);
		txtStudentName = new RestrictiveTextField();
		lblNamemsg = new Label();
		gridPane.add(txtStudentName, 1, 1);
		gridPane.add(lblNamemsg, 2, 1);

		gridPane.add(new Label("Quize Marks (enter 0-100)"), 0, 2);
		txtqMark = new RestrictiveTextField();
		txtqMark.setPromptText("0-100");
		txtqMark.setRestrict("[0-9.]");
		lblQuizemsg = new Label();
		gridPane.add(txtqMark, 1, 2);
		gridPane.add(lblQuizemsg, 2, 2);

		gridPane.add(new Label("Assignment 1 Marks (enter 0-100)"), 0, 3);
		txtA1 = new RestrictiveTextField();
		txtA1.setPromptText("0-100");
		txtA1.setRestrict("[0-9.]");
		lblA1msg = new Label();
		gridPane.add(txtA1, 1, 3);
		gridPane.add(lblA1msg, 2, 3);

		gridPane.add(new Label("Assignment 2 Marks (enter 0-100)"), 0, 4);
		txtA2 = new RestrictiveTextField();
		txtA2.setPromptText("0-100");
		txtA2.setRestrict("[0-9.]");
		lblA2msg = new Label();
		gridPane.add(txtA2, 1, 4);
		gridPane.add(lblA2msg, 2, 4);

		gridPane.add(new Label("Assignment 3 Marks (enter 0-100)"), 0, 5);
		txtA3 = new RestrictiveTextField();
		txtA3.setPromptText("0-100");
		txtA3.setRestrict("[0-9.]");
		lblA3msg = new Label();
		gridPane.add(txtA3, 1, 5);
		gridPane.add(lblA3msg, 2, 5);

		gridPane.add(new Label("Exam Marks (enter 0-100)"), 0, 6);
		txtExamMarks = new RestrictiveTextField();
		txtExamMarks.setPromptText("0-100");
		txtExamMarks.setRestrict("[0-9.]");
		lblExammsg = new Label();
		gridPane.add(txtExamMarks, 1, 6);
		gridPane.add(lblExammsg, 2, 6);

		// tableMain;
		tableMain.getItems().clear();

		tableMain.getColumns().addAll(customTableView.getColumn(tableMain));
		tableMain.getItems().addAll(dataAccess.getAllStudents());

		// HBox in the bottom
		HBox hBox = new HBox();
		hBox.prefWidth(Double.MAX_VALUE);

		btnStudentMarks = new Button("Student Marks");
		btnUpdateStudent = new Button("Update");
		btnClear = new Button("Clear");
		hBox.getChildren().addAll(btnClear, btnUpdateStudent, btnStudentMarks);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(10, 10, 10, 0));
		hBox.getStyleClass().add("hBoxBackGroundColor");

		vBox.getChildren().addAll(gridPane, tableMain, hBox);
		root.setCenter(vBox);
		eventHandling();

	}

	// Action performed by the controls.
	private void eventHandling() {
		setTextFieldErrorProperty();// Display the error message.

		btnStudentMarks.setOnAction(e -> {
			if (actionToPerform == "ADD") {
				addStudentDetails();
			}
		});

		btnUpdateStudent.setOnAction(e2 -> {
			try {
				updateStudentRecord();
			} catch (Exception e1) {
				alertMessage(e1.getMessage());
			}
		});

		// Add record in table view when enter key is pressed in txtExamMarks
		txtExamMarks.setOnKeyPressed(keyPressed -> {

			if (keyPressed.getCode() == KeyCode.ENTER) {
				if (actionToPerform == "ADD") {
					addStudentDetails();
				} else {
					try {
						updateStudentRecord();
					} catch (Exception e1) {
						alertMessage(e1.getMessage());
					}
				}

			}
		});

		// Pass selected value to text field
		tableMain.setOnMouseClicked(mouseClicked -> {
			if (mouseClicked.getClickCount() == 2) {
				selectedIndex = tableMain.getSelectionModel().getSelectedIndex();
				modelMain = tableMain.getSelectionModel().getSelectedItem();
				txtId.setText(modelMain.getID().toString());
				txtStudentName.setText(modelMain.getName());
				txtqMark.setText(Double.toString(modelMain.getQMarks()));
				txtA1.setText(Double.toString(modelMain.getA1()));
				txtA2.setText(Double.toString(modelMain.getA2()));
				txtA3.setText(Double.toString(modelMain.getA3()));
				txtExamMarks.setText(Double.toString(modelMain.getExamMarks()));
				actionToPerform = "UPDATE";
			}
		});

		// Clear textfield
		btnClear.setOnAction(clear -> {
			ClearAllTextField();
		});

	}

	// Update student record
	private void updateStudentRecord() throws NumberFormatException, Exception {
		if (isCompleted()) {
			Double Result = getStudentResult(Double.parseDouble(txtqMark.getText()),
					Double.parseDouble(txtA1.getText()), Double.parseDouble(txtA2.getText()),
					Double.parseDouble(txtA3.getText()), Double.parseDouble(txtExamMarks.getText()));
			dataAccess.updateStudentDetails(modelMain.getIdStudent(), Integer.parseInt(txtId.getText()),
					txtStudentName.getText(), Double.parseDouble(txtqMark.getText()),
					Double.parseDouble(txtA1.getText()), Double.parseDouble(txtA2.getText()),
					Double.parseDouble(txtA3.getText()), Double.parseDouble(txtExamMarks.getText()), Result,
					getStudentGrade(Result));
			refreshTable();
			tableMain.getSelectionModel().select(selectedIndex);
			ClearAllTextField();
		}
	}

	// Add the record in table view and save in database
	private void addStudentDetails() {
		if (isCompleted()) {
			Double Result = getStudentResult(Double.parseDouble(txtqMark.getText()),
					Double.parseDouble(txtA1.getText()), Double.parseDouble(txtA2.getText()),
					Double.parseDouble(txtA3.getText()), Double.parseDouble(txtExamMarks.getText()));
			try {
				dataAccess.saveStudent(Integer.parseInt(txtId.getText()), txtStudentName.getText(),
						Double.parseDouble(txtqMark.getText()), Double.parseDouble(txtA1.getText()),
						Double.parseDouble(txtA2.getText()), Double.parseDouble(txtA3.getText()),
						Double.parseDouble(txtExamMarks.getText()), Result, getStudentGrade(Result));

			} catch (Exception e1) {
				alertMessage(e1.getMessage());
			}
			refreshTable();
			tableMain.getSelectionModel().select(tableMain.getItems().size() - 1);
			ClearAllTextField();
		}
	}

	// Refresh the table after adding and updating student records
	private void refreshTable() {
		try {
			mainModel = FXCollections.observableArrayList(dataAccess.getAllStudents());
			tableMain.setItems(mainModel);

		} catch (Exception e) {
			alertMessage(e.getMessage());
		}
	}

	// Add and returns input value to main model
	public static ObservableList<MainModel> addStudentDetails(int id, String name, Double qMarks, Double a1, Double a2,
			Double a3, Double examMarks, Double result, String grade) {
		mainModel.addAll(new MainModel(id, name, qMarks, a1, a2, a3, examMarks, result, grade));
		return mainModel;
	}

	// Calculate Result
	public Double getStudentResult(Double QMarks, Double A1Marks, Double A2Marks, Double A3Marks, Double ExamMarks) {
		DecimalFormat df=new DecimalFormat("##.##");
		return Double.parseDouble(df.format((QMarks * 0.05) + (A1Marks * 0.15) + (A2Marks * 0.2) + (A3Marks * 0.10) + (ExamMarks * 0.5)));

	}

	// Calculate Grade
	public String getStudentGrade(Double result) {

		if (result >= 85) {
			return "HD";
		} else if (result >= 75 && result < 85) {
			return "DI";
		} else if (result >= 65 && result < 75) {
			return "CR";
		} else if (result >= 50 && result < 65) {
			return "PS";
		} else
			return "FL";
	}

	// Clear all text field
	public void ClearAllTextField() {
		txtId.clear();
		txtStudentName.clear();
		txtqMark.clear();
		txtA1.clear();
		txtA2.clear();
		txtA3.clear();
		txtExamMarks.clear();
		txtId.requestFocus();
		actionToPerform = "ADD";
	}

	// validate checkbox empty or complete
	public boolean isCompleted() {
		int i = 0;
		if (txtId.getText().isEmpty()) {
			lblIDmsg.setText("Please enter student id.");
			lblIDmsg.getStyleClass().add(("error-font"));
			txtId.getStyleClass().add(("validation-error"));
			i++;
		}
		if (txtStudentName.getText().isEmpty()) {
			lblNamemsg.setText("Please enter student name.");
			lblNamemsg.getStyleClass().add(("error-font"));
			txtStudentName.getStyleClass().add(("validation-error"));
			i++;
		}
		if (txtqMark.getText().isEmpty()) {
			lblQuizemsg.setText("Please enter quize marks.");
			lblQuizemsg.getStyleClass().add(("error-font"));
			txtqMark.getStyleClass().add(("validation-error"));
			i++;
		}
		if (txtA1.getText().isEmpty()) {
			lblA1msg.setText("Please enter assignment 1 marks.");
			lblA1msg.getStyleClass().add(("error-font"));
			txtA1.getStyleClass().add(("validation-error"));
			i++;
		}
		if (txtA2.getText().isEmpty()) {
			lblA2msg.setText("Please enter assignment 2 marks.");
			lblA2msg.getStyleClass().add(("error-font"));
			txtA2.getStyleClass().add(("validation-error"));
			i++;
		}
		if (txtA3.getText().isEmpty()) {
			lblA3msg.setText("Please enter assignment 3 marks.");
			lblA3msg.getStyleClass().add(("error-font"));
			txtA3.getStyleClass().add(("validation-error"));
			i++;
		}
		if (txtExamMarks.getText().isEmpty()) {
			lblExammsg.setText("Please enter exam marks.");
			lblExammsg.getStyleClass().add(("error-font"));
			txtExamMarks.getStyleClass().add(("validation-error"));
			i++;
		}
		if (i > 0) {
			Toolkit.getDefaultToolkit().beep();
			return false;

		} else {
			return true;
		}

	}

	// Event for text changed property
	private void setTextFieldErrorProperty() {
		txtId.textProperty().addListener(e -> {
			setTextFieldError(txtId, lblIDmsg, 1);
		});
		txtStudentName.textProperty().addListener(e -> {
			setTextFieldError(txtStudentName, lblNamemsg, 2);
		});

		txtqMark.textProperty().addListener(e -> {
			setTextFieldError(txtqMark, lblQuizemsg, 3);
		});
		txtA1.textProperty().addListener(e -> {
			setTextFieldError(txtA1, lblA1msg, 3);
		});
		txtA2.textProperty().addListener(e -> {
			setTextFieldError(txtA2, lblA2msg, 3);
		});
		txtA3.textProperty().addListener(e -> {
			setTextFieldError(txtA3, lblA3msg, 3);
		});
		txtExamMarks.textProperty().addListener(e -> {
			setTextFieldError(txtExamMarks, lblExammsg, 3);
		});
	}

	// Used to display information message in application
	public void setTextFieldError(TextField textfield, Label lbl, int Flag) {
		switch (Flag) {
		case 1:
			if (!textfield.getText().isEmpty()) {
				textfield.getStyleClass().removeAll("validation-error");
				lbl.getStyleClass().removeAll(("error-font"));
				lbl.setText("");
			}
			break;
		case 2:
			if (!textfield.getText().isEmpty()) {
				textfield.getStyleClass().removeAll("validation-error");
				lbl.getStyleClass().removeAll(("error-font"));
				lbl.setText("");
			}
			break;
		case 3:// Check the value between 0-100
			try {
				if (!textfield.getText().trim().isEmpty()) {
					if (Double.parseDouble(textfield.getText()) < 0 || Double.parseDouble(textfield.getText()) > 100) {
						textfield.getStyleClass().add(("validation-error"));
						lbl.setText("Please enter the value between 0 - 100.");
						lbl.getStyleClass().add(("error-font"));
						Toolkit.getDefaultToolkit().beep();// Alert with the
															// sound
					} else {
						textfield.getStyleClass().removeAll("validation-error");
						lbl.getStyleClass().removeAll(("error-font"));
						lbl.setText("");
					}
				}
			} catch (Exception ex) {
				alertMessage(ex.getMessage());
			}

			break;
		default:
			break;
		}

	}

	// Alert user with the message
	public void alertMessage(String alertMessage) {
		Alert alert = new Alert(AlertType.ERROR);// Display information
		// for invalid input
		alert.setTitle("Error Dialog: Invalid Input");
		// String[] message = alertMessage.split(":");
		alert.setHeaderText(alertMessage);
		alert.setContentText("Ooops, there is an error!");
		Toolkit.getDefaultToolkit().beep();
		alert.showAndWait();
	}
}
