package Duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Ui class represents the user interface for the Duke program.
 * It handles user input and displays messages to the user.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }
    private Parser parser = new Parser();

    /**
     * Gets user input from the command line and processes it using a Parser object.
     * @param tasks the TaskList object to store and manage tasks
     * @param storage the Storage object to save and load tasks
     */
    public void getInput(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            try {
                parser.parse(s, tasks);
                s = sc.nextLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                s = sc.nextLine();
            }
        }
        if (s.equals("bye")) {
            try {
                storage.saveTasks(tasks.taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Bye! Auntie maggie see you later!");
        }
    }
}


