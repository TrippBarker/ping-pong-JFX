package application;
	
import application.controllers.StartController;
import application.scenes.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	public int gridLenUnit = 25;
	public int sceneWidth = gridLenUnit * 40;
	public int sceneHeight = gridLenUnit * 30;
	
	@Override
	public void start(Stage startStage) throws Exception {
		Group root = new Group();
		StartScene startScene = new StartScene();
		Scene scene = startScene.buildScene(gridLenUnit, sceneHeight, sceneWidth, root);
		startStage.setTitle("PING PONG");
		startStage.setResizable(false);
		startStage.setScene(scene);
		startStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
