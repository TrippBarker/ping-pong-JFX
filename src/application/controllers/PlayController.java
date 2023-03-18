package application.controllers;

import application.scenes.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayController {
	private Scene scene;
	private Stage stage;
	private SceneSwitcher ss;
	private int padSpeed = 1;
	private String[] actions = {"", "", ""};

	
	public PlayController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		addListeners();
	}
	
	public void addListeners() {
		ObservableList nodes = scene.getRoot().getChildrenUnmodifiable();
		Rectangle leftPad = (Rectangle) nodes.get(0);
		Rectangle rightPad = (Rectangle) nodes.get(1);
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					String key = ke.getCode().toString();
					System.out.println(ke.getEventType());
					switch (key) {
					case "W":
						leftPad.setTranslateY(leftPad.getTranslateY() - padSpeed);
						actions[0] = "up";
						break;
					case "S":
						leftPad.setTranslateY(leftPad.getTranslateY() + padSpeed);
						actions[0] = "down";
						break;
					case "UP":
						rightPad.setTranslateY(rightPad.getTranslateY() - padSpeed);
						actions[1] = "up";
						break;
					case "DOWN":
						rightPad.setTranslateY(rightPad.getTranslateY() + padSpeed);
						actions[1] = "down";
						break;
					case "SPACE":
						actions[2] = "fire";
						break;
					}
				}
				
			});
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					String key = ke.getCode().toString();
					System.out.println(ke.getEventType());
					switch (key) {
					case "W":
						leftPad.setTranslateY(leftPad.getTranslateY() - padSpeed);
						actions[0] = "";
						break;
					case "S":
						leftPad.setTranslateY(leftPad.getTranslateY() + padSpeed);
						actions[0] = "";
						break;
					case "UP":
						rightPad.setTranslateY(rightPad.getTranslateY() - padSpeed);
						actions[1] = "";
						break;
					case "DOWN":
						rightPad.setTranslateY(rightPad.getTranslateY() + padSpeed);
						actions[1] = "";
						break;
					case "SPACE":
						actions[2] = "";
						break;
					}
				}
			});
		
	}
}
