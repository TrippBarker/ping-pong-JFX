package application.scenes;

import application.Main;
import application.controllers.StartController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class StartScene {
	
	public Scene buildScene(int gridLenUnit, int sceneHeight, int sceneWidth, Stage primaryStage) {
		Group root = new Group();
		root.getChildren().add(createButton("START", sceneWidth / 2, sceneHeight / 2));
		root.getChildren().add(createButton("EXIT", sceneWidth / 2, (sceneHeight / 4) * 3));
		Scene startScene = new Scene(root, sceneWidth, sceneHeight);
		StartController startCont = new StartController(startScene, primaryStage);
		return startScene;
	}
	
	private Button createButton(String text, int x, int y) {
		Button button = new Button(text);
		button.setId(text);
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		button.setTranslateX(x - 50);
		button.setTranslateY(y - 50);
		return button;
	}
}
