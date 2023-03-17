package application.scenes;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class StartScene {
	
	public Scene buildScene(int gridLenUnit, int sceneHeight, int sceneWidth, Group root) {
		root.getChildren().add(createButton("START", sceneWidth / 2, sceneHeight / 2));
		root.getChildren().add(createButton("EXIT", sceneWidth / 2, (sceneHeight / 4) * 3));
		Scene startScene = new Scene(root, sceneWidth, sceneHeight);
		return startScene;
	}
	
	private Button createButton(String text, int x, int y) {
		Button button = new Button(text);
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		button.setTranslateX(x - 50);
		button.setTranslateY(y - 50);
		return button;
	}
}
