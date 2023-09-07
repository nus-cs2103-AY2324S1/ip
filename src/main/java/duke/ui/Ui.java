package duke.ui;

import java.util.Optional;
import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Commands;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * Controls any user interaction with the user.
 * Examples: text output, error messages
 */
public class Ui {
    private static final String SEPARATOR_LINE = "____________________________________________________________";
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for an Ui handler.
     *
     * @param taskList
     */
    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }


    /**
     * Begins interaction with the user
     */
    public void beginLogging() {

        printDivider();
        String entranceMsg = "Hello! I'm Elon Musk.\n"
                + "What can I do for you?";
        System.out.println(entranceMsg);
        printDivider();

        String inputString = "";

        Scanner keyboard = new Scanner(System.in);

        loop:
        while (true) {
            try {
                inputString = keyboard.nextLine();
                printDivider();
                boolean canContinue = Parser.parse(inputString, taskList, storage);
                if (!canContinue) {
                    break;
                }
                printDivider();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Prints feedback to the user on what and how a Task got modified,
     * based on the user's command.
     *
     * @param command
     */
    public static String printResult(Commands command, Optional<Task> task, TaskList taskList) {
        Optional<String> printRes = Optional.empty();
        switch (command) {
        case TODO:
        case DEADLINE:
        case EVENT: {
            printRes = task.map(t -> {
//                System.out.println("\uD83D\uDE0A I've added a new task: " + t.toString());
//                System.out.println("Now you have " + taskList.getSize() + " tasks!");
                return "\\uD83D\\uDE0A I've added a new task: " + t.toString() + "\n" + "Now you have " + taskList.getSize() + " tasks!";


            });
            break;
        }
        case MARK: {
//            task.ifPresent(t -> System.out.println("Nice! I've marked this task as done: \n    " + t.toString()));
            printRes = task.map(t -> {
                return "Nice! I've marked this task as done: \n    " + t.toString();
            });

            break;
        }
        case UNMARK: {
//            task.ifPresent(t -> System.out.println("Nice! I've marked this task as undone: \n    " + t.toString()));
            printRes = task.map(t -> "Nice! I've marked this task as undone: \n    " + t.toString());
            break;
        }
        case DELETE: {
//            task.ifPresent(t -> System.out.println("\uD83D\uDE0A I've removed this task: " + t.toString()));
            printRes = task.map(t -> "\uD83D\uDE0A I've removed this task: " + t.toString());
            break;
        }
        case LIST: {
            System.out.println(taskList);
            printRes = Optional.of(taskList.toString());
            break;
        }
        case FIND: {
            if (taskList.getSize() == 0) {
//                System.out.println("Couldn't find any matching tasks!");
                printRes = Optional.of("Couldn't find any matching tasks!");
            } else {
                printRes = Optional.of("I found " + taskList.getSize() + " matching tasks:\n" + taskList);
//                System.out.println("I found " + taskList.getSize() + " matching tasks:");
//                System.out.println(taskList.toString());
            }
            break;
        }
        case BYE: {
//            String exitMsg = "Bye! Hope to see you again soon.";
//            System.out.println(exitMsg);
            printRes = Optional.of("Bye! Hope to see you again soon.");

            break;
        }
        default: {
            System.out.println("Unhandled enum error!");

            break;
        }
        }
        return printRes.get();
    }


    /**
     * Prints a simple divider line to the screen.
     */
    public static void printDivider() {
        System.out.println(SEPARATOR_LINE);
    }

}
