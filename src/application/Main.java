package application;
	
import application.controllers.*;
import application.scenes.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Main extends Application {
	
	public int gridLenUnit = 25;
	public int sceneWidth = gridLenUnit * 40;
	public int sceneHeight = gridLenUnit * 30;
	
	@Override
	public void start(Stage startStage) throws Exception {
		Group root = new Group();
		SceneSwitcher ss = new SceneSwitcher();
		StartScene startScene = new StartScene();
		Scene scene = startScene.buildScene(gridLenUnit, sceneHeight, sceneWidth, root);
		StartController startCont = new StartController(scene, startStage);
		startStage.setTitle("PING PONG");
		startStage.setResizable(false);
		ss.switchScene(startStage, scene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
