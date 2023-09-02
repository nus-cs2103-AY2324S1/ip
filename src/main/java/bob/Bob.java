package bob;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import bob.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for Bob
 */
public class Bob extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Bob constructor, initialise storage, tasks and ui
     */
    public Bob() {
        storage = new Storage("./bob.txt");
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String nextLine = sc.nextLine();
                if (nextLine.isEmpty()) {
                    throw new NoSuchElementException();
                }

                Command c = Parser.parse(nextLine);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (NoSuchElementException e) {
                ui.stringFormat(new String[]{"Write something!"});
            }
        }
    }
//
//    public static void main(String[] args) {
//        new Bob("./bob.txt").run();
//    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}

