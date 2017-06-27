import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class searchController {
	CustomTableView customTableView = new CustomTableView();
	DataAccess dataAccess = new DataAccess();
	private TableView<MainModel> tableMain = new TableView<MainModel>();

	// Design Search Form
	public void searchStudentGUI(BorderPane root, ComboBox<String> cmb, TextField txt) throws Exception {
		tableMain.getColumns().addAll(customTableView.getColumn(tableMain));

		if (cmb.getSelectionModel().getSelectedIndex() > -1) {
			tableMain.getItems().addAll(dataAccess.searchStudent(cmb.getValue(), txt.getText()));
		}
		root.setCenter(tableMain);
	}

}
