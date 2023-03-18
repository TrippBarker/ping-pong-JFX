package application.controllers;

import application.scenes.SceneSwitcher;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PlayController {
	private Scene scene;
	private Stage stage;
	private SceneSwitcher ss;
	
	public PlayController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		addListeners();
	}
	
	public void addListeners() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				System.out.println(ke.getCode());
			}
			
		});
	}
}
