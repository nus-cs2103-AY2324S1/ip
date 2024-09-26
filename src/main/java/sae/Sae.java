package sae;

import java.io.IOException;
import java.util.Scanner;
import sae.task.TaskList;
import sae.util.Parser;
import sae.util.Storage;
import sae.util.Ui;


/**
 * The Sae class represents an interactive task manager.
 * Users can add, list, mark, and unmark tasks using this program.
 */
public class Sae {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;


    /**
     * Constructs a new Sae instance.
     */
    public Sae() {
        TaskList temp;
        this.storage = new Storage("data/sae.txt");
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            temp = storage.loadTasks("data/sae.txt");
        } catch (IOException e) {
            temp = new TaskList();
        }
        this.tasks = temp;
    }

    /**
     * Runs the sae application.
     */
    public void run() {
        ui.greetUser();

        TaskList store = this.tasks;

        Scanner input = new Scanner(System.in);

        while (true) {
            String str = input.nextLine();

            String[] commandTask = str.split(" ", 2);

            String command = commandTask[0];

            if (command.equals("bye")) {
                ui.bidGoodbye();
                break;
            }

            try {
                parser.executeCommand(tasks, str);
                this.storage.saveTasks(tasks);
            } catch (IOException e) {
                System.out.println("An error occurred while saving tasks: " + e.getMessage());
            }
        }
        input.close();
    }

    /**
     * The main method to start the Sae application.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Sae sae = new Sae();
        sae.run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
            try {
                String ans = parser.executeCommand(tasks, userInput);
                this.storage.saveTasks(tasks);
                return ans;
            } catch (IOException e) {
                return e.getMessage();
            }
    }
}
