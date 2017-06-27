import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomTableView extends TableView<MainModel> {
		
	// Used to design Tableview with the columns
	public ArrayList<TableColumn<MainModel, ?>> getColumn(TableView<MainModel> table) {
		table.setStyle("-fx-selection-bar: #336699; -fx-selection-bar-non-focused: #336699;"); // set
		table.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
		table.setTableMenuButtonVisible(true);
		
		int i;
		ArrayList<TableColumn<MainModel, ?>> columns = new ArrayList<TableColumn<MainModel, ?>>();

		String[] columnNames = { "Name", "ID", "Quize Mark", "Assignment 1", "Assignment 2","Assignment 3","Exam Mark", "Results", "Grade" };// Header
		String[] variableNames = { "Name", "ID", "QMarks", "A1", "A2","A3","ExamMarks", "Result", "Grade" };// Variables

		i = 0;
		TableColumn<MainModel, String> nameCol = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Integer> idCol = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Double> qmarksCol = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Double> a1Col = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Double> a2Col = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Double> a3Col = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Double> exammarksCol = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, Double> resultCol = new TableColumn<>(columnNames[i++]);
		TableColumn<MainModel, String> gradeCol = new TableColumn<>(columnNames[i++]);
		
		
		i = 0;
		nameCol.setCellValueFactory(new PropertyValueFactory<MainModel, String>(variableNames[i++]));
		idCol.setCellValueFactory(new PropertyValueFactory<MainModel, Integer>(variableNames[i++]));
		qmarksCol.setCellValueFactory(new PropertyValueFactory<MainModel, Double>(variableNames[i++]));
		a1Col.setCellValueFactory(new PropertyValueFactory<MainModel, Double>(variableNames[i++]));
		a2Col.setCellValueFactory(new PropertyValueFactory<MainModel, Double>(variableNames[i++]));
		a3Col.setCellValueFactory(new PropertyValueFactory<MainModel, Double>(variableNames[i++]));
		exammarksCol.setCellValueFactory(new PropertyValueFactory<MainModel, Double>(variableNames[i++]));
		resultCol.setCellValueFactory(new PropertyValueFactory<MainModel, Double>(variableNames[i++]));
		gradeCol.setCellValueFactory(new PropertyValueFactory<MainModel, String>(variableNames[i++]));

		
		
		columns.add(nameCol);
		columns.add(idCol);
		columns.add(qmarksCol);
		columns.add(a1Col);
		columns.add(a2Col);
		columns.add(a3Col);
		columns.add(exammarksCol);
		columns.add(resultCol);
		columns.add(gradeCol);

		return columns;
	}

}
