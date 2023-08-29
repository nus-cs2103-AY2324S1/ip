package duke;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final String DUKE_FILEPATH = "./src/main/data/duke.txt";
    public static final String lineSeparator = "____________________________________________________________";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program by waiting for user input and responding to it.
     * If user input is "bye", the program will exit.
     */
    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }

            } catch (DukeException e) {
                System.out.println(lineSeparator + "\n" + e.getMessage() + "\n" + lineSeparator);
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke(DUKE_FILEPATH).run();
    }

    /**
     * Display ordered list of tasks.
     *
     * @param taskList list of tasks.
     */
    public static void list(ArrayList<Task> taskList) {
        // Display Ordered list
        System.out.println(Duke.lineSeparator);
        System.out.println("Here are the tasks in your list:");

        int i = 0;
        for (Task task : taskList) {
            String description = task.toString();
            System.out.printf("%d. %s\n", i + 1, description);
            i++;
        }
        System.out.println(Duke.lineSeparator);
    }


}
