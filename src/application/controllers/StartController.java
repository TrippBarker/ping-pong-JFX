package application.controllers;

import java.io.IOException;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	private Scene scene;
	private Stage stage;
	
	public StartController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		addListeners();
	}
	
	public void addListeners() {
		for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
			Button button = (Button) node;
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					switch(button.getId()) {
					case "START":
						try {
							Main.ss.switchScene(stage, Main.playScene);
							Main.playSceneBuilder.playCont.runGame();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "EXIT":
						stage.close();
						Platform.exit();
						System.out.println("Buh-bye now");
						break;
					}
				}
				
			});
			
		}
	}
}
