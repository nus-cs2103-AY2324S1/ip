package sae;

import sae.task.TaskList;
import sae.util.Parser;
import sae.util.Storage;
import sae.util.Ui;
import sae.exceptions.SaeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Sae class represents an interactive sae.task manager.
 * Users can add, list, mark, and unmark tasks using this program.
 */
public class Sae {

    private final Storage storage;
    private final TaskList store;
    private final Ui ui;
    private final Parser parser;



    public Sae(String filePath) {
        TaskList temp;
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            temp = storage.loadTasks(filePath);
        } catch (IOException e) {
            temp = new TaskList();
        }
        this.store = temp;
    }

    public void run() {
        ui.greetUser();

        TaskList store = this.store;

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
                parser.executeCommand(store, commandTask);
                this.storage.saveTasks(store);
            } catch (SaeException e) {
                System.out.println("☹ " + e.getMessage());
            } catch (IOException e) {
                System.out.println("An error occurred while saving tasks: " + e.getMessage());
            }
        }
        input.close();
    }

    public static void main(String[] args) {
        Sae sae = new Sae("./data/sae.txt");
        sae.run();
    }

}