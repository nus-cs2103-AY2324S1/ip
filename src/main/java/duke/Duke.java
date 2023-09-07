package duke;

import java.io.IOException;
import java.io.File;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class for the ChatBot
 */
public class Duke {
    public static final String TEXTFILE = "duke.txt";
    public static final String DATAFILE = "data.txt";
    public static final String DELIM = " ";
    public static final String GREETING = "-------------------------------\n"
            + "Hello! I'm Skog.\n"
            + "What can I do for you?\n"
            + "-------------------------------\n";
    public static final String EXIT = "-------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-------------------------------\n";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * @param filePath Specifies the name of the text file
     */
    public Duke(String filePath) {
        this.storage = new Storage(TEXTFILE, DATAFILE);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public Duke(){}
    /**
     * Method to initiate the chatbot.
     */
    public void run() {
        try {
            File myFile = new File(TEXTFILE);
            if (myFile.createNewFile()) {
                System.out.println("-------------------------------\n"
                        + "Welcome! New text file created.");
            } else {
                // go straight into task control
            }
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }

        System.out.println(GREETING);

        while (ui.sc.hasNext()) {
            try {
                String[] arr = ui.sc.nextLine().split(DELIM);
                String type = arr[0];

                if (type.equals("bye")) {
                    ui.end();
                    storage.saveTextFile(tasks);
                    storage.saveDataFile(tasks);
                    System.out.println(EXIT);
                    break;
                } else if (type.equals("list")) {
                    tasks.listOut();
                } else if (type.equals("mark")) {
                    tasks.mark(arr);
                } else if (type.equals("unmark")) {
                    tasks.unmark(arr);
                } else if (type.equals("delete")) {
                    tasks.delete(arr);
                } else if (type.equals("find")) {
                    tasks.find(arr);
                } else {
                    // check for task type first
                    if (type.equals("todo")) {
                        if (arr.length == 1) {
                            throw new EmptyDescription();
                        } else {
                            String desc = tasks.getDescription(arr);
                            tasks.addTodo(desc);
                        }
                    } else if (type.equals("deadline")) {
                        String desc = tasks.getDescription(arr);
                        String date = tasks.getDeadline(arr);
                        tasks.addDeadline(desc, date);
                    } else if (type.equals("event")) {
                        String desc = tasks.getDescription(arr);
                        String timeline = tasks.getEventTimeline(arr);
                        tasks.addEvent(desc, timeline);
                    } else {
                        throw new WrongInput();
                    }
                }
            } catch (EmptyDescription | WrongInput e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Echos the user's input.
     * @param input String that user inpt into the chat bot
     * @return An echo of what the user said
     */
    public String getResponse(String input) {
        return "Skog heard: " + input;
    }

    public static void main(String[] args) {
        new Duke(TEXTFILE).run();
    }
}
