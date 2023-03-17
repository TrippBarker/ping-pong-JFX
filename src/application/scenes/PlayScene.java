package application.scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public class PlayScene {
	public Scene buildScene(int gridLenUnit, int sceneHeight, int sceneWidth, Group root) {
		root.getChildren().add(newPaddle());
		Scene playScene = new Scene(root, sceneWidth, sceneHeight);
		return playScene;
	}
	
	private Rectangle newPaddle() {
		Rectangle paddle = new Rectangle();
		paddle.setWidth(50);
		paddle.setHeight(150);
		return paddle;
	}
}
