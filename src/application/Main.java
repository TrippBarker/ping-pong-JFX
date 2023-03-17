package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage startStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene startScene = new Scene(root,1000,750);
		startStage.setTitle("PING PONG");
		startStage.setResizable(false);
		startStage.setScene(startScene);
		startStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
