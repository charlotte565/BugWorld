
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	static // static ArrayList<Bug> bugs = new ArrayList<Bug>();
	World bugWorld = new World();
	private static final int width = bugWorld.getWidth();
	private static final int height = bugWorld.getHeight();

	final static Group root = new Group();

	@Override
	public void start(Stage primaryStage) throws Exception {

		restartWorld();

		// Add Slider
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(200);
		slider.setValue(100);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);

		final double[] sliderValue = { 90 };

		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				sliderValue[0] = new_val.doubleValue();
				System.out.println(sliderValue[0]);
			}
		});

		// Keyframe to add to timeline
		KeyFrame frame = new KeyFrame(Duration.millis(sliderValue[0]), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				World.plantGrow(bugWorld.getPlants());
				bugWorld.updateWorld(bugWorld.getBugs());
				bugWorld.drawWorld();
			}

		});

		// vBox for buttons
		VBox vBox = new VBox();

		// Add Buttons
		Button btnPause = new Button();
		btnPause.setText("Pause");

		Button btnPlay = new Button();
		btnPlay.setText("Play");

		Button btnStop = new Button();
		btnStop.setText("Stop");

		Button btnRestart = new Button();
		btnRestart.setText("Restart");

		vBox.getChildren().add(btnPause);
		vBox.getChildren().add(btnPlay);
		vBox.getChildren().add(btnStop);
		vBox.getChildren().add(btnRestart);
		vBox.getChildren().add(slider);

		VBox vboxRoot = new VBox();

		vboxRoot.getChildren().add(root);

		// Border pane to contain elements
		BorderPane border = new BorderPane();

		border.getChildren().add(root);
		border.setRight(vBox);
		border.setBottom(slider);

		border.setStyle("-fx-background-color: #8FBC8F;");

		// create scene
		final Scene scene = new Scene(border, width * World.getImagewidth(), height * World.getImagewidth());

//		

		// create timeline
		Timeline timeline = new Timeline();
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();

		// actions for buttons
		btnPause.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				timeline.pause();
			}
		});

		btnPlay.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				timeline.play();
			}
		});

		btnStop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				timeline.stop();
			}
		});

		btnRestart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				restartWorld();
			}
		});

		// actions for slider mouse pressing
		slider.setOnMousePressed(event -> {
			// On mouse down, pause the timeline
			timeline.stop();
			timeline.getKeyFrames().clear();
		});

		slider.setOnMouseReleased(event -> {
			// Add the new keyFrame to the timeline with new speed

			timeline.getKeyFrames()
					.add(new KeyFrame(Duration.millis(201 - sliderValue[0]), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent t) {
							World.plantGrow(bugWorld.getPlants());
							bugWorld.updateWorld(bugWorld.getBugs());
							bugWorld.drawWorld();
						}

						
					}));

			timeline.playFromStart();

		});

		// primary stage set up
		primaryStage.setTitle("Hello Bug World");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	// method to generate a new bugWorld
	public void restartWorld() {
		bugWorld.addEntities();
		root.getChildren().clear();

		for (Obstacle o : bugWorld.getObs()) {
			root.getChildren().add(o.getView());
		}

		for (Plant p : bugWorld.getPlants()) {
			root.getChildren().add(p.getView());
		}

		for (Bug b : bugWorld.getBugs()) {

			root.getChildren().add(b.getView());

		}
	}

	// Method to add a new plant to the root group
	public static void addPlantRoot(Plant plant) {
		bugWorld.addPlants(plant);
		root.getChildren().add(plant.getView());
	}

	public static void removePlantRoot(Plant plant) {

		root.getChildren().remove(plant.getView());
	}

	public static void removeBugRoot(Bug bug) {

		root.getChildren().remove(bug.getView());
	}

}
