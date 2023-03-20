package application.scenes;

import application.controllers.PlayController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PlayScene {
	
	public PlayController playCont;
	
	
	public Scene buildScene(int gridLenUnit, int sceneHeight, int sceneWidth, Stage primaryStage) {
		Group root = new Group();
		root.getChildren().add(newPaddle("LEFT", 50, sceneHeight / 2, Color.DEEPSKYBLUE));
		root.getChildren().add(newPaddle("RIGHT", sceneWidth - 75, sceneHeight / 2, Color.HOTPINK));
		root.getChildren().add(newBall("BALL", sceneWidth / 2, sceneHeight / 2));
		root.getChildren().add(newScore("LEFTSCORE", 150, 0, Color.BLUE));
		root.getChildren().add(newScore("RIGHTSCORE", sceneWidth - 300, 0, Color.RED));
		root.getChildren().add(gameOver("GAMEOVER", 25, 100, Color.SLATEGREY, sceneWidth, sceneHeight));
		root.getChildren().add(newButton("AGAIN", (sceneWidth / 2) - 150, sceneHeight / 2));
		root.getChildren().add(newButton("END", (sceneWidth / 2) + 50, sceneHeight / 2));
		root.getChildren().add(newLabel("GAMEOVERLABEL", (sceneWidth / 2) - 250, 150));
		Scene playScene = new Scene(root, sceneWidth, sceneHeight);
		playScene.setFill(Color.DARKSLATEGRAY);
		playCont = new PlayController(playScene, primaryStage);
		return playScene;
	}
	
	private Rectangle newPaddle(String id, int x, int y, Color color) {
		Rectangle paddle = new Rectangle();
		paddle.setFill(color);
		paddle.setWidth(25);
		paddle.setHeight(150);
		paddle.setId(id);
		paddle.setTranslateX(x);
		paddle.setTranslateY(y - (paddle.getHeight()/2));
		return paddle;
	}
	
	private Circle newBall(String id, int x, int y) {
		Circle ball = new Circle();
		ball.setRadius(20);
		ball.setId(id);
		ball.setTranslateX(x);
		ball.setTranslateY(y);
		ball.setFill(Color.RED);
		return ball;
	}
	
	private Label newScore(String id, int x, int y, Color color) {
		Label score = new Label("00");
		score.setId(id);
		score.setPrefWidth(150);
		score.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
		score.setFont(Font.font("courier new", FontWeight.BOLD, 100));
		score.setTextFill(color);
		score.setAlignment(Pos.CENTER);
		score.setTranslateX(x);
		score.setTranslateY(y);
		score.setOpacity(.5);
		return score;
	}
	
	private Rectangle gameOver(String id, int x, int y, Color color, int sceneWidth, int sceneHeight) {
		Rectangle over = new Rectangle();
		over.setId(id);
		over.setWidth(sceneWidth - (x*2));
		over.setHeight(sceneHeight - y - 25);
		over.setFill(color);
		over.setTranslateX(x);
		over.setTranslateY(y);
		over.setVisible(false);
		return over;
	}
	
	private Button newButton(String id, int x, int y) {
		Button button = new Button();
		button.setId(id);
		button.setText(id);
		button.setTranslateX(x);
		button.setTranslateY(y);
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		button.setVisible(false);
		return button;
	}
	
	private Label newLabel(String id, int x, int y) {
		Label label = new Label();
		label.setFont(Font.font("courier new", FontWeight.BOLD, 74));
		label.setAlignment(Pos.CENTER);
		label.setId(id);
		label.setPrefWidth(500);
		label.setPrefHeight(150);
		label.setTranslateX(x);
		label.setTranslateY(y);
		label.setVisible(false);
		return label;
	}
}
