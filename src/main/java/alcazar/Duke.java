package alcazar;
/**
 * The class with the main method where all the functionality begins.
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Method to run all the functionality to drive this project.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Parser p = new Parser();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = p.isExit(fullCommand);
                if (isExit) {
                    continue;
                }
                ui.showLine(); // show the divider line ("_______")
                p.parse(fullCommand, tasks, ui, storage);
            } catch (InvalidTaskException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch (InvalidArgumentException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch(InvalidSerialException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMsg();
    }

    /**
     * Main Method
     * @param args The standard args argument to main which
     *             provides more readability to the user
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke("./src/main/java/data/tasks.txt");
        chatBot.run();
    }
}
