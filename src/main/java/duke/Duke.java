package duke;

import java.util.Scanner;

/**
 * Contains the chatbot Brobot. It allows users to add and delete different types of tasks and mark them
 * as complete or incomplete
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts running the chatbot on the terminal.
     */
    public void run() {
        ui.printGreetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // keep prompting user until user enters "bye"
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            Command command = Parser.decideCommand(input);

            try {
                switch (command) {
                case BYE:
                    // exit program
                    ui.printExitMessage();
                    return;
                case LIST:
                    tasks.printList();
                    break;
                case MARK:
                    tasks.markTask(words[1]);
                    break;
                case UNMARK:
                    tasks.unmarkTask(words[1]);
                    break;
                case DELETE:
                    tasks.deleteTask(words[1]);
                    break;
                case TODO:
                    tasks.addTodo(input);
                    break;
                case EVENT:
                    tasks.addEvent(input);
                    break;
                case DEADLINE:
                    tasks.addDeadline(input);
                    break;
                case FIND:
                    tasks.find(input);
                    break;
                default:
                    // invalid input
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                storage.writeToFile();

            } catch (DukeException e) {
                ui.printExceptionMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
