import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
//test
public class Main extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Vieuw.fxml"));
		primaryStage.setTitle("Simulatie van geheugen en processor bij paginatie");
		primaryStage.setScene(new Scene(root,300,250));
		primaryStage.show();
	}
}
