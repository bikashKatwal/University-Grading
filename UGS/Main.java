import javafx.animation.FadeTransition;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Main extends Application {

	private Label lbl1;
	private Label lbl2;
	private TextField txtTableName;
	private Button btnadd;

	private Stage primaryStage;
	private Scene scene;
	private MainController mainController;
	private addController controllerAdd;

	BorderPane rootLayout = new BorderPane();
	AnchorPane anchorPane = new AnchorPane();

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.scene = new Scene(anchorPane, 400, 200);
			this.primaryStage.setTitle("Grade Processing- Java Programming 2");
			this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Image/java.jpg")));
			systemConfigurationGUI(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// System Configuration Design a form to enter table name and save it in database
	public void systemConfigurationGUI(AnchorPane anchorPane1) {
		ImageView img = new ImageView("/Image/infinite.jpg");

		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), img);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.play();

		GridPane grid = new GridPane();
		grid.setHgap(10);
		lbl1 = new Label("Table Name:");
		lbl1.setTextFill(Color.WHITE);
		lbl2 = new Label("Saved Sucessfully!!");
		lbl2.setVisible(false);
		txtTableName = new TextField();
		btnadd = new Button("Add");
		grid.add(lbl1, 0, 0);
		grid.add(txtTableName, 1, 0);
		grid.add(btnadd, 2, 0);
		grid.add(lbl2, 1, 1);
		grid.setPadding(new Insets(50, 0, 0, 50));
		FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), grid);
		fadeTransition2.setFromValue(0);
		fadeTransition2.setToValue(1);
		fadeTransition2.play();

		anchorPane1.getChildren().addAll(img, grid);

		// Creates table
		btnadd.setOnAction(e -> {
			createTableName();
		});
		
		// Pressed enter
		txtTableName.setOnKeyPressed(keypressed -> {
			if (keypressed.getCode() == KeyCode.ENTER) {
				createTableName();
			}
		});

	}
//Display Table Name on the Top Left of the design
	public void createTableName() {
		try {
			if (!txtTableName.getText().isEmpty()) {
				mainController = new MainController();
				String tableName = txtTableName.getText();
				if (mainController.createDatabaseTable(tableName)) {
					primaryStage.close();
					TableNameStatic.setTableName(tableName);
					mainController.setMainApp(rootLayout, primaryStage, scene);
				}
			}
		} catch (Exception e1) {
			controllerAdd.alertMessage(e1.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
