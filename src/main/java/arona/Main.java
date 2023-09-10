    package arona;

    import java.io.IOException;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.scene.image.Image;
    import javafx.scene.layout.AnchorPane;
    import javafx.stage.Stage;

    /**
     * A GUI for Arona using FXML.
     */
    public class Main extends Application {

        private Arona arona = new Arona("./src/main/java/arona/data/arona.txt");


        /**
         * The entry point for the JavaFX application.
         *
         * @param stage The primary stage for the JavaFX application.
         */
        @Override
        public void start(Stage stage) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
                AnchorPane ap = fxmlLoader.load();

                Image appIcon = new Image("/images/arona.png");
                stage.getIcons().add(appIcon);

                stage.setTitle("Arona - Your Virtual Assistant");

                Scene scene = new Scene(ap);
                stage.setScene(scene);
                fxmlLoader.<MainWindow>getController().setArona(arona);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
