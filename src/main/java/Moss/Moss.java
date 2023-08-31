package moss;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Moss.Moss is a simple task management application that allows users to add, list, mark, unmark, and delete tasks.
 */
public class Moss {
    static ArrayList<Task> things = new ArrayList<>();
    private UI ui;
    /**
     * The main method that initializes the application and handles user input.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) throws MossException {
        Storage storage = new Storage();

        try {
            things = (ArrayList<Task>) storage.loadTasks();
        } catch (Exception e) {
            throw new MossException("Failed to load tasks");
        }

        UI ui = new UI();
        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        // Process user input until "bye" is entered
        while (!message.equals("bye")) {
            TaskList taskList= new TaskList();
            Parser parser = new Parser(message, things, storage);
            parser.execute(message, things, storage);
            message = sc.nextLine();
        }
        // Farewell message
        ui.bye();
    }

}
