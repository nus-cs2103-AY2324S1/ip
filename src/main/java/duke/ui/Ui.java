package duke.ui;

import java.util.Optional;
import java.util.Scanner;

import duke.exceptions.UnknownCommandException;
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
                boolean isTerminateCommand = Parser.isTerminateCommand(inputString);
                if (isTerminateCommand) {
                    System.out.println("Byebye!");
                    break;
                }
                Parser.parseAndPrint(inputString, taskList, storage);

                storage.saveTasks(taskList);
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
    public static void printResult(Commands command, Task task, TaskList taskList) throws UnknownCommandException {
        System.out.println(getResponseMessage(command, task, taskList));
    }

    /**
     * Gets the text that a user should see, either in GUI or in command line.
     *
     * @param command
     * @param task
     * @param taskList
     * @return
     */
    public static String getResponseMessage(Commands command, Task task, TaskList taskList) throws UnknownCommandException {
        String result = "";
        switch (command) {
        case TODO:
        case DEADLINE:
        case EVENT: {
            result = "\uD83D\uDE0A I've added a new task: " + task + "\n" + "Now you have " + taskList.getSize() + " tasks!";
            break;
        }
        case MARK: {
            result = "Nice! I've marked this task as done: \n    " + task;

            break;
        }
        case UNMARK: {
            result = "Nice! I've marked this task as undone: \n    " + task;
            break;
        }
        case DELETE: {
            result = ("\uD83D\uDE0A I've removed this task: " + task);
            break;
        }
        case LIST: {
            result = taskList.toString();
            break;
        }
        case FIND: {
            if (taskList.getSize() == 0) {
                result = ("Couldn't find any matching tasks!");
            } else {
                result = "I found " + taskList.getSize() + " matching tasks:" + "\n" + taskList;

            }
            break;
        }
        case BYE: {
            result = "Bye! Hope to see you again soon.";


            break;
        }
        default: {
            throw new UnknownCommandException();


        }
        }

        return result;
    }

    /**
     * Prints a simple divider line to the screen.
     */
    public static void printDivider() {
        System.out.println(SEPARATOR_LINE);
    }

}
