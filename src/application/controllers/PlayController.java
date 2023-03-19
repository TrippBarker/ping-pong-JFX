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
	private int ballXSpeed = 0;
	private int ballYSpeed = 0;
	private String[] actions = { "", "", "" };
	private ObservableList<Node> nodes;
	private int leftScore = 00;
	private int rightScore = 00;
	private int bounces = 0;

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
					if (ballXSpeed == 0) {
						double num = Math.random();
						if (num > .5) {
							num = 2;
						} else {
							num = -2;
						}
						ballXSpeed = (int)num;
						ballYSpeed = 0;
						actions[2] = "";
					}
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
		if (bounces == 3 && Math.abs(ballXSpeed) < 10) {
			if (ballXSpeed < 0) {
				ballXSpeed--;
			} else {
				ballXSpeed++;
			}
			bounces = 0;
		}
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
			int ballYSpeedChange = 0;
			if (ball.getTranslateX() + (ballXSpeed) + 20 <= 115 && ball.getTranslateX() + (ballXSpeed) + 20 >= 100) {
				Rectangle leftPad = (Rectangle) nodes.get(0);
				if (ball.getTranslateY() >= leftPad.getTranslateY() - 20 && ball.getTranslateY() <= leftPad.getTranslateY() + 175) {
					ballXSpeed = 0 + Math.abs(ballXSpeed);
					ballYSpeedChange = (int)ball.getTranslateY() - (int)leftPad.getTranslateY();
					if (ballYSpeedChange < 25) {
						ballYSpeed = -3;
					} else if (ballYSpeedChange <= 50) {
						ballYSpeed = -2;
					} else if (ballYSpeedChange <= 65) {
						ballYSpeed = -1;
					} else if (ballYSpeedChange <= 85) {
						ballYSpeed = 0;
					} else if (ballYSpeedChange <= 100) {
						ballYSpeed = 1;
					} else if (ballYSpeedChange <= 125) {
						ballYSpeed = 2;
					} else {
						ballYSpeed = 3;
					}
					bounces++;
				}
			}
			
			if (ball.getTranslateX() + (ballXSpeed) - 20 >= scene.getWidth() - 115 && ball.getTranslateX() + (ballXSpeed) - 20 <= scene.getWidth() - 100) {
				Rectangle rightPad = (Rectangle) nodes.get(1);
				if (ball.getTranslateY() >= rightPad.getTranslateY() - 20 && ball.getTranslateY() <= rightPad.getTranslateY() + 175) {
					ballXSpeed = 0 - Math.abs(ballXSpeed);
					ballYSpeedChange = (int)ball.getTranslateY() - (int)rightPad.getTranslateY();
					if (ballYSpeedChange < 25) {
						ballYSpeed = -3;
					} else if (ballYSpeedChange <= 50) {
						ballYSpeed = -2;
					} else if (ballYSpeedChange <= 65) {
						ballYSpeed = -1;
					} else if (ballYSpeedChange <= 85) {
						ballYSpeed = 0;
					} else if (ballYSpeedChange <= 100) {
						ballYSpeed = 1;
					} else if (ballYSpeedChange <= 125) {
						ballYSpeed = 2;
					} else {
						ballYSpeed = 3;
					}
					bounces++;
				}
			}
			if (ball.getTranslateX() + (ballXSpeed) + 20 >= scene.getWidth()) {
				ball.setTranslateX(scene.getWidth() / 2);
				ballXSpeed = -2;
				leftScore++;
				bounces = 0;
			} else if (ball.getTranslateX() + (ballXSpeed) - 20 <= 0){
				ball.setTranslateX(scene.getWidth() / 2);
				ballXSpeed = 2;
				rightScore++;
				bounces = 0;
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
