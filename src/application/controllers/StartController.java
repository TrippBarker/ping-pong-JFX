package application.controllers;

import java.io.IOException;

import application.Main;
import application.scenes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	private Scene scene;
	private Stage stage;
	private SceneSwitcher ss;
	
	public StartController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		addListeners();
	}
	
	public void addListeners() {
		ObservableList<Node> nodes = scene.getRoot().getChildrenUnmodifiable();
		Button startButton = (Button) nodes.get(0);
	}
}
