package application.controllers;

import java.io.File;

import application.scenes.SceneSwitcher;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayController {
	private Scene scene;
	private Stage stage;
	private SceneSwitcher ss;
	private double padSpeed = 10;
	private double ballXSpeed = 0;
	private double ballYSpeed = 0;
	private String[] actions = { "", "", "" };
	private ObservableList<Node> nodes;
	private int leftScore = 0;
	private int rightScore = 0;
	private int bounces = 0;
	private Media leftSound = new Media(new File("src/application/sounds/leftSound.mp3").toURI().toString());
	private Media rightSound = new Media(new File("src/application/sounds/rightSound.mp3").toURI().toString());
	private Media wallSound = new Media(new File("src/application/sounds/wallSound.mp3").toURI().toString());
	private Media zzzSound = new Media(new File("src/application/sounds/zzzSound.mp3").toURI().toString());
	private MediaPlayer player;
	private Rectangle gameOverScreen;
	private Button againButton;
	private Button endButton;
	private Label gameOverMessage;
	private Label spaceToStartMessage;
	private double startMessageOp = 0.5;
	private boolean decreasing = false;
	private boolean playing = true;

	public PlayController(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		this.nodes = scene.getRoot().getChildrenUnmodifiable();
		spaceToStartMessage = (Label)nodes.get(5);
		spaceToStartMessage.setText("SPACE TO\n  PLAY");
		spaceToStartMessage.setTextFill(Color.WHITE);
		gameOverScreen = (Rectangle)nodes.get(6);
		againButton = (Button)nodes.get(7);
		endButton = (Button)nodes.get(8);
		gameOverMessage = (Label)nodes.get(9);
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
					if (ballXSpeed == 0 && playing) {
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
		againButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				gameOverScreen.setVisible(false);
				againButton.setVisible(false);
				endButton.setVisible(false);
				gameOverMessage.setVisible(false);
				leftScore = 0;
				rightScore = 0;
				playing = true;
			}
			
		});
		endButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				stage.close();
				Platform.exit();
				System.out.println("later gator");
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
		if(ballXSpeed == 0 && playing) {
			spaceToStartMessage.setVisible(true);
			spaceToStartMessage.setOpacity(startMessageOp);
			System.out.println(startMessageOp);
			if (startMessageOp >= .99 || startMessageOp > .25 && decreasing) {
				System.out.println("1");
				if (startMessageOp >= .99 && !decreasing) {
					decreasing = true;
				}
				startMessageOp -= .01;
			} else {
				System.out.println("2");
				decreasing = false;
				startMessageOp += .01;
			}
			
			
		} else {
			spaceToStartMessage.setVisible(false);
		}
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
			if (ball.getTranslateX() + (ballXSpeed) + 20 <= 115 && ball.getTranslateX() + (ballXSpeed) + 20 >= 50) {
				Rectangle leftPad = (Rectangle) nodes.get(0);
				if (ball.getTranslateY() >= leftPad.getTranslateY() - 20 && ball.getTranslateY() <= leftPad.getTranslateY() + 175) {
					playSound(leftSound);
					updateBallTrajectory(ball.getTranslateY() - leftPad.getTranslateY());
				}
			}
			
			if (ball.getTranslateX() + (ballXSpeed) - 20 >= scene.getWidth() - 115 && ball.getTranslateX() + (ballXSpeed) - 20 <= scene.getWidth() - 50) {
				Rectangle rightPad = (Rectangle) nodes.get(1);
				if (ball.getTranslateY() >= rightPad.getTranslateY() - 20 && ball.getTranslateY() <= rightPad.getTranslateY() + 175) {
					playSound(rightSound);
					updateBallTrajectory(ball.getTranslateY() - rightPad.getTranslateY());
				}
			}
			if (ball.getTranslateX() + (ballXSpeed) + 20 >= scene.getWidth()) {
				ball.setTranslateX(scene.getWidth() / 2);
				ballXSpeed = 0 - (Math.abs(ballXSpeed) - 2);
				if (ballXSpeed > -2 ) {
					ballXSpeed = -2;
				}
				playSound(zzzSound);
				leftScore++;
				if (leftScore == 10) {
					gameOver("BLUE");
				}
				bounces = 0;
			} else if (ball.getTranslateX() + (ballXSpeed) - 20 <= 0){
				ball.setTranslateX(scene.getWidth() / 2);
				ballXSpeed = 0 + (Math.abs(ballXSpeed) - 2);
				if (ballXSpeed < 2) {
					ballXSpeed = 2;
				}
				playSound(zzzSound);
				rightScore++;
				if (rightScore == 10) {
					gameOver("RED");
				}
				bounces = 0;
			} else {
				ball.setTranslateX(ball.getTranslateX() + (ballXSpeed));
			}
			
			if (ball.getTranslateY() + (ballYSpeed) - 20 <= 0) {
				ballYSpeed = 0 + Math.abs(ballYSpeed);
				playSound(wallSound);
			} else if (ball.getTranslateY() + (ballYSpeed) + 20 >= scene.getHeight() ) {
				ballYSpeed = 0 - Math.abs(ballYSpeed);
				playSound(wallSound);
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
	
	public void updateBallTrajectory(double padSect) {
		ballXSpeed *= -1;
		if (padSect < 25) {
			ballYSpeed = -3;
		} else if (padSect <= 50) {
			ballYSpeed = -2;
		} else if (padSect <= 65) {
			ballYSpeed = -1;
		} else if (padSect <= 85) {
			ballYSpeed = 0;
		} else if (padSect <= 100) {
			ballYSpeed = 1;
		} else if (padSect <= 125) {
			ballYSpeed = 2;
		} else {
			ballYSpeed = 3;
		}
		bounces++;
	}
	
	public void playSound(Media sound) {
		player = new MediaPlayer(sound);
		player.play();
	}
	
	public void gameOver(String winner) {
		ballXSpeed = 0;
		ballYSpeed = 0;
		gameOverScreen.setVisible(true);
		againButton.setVisible(true);
		endButton.setVisible(true);
		gameOverMessage.setVisible(true);
		gameOverMessage.setText(winner + " WINS");
		playing = false;
	}
}
