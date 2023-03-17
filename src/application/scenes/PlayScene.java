package application.scenes;

import application.controllers.PlayController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PlayScene {
	public Scene buildScene(int gridLenUnit, int sceneHeight, int sceneWidth, Stage primaryStage) {
		Group root = new Group();
		root.getChildren().add(newPaddle("LEFT", 50, sceneHeight / 2));
		root.getChildren().add(newPaddle("RIGHT", sceneWidth - 75, sceneHeight / 2));
		root.getChildren().add(newScore("LEFTSCORE", 150, 0, Color.BLUE));
		root.getChildren().add(newScore("RIGHTSCORE", sceneWidth - 300, 0, Color.RED));
		Scene playScene = new Scene(root, sceneWidth, sceneHeight);
		PlayController playCont = new PlayController(playScene, primaryStage);
		return playScene;
	}
	
	private Rectangle newPaddle(String id, int x, int y) {
		Rectangle paddle = new Rectangle();
		paddle.setWidth(25);
		paddle.setHeight(150);
		paddle.setId(id);
		paddle.setTranslateX(x);
		paddle.setTranslateY(y - (paddle.getHeight()/2));
		return paddle;
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
		return score;
	};
	
}
