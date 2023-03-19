package application.controllers;

import application.scenes.SceneSwitcher;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayController {
	private Scene scene;
	private Stage stage;
	private SceneSwitcher ss;
	private int padSpeed = 10;
	private int ballXSpeed = -1;
	private int ballYSpeed = 1;
	private String[] actions = { "", "", "" };
	private ObservableList<Node> nodes;
	private int leftScore = 00;
	private int rightScore = 00;

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
		AnimationTimer timer = (new AnimationTimer() {
			@Override
			public void handle(long now) {
				runGame();
			}
		});
		timer.start();
	}

	public void runGame() {
		for (int i = 0; i < 2; i++) {
			Rectangle pad = (Rectangle) nodes.get(i);
			switch (actions[i]) {
			case "":
				pad.setTranslateY(pad.getTranslateY());
				break;
			case "up":
				if (!(pad.getTranslateY() <= 0)) {
					pad.setTranslateY(pad.getTranslateY() - padSpeed);
				}
				break;
			case "down":
				if (!(pad.getTranslateY() + pad.getHeight() >= scene.getHeight())) {
					pad.setTranslateY(pad.getTranslateY() + padSpeed);
				}
				break;
			default:
				break;
			}
			Circle ball = (Circle) nodes.get(2);
			if (ball.getTranslateX() + (ballXSpeed) + 20 >= scene.getWidth()) {
				ball.setTranslateX(scene.getWidth() / 2);
				leftScore++;
			} else if (ball.getTranslateX() + (ballXSpeed) - 20 <= 0){
				ball.setTranslateX(scene.getWidth() / 2);
				rightScore++;
			} else {
				ball.setTranslateX(ball.getTranslateX() + (ballXSpeed));
			}
			
			if (ball.getTranslateY() + (ballYSpeed) - 20 <= 0) {
				ballYSpeed = 0 + Math.abs(ballYSpeed);
			} else if (ball.getTranslateY() + (ballYSpeed) + 20 >= scene.getHeight() ) {
				ballYSpeed = 0 - Math.abs(ballYSpeed);
			}
			ball.setTranslateY(ball.getTranslateY() + (ballYSpeed));
		}
		updateScores();
	}
	
	public void updateScores() {
		Label leftLabel = (Label)nodes.get(3);
		Label rightLabel = (Label) nodes.get(4);
		leftLabel.setText(String.valueOf(leftScore));
		rightLabel.setText(String.valueOf(rightScore));
	}
}
