package application.controllers;

import application.scenes.SceneSwitcher;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayController {
	private Scene scene;
	private Stage stage;
	private SceneSwitcher ss;
	
	public PlayController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
	}
}
