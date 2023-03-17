package application.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class StartScene {
	
	public Scene buildScene(int gridLenUnit, int sceneWidth, int sceneHeight, BorderPane root) {
		Scene startScene = new Scene(root, sceneWidth, sceneHeight);
		return startScene;
	}
}
