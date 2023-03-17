package application.scenes;

import application.controllers.PlayController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayScene {
	public Scene buildScene(int gridLenUnit, int sceneHeight, int sceneWidth, Stage primaryStage) {
		Group root = new Group();
		root.getChildren().add(newPaddle());
		Scene playScene = new Scene(root, sceneWidth, sceneHeight);
		PlayController playCont = new PlayController(playScene, primaryStage);
		return playScene;
	}
	
	private Rectangle newPaddle() {
		Rectangle paddle = new Rectangle();
		paddle.setWidth(50);
		paddle.setHeight(150);
		return paddle;
	}
}
