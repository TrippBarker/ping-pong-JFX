package application.scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
	
	public void switchScene(Stage stage, Scene scene) throws IOException {
		stage.setScene(scene);
		stage.show();
	}
}
