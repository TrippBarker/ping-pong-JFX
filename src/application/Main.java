package application;
	
import application.scenes.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static int gridLenUnit = 25;
	public static int sceneWidth = gridLenUnit * 40;
	public static int sceneHeight = gridLenUnit * 30;
	public static SceneSwitcher ss = new SceneSwitcher();
	public static StartScene startSceneBuilder = new StartScene();
	public static Scene startScene;
	public static PlayScene playSceneBuilder = new PlayScene();
	public static Scene playScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		startScene = startSceneBuilder.buildScene(gridLenUnit, sceneHeight, sceneWidth, primaryStage);
		playScene = playSceneBuilder.buildScene(gridLenUnit, sceneHeight, sceneWidth, primaryStage);
		primaryStage.setTitle("PING PONG");
		primaryStage.setResizable(false);
		ss.switchScene(primaryStage, startScene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
