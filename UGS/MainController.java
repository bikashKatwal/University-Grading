import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {
	DataAccess dataAccess = new DataAccess();
	// Main Form
	private TreeView<String> treeView;
	private RestrictiveTextField txtSearch;
	private Button btnSearch;
	private ComboBox<String> cmbSearchBy;
	boolean running = true;
	public static String tableName;

	private addController controllerAdd;
	searchController controllerSearch;

	// Reference to Main.java
	private BorderPane root;

	public void setMainApp(BorderPane rootLayout, Stage primaryStage, Scene scene) throws Exception {
		this.root = rootLayout;

		scene = new Scene(root, 800, 600);
		root.prefWidthProperty().bind(scene.widthProperty());
		root.prefHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle("Grade Processing- Java Programming 2");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Image/java.jpg")));
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			running = false;
		});
		Init(root);
		primaryStage.show();
	}

	// Creating the design of Main

	@SuppressWarnings("unchecked")
	public void Init(BorderPane root) throws Exception {
		HBox hBox = new HBox();
		Region r1 = new Region();
		HBox.setHgrow(r1, Priority.ALWAYS);

		HBox hbox1 = new HBox();
		hbox1.setAlignment(Pos.CENTER_LEFT);

		Label lbltableName = new Label("Table Name: " + TableNameStatic.getTableName());
		lbltableName.setFont(Font.font("Verdana", 20));
		hbox1.getChildren().add(lbltableName);

		FadeTransition fadeIn = new FadeTransition(Duration.INDEFINITE, lbltableName);
		fadeIn.setFromValue(1);
		fadeIn.setToValue(0);
		fadeIn.setAutoReverse(true);

		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), lbltableName);
		fadeOut.setFromValue(0);
		fadeOut.setToValue(1);
		fadeOut.setAutoReverse(true);

		// Implementation of Thread to fadeIn/Fadeout table Name
		new Thread() {

			public void run() {
				while (running) {
					fadeIn.play();
					fadeOut.play();

				}
			}
		}.start();

		HBox topHbox = new HBox();
		topHbox.setAlignment(Pos.BASELINE_RIGHT);
		topHbox.setSpacing(10);
		hBox.getStyleClass().add("TopHBox");
		Label lbl = new Label("Search By:");

		// Design for combo box
		cmbSearchBy = new ComboBox<String>();
		cmbSearchBy.setPrefWidth(150);
		cmbSearchBy.setPromptText("--[Select]--");
		cmbSearchBy.getItems().add("ID");
		txtSearch = new RestrictiveTextField();

		// Design for button
		btnSearch = new Button("Search");
		btnSearch.setPrefWidth(100);
		topHbox.getChildren().addAll(lbl, cmbSearchBy, txtSearch, btnSearch);
		hBox.getChildren().addAll(hbox1, r1, topHbox);

		root.setTop(hBox);

		// tree view design
		treeView = new TreeView<String>();
		treeView.setPrefWidth(165);
		Image addicon = new Image(getClass().getResourceAsStream("/Image/add.jpg"));
		Image searchicon = new Image(getClass().getResourceAsStream("/Image/search.jpg"));

		TreeItem<String> treeItem = new TreeItem<>("Student");
		treeItem.setExpanded(true);
		TreeItem<String> Add = new TreeItem<>("Add New Student", new ImageView(addicon));
		TreeItem<String> Search = new TreeItem<>("Search", new ImageView(searchicon));
		treeItem.getChildren().addAll(Add, Search);
		treeView.setRoot(treeItem);
		root.setLeft(treeView);

		treeView.getSelectionModel().clearAndSelect(1);
		eventHandling();

		LoadGUI(root);
	}

	// Event handle for Main form
	private void eventHandling() {

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				@SuppressWarnings("unchecked")
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				if (selectedItem.getValue() == "Add New Student") {
					controllerAdd = new addController();
					try {
						controllerAdd.addNewStudentGUI(root);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						controllerSearch = new searchController();
						controllerSearch.searchStudentGUI(root, cmbSearchBy, txtSearch);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});

		// Search the student record
		btnSearch.setOnAction(e -> {
			try {
				searchStudentDetails();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		// input search value in the textfield
		txtSearch.setOnKeyPressed(keyPressed -> {
			if (keyPressed.getCode() == KeyCode.ENTER) {
				try {
					searchStudentDetails();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				treeView.getSelectionModel().clearAndSelect(2);
			}
		});

		//Select the item to search(eg:ID)
		cmbSearchBy.setOnAction(select -> {
			if (cmbSearchBy.getSelectionModel().getSelectedItem().equals("ID")) {
				txtSearch.setPromptText("########");
				txtSearch.setMaxLength(8);// limit character
				txtSearch.setRestrict("[0-9]");
			} else {
				txtSearch = new RestrictiveTextField();
			}
		});
	}
//Search Student Details when user select ID from the drop down list
	private void searchStudentDetails() throws Exception {
		controllerSearch = new searchController();
		if (cmbSearchBy.getSelectionModel().getSelectedIndex() > -1) {
			controllerSearch.searchStudentGUI(root, cmbSearchBy, txtSearch);
			treeView.getSelectionModel().clearAndSelect(2);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Please select the Search By value", ButtonType.OK);
			alert.setTitle("Information:");
			alert.showAndWait();
		}

	}
//Load Add Controller GUI in Main GUI form
	private void LoadGUI(BorderPane root) throws Exception {
		if (treeView.getSelectionModel().getSelectedIndex() == 1) {
			controllerAdd = new addController();
			try {
				controllerAdd.addNewStudentGUI(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			controllerSearch = new searchController();
			controllerSearch.searchStudentGUI(root, cmbSearchBy, txtSearch);
		}
	}
//Create table in database
	public boolean createDatabaseTable(String tableName) throws Exception {
		return dataAccess.createTableName(tableName);
	}

}
