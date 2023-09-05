package dot;

import java.util.function.Consumer;

import dot.controllers.MainWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Main class which serves as the entry point for the app.
 * It handles switching between exit page and MainWindow scene.
 */
public class Main extends Application {

    private final Dot dot = new Dot(100);
    private Scene mainWindowScene;

    /**
     * Handles re-entering an app, with a nice loading bar.
     */
    private final Consumer<Stage> handleReEnterApp = (Stage stage) -> {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.0);

        StackPane loadingScenePane = new StackPane();
        loadingScenePane.getChildren().add(progressBar);
        loadingScenePane.setPrefHeight(600.0);
        loadingScenePane.setPrefWidth(400.0);

        Scene loadingScene = new Scene(loadingScenePane);
        stage.setScene(loadingScene);
        stage.show();

        KeyFrame[] progressBarAnimationFrames = new KeyFrame[6];
        for (int i = 0; i < 6; i++) {
            double progress = 0.2 * i;
            progressBarAnimationFrames[i] = new KeyFrame(
                    Duration.seconds(i * 0.1),
                    event -> progressBar.setProgress(progress)
            );
        }

        Timeline timeline = new Timeline(progressBarAnimationFrames);
        timeline.setOnFinished(event -> {
            stage.setScene(this.mainWindowScene);
            stage.show();
        });
        timeline.play();
    };

    /**
     * Handles exiting the app, directing to a landing exit page.
     */
    private final Consumer<Stage> handleExitApp = (Stage stage) -> {
        Label exitPageLabel = new Label("You have exited the app.");
        exitPageLabel.setFont(new Font("Arial", 20));
        Button reEnterButton = new Button("Launch Dot");
        reEnterButton.setOnMouseClicked((event) -> {
            handleReEnterApp.accept(stage);
        });
        VBox exitPageContainer = new VBox();
        exitPageContainer.setPrefHeight(600.0);
        exitPageContainer.setPrefWidth(400.0);
        exitPageContainer.setAlignment(Pos.CENTER);
        exitPageContainer.getChildren().addAll(exitPageLabel, reEnterButton);
        exitPageContainer.setSpacing(5.0);
        Scene exitPageScene = new Scene(exitPageContainer);
        stage.setScene(exitPageScene);
        stage.show();
    };

    /**
     * {@inheritDoc}
     * <p>
     * The overriding start method for Dot class. the entry point.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        stage.setResizable(false);

        MainWindow mainWindow = new MainWindow(this.dot, () -> {
            handleExitApp.accept(stage);
        });
        // Adapted from https://stackoverflow.com/questions/47914126/javafx-scrollpane-cant-change-background
        // ScrollPane doesn't have structure till stage is instantiated
        stage.setOnShown(event -> {
            mainWindow.lookup(".viewport").getStyleClass().add("transparent-background");
        });

        Scene scene = new Scene(mainWindow);
        this.mainWindowScene = scene;

        try {
            scene.getStylesheets().add(
                    this.getClass().getResource("/stylesheets/MainWindowStyle.css").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Error reading stylesheet.");
        }

        stage.setTitle("Dot");
        stage.setScene(scene);
        stage.show();
    }
}

