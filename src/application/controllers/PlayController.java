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
	private String[] actions = { "", "", "" };
	private ObservableList<Node> nodes;

	public PlayController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		this.nodes = scene.getRoot().getChildrenUnmodifiable();
		addListeners();
	}

	public void addListeners() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				String key = ke.getCode().toString();
				System.out.println(ke.getEventType());
				switch (key) {
				case "W":
					actions[0] = "up";
					break;
				case "S":
					actions[0] = "down";
					break;
				case "UP":
					actions[1] = "up";
					break;
				case "DOWN":
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
					actions[0] = "";
					break;
				case "S":
					actions[0] = "";
					break;
				case "UP":
					actions[1] = "";
					break;
				case "DOWN":
					actions[1] = "";
					break;
				case "SPACE":
					actions[2] = "";
					break;
				}
			}
		});
	}

	public void runGame() {
		for (int i = 0; i < 2; i++) {
			Rectangle pad = (Rectangle) nodes.get(i);
			System.out.println("hello");
			switch (actions[i]) {
			case "":
				pad.setTranslateY(pad.getTranslateY());
				break;
			case "up":
				pad.setTranslateY(pad.getTranslateY() - padSpeed);
				break;
			case "down":
				pad.setTranslateY(pad.getTranslateY() + padSpeed);
				break;
			default:
				break;
			}
		}
	}

	public void startClock() {
		boolean playing = true;
		long lastLoopRunTime = System.nanoTime();
		long targetFPS = 60;
		long optimalTime = 1000000000 / targetFPS;
		while (playing) {
			long now = System.nanoTime();
			long nowVSthen = now - lastLoopRunTime;
			if (nowVSthen >= optimalTime) {
				lastLoopRunTime = now;
				runGame();
			}
		}
	}
}
